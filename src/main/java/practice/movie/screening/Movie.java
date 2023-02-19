package practice.movie.screening;

import practice.movie.discount.DiscountPolicy;

import java.time.Duration;

public class Movie {
    private String title; // 제목
    private Duration runningTime; // 상영시간
    private Money fee; // 기본요금
    private DiscountPolicy discountPolicy; // 할인정책 -> Movie 에서 DiscountPolicy 인스턴스 사용 (합성)

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee(){
        return fee;
    }

    public void changeDiscountPolicy(DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
    }

    // discountPolicy: 코드의 의존성과 실행 시점의 의존성이 달라짐(polymorphism). 자식 클래스가 부모 클래스를 대신함(upcasting)
    public Money calculateMovieFee(Screening screening){
        return fee.minus(discountPolicy.calculateDiscountAmount(screening)); // 1인당 예매 요금
    }
}

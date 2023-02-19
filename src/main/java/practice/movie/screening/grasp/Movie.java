package practice.movie.screening.grasp;

import practice.movie.discount.grasp.DiscountCondition;
import practice.movie.screening.Money;

import java.time.Duration;
import java.util.List;

public abstract class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    public Movie(String title, Duration runningTime, Money fee, List<DiscountCondition> discountConditions) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountConditions = discountConditions;
    }

    protected Money getFee(){
        return fee;
    }

    /* GRASP 패턴 구현체(2)
        Movie 는 Screening 의 메세지에 응답하기 위해 calculateMovieFee 를 구현해야 한다.
        요금을 계산하기 위해 Movie 는 기본 요금, 할인 조건, 할인 정책 등의 정보를 알아야한다.
        calculateMovieFee 를 구현하면서 파생적으로 생성된 메서드: isDiscountable, calculateDiscountAmount, isSatisfiedBy
        * */
    public Money calculateMovieFee(Screening screening){
        if(isDiscountable(screening)){
            return fee.minus(calculateDiscountAmount());
        }
        return fee;
    }

    private boolean isDiscountable(Screening screening){
        return discountConditions.stream().anyMatch(c -> c.isSatisfiedBy(screening));
    }

    abstract protected Money calculateDiscountAmount();
}

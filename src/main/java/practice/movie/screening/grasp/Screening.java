package practice.movie.screening.grasp;

import practice.movie.customer.Customer;
import practice.movie.reservation.grasp.Reservation;
import practice.movie.screening.Money;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie; // 상영할 영화
    private int sequence; // 순번
    private LocalDateTime whenScreened; // 상영 시작 시간

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getSequence() {
        return sequence;
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }


    /* GRASP 패턴 구현체(1)
            Screening 은 예매하라 메세지에 응답할 수 있어야한다.
            Movie 에 가격을 계산하라 메세지를 전송해야하기 때문에 movie 에 대한 참조도 포함해야한다.
            * */
    public Reservation reserve(Customer customer, int audienceCount){
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(int audienceCount){
        return movie.calculateMovieFee(this).times(audienceCount);
    }
}

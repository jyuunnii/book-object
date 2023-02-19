package practice.movie.screening;

import practice.movie.customer.Customer;
import practice.movie.reservation.Reservation;

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

    public LocalDateTime getStartTime(){
        return whenScreened;
    }

    public Money getMovieFee(){
        return movie.getFee();
    }

    /* 영화 예매 관련 메서드 */
    public boolean isSequence(int sequence){
        return this.sequence == sequence;
    }

    private Money calculateFee(int audienceCount){
        return movie.calculateMovieFee(this).times(audienceCount);
    }

    public Reservation reserve(Customer customer, int audienceCount){
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }
}

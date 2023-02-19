package practice.movie.reservation.dataCentric;

import practice.movie.customer.Customer;
import practice.movie.screening.Money;
import practice.movie.screening.dataCentric.Screening;

public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount){
        Money fee = screening.calculateFee(audienceCount);
        return new Reservation(customer, screening, fee, audienceCount);
    }
}

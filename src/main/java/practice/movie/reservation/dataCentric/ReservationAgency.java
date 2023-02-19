package practice.movie.reservation.dataCentric;

import practice.movie.customer.Customer;
import practice.movie.discount.dataCentric.DiscountCondition;
import practice.movie.discount.dataCentric.DiscountConditionType;
import practice.movie.screening.Money;
import practice.movie.screening.dataCentric.Movie;
import practice.movie.screening.dataCentric.Screening;

public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount){
        Movie movie = screening.getMovie();
        boolean discountable = false;
        Money fee;

        for(DiscountCondition condition : movie.getDiscountConditions()){
            if(condition.getType() == DiscountConditionType.PERIOD){
                discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
                        condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                        condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
            }else {
                discountable = condition.getSequence() == screening.getSequence();
            }

            if(discountable) break;
        }

        if(discountable){
            Money discountAmount = Money.ZERO;
            switch (movie.getMovieType()){
                case AMOUNT_DISCOUNT:
                    discountAmount = movie.getDiscountAmount();
                    break;
                case PERCENT_DISCOUNT:
                    discountAmount = movie.getFee().times(movie.getDiscountPercent());
                    break;
                case NONE_DISCOUNT:
                    discountAmount = Money.ZERO;
                    break;
            }
            fee = movie.getFee().minus(discountAmount);
        }else {
            fee = movie.getFee();
        }

        return new Reservation(customer, screening, fee, audienceCount);
    }
}

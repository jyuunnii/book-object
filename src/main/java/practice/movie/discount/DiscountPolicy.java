package practice.movie.discount;

import practice.movie.screening.Money;
import practice.movie.screening.Screening;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}

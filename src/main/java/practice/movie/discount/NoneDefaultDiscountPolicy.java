package practice.movie.discount;

import practice.movie.screening.Money;
import practice.movie.screening.Screening;

public class NoneDefaultDiscountPolicy implements DiscountPolicy {
    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}

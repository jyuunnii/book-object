package practice.movie.discount.grasp;

import practice.movie.screening.grasp.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}

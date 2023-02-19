package practice.movie.discount;

import practice.movie.screening.Screening;

// DiscountPolicy 와 달리 구현을 공유할 필요가 없음 -> interface
public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}

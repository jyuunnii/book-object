package practice.movie.screening;

import java.math.BigDecimal;

public class Money {
    public static final Money ZERO = Money.wons(0);
    private final BigDecimal amount;

    Money(BigDecimal amount){
        this.amount = amount;
    }

    /* wons 메서드 : 오버로딩(overloading) */
    public static Money wons(long amount){
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount){
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money times(double percent){
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public boolean isLessThan(Money other){
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other){
        return amount.compareTo(other.amount) >= 0;
    }
}

package practice.movie.screening.dataCentric;

import practice.movie.discount.dataCentric.DiscountCondition;
import practice.movie.discount.dataCentric.DiscountConditionType;
import practice.movie.screening.Money;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    /* 하기 responsibility-centric 과 다른 필드들. -> 캡슐화를 위반한다.*/
    private List<DiscountCondition> discountConditions;
    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Duration runningTime) {
        this.runningTime = runningTime;
    }

    public Money getFee() {
        return fee;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }

    public List<DiscountCondition> getDiscountConditions() {
        return discountConditions;
    }

    public void setDiscountConditions(List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public boolean isDiscountable(LocalDateTime whenScreened, int sequence){
        for(DiscountCondition condition : discountConditions){
            if(condition.getType() == DiscountConditionType.PERIOD && condition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())){
                return true;
            }
            if(condition.isDiscountable(sequence)){
                return true;
            }
        }
        return false;
    }

    // FIXME: 3가지 메서드를 통해 할인정책의 종류를 노출하고 있다.
    public Money calculateAmountDiscountedFee(){
        if(movieType != MovieType.AMOUNT_DISCOUNT){
            throw new IllegalArgumentException();
        }
        return fee.minus(discountAmount);
    }

    public Money calculatePercentDiscounted (){
        if(movieType != MovieType.PERCENT_DISCOUNT){
            throw new IllegalArgumentException();
        }
        return fee.minus(fee.times(discountPercent));
    }

    public Money calculateNoneDiscountedFee(){
        if(movieType != MovieType.NONE_DISCOUNT){
            throw new IllegalArgumentException();
        }
        return fee;
    }
}

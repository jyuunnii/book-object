package practice.movie.discount.grasp;

import practice.movie.screening.grasp.Screening;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
    private DiscountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    /* GRASP 패턴 구현체(3)
    Movie 의 calculateMovieFee 에서 DiscountCondition 에 할인 여부를 판단하라는 메세지를 전송한다.
    할인 여부를 판단하기 위해 기간 조건을 위한 요일, 시작 시간, 종료 시간 그리고 순번 조건을 위한 상영 순번을 인스턴스 변수로 갖는다.
    * */
    public boolean isSatisfiedBy(Screening screening){
        if(type == DiscountConditionType.PERIOD){
            return isSatisfiedByPeriod(screening);
        }
        return isSatisfiedBySequence(screening);
    }

    // FIXME: 서로 다른 조건으로 변경이 발생하는 isSatisfiedByXX (낮은 응집도)
    private boolean isSatisfiedBySequence(Screening screening) {
        return sequence == screening.getSequence();
    }

    private boolean isSatisfiedByPeriod(Screening screening) {
        return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
                startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                endTime.compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
    }
}

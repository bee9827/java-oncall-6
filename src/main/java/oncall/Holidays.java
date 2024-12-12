package oncall;

import java.time.MonthDay;

public enum Holidays {
    NEW_YEAR("신정", MonthDay.of(1,1)),
    INDEPENDENCE_MOVEMENT_DAY("삼일절",MonthDay.of(3,1)),
    CHILDREN_DAY("어린이날",MonthDay.of(5,5)),
    MEMORIAL_DAY("현충일",MonthDay.of(6,6)),
    LIBERATION_DAY("광복절",MonthDay.of(8,15)),
    NATIONAL_FOUNDATION_DAY("개천절",MonthDay.of(10,3)),
    HANGUL_DAY("한글날",MonthDay.of(10,9)),
    CHRISTMAS("성탄절",MonthDay.of(12,25));

    private final String name;
    private final MonthDay monthDay;

    Holidays(String name, MonthDay monthDay) {
        this.name = name;
        this.monthDay = monthDay;
    }

    public static boolean isHoliday(MonthDay monthDay) {
        for(Holidays holiday : Holidays.values()) {
            if(holiday.monthDay.equals(monthDay)) {
                return true;
            }
        }
        return false;
    }
}

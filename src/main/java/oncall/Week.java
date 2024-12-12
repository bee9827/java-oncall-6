package oncall;

import java.time.DayOfWeek;

public enum Week {
    MONDAY("월", DayOfWeek.MONDAY,WeekStatus.WEEKDAY),
    TUESDAY("화",DayOfWeek.TUESDAY,WeekStatus.WEEKDAY),
    WEDNESDAY("수",DayOfWeek.WEDNESDAY,WeekStatus.WEEKDAY),
    THURSDAY("목",DayOfWeek.THURSDAY,WeekStatus.WEEKDAY),
    FRIDAY("금",DayOfWeek.FRIDAY,WeekStatus.WEEKDAY),
    SATURDAY("토",DayOfWeek.SATURDAY,WeekStatus.WEEKEND),
    SUNDAY("일",DayOfWeek.SUNDAY,WeekStatus.WEEKEND);

    private final String name;
    private final DayOfWeek dayOfWeek;
    private final WeekStatus weekStatus;

    Week(String name, DayOfWeek dayOfWeek, WeekStatus weekStatus) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.weekStatus = weekStatus;
    }

    public static DayOfWeek getDayOfWeek(String name) {
        for(Week week : values()) {
            if(week.name.equals(name)) {
                return week.dayOfWeek;
            }
        }
        return null;
    }
}

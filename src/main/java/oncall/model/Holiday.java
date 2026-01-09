package oncall.model;

import java.util.List;

public enum Holiday {
    NEW_YEAR(1, 1),
    MARCH_1ST(3, 1),
    CHILDREN_DAY(5, 5),
    JUN_6TH(6, 6),
    LIBERATION_DAY(8, 15),
    OCTOBER_3RD(10, 3),
    X_MAX(12, 25),
    ;

    private final static List<DayOfWeek> WEEKEND = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    private final int month;
    private final int dayOfMonth;

    Holiday(int month, int dayOfMonth) {
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    public static boolean isHoliday(int month, int dayOfMonth, DayOfWeek dayOfWeek) {
        if (WEEKEND.contains(dayOfWeek)) {
            return true;
        }
        for (Holiday value : values()) {
            if (value.month == month && value.dayOfMonth == dayOfMonth) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWeekendAndHoliday(int month, int dayOfMonth, DayOfWeek dayOfWeek) {
        for (Holiday value : values()) {
            if (value.month == month && value.dayOfMonth == dayOfMonth && !WEEKEND.contains(dayOfWeek)) {
                return true;
            }
        }
        return false;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }
}

package oncall;

import java.time.DayOfWeek;

public class DateChecker {
    public static final int MIN_MONTH = 1;
    public static final int MAX_MONTH = 12;

    private final int month;
    private final DayOfWeek dayOfWeek;

    public DateChecker(int month, DayOfWeek dayOfWeek) {
        validateMonth(month);
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    private void validateMonth(int month) {
        if (month < MIN_MONTH || month > MAX_MONTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONTH.getMessage());
        }
    }

    public int getLastDayOfMonth(int month) {
        if (month == 2) {
            return 28;
        }
        if (month < 8) {
            return 30 + month % 2;
        }
        return 30 + (month + 1) % 2;
    }

    public boolean isHoliday(int day) {
        DayOfWeek dayOfweek = this.dayOfWeek.plus(day - 1);
        return !isWeekend(dayOfweek)
                && StatutoryHolidays.of(month, day) != null;
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY
                || dayOfWeek == DayOfWeek.SUNDAY;
    }
}

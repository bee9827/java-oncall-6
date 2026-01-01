package oncall;


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

    public int getLastDayOfMonth() {
        if (month == 2) {
            return 28;
        }
        if (month < 8) {
            return 30 + month % 2;
        }
        return 30 + (month + 1) % 2;
    }

    public boolean isHoliday(int day) {
        DayOfWeek dayOfWeek = this.dayOfWeek.plus(day - 1);
        return Holidays.of(month, day) != null
                || DayOfWeek.isWeekend(dayOfWeek);
    }

    public boolean isHolidayAndWeekDay(int day) {
        DayOfWeek dayOfweek = this.dayOfWeek.plus(day - 1);
        return !DayOfWeek.isWeekend(dayOfweek)
                && Holidays.of(month, day) != null;
    }

    public int getMonth() {
        return month;
    }

    public DayOfWeek getDayOfWeek(int day) {
        return dayOfWeek.plus(day - 1);
    }
}

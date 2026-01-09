package oncall.model;

import oncall.error.ErrorMessage;

public class OnCallDate {
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private final int month;
    private final DayOfWeek dayOfWeek;

    public OnCallDate(int month, DayOfWeek dayOfWeek) {
        validateMonth(month);
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    private void validateMonth(int month) {
        if (month < MIN_MONTH || month > MAX_MONTH) {
            throw new IllegalArgumentException(ErrorMessage.MONTH_VALUE.getLabel());
        }
    }

    public int getLastDayOfMonth() {
        if (month == 2) {
            return 28;
        }
        if (month < 7) {
            return month % 2 + 30;
        }
        return (month + 1) % 2 + 30;
    }

    public DayOfWeek getDayOfWeek(int day) {
        return dayOfWeek.plus(day - 1);
    }

    public int getMonth() {
        return month;
    }
}

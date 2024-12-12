package oncall;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class PrintDateFormat {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("M월 d일");
    public static final TextStyle STYLE = TextStyle.SHORT;
    public static final Locale LOCALE = Locale.KOREAN;
    public static final String WEEKDAY_BUT_HOLIDAY_MESSAGE = "(휴일)";

    public static String printDateFormat(MonthDay monthDay, DayOfWeek startDayOfWeek, boolean weekdayButHoliday, Worker worker) {
        return getMonthDay(monthDay) + " " + getDayOfWeek(startDayOfWeek, weekdayButHoliday) + " " + worker.getName();
    }

    public static String getMonthDay(MonthDay monthDay) {
        return monthDay.format(FORMATTER);
    }

    public static String getDayOfWeek(DayOfWeek dayOfWeek, boolean weekdayButHoliday) {
        if (weekdayButHoliday) {
            return dayOfWeek.getDisplayName(STYLE, LOCALE) + WEEKDAY_BUT_HOLIDAY_MESSAGE;
        }
        return dayOfWeek.getDisplayName(STYLE, LOCALE);
    }
}

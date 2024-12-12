package oncall;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;

public class HolidayDate {
    public static final boolean LEAP_YEAR = false;
    public static final int START_DAY = 1;
    private final Month startMonth;
    private final DayOfWeek startDayOfWeek;

    public HolidayDate(Month startMonth, DayOfWeek startDayOfWeek) {
        this.startMonth = startMonth;
        this.startDayOfWeek = startDayOfWeek;
    }


    public Month getStartMonth(){
        return startMonth;
    }

    public DayOfWeek getDayOfWeek(int day) {
        int differentOfDayOfWeek =  day - START_DAY;
        return startDayOfWeek.plus(differentOfDayOfWeek);
    }

    public int getLength(){
        return startMonth.length(LEAP_YEAR);
    }

    public boolean isWeekday(int day) {
        return getDayOfWeek(day) != DayOfWeek.SATURDAY && getDayOfWeek(day) != DayOfWeek.SUNDAY;
    }

    public boolean isHoliday(int day) {
        MonthDay monthDay = MonthDay.of(startMonth, day);
        return Holidays.isHoliday(monthDay);
    }

    public boolean isWeekdayButHoliday(int day) {
        return isHoliday(day) && isWeekday(day);
    }
}

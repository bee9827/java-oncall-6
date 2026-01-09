package oncall.view;

import java.util.List;
import oncall.model.Crew;
import oncall.model.DayOfWeek;
import oncall.model.Holiday;
import oncall.model.OnCallDate;

public class OutputView {
    private static final String WORK_SCHEDULE_FORMAT = "%d월 %d일 %s %s%n";

    public void printError(String message) {
        System.out.println(message);
    }

    public void printWorkSchedules(OnCallDate onCallDate, List<Crew> workSchedule) {
        int month = onCallDate.getMonth();
        for (int i = 1; i <= onCallDate.getLastDayOfMonth(); i++) {
            DayOfWeek dayOfWeek = onCallDate.getDayOfWeek(i);
            int idx = i - 1;
            printWorkSchedule(month, i, dayOfWeek, workSchedule.get(idx).getName());
        }
    }

    public void printWorkSchedule(int month, int dayOfMonth, DayOfWeek dayOfWeek, String name) {
        String dayOfWeekFormat = getDayOfWeekFormat(month, dayOfMonth, dayOfWeek);
        System.out.printf(WORK_SCHEDULE_FORMAT, month, dayOfMonth, dayOfWeekFormat, name);
    }

    private String getDayOfWeekFormat(int month, int dayOfMonth, DayOfWeek dayOfWeek) {
        if (Holiday.isWeekendAndHoliday(month, dayOfMonth, dayOfWeek)) {
            return dayOfWeek.getLabel() + "(휴일)";
        }
        return dayOfWeek.getLabel();
    }
}

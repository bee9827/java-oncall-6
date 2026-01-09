package oncall.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnCall {
    private final OnCallDate onCallDate;

    private final OnCallCrews weekdayCrews;
    private final OnCallCrews holidayCrews;
    private final List<Crew> workSchedule = new ArrayList<>();


    public OnCall(OnCallDate onCallDate, List<Crew> weekday, List<Crew> holiday) {
        this.onCallDate = onCallDate;
        this.weekdayCrews = new OnCallCrews(weekday);
        this.holidayCrews = new OnCallCrews(holiday);
    }

    public List<Crew> getWorkSchedule() {
        if (workSchedule.isEmpty()) {
            int lastDayOfMonth = onCallDate.getLastDayOfMonth();
            fillSequentially(lastDayOfMonth);
        }
        return Collections.unmodifiableList(workSchedule);
    }

    private void fillSequentially(int lastDayOfMonth) {
        Crew prevWorker = null;
        Crew nextWorker = null;
        for (int i = 1; i <= lastDayOfMonth; i++) {
            DayOfWeek dayOfWeek = onCallDate.getDayOfWeek(i);
            boolean isHoliday = Holiday.isHoliday(onCallDate.getMonth(), i, dayOfWeek);

            if (isHoliday) {
                nextWorker = holidayCrews.getNextWorker(prevWorker);
                workSchedule.add(nextWorker);
                continue;
            }
            nextWorker = weekdayCrews.getNextWorker(prevWorker);
            workSchedule.add(nextWorker);
        }
    }
}

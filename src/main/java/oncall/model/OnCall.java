package oncall.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import oncall.error.ErrorMessage;

public class OnCall {
    private static final int CREW_MAX_SIZE = 35;

    private final OnCallDate onCallDate;
    private final List<Crew> weekday;
    private final List<Crew> holiday;

    private final Queue<Crew> weekdayQueue = new LinkedList<>();
    private final Queue<Crew> holidayQueue = new LinkedList<>();
    private final List<Crew> workSchedule = new ArrayList<>();

    public OnCall(OnCallDate onCallDate, List<Crew> weekday, List<Crew> holiday) {
        validateCrew(weekday);
        validateCrew(holiday);
        this.onCallDate = onCallDate;
        this.weekday = weekday;
        this.holiday = holiday;
    }

    public List<Crew> getWorkSchedule() {
        if (workSchedule.isEmpty()) {
            run();
        }
        return workSchedule;
    }

    private void run() {
        int lastDayOfMonth = onCallDate.getLastDayOfMonth();
        fillSequentially(lastDayOfMonth);
    }

    private void fillSequentially(int lastDayOfMonth) {
        for (int i = 1; i <= lastDayOfMonth; i++) {
            DayOfWeek dayOfWeek = onCallDate.getDayOfWeek(i);
            boolean isHoliday = Holiday.isHoliday(onCallDate.getMonth(), i, dayOfWeek);

            if (isHoliday) {
                put(holiday, holidayQueue, i);
                continue;
            }
            put(weekday, weekdayQueue, i);
        }
    }

    private void put(List<Crew> crews, Queue<Crew> queue, int i) {
        int idx = (i - 1) % crews.size();
        if (!queue.isEmpty()) {
            workSchedule.add(queue.poll());
            return;
        }
        if (isContinuousWork(crews, i, idx)) {
            queue.add(crews.get(idx));
            workSchedule.add(crews.get(idx + 1));
            return;
        }
        workSchedule.add(crews.get(idx));
    }

    private boolean isContinuousWork(List<Crew> crews, int i, int idx) {
        return i > 1 && crews.get(idx) == workSchedule.get(workSchedule.size() - 1);
    }

    private void validateCrew(List<Crew> crews) {
        if (isDuplicate(crews)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_CREW.getLabel());
        }
        if (crews.size() > CREW_MAX_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.CREW_MAX_SIZE.getLabel());
        }
    }

    private boolean isDuplicate(List<Crew> crews) {
        return crews.stream().distinct()
                .toList().size() != crews.size();
    }
}

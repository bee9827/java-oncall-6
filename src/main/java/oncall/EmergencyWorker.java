package oncall;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class EmergencyWorker {
    private static final int MIN_SIZE = 5;
    private static final int MAX_SIZE = 35;

    private final ArrayDeque<Worker> weekdayWorkers = new ArrayDeque<>();
    private final ArrayDeque<Worker> weekendWorkers = new ArrayDeque<>();
    private final List<Worker> dayWorkers = new ArrayList<>();

    public EmergencyWorker(List<Worker> weekdays, List<Worker> weekends) {
        validate(weekdays, weekends);
        weekdayWorkers.addAll(weekdays);
        weekendWorkers.addAll(weekends);
    }

    private void validate(List<Worker> weekdays, List<Worker> weekends) {
        if (weekdays.size() < MIN_SIZE || weekends.size() < MIN_SIZE ||
                weekdays.size() > MAX_SIZE || weekends.size() > MAX_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WORKER_SIZE.getMessage());
        }
        if (isDuplicated(weekdays) || isDuplicated(weekends)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WORKER_DUPLICATE.getMessage());
        }
    }

    private boolean isDuplicated(List<Worker> workers) {
        return workers.stream()
                .distinct()
                .toList()
                .size() != workers.size();
    }

    public String getNextWorker(boolean isHoliday) {
        if (isHoliday) {
            return work(weekendWorkers);
        }
        return work(weekdayWorkers);
    }

    public List<String> getWorkedList() {
        return dayWorkers.stream()
                .map(Worker::name)
                .toList();
    }

    private String work(ArrayDeque<Worker> workers) {
        if (!dayWorkers.isEmpty() && dayWorkers.getLast() == workers.peekFirst()) {
            swapWorker(workers);
        }
        Worker worker = workers.pollFirst();
        workers.add(worker);
        dayWorkers.add(worker);

        return worker.name();
    }

    private void swapWorker(ArrayDeque<Worker> workers) {
        Worker continuousWorker = workers.pollFirst();
        Worker substituteWorker = workers.pollFirst();
        workers.addFirst(continuousWorker);
        workers.addFirst(substituteWorker);
    }

}

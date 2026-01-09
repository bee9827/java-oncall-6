package oncall.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import oncall.error.ErrorMessage;

public class OnCallCrews {
    private static final int CREW_MAX_SIZE = 35;

    private final List<Crew> crews;
    private final Queue<Crew> weekdayQueue = new LinkedList<>();
    private int idx = 0;

    public OnCallCrews(List<Crew> crews) {
        validateCrew(crews);
        this.crews = crews;
    }

    public Crew getNextWorker(final Crew prev) {
        int cur = idx;
        idx += 1;

        if (!weekdayQueue.isEmpty()) {
            return weekdayQueue.poll();
        }
        if (prev == crews.get(cur)) {
            weekdayQueue.add(crews.get(cur));
            return crews.get(cur + 1);
        }
        return crews.get(cur);
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

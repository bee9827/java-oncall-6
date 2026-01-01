package oncall;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import oncall.model.EmergencyWorker;
import oncall.model.Worker;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class EmergencyWorkerTest {

    @Test
    @DisplayName("다음 근무자 정보를 받아 온다")
    public void getNextWorker() {
        List<Worker> weekdayWorkers = List.of("일일", "이이", "삼삼", "사사", "오오")
                .stream()
                .map(Worker::new)
                .toList();
        List<Worker> holidayWorkers = weekdayWorkers.stream().toList();
        EmergencyWorker emergencyWorker = new EmergencyWorker(weekdayWorkers, holidayWorkers);

        String firstWorker = emergencyWorker.getNextWorker(true);
        String secondWorker = emergencyWorker.getNextWorker(false);
        assertThat(firstWorker).isNotEqualTo(secondWorker);
    }
}

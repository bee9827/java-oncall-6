package oncall;

import java.util.List;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private static List<Worker> toWorker(List<String> weekdayWorker) {
        return weekdayWorker.stream()
                .map(Worker::new)
                .toList();
    }

    public void run() {
        DateDto dateDto = inputView.readDate();
        DateChecker dateChecker = new DateChecker(dateDto.month(), dateDto.dayOfWeek());

        List<String> weekdayWorker = inputView.readWeekdayWorker();
        List<String> holidayWorker = inputView.readHolidayWorker();
        EmergencyWorker emergencyWorker = createEmergencyWorker(weekdayWorker, holidayWorker);

        for (int day = 1; day <= dateChecker.getLastDayOfMonth(); day++) {
            boolean isHoliday = dateChecker.isHoliday(day);
            String nextWorker = emergencyWorker.getNextWorker(isHoliday);
            boolean isHolidayAndWeekday = dateChecker.isHolidayAndWeekDay(day);
            outputView.printWorker(
                    dateChecker.getMonth(), day,
                    dateChecker.getDayOfWeek(day).getLabel(), nextWorker, isHolidayAndWeekday);
        }
    }

    private EmergencyWorker createEmergencyWorker(List<String> weekdayWorker, List<String> holidayWorker) {
        return new EmergencyWorker(toWorker(weekdayWorker), toWorker(holidayWorker));
    }
}

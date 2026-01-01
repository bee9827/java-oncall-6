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
        DateChecker dateChecker = ErrorHandler.retry(
                () -> createDateCheker(inputView.readDate()),
                outputView);

        EmergencyWorker emergencyWorker = ErrorHandler.retry(
                () -> createEmergencyWorker(
                        inputView.readWeekdayWorker(),
                        inputView.readHolidayWorker()),
                outputView);

        for (int day = 1; day <= dateChecker.getLastDayOfMonth(); day++) {
            boolean isHoliday = dateChecker.isHoliday(day);
            String nextWorker = emergencyWorker.getNextWorker(isHoliday);
            boolean isHolidayAndWeekday = dateChecker.isHolidayAndWeekDay(day);
            outputView.printWorker(
                    dateChecker.getMonth(), day,
                    dateChecker.getDayOfWeek(day).getLabel(), nextWorker, isHolidayAndWeekday);
        }
    }

    private DateChecker createDateCheker(DateDto dateDto) {
        return new DateChecker(dateDto.month(), dateDto.dayOfWeek());
    }

    private EmergencyWorker createEmergencyWorker(List<String> weekdayWorker, List<String> holidayWorker) {
        return new EmergencyWorker(toWorker(weekdayWorker), toWorker(holidayWorker));
    }
}

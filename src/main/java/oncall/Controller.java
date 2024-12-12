package oncall;

import java.time.Month;
import java.util.List;
import java.util.function.Supplier;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(){
        HolidayDate holidayDate = runWithRetry(inputView::askHolidayDate);
        Workers weekdaysWorker = runWithRetry(inputView::askWeekDaysWorker);
        Workers weekendsWorker = runWithRetry(inputView::askWeekDaysWorker);
        WorkerScheduler workerScheduler = new WorkerScheduler(weekdaysWorker,weekendsWorker);
        OncallService oncallService = new OncallService(workerScheduler, holidayDate);

        outputView.printList(oncallService.getAll());
    }

    public <T> T  runWithRetry(Supplier<T> supplier){
        while(true) {
            try {
                return supplier.get();
            } catch (RuntimeException e) {
                outputView.printError(e);
            }
        }
    }
}

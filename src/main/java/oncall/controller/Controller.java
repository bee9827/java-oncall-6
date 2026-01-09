package oncall.controller;

import java.util.List;
import oncall.error.ErrorHandler;
import oncall.model.Crew;
import oncall.model.OnCall;
import oncall.model.OnCallDate;
import oncall.view.InputView;
import oncall.view.OnCallDateDto;
import oncall.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final ErrorHandler errorHandler;

    public Controller(InputView inputView, OutputView outputView, ErrorHandler errorHandler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.errorHandler = errorHandler;
    }

    public void run() {
        OnCallDate oncallDate = errorHandler.retryWithPrintError(this::readOnCallDate);
        OnCall onCall = errorHandler.retryWithPrintError(() -> readOnCallCrews(oncallDate));
        List<Crew> workSchedule = onCall.getWorkSchedule();
        outputView.printWorkSchedules(oncallDate, workSchedule);
    }

    private OnCallDate readOnCallDate() {
        OnCallDateDto onCallDateDto = inputView.readMonthAndDayOfWeek();
        return new OnCallDate(onCallDateDto.month(), onCallDateDto.dayOfWeek());
    }

    private OnCall readOnCallCrews(OnCallDate onCallDate) {
        List<Crew> weekDayCrews = getCrews(inputView.readWeekDayOnCall());
        List<Crew> holidayCrews = getCrews(inputView.readHolidayOnCall());

        return new OnCall(onCallDate, weekDayCrews, holidayCrews);
    }

    private List<Crew> getCrews(List<String> names) {
        return names.stream()
                .map(Crew::new)
                .toList();
    }
}

package oncall.error;

import java.util.function.Supplier;
import oncall.view.OutputView;

public class ErrorHandler {
    private final OutputView outputView;

    public ErrorHandler(OutputView outputView) {
        this.outputView = outputView;
    }

    public <T> T retryWithPrintError(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}

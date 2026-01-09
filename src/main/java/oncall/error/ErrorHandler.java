package oncall.error;

import oncall.controller.OutputView;

public class ErrorHandler {
    private final OutputView outputView;

    public ErrorHandler(OutputView outputView) {
        this.outputView = outputView;
    }

    public void retryWithPrintError(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e){
                outputView.printError(e.getMessage());
            }
        }
    }
}

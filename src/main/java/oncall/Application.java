package oncall;

import oncall.controller.Controller;
import oncall.view.OutputView;
import oncall.error.ErrorHandler;
import oncall.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ErrorHandler errorHandler = new ErrorHandler(outputView);
        Controller controller = new Controller(inputView,outputView,errorHandler);
        controller.run();

    }
}

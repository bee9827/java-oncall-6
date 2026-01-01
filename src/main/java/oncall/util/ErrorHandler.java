package oncall.util;

import java.util.function.Supplier;
import oncall.view.OutputView;

public class ErrorHandler {
    public static <T> T retry(Supplier<T> supplier, OutputView outputView) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}

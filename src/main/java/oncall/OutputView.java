package oncall;

import java.util.List;

public class OutputView {
    public void printError(RuntimeException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public void printList(List<String> all) {
        all.forEach(System.out::println);
    }
}

package oncall;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static List<String> split(String names, String Delimiter) {
        return Arrays.stream(names.split(Delimiter))
                .map(String::trim)
                .toList();
    }

    public static Integer toInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }
}

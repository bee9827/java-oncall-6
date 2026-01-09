package oncall.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import oncall.error.ErrorMessage;

public class Parser {

    public static List<String> split(String s) {
        try {
            return Arrays.stream(s.split(","))
                    .toList();
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getLabel());
        }
    }

    public static Integer toInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getLabel());
        }
    }
}

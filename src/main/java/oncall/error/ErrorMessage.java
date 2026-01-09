package oncall.error;

public enum ErrorMessage {
    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    NAME_LENGTH("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    MONTH_VALUE("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");

    public static final String PREFIX = "[ERROR] ";
    private final String label;

    ErrorMessage(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

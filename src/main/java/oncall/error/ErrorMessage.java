package oncall.error;

public enum ErrorMessage {
    INVALID_INPUT("유효하지 않은 입력입니다."),
    NAME_LENGTH("이름의 길이가 잘못됐습니다.")
    ;

    public static final String PREFIX = "[ERROR] ";
    private final String label;

    ErrorMessage(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

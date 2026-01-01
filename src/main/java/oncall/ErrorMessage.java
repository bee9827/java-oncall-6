package oncall;

public enum ErrorMessage {
    INVALID_NAME("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_MONTH("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_WORKER_SIZE("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_WORKER_DUPLICATE("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_FORMAT("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}

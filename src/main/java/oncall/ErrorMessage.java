package oncall;

public enum ErrorMessage {
    INVALID_NAME(""),
    INVALID_MONTH(""),
    INVALID_WORKER_SIZE(""),
    INVALID_WORKER_DUPLICATE(""),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}

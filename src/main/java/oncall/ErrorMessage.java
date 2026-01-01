package oncall;

public enum ErrorMessage {
    INVALID_NAME(""),
    INVALID_MONTH("")
    ;
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}

package oncall.model;

public record Worker(String name) {
    public static final int MAX_LENGTH = 5;
    public Worker {
        if(name.isEmpty() || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NAME.getMessage());
        }
    }
}

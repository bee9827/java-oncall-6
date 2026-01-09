package oncall.model;

import oncall.error.ErrorMessage;

public class Crew {
    private static final int NAMX_MAX_LENGTH = 5;
    private final String name;

    public Crew(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.isBlank() || name.length() > NAMX_MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.NAME_LENGTH.getLabel());
        }
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }
}

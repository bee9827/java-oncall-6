package oncall.model;

import oncall.error.ErrorMessage;

public enum DayOfWeek {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일"),
    ;
    private String label;

    DayOfWeek(String label) {
        this.label = label;
    }

    public static DayOfWeek from(String s) {
        for (DayOfWeek value : values()) {
            if (value.label.equals(s)) {
                return value;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getLabel());
    }

    public String getLabel() {
        return label;
    }
}

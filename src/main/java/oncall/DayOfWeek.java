package oncall;

public enum DayOfWeek {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private static final DayOfWeek[] ENUMS = DayOfWeek.values();

    private String label;

    private DayOfWeek(String label) {
        this.label = label;
    }

    public static boolean isWeekend(DayOfWeek day) {
        return day == SATURDAY || day == SUNDAY;
    }

    public DayOfWeek plus(long days) {
        int amount = (int) (days % 7);
        return ENUMS[(ordinal() + (amount + 7)) % 7];
    }
}

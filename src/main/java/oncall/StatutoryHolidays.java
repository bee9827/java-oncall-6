package oncall;

public enum StatutoryHolidays {
    NEW_YEAR(1,1),
    MARCH_FIRST(3,1),
    MAY_FIFTH(5,5),
    JUNE_SIXTH(6,6),
    AUGUST_15TH(8,15),
    OCTOBER_3TH(10,3),
    OCTOBER_9TH(10,9),
    X_MAS(12,25),

    ;
    private final int month;
    private final int day;

    StatutoryHolidays(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public static StatutoryHolidays of(int month, int day) {
        for(StatutoryHolidays statutory : StatutoryHolidays.values()) {
            if(statutory.month == month && statutory.day == day) {
                return statutory;
            }
        }
        return null;
    }
}

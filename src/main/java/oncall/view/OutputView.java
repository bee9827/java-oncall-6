package oncall.view;

public class OutputView {
    public static final String ERROR_PREFIX = "[ERROR] ";

    public void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public void printWorker(int month, int day, String dayOfWeek, String nextWorker, boolean isHolidayAndWeekdays) {
        if (isHolidayAndWeekdays) {
            dayOfWeek += "(휴일)";
        }
        System.out.printf("%d월 %d일 %s %s%n", month, day, dayOfWeek, nextWorker);
    }
}

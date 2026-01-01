package oncall;

public class OutputView {
    public void printWorker(int month, int day, String dayOfWeek, String nextWorker, boolean isHolidayAndWeekdays) {
        if(isHolidayAndWeekdays) {
            dayOfWeek += "(휴일)";
        }
        System.out.printf("%d월 %d일 %s %s%n", month, day, dayOfWeek, nextWorker);
    }
}

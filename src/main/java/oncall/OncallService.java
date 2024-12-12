package oncall;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

public class OncallService {
    private final WorkerScheduler workerScheduler;
    private final HolidayDate holidayDate;

    public OncallService(WorkerScheduler workerScheduler, HolidayDate holidayDate) {
        this.workerScheduler = workerScheduler;
        this.holidayDate = holidayDate;
    }

    public List<String> getAll() {
        List<String> infos = new ArrayList<>();
        for (int i = HolidayDate.START_DAY; i <= holidayDate.getLength(); i++) {
            MonthDay monthDay = MonthDay.of(holidayDate.getStartMonth(), i);
            DayOfWeek startDayOfWeek = holidayDate.getDayOfWeek(i);
            boolean weekdayButHoliday = holidayDate.isWeekdayButHoliday(i);
            Worker worker = getWorker(i);
            infos.add(PrintDateFormat.printDateFormat(monthDay, startDayOfWeek, weekdayButHoliday, worker));
        }
        return infos;
    }

    private Worker getWorker(int i) {
        if (holidayDate.isWeekday(i)) {
            return workerScheduler.getNextWeekDayWorker();
        }
        return workerScheduler.getNextWeekendWorker();
    }

}

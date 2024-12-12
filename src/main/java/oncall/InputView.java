package oncall;

import camp.nextstep.edu.missionutils.Console;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class InputView {
    public static final String SEPARATOR = ",";
    public HolidayDate askHolidayDate() {
        String[] inputs = Console.readLine()
                .split(SEPARATOR);

        Month month = Month.of(Integer.parseInt(inputs[0]));
        DayOfWeek dayOfWeek = Week.getDayOfWeek(inputs[1]);
        return new HolidayDate(month, dayOfWeek);
    }

    public Workers askWeekDaysWorker() {
        String[] inputs = Console.readLine().split(SEPARATOR);

        List<Worker> workers = new ArrayList<>();
        for(int i=0; i<inputs.length; i++) {
            workers.add(new Worker(inputs[i]));
        }
        return new Workers(workers);
    }

}

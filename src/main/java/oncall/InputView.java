package oncall;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {
    public static final String DEFAULT_DELIMITER = ",";

    public DateDto readDate() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String values = Console.readLine();
        List<String> monthAndDay = Parser.split(values, DEFAULT_DELIMITER);
        if (monthAndDay.size() != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
        return new DateDto(Parser.toInteger(monthAndDay.getFirst()), DayOfWeek.fromLabel(monthAndDay.getLast()));
    }

    public List<String> readHolidayWorker() {
        System.out.println("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String names = Console.readLine();
        return Parser.split(names, DEFAULT_DELIMITER);
    }

    public List<String> readWeekdayWorker() {
        System.out.println("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String names = Console.readLine();
        return Parser.split(names, DEFAULT_DELIMITER);
    }
}

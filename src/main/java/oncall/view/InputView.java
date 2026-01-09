package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.model.DayOfWeek;
import oncall.util.Parser;

public class InputView {
    private static final String READ_MONTH_AND_DAY_OF_WEEK = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String READ_WEEK_DAY_ON_CALL = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String READ_HOLIDAY_ON_CALL = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";

    public OnCallDateDto readMonthAndDayOfWeek() {
        System.out.println(READ_MONTH_AND_DAY_OF_WEEK);
        List<String> split = Parser.split(Console.readLine());
        return new OnCallDateDto(Parser.toInteger(split.get(0)), DayOfWeek.from(split.get(1)));
    }

    public List<String> readWeekDayOnCall() {
        System.out.println(READ_WEEK_DAY_ON_CALL);
        return Parser.split(Console.readLine());
    }

    public List<String> readHolidayOnCall() {
        System.out.println(READ_HOLIDAY_ON_CALL);
        return Parser.split(Console.readLine());
    }
}

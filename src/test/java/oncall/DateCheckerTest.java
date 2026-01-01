package oncall;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class DateCheckerTest {

    public static Stream<Arguments> isHolidayAndWeekDay() {
        return Stream.of(
                Arguments.of(DayOfWeek.MONDAY, true),
                Arguments.of(DayOfWeek.FRIDAY, true),
                Arguments.of(DayOfWeek.SATURDAY, false),
                Arguments.of(DayOfWeek.SUNDAY, false)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "1,true", //1.1[월](신정)
            "2,false",
            "6,true" // 1.6[토]
    })
    @DisplayName("휴일이면 True를 반환한다")
    public void isHoliday(int day, boolean isHoliday) {
        DateChecker dateChecker = new DateChecker(1, DayOfWeek.MONDAY);
        assertThat(dateChecker.isHoliday(day)).isEqualTo(isHoliday);
    }

    @ParameterizedTest
    @CsvSource({
            "0", "13", "-1"
    })
    @DisplayName("1~12 값이 아니라면 예외를 던진다")
    public void constructor(int month) {
        assertThatThrownBy(() -> new DateChecker(month, DayOfWeek.MONDAY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_MONTH.getMessage());
    }

    @ParameterizedTest
    @DisplayName("평일 이면서 법정 공휴일인 경우 휴일 임을 알려준다.")
    @MethodSource
    public void isHolidayAndWeekDay(DayOfWeek dayOfWeek, boolean isHoliday) {
        DateChecker dateChecker = new DateChecker(1, dayOfWeek);
        assertThat(dateChecker.isHolidayAndWeekDay(1)).isEqualTo(isHoliday);
        assertThat(dateChecker.isHolidayAndWeekDay(2)).isEqualTo(false);
    }

    @ParameterizedTest
    @DisplayName("해당하는 달에 몇일이 있는지 알 수 있다.")
    @CsvSource({
            "1,31", "2,28", "3,31", "4,30",
            "5,31", "6,30", "7,31", "8,31",
            "9,30", "10,31", "11,30", "12,31"
    })
    public void getLastDayOfMonth(int month, int day) {
        DateChecker dateChecker = new DateChecker(month, DayOfWeek.MONDAY);
        assertThat(dateChecker.getLastDayOfMonth()).isEqualTo(day);

    }
}

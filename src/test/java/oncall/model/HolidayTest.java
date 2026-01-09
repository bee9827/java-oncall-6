package oncall.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HolidayTest {

    @ParameterizedTest
    @CsvSource({
            "1,1,월,true",
            "1,1,화,true",
            "1,2,토,true",
            "1,2,월,false"
    })
    void isHoliday(int month, int dayOfMonth, String dayOfWeekValue, boolean expected) {
        DayOfWeek dayOfWeek = DayOfWeek.from(dayOfWeekValue);

        assertThat(Holiday.isHoliday(month, dayOfMonth, dayOfWeek)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,일,false",
            "1,1,월,true"
    })
    void isWeekendAndHoliday(int month, int dayOfMonth, String dayOfWeekValue, boolean expected) {
        DayOfWeek dayOfWeek = DayOfWeek.from(dayOfWeekValue);
        assertThat(Holiday.isWeekendAndHoliday(month,dayOfMonth,dayOfWeek)).isEqualTo(expected);
    }
}

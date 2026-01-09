package oncall.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import oncall.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DayOfWeekTest {
    @Test
    @DisplayName("DayOfWeek.from(): [예외] 값이 다를경우 예외를 발생시킨다.")
    void staticConstructor(){
        assertThatThrownBy(()->DayOfWeek.from("월요일"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_INPUT.getLabel());
    }

    @ParameterizedTest(name = "[{0}].plus({1}) = [{2}]")
    @DisplayName("plus(): n일뒤 요일을 반환한다.")
    @CsvSource({
            "월,1,화",
            "월,6,일",
            "월,7,월",
            "월,14,월"
    })
    void plus(String dayOfWeekValue,int day, String expectedValue) {
        DayOfWeek dayOfWeek = DayOfWeek.from(dayOfWeekValue);
        DayOfWeek expected = DayOfWeek.from(expectedValue);

        assertThat(dayOfWeek.plus(day)).isEqualTo(expected);
    }
}

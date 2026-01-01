package oncall;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import oncall.model.ErrorMessage;
import oncall.model.Worker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;

class WorkerTest {

    @DisplayName("이름이 1~5글자가 아니면 예외를 발생시킨다.")
    @ParameterizedTest
    @CsvSource({
            "다섯글자넘어감"
    })
    @EmptySource
    public void invalidName(String name){
        assertThatThrownBy(()-> new Worker(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_NAME.getMessage());
    }

}

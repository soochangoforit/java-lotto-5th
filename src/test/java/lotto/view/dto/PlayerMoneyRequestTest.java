package lotto.view.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerMoneyRequestTest {

    @ParameterizedTest(name = "공백 혹은 빈문자열인 {0}을 입력받으면, IllegalArgumentException을 반환한다")
    @ValueSource(strings = {"", " ", "  "})
    void InputMoneyRequest생성자는_빈문자열_혹은_공백을_입력받으면_IllegalArgumentException을_던진다(String inputMoney) {
        assertThatThrownBy(() -> PlayerMoneyRequest.from(inputMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "숫자가 아닌 문자열 {0}을 입력받으면, IllegalArgumentException을 반환한다")
    @ValueSource(strings = {"abc", "가나다"})
    void InputMoneyRequest생성자는_숫자가_아닌_문자열을_입력받으면_IllegalArgumentException을_던진다(String inputMoney) {
        assertThatThrownBy(() -> PlayerMoneyRequest.from(inputMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }


}

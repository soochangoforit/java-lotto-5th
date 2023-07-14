package lotto.view.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputMoneyRequestTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void InputMoneyRequest생성자는_빈문자열_혹은_공백을_입력받으면_IllegalArgumentException을_던진다(String inputMoney) {
        assertThatThrownBy(() -> new InputMoneyRequest(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 숫자만 입력 가능합니다.");
    }
}

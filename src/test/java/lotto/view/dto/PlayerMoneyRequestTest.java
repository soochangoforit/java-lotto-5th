package lotto.view.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.Money;

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


    @ParameterizedTest(name = "구입금액: {0}이라면, 구입가능한 로또개수는 {1}개이다.")
    @CsvSource(value = {
            "14000, 14",
            "1000, 1"
    })
    void calculateLottoCount메서드는_로또티켓구매_금액을_받아_만들_수_있는_로또티켓개수을_응답한다(long playerMoney, long ticketCountExpected) {
        long ticketCount = Money.from(playerMoney).calculateLottoCount(1000);

        assertThat(ticketCount).isEqualTo(ticketCountExpected);
    }

    @ParameterizedTest(name = "구매 금액 {0}을 입력 받으면 로또 티켓으로 나누어 떨어질 수 있기에 true를 응답한다")
    @ValueSource(strings = {"14000", "1000"})
    void isDivided메서드는_로또티켓구매_금액을_받아_로또티켓가격으로_나누어_떨어지면_true를_응답한다(long playerMoney) {

        boolean isDivided = Money.from(playerMoney).isDivided(1000);

        assertThat(isDivided).isTrue();
    }

    @ParameterizedTest(name = "구매 금액 {0}을 입력 받으면 로또 티켓으로 나누어 떨어지지 않기에 false를 응답한다")
    @ValueSource(strings = {"14001", "1001"})
    void isDivided메서드는_로또티켓구매_금액을_받아_로또티켓가격으로_나누어_떨어지지_않으면_false를_응답한다(long playerMoney) {
        boolean isDivided = Money.from(playerMoney).isDivided(1000);

        assertThat(isDivided).isFalse();
    }
}

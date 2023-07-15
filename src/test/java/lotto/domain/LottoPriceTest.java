package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoPriceTest {

    @ParameterizedTest(name = "구입금액: {0}이라면, 구입가능한 로또개수는 {1}개이다.")
    @CsvSource(value = {
            "14000, 14",
            "1000, 1"
    })
    void calculateLottoCount메서드는_구입금액을_입력받아_구입가능한_로또개수를_반환한다(Money playerMoney, int ticketCountExpected) {
        LottoCount lottoCount = LottoPrice.calculateLottoCount(playerMoney);
        assertThat(lottoCount).isEqualTo(new LottoCount(ticketCountExpected));
    }

    @ParameterizedTest(name = "1000 단위가 아닌, 구입금액: {0}이라면, IllegalArgumentException을 반환한다")
    @ValueSource(strings = {"10001", "900"})
    void calculateLottoCount메서드는_구입금액을_입력받아_로또가격으로_나눌_수_없는_경우_Exception을_반환한다(Money inputMoney) {
        assertThatThrownBy(() -> LottoPrice.calculateLottoCount(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 단위로 입력 가능합니다.");
    }
}

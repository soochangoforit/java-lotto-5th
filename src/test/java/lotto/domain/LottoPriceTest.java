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
    void calculateLottoCount메서드는_구입금액을_입력받아_구입가능한_로또개수를_반환한다(long playerMoney, int ticketCountExpected) {
        TicketCount ticketCount = LottoPrice.calculateLottoCount(Money.from(playerMoney));
        assertThat(ticketCount).isEqualTo(new TicketCount(ticketCountExpected));
    }

    @ParameterizedTest(name = "1000 단위가 아닌, 구입금액: {0}이라면, IllegalArgumentException을 반환한다")
    @ValueSource(longs = {14001, 1001})
    void calculateLottoCount메서드는_구입금액을_입력받아_로또가격으로_나눌_수_없는_경우_Exception을_반환한다(long inputMoney) {
        Money money = Money.from(inputMoney);

        assertThatThrownBy(() -> LottoPrice.calculateLottoCount(money))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

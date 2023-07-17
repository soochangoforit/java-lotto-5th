package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

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

    @Test
    void 구매금액이_0이라면_Exception을_반환한다() {
        assertThatThrownBy(() -> Money.from(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

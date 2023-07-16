package lotto.domain;

import static lotto.domain.LottoPrize.FOURTH_PRIZE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TotalLottoPrizeTest {

    private final TotalLottoPrize totalLottoPrize = TotalLottoPrize.from(Map.of
            (
                    FOURTH_PRIZE, 1L
            )
    );

    private static Stream<Arguments> winningRate() {
        return Stream.of(
                Arguments.of(
                        10000L,
                        WinningRate.from(500.0f)
                ),
                Arguments.of(
                        50000L,
                        WinningRate.from(100.0f)
                ),
                Arguments.of(
                        100000L,
                        WinningRate.from(50.0f)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("winningRate")
    void calculateWinningRate는_전체상금에서_투입한_금액_대비_수익률을_계산한다(long money, WinningRate expectedRate) {
        Money playerMoney = Money.from(money);

        WinningRate winningRate = totalLottoPrize.calculateWinningRate(playerMoney);

        assertThat(winningRate).isEqualTo(expectedRate);
    }
}

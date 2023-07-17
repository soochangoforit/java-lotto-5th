package lotto.domain;

import static lotto.domain.LottoPrize.FIFTH_PRIZE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoPrizeTest {


    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(0, false, LottoPrize.NOT_MATCHED),
                Arguments.of(1, false, LottoPrize.NOT_MATCHED),
                Arguments.of(3, false, FIFTH_PRIZE),
                Arguments.of(3, true, FIFTH_PRIZE),
                Arguments.of(4, false, LottoPrize.FOURTH_PRIZE),
                Arguments.of(4, true, LottoPrize.FOURTH_PRIZE),
                Arguments.of(5, false, LottoPrize.THIRD_PRIZE),
                Arguments.of(5, true, LottoPrize.SECOND_PRIZE),
                Arguments.of(6, false, LottoPrize.FIRST_PRIZE),
                Arguments.of(6, true, LottoPrize.FIRST_PRIZE)
        );
    }

    @ParameterizedTest(name = "[{index}] matchCount: {0}, hasBonus: {1}, expectedLottoPrize: {2}")
    @MethodSource("provideTestData")
    void from메서드는_매칭된_숫자와_보너스번호를_맞춘_여부에_따라서_LottoPrize_Enum을_반환한다(
            int matchCount, boolean hasBonus, LottoPrize expectedLottoPrize
    ) {
        LottoPrize actualLottoPrize = LottoPrize.getLottoPrize(matchCount, hasBonus);
        assertThat(actualLottoPrize).isEqualTo(expectedLottoPrize);
    }


    @Test
    void multiply메서드는_로또_개수를_입력받아_상금을_곱해서_반환한다() {
        long expected = 5000L * 3L;
        long actual = FIFTH_PRIZE.multiply(3L);

        assertThat(actual).isEqualTo(expected);
    }

}

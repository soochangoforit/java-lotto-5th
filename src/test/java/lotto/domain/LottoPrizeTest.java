package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoPrizeTest {


    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(0, false, LottoPrize._NOT_MATCHED),
                Arguments.of(1, false, LottoPrize._NOT_MATCHED),
                Arguments.of(3, false, LottoPrize._5TH_PRIZE),
                Arguments.of(3, true, LottoPrize._5TH_PRIZE),
                Arguments.of(4, false, LottoPrize._4TH_PRIZE),
                Arguments.of(4, true, LottoPrize._4TH_PRIZE),
                Arguments.of(5, false, LottoPrize._3RD_PRIZE),
                Arguments.of(5, true, LottoPrize._2ND_PRIZE),
                Arguments.of(6, false, LottoPrize._1ST_PRIZE),
                Arguments.of(6, true, LottoPrize._1ST_PRIZE)
        );
    }

    @ParameterizedTest(name = "[{index}] matchCount: {0}, hasBonus: {1}, expectedLottoPrize: {2}")
    @MethodSource("provideTestData")
    void from메서드는_매칭된_숫자와_보너스번호를_맞춘_여부에_따라서_LottoPrize_Enum을_반환한다(
            int matchCount, boolean hasBonus, LottoPrize expectedLottoPrize
    ) {
        LottoPrize actualLottoPrize = LottoPrize.getPrizeFrom(matchCount, hasBonus);
        assertThat(actualLottoPrize).isEqualTo(expectedLottoPrize);
    }

}

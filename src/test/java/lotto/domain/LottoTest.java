package lotto.domain;

import static lotto.domain.LottoPrize.FIFTH_PRIZE;
import static lotto.domain.LottoPrize.FIRST_PRIZE;
import static lotto.domain.LottoPrize.FOURTH_PRIZE;
import static lotto.domain.LottoPrize.SECOND_PRIZE;
import static lotto.domain.LottoPrize.THIRD_PRIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> Lotto.from(1, 2, 3, 4, 5, 6, 7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> Lotto.from(1, 2, 3, 4, 5, 5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // 아래에 추가 테스트 작성 가능
    @ParameterizedTest
    @MethodSource("outOfRangeLottoNumbers")
    void from을_통해_로또을_만드는_경우_로또번호_범위값을_벗어난_숫자가_들어오면_예외가_발생한다(List<Integer> lottoNumbers) {
        assertThatThrownBy(() -> Lotto.from(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<List<Integer>> outOfRangeLottoNumbers() {
        return Stream.of(
                List.of(0, 1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 46)
        );
    }


    @Test
    void validateDuplicate는_보너스볼이_로또번호와_중복되면_예외를_발생시킨다() {
        final Lotto winningLottoNumber = Lotto.from(1, 2, 3, 4, 5, 6);
        final LottoNumber bonusLottoNumber = LottoNumber.from(1);

        assertThatThrownBy(() -> winningLottoNumber.validateDuplicate(bonusLottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateDuplicate는_중복된_숫자가_없는_경우_예외를_발생시키지_않는다() {
        final Lotto winningLottoNumber = Lotto.from(1, 2, 3, 4, 5, 6);
        final LottoNumber bonusLottoNumber = LottoNumber.from(7);

        assertDoesNotThrow(() -> winningLottoNumber.validateDuplicate(bonusLottoNumber));
    }


    private static Stream<Arguments> calculateLottoPrize() {
        return Stream.of(
                Arguments.of(Lotto.from(1, 2, 3, 4, 5, 6), LottoNumber.from(7), FIRST_PRIZE),
                Arguments.of(Lotto.from(1, 2, 3, 4, 5, 8), LottoNumber.from(6), SECOND_PRIZE),
                Arguments.of(Lotto.from(1, 2, 3, 4, 5, 8), LottoNumber.from(7), THIRD_PRIZE),
                Arguments.of(Lotto.from(1, 2, 3, 4, 7, 8), LottoNumber.from(9), FOURTH_PRIZE),
                Arguments.of(Lotto.from(1, 2, 3, 7, 8, 9), LottoNumber.from(10), FIFTH_PRIZE),
                Arguments.of(Lotto.from(40, 41, 42, 43, 44, 45), LottoNumber.from(39), LottoPrize.NOT_MATCHED)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateLottoPrize")
    void calculateLottoPrize는_당첨번호와_보너스볼을_받아서_당첨여부을_계산한다(
            Lotto winningLotto, LottoNumber bonusLottoNumber, LottoPrize expectedLottoPrize
    ) {
        final Lotto playerLotto = Lotto.from(1, 2, 3, 4, 5, 6);

        final LottoPrize actualLottoPrize = playerLotto.calculateLottoPrize(winningLotto, bonusLottoNumber);

        assertThat(actualLottoPrize).isEqualTo(expectedLottoPrize);
    }


    @Test
    void getLottoNumbers는_로또번호를_정렬해서_리턴한다() {
        final Lotto lotto = Lotto.from(6, 5, 4, 3, 2, 1);

        final List<Integer> actualLottoNumbers = lotto.getLottoNumbers();

        assertThat(actualLottoNumbers).containsExactly(1, 2, 3, 4, 5, 6);
    }


}

package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
}

package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DuplicateValidatorTest {

    public static Stream<Arguments> bonusNumberDuplicateWithWinningLotto() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(1)),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(2))
        );
    }

    @ParameterizedTest
    @MethodSource("bonusNumberDuplicateWithWinningLotto")
    void validate는_보너스넘버가_당첨번호와_중복되는_경우_예외를_발생시킨다(Lotto winningLotto, LottoNumber bonusLotto) {
        assertThatThrownBy(() -> DuplicateValidator.validate(winningLotto, bonusLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> bonusNumberNotDuplicateWithWinningLotto() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7)),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(8))
        );
    }

    @ParameterizedTest
    @MethodSource("bonusNumberNotDuplicateWithWinningLotto")
    void validate는_보너스넘버가_당첨번호와_중복되지_않는_경우_예외를_발생시키지_않는다(Lotto winningLotto, LottoNumber bonusLotto) {
        assertDoesNotThrow(() -> DuplicateValidator.validate(winningLotto, bonusLotto));
    }


}

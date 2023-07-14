package lotto.view.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoDtoTest {


    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void from메서드는_공백_빈문자열이_오는_경우_예외를_발생시킨다(String rawWinningLottoNumbers) {
        assertThatThrownBy(() -> WinningLottoDto.from(rawWinningLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1:2:3:4:5:6", "1,2:3:4:5:6"})
    void from메서드는_구분자가_올바르지_않은_경우_예외를_발생시킨다(String rawWinningLottoNumbers) {
        assertThatThrownBy(() -> WinningLottoDto.from(rawWinningLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6,7", "1,2,3"})
    void from메서드는_6개의_숫자가_아닌_경우_예외를_발생시킨다(String rawWinningLottoNumbers) {
        assertThatThrownBy(() -> WinningLottoDto.from(rawWinningLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

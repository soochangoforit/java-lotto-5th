package lotto.view.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
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


    @Test
    void from메서드는_모든_유효성_검사가_끝나면_객체를_생성한다() {
        String rawWinningLottoNumbers = "1,2,3,4,5,6";

        WinningLottoDto result = WinningLottoDto.from(rawWinningLottoNumbers);

        assertEquals(WinningLottoDto.class, result.getClass());
    }
}

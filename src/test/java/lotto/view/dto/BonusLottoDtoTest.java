package lotto.view.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusLottoDtoTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void from메서드는_공백_빈문자열이_오는_경우_예외를_발생시킨다(String rawBonusLottoNumber) {
        assertThatThrownBy(() -> BonusLottoDto.from(rawBonusLottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6,7", "1,2,3"})
    void from메서드는_1개의_숫자가_아닌_경우_예외를_발생시킨다(String rawBonusLottoNumber) {
        assertThatThrownBy(() -> BonusLottoDto.from(rawBonusLottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "가"})
    void from메서드는_숫자가_아닌_경우_예외를_발생시킨다(String rawBonusLottoNumber) {
        assertThatThrownBy(() -> BonusLottoDto.from(rawBonusLottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void from메서드는_유효성검사를_모두_통과하는_경우_객체를_생성한다() {
        String rawBonusLottoNumber = "42";

        BonusLottoDto result = BonusLottoDto.from(rawBonusLottoNumber);

        assertEquals(BonusLottoDto.class, result.getClass());
        assertEquals(42, result.getNumber());  // Assuming getNumber() is a method in BonusLottoDto
    }


}

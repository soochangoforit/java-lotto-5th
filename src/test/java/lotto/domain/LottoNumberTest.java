package lotto.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void lottoNumber생성자에는_1이상_45이하의_값만_들어올_수_있다(int number) {
        assertDoesNotThrow(() -> LottoNumber.from(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void lottoNumber생성자에는_범위값을_벗어난_숫자가_들어오면_예외가_발생한다(int number) {
        assertThatThrownBy(() -> LottoNumber.from(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.ONLY_LOTTO_NUMBER_IN_RANGE);
    }

    @Test
    void lottoNumber는_내부_값이_같으면_같은_객체라고_판단한다() {
        LottoNumber actualLottoNumber = LottoNumber.from(1);
        LottoNumber expectedLottoNumber = LottoNumber.from(1);

        assertThat(actualLottoNumber).isEqualTo(expectedLottoNumber);
    }

}

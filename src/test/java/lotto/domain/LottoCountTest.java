package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoCountTest {


    @Test
    void lottoCount는_내부적으로_같은_값을_가지는_같은_객체로_판단한다() {
        LottoCount actualLottoCount = new LottoCount(1);
        LottoCount expectedLottoCount = new LottoCount(1);

        assertThat(actualLottoCount).isEqualTo(expectedLottoCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void lottoCount는_0이하가_되면_예외가_발생한다(int count) {
        assertThatThrownBy(() -> new LottoCount(count))
                .isInstanceOf(IllegalArgumentException.class);
    }

}

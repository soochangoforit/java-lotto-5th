package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;

class LottoCountTest {


    @Test
    void lottoCount는_내부적으로_같은_값을_가지는_같은_객체로_판단한다() {
        LottoCount actualLottoCount = new LottoCount(1);
        LottoCount expectedLottoCount = new LottoCount(1);

        assertThat(actualLottoCount).isEqualTo(expectedLottoCount);
    }

}

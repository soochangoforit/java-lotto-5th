package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class WinningRateTest {

    @Test
    void from는_소숫점_두번째_자리에서_반올림한다() {
        WinningRate rate = WinningRate.from(300.1836f);

        assertThat(rate).isEqualTo(WinningRate.from(300.2f));
    }
}

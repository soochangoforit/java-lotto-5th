package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TicketCountTest {


    @Test
    void lottoCount는_내부적으로_같은_값을_가지는_같은_객체로_판단한다() {
        TicketCount actualTicketCount = new TicketCount(1);
        TicketCount expectedTicketCount = new TicketCount(1);

        assertThat(actualTicketCount).isEqualTo(expectedTicketCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void lottoCount는_0이하가_되면_예외가_발생한다(int count) {
        assertThatThrownBy(() -> new TicketCount(count))
                .isInstanceOf(IllegalArgumentException.class);
    }

}

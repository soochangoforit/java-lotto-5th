package lotto.domain;

import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTicketFactoryTest {


    private static Stream<Arguments> lottoCountAndGenerator() {
        return Stream.of(
                Arguments.of(new TicketCount(1), (NumberGenerator) (a, b, c) -> List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of(new TicketCount(2), (NumberGenerator) (a, b, c) -> List.of(40, 41, 42, 43, 44, 45))
        );
    }

    public static Stream<Arguments> lottoCountAndOverSizeGenerator() {
        return Stream.of(
                Arguments.of(new TicketCount(1), (NumberGenerator) (a, b, c) -> List.of(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(new TicketCount(2), (NumberGenerator) (a, b, c) -> List.of(39, 40, 41, 42, 43, 44, 45))
        );
    }

    public static Stream<Arguments> lottoCountAndDuplicateNumberGenerator() {
        return Stream.of(
                Arguments.of(new TicketCount(1), (NumberGenerator) (a, b, c) -> List.of(1, 2, 3, 4, 5, 5)),
                Arguments.of(new TicketCount(2), (NumberGenerator) (a, b, c) -> List.of(40, 41, 42, 43, 44, 44))
        );
    }

    public static Stream<Arguments> lottoCountAndOverTheNumberRangeGenerator() {
        return Stream.of(
                Arguments.of(new TicketCount(1), (NumberGenerator) (a, b, c) -> List.of(0, 1, 2, 3, 4, 5)),
                Arguments.of(new TicketCount(2), (NumberGenerator) (a, b, c) -> List.of(1, 2, 3, 4, 5, 46))
        );
    }

    @ParameterizedTest
    @MethodSource("lottoCountAndGenerator")
    void createLottoTicket메서드는_사고자하는_로또_개수에_맞게_구성된_로또_티켓을_생성한다(
            TicketCount ticketCount, NumberGenerator generator
    ) {
        LottoTicket lottoTicket = LottoTicketFactory.createLottoTicket(ticketCount, generator);

        Assertions.assertThat(lottoTicket.size()).isEqualTo(ticketCount.getCount());
    }

    @ParameterizedTest
    @MethodSource("lottoCountAndOverSizeGenerator")
    void createLottoTicket메서드는_로또에_들어가는_숫자_개수가_6개를_넘어가면_예외가_발생(
            TicketCount ticketCount, NumberGenerator generator
    ) {
        Assertions.assertThatThrownBy(() -> LottoTicketFactory.createLottoTicket(ticketCount, generator))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("lottoCountAndDuplicateNumberGenerator")
    void createLottoTicket메서드는_로또에_들어가는_숫자가_중복되면_예외가_발생(
            TicketCount ticketCount, NumberGenerator generator
    ) {
        Assertions.assertThatThrownBy(() -> LottoTicketFactory.createLottoTicket(ticketCount, generator))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("lottoCountAndOverTheNumberRangeGenerator")
    void createLottoTicket메서드는_로또에_들어가는_각_숫자가_범위를_벗어나면_예외가_발생(
            TicketCount ticketCount, NumberGenerator generator
    ) {
        Assertions.assertThatThrownBy(() -> LottoTicketFactory.createLottoTicket(ticketCount, generator))
                .isInstanceOf(IllegalArgumentException.class);
    }


}

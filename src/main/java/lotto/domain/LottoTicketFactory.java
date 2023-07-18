package lotto.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.LottoNumber.MAX_NUMBER;
import static lotto.domain.LottoNumber.MIN_NUMBER;

import java.util.stream.Stream;

public final class LottoTicketFactory {

    private LottoTicketFactory() {
    }

    public static LottoTicket createLottoTicket(final TicketCount ticketCount, final NumberGenerator numberGenerator) {
        return Stream.generate(() -> numberGenerator.generateNumbers(MIN_NUMBER, MAX_NUMBER, LOTTO_SIZE))
                .limit(ticketCount.getCount())
                .map(Lotto::from)
                .collect(collectingAndThen(toList(), LottoTicket::new));
    }
}

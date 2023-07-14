package lotto.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.LottoNumber.MAX_NUMBER;
import static lotto.domain.LottoNumber.MIN_NUMBER;

import java.util.stream.Stream;

public class LottoTicketFactory {

    private LottoTicketFactory() {
    }

    public static LottoTicket createLottoTicket(final LottoCount lottoCount, final NumberGenerator generator) {
        return Stream.generate(() -> generator.generateNumbers(MIN_NUMBER, MAX_NUMBER, LOTTO_SIZE))
                .limit(lottoCount.getCount())
                .map(Lotto::new)
                .collect(collectingAndThen(toList(), LottoTicket::new));
    }
}

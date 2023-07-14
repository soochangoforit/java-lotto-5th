package lotto.view.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoTicket;

public class LottoTicketInfoDto {

    private final List<List<Integer>> lottoTicket;

    private final int ticketSize;

    private LottoTicketInfoDto(final List<List<Integer>> lottoTicket, final int ticketSize) {
        this.lottoTicket = lottoTicket;
        this.ticketSize = ticketSize;
    }

    public static LottoTicketInfoDto from(final LottoTicket lottoTicket) {
        List<List<Integer>> lottoTicketInfo = lottoTicket.getLottos().stream()
                .map(Lotto::getNumbers)
                .collect(Collectors.toList());

        return new LottoTicketInfoDto(lottoTicketInfo, lottoTicket.size());
    }

    public int size() {
        return ticketSize;
    }

    public List<List<Integer>> getLottoTicket() {
        return lottoTicket;
    }
}

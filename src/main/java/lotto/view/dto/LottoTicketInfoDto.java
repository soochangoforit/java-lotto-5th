package lotto.view.dto;

import java.util.List;

import lotto.domain.LottoTicket;

public class LottoTicketInfoDto {

    private final List<List<Integer>> lottoTicket;

    private final int ticketSize;

    private LottoTicketInfoDto(final List<List<Integer>> lottoTicket, final int ticketSize) {
        this.lottoTicket = lottoTicket;
        this.ticketSize = ticketSize;
    }

    public static LottoTicketInfoDto from(final LottoTicket lottoTicket) {
        List<List<Integer>> lottoTicketInfo = lottoTicket.getAllLottoNumbers();

        return new LottoTicketInfoDto(lottoTicketInfo, lottoTicket.size());
    }

    public int size() {
        return ticketSize;
    }

    public List<List<Integer>> getLottoTicket() {
        return lottoTicket;
    }
}

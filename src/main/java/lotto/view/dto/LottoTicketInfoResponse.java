package lotto.view.dto;

import java.util.List;

import lotto.domain.LottoTicket;

public class LottoTicketInfoResponse {

    private final List<List<Integer>> lottoTicket;

    private final int ticketSize;

    private LottoTicketInfoResponse(final List<List<Integer>> lottoTicket, final int ticketSize) {
        this.lottoTicket = lottoTicket;
        this.ticketSize = ticketSize;
    }

    public static LottoTicketInfoResponse from(final LottoTicket lottoTicket) {
        List<List<Integer>> lottoTicketInfo = lottoTicket.getAllLottoNumbers();

        return new LottoTicketInfoResponse(lottoTicketInfo, lottoTicket.size());
    }

    public int size() {
        return ticketSize;
    }

    public List<List<Integer>> getLottoTicket() {
        return lottoTicket;
    }
}

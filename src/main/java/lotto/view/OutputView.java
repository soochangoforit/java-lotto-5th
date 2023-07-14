package lotto.view;

import lotto.view.dto.LottoTicketInfoDto;

public enum OutputView {

    INSTANCE;

    private static final String LOTTO_TICKET_SIZE_MESSAGE = "%d개를 구매했습니다.\n";

    public void printLottoTicket(final LottoTicketInfoDto infoDto) {
        System.out.println();
        System.out.printf(LOTTO_TICKET_SIZE_MESSAGE, infoDto.size());
        infoDto.getLottoTicket().forEach(System.out::println);
    }
}

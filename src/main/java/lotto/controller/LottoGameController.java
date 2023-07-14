package lotto.controller;

import lotto.domain.LottoCount;
import lotto.domain.LottoPrice;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketFactory;
import lotto.domain.NumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.InputMoneyRequest;
import lotto.view.dto.LottoTicketInfoDto;

public class LottoGameController {

    private static final InputView inputView = InputView.INSTANCE;
    private static final OutputView outputView = OutputView.INSTANCE;

    private final NumberGenerator numberGenerator;

    public LottoGameController(final NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }


    public void run() {
        InputMoneyRequest moneyRequest = inputView.inputMoney();
        LottoCount lottoCount = LottoPrice.calculateLottoCount(moneyRequest);
        LottoTicket lottoTicket = LottoTicketFactory.createLottoTicket(lottoCount, numberGenerator);
        LottoTicketInfoDto responseDto = LottoTicketInfoDto.from(lottoTicket);


    }
}

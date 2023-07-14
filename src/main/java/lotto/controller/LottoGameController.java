package lotto.controller;

import lotto.domain.DuplicateValidator;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPrice;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketFactory;
import lotto.domain.NumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.BonusLottoDto;
import lotto.view.dto.InputMoneyRequest;
import lotto.view.dto.LottoTicketInfoDto;
import lotto.view.dto.WinningLottoDto;

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
        outputView.printLottoTicket(responseDto);

        WinningLottoDto winningLottoDto = inputView.inputWinningLotto();
        Lotto winningLotto = new Lotto(winningLottoDto.getWinningNumbers());
        BonusLottoDto bonusLottoDto = inputView.inputBonusLotto();
        LottoNumber bonusLotto = new LottoNumber(bonusLottoDto.getBonusNumber());
        DuplicateValidator.validate(winningLotto, bonusLotto);

    }
}

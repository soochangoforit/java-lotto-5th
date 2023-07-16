package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPrice;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketFactory;
import lotto.domain.Money;
import lotto.domain.NumberGenerator;
import lotto.domain.TicketCount;
import lotto.domain.TotalLottoPrize;
import lotto.domain.WinningRate;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.BonusLottoDto;
import lotto.view.dto.LottoTicketInfoDto;
import lotto.view.dto.PlayerMoneyRequest;
import lotto.view.dto.WinningLottoDto;
import lotto.view.dto.WinningStatisticInfo;

public class LottoGameController {

    private static final InputView inputView = InputView.INSTANCE;
    private static final OutputView outputView = OutputView.INSTANCE;

    private final NumberGenerator numberGenerator;

    public LottoGameController(final NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }


    public void run() {
        PlayerMoneyRequest playerMoneyRequest = inputView.scanPlayerMoney();
        Money playerMoney = Money.from(playerMoneyRequest.getPlayerMoney());
        TicketCount ticketCount = LottoPrice.calculateLottoCount(playerMoney);
        LottoTicket lottoTicket = LottoTicketFactory.createLottoTicket(ticketCount, numberGenerator);
        LottoTicketInfoDto responseDto = LottoTicketInfoDto.from(lottoTicket);
        outputView.printLottoTicket(responseDto);

        WinningLottoDto winningLottoDto = inputView.scanWinningLotto();
        Lotto winningLotto = Lotto.from(winningLottoDto.getWinningLotto());
        BonusLottoDto bonusLottoDto = inputView.scanBonusLotto();
        LottoNumber bonusLotto = LottoNumber.from(bonusLottoDto.getBonusNumber());
        winningLotto.validateDuplicate(bonusLotto);

        TotalLottoPrize totalLottoPrize = lottoTicket.getTotalPrize(winningLotto, bonusLotto);
        WinningRate winningRate = totalLottoPrize.calculateWinningRate(playerMoney);
        WinningStatisticInfo resultDto = WinningStatisticInfo.from(totalLottoPrize.getLottoPrizes(), winningRate.getRate());
        outputView.printWinningStatisticResult(resultDto);
    }
}

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
import lotto.domain.WinningLottoInfo;
import lotto.domain.WinningRate;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.BonusLottoRequest;
import lotto.view.dto.LottoTicketInfoResponse;
import lotto.view.dto.PlayerMoneyRequest;
import lotto.view.dto.WinningLottoRequest;
import lotto.view.dto.WinningStatisticResponse;

public class LottoGameController {

    private static final InputView inputView = InputView.INSTANCE;
    private static final OutputView outputView = OutputView.INSTANCE;

    private final NumberGenerator numberGenerator;

    public LottoGameController(final NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }


    public void run() {
        try {
            Money playerMoney = getPlayerMoney();
            LottoTicket lottoTicket = getLottoTicket(playerMoney);
            WinningLottoInfo winningLottoInfo = getWinningLottoInfo();
            TotalLottoPrize totalLottoPrize = getTotalPrize(lottoTicket, winningLottoInfo);
            printWinningStatistics(playerMoney, totalLottoPrize);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private WinningLottoInfo getWinningLottoInfo() {
        Lotto winningLotto = getWinningLotto();
        LottoNumber bonusLotto = getBonusLotto();
        return WinningLottoInfo.from(winningLotto, bonusLotto);
    }

    private static TotalLottoPrize getTotalPrize(final LottoTicket lottoTicket, final WinningLottoInfo winningLottoInfo) {
        return lottoTicket.getTotalPrize(winningLottoInfo.getWinningLotto(), winningLottoInfo.getBonusNumber());
    }


    private Money getPlayerMoney() {
        PlayerMoneyRequest playerMoneyRequest = inputView.scanPlayerMoney();
        return Money.from(playerMoneyRequest.getPlayerMoney());
    }

    private LottoTicket getLottoTicket(Money playerMoney) {
        TicketCount ticketCount = LottoPrice.calculateLottoCount(playerMoney);
        LottoTicket lottoTicket = LottoTicketFactory.createLottoTicket(ticketCount, numberGenerator);
        LottoTicketInfoResponse responseDto = LottoTicketInfoResponse.from(lottoTicket);
        outputView.printLottoTicket(responseDto);
        return lottoTicket;
    }

    private Lotto getWinningLotto() {
        WinningLottoRequest winningLottoRequest = inputView.scanWinningLotto();
        return Lotto.from(winningLottoRequest.getWinningLotto());
    }

    private LottoNumber getBonusLotto() {
        BonusLottoRequest bonusLottoRequest = inputView.scanBonusLotto();
        return LottoNumber.from(bonusLottoRequest.getBonusNumber());
    }

    private void printWinningStatistics(Money playerMoney, TotalLottoPrize totalLottoPrize) {
        WinningRate winningRate = totalLottoPrize.calculateWinningRate(playerMoney);
        WinningStatisticResponse resultDto = WinningStatisticResponse.from(totalLottoPrize.getLottoPrizes(), winningRate.getRate());
        outputView.printWinningStatisticResult(resultDto);
    }
}

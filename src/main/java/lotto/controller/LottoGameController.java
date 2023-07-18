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
        try {
            Money playerMoney = getPlayerMoney();
            LottoTicket lottoTicket = getLottoTicket(playerMoney);

            Lotto winningLotto = getWinningLotto();
            LottoNumber bonusLotto = getBonusLotto();
            validateDuplicateLottoNumber(winningLotto, bonusLotto);

            TotalLottoPrize totalLottoPrize = getTotalPrize(lottoTicket, winningLotto, bonusLotto);
            printWinningStatistics(playerMoney, totalLottoPrize);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private static TotalLottoPrize getTotalPrize(final LottoTicket lottoTicket, final Lotto winningLotto, final LottoNumber bonusLotto) {
        return lottoTicket.getTotalPrize(winningLotto, bonusLotto);
    }

    private void validateDuplicateLottoNumber(final Lotto winningLotto, final LottoNumber bonusLotto) {
        winningLotto.validateDuplicate(bonusLotto);
    }

    private Money getPlayerMoney() {
        PlayerMoneyRequest playerMoneyRequest = inputView.scanPlayerMoney();
        return Money.from(playerMoneyRequest.getPlayerMoney());
    }

    private LottoTicket getLottoTicket(Money playerMoney) {
        TicketCount ticketCount = LottoPrice.calculateLottoCount(playerMoney);
        LottoTicket lottoTicket = LottoTicketFactory.createLottoTicket(ticketCount, numberGenerator);
        LottoTicketInfoDto responseDto = LottoTicketInfoDto.from(lottoTicket);
        outputView.printLottoTicket(responseDto);
        return lottoTicket;
    }

    private Lotto getWinningLotto() {
        WinningLottoDto winningLottoDto = inputView.scanWinningLotto();
        return Lotto.from(winningLottoDto.getWinningLotto());
    }

    private LottoNumber getBonusLotto() {
        BonusLottoDto bonusLottoDto = inputView.scanBonusLotto();
        return LottoNumber.from(bonusLottoDto.getBonusNumber());
    }

    private void printWinningStatistics(Money playerMoney, TotalLottoPrize totalLottoPrize) {
        WinningRate winningRate = totalLottoPrize.calculateWinningRate(playerMoney);
        WinningStatisticInfo resultDto = WinningStatisticInfo.from(totalLottoPrize.getLottoPrizes(), winningRate.getRate());
        outputView.printWinningStatisticResult(resultDto);
    }
}

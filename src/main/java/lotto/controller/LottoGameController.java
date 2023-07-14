package lotto.controller;

import lotto.domain.LottoCount;
import lotto.domain.LottoPrice;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.InputMoneyRequest;

public class LottoGameController {

    private static final InputView inputView = InputView.INSTANCE;
    private static final OutputView outputView = OutputView.INSTANCE;


    public void run() {
        InputMoneyRequest moneyRequest = inputView.inputMoney();
        LottoCount lottoCount = LottoPrice.calculateLottoCount(moneyRequest);

    }
}

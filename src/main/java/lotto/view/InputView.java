package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.view.dto.BonusLottoRequest;
import lotto.view.dto.PlayerMoneyRequest;
import lotto.view.dto.WinningLottoRequest;

public enum InputView {

    INSTANCE;

    private static final String SCAN_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String SCAN_WINNING_LOTTO_MESSAGE = "당첨 번호를 입력해 주세요.";

    private static final String SCAN_BONUS_LOTTO_MESSAGE = "보너스 볼을 입력해 주세요.";

    public PlayerMoneyRequest scanPlayerMoney() {
        System.out.println(SCAN_MONEY_MESSAGE);
        String playerMoney = Console.readLine();
        return PlayerMoneyRequest.from(playerMoney);
    }

    public WinningLottoRequest scanWinningLotto() {
        System.out.println(SCAN_WINNING_LOTTO_MESSAGE);
        String winningLottoNumbers = Console.readLine();
        return WinningLottoRequest.from(winningLottoNumbers);
    }

    public BonusLottoRequest scanBonusLotto() {
        System.out.println(SCAN_BONUS_LOTTO_MESSAGE);
        String rawBonusLottoNumber = Console.readLine();
        return BonusLottoRequest.from(rawBonusLottoNumber);
    }
}

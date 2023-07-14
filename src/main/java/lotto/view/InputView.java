package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.view.dto.InputMoneyRequest;
import lotto.view.dto.WinningLottoDto;

public enum InputView {

    INSTANCE;

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";

    public InputMoneyRequest inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String lottoMoney = Console.readLine();
        return new InputMoneyRequest(lottoMoney);
    }

    public WinningLottoDto inputWinningLotto() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String rawWinningLottoNumbers = Console.readLine();
        return WinningLottoDto.from(rawWinningLottoNumbers);
    }
}

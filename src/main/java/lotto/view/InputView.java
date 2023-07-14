package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.view.dto.InputMoneyRequest;

public enum InputView {

    INSTANCE;

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";

    public InputMoneyRequest inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String lottoMoney = Console.readLine();
        return new InputMoneyRequest(lottoMoney);
    }
}

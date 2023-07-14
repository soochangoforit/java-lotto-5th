package lotto.domain;

import lotto.view.dto.InputMoneyRequest;

public class LottoPrice {

    private static final int LOTTO_PRICE = 1000;

    public static LottoCount calculateLottoCount(InputMoneyRequest playerMoney) {
        validate(playerMoney);
        int ticketCount = playerMoney.divide(LOTTO_PRICE);
        return new LottoCount(ticketCount);
    }

    private static void validate(InputMoneyRequest playerMoney) {
        if (!isDivided(playerMoney)) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위로 입력 가능합니다.");
        }
    }

    private static boolean isDivided(final InputMoneyRequest playerMoney) {
        return playerMoney.divide(LOTTO_PRICE) == 0;
    }
}

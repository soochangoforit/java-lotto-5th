package lotto.domain;

import static lotto.domain.ErrorMessage.ONLY_CAN_DIVIDE_BY_LOTTO_PRICE;

import lotto.view.dto.InputMoneyRequest;

public class LottoPrice {

    private static final int LOTTO_PRICE = 1000;

    public static LottoCount calculateLottoCount(InputMoneyRequest playerMoney) {
        validate(playerMoney);
        long ticketCount = playerMoney.calculateLottoCount(LOTTO_PRICE);
        return new LottoCount(ticketCount);
    }

    private static void validate(InputMoneyRequest playerMoney) {
        if (!isDivided(playerMoney)) {
            throw new IllegalArgumentException(ONLY_CAN_DIVIDE_BY_LOTTO_PRICE);
        }
    }

    private static boolean isDivided(final InputMoneyRequest playerMoney) {
        return playerMoney.isDivided(LOTTO_PRICE);
    }
}

package lotto.domain;

import static lotto.domain.ErrorMessage.ONLY_CAN_DIVIDE_BY_LOTTO_PRICE;

public class LottoPrice {

    private static final int LOTTO_PRICE = 1000;

    public static LottoCount calculateLottoCount(Money playerMoney) {
        validate(playerMoney);
        long ticketCount = playerMoney.calculateLottoCount(LOTTO_PRICE);
        return new LottoCount(ticketCount);
    }

    private static void validate(Money playerMoney) {
        if (!isDivided(playerMoney)) {
            throw new IllegalArgumentException(ONLY_CAN_DIVIDE_BY_LOTTO_PRICE);
        }
    }

    private static boolean isDivided(final Money playerMoney) {
        return playerMoney.isDivided(LOTTO_PRICE);
    }
}

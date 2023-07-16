package lotto.domain;

import static lotto.domain.ErrorMessage.CAN_NOT_UNDER_ZERO;

public class Money {

    private static final long ZERO = 0L;

    private final long playerMoney;

    public Money(final long playerMoney) {
        validate(playerMoney);
        this.playerMoney = playerMoney;
    }

    public static Money from(final long playerMoney) {
        return new Money(playerMoney);
    }

    private void validate(final long money) {
        if (isUnderZero(money)) {
            throw new IllegalArgumentException(CAN_NOT_UNDER_ZERO);
        }
    }

    private static boolean isUnderZero(final long money) {
        return money <= ZERO;
    }


    public boolean isDivided(final int lottoPrice) {
        return playerMoney % lottoPrice == ZERO;
    }

    public long calculateLottoCount(final int lottoPrice) {
        return playerMoney / lottoPrice;
    }

    public long getAmount() {
        return playerMoney;
    }
}

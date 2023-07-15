package lotto.domain;

import static lotto.domain.ErrorMessage.CAN_NOT_UNDER_ZERO;

public class Money {

    private static final long ZERO = 0L;

    private final long money;

    private Money(final long money) {
        validate(money);
        this.money = money;
    }

    public static Money from(final String playerMoney) {
        return new Money(Long.parseLong(playerMoney));
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
        return money % lottoPrice == ZERO;
    }

    public long calculateLottoCount(final int lottoPrice) {
        return money / lottoPrice;
    }
}

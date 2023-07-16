package lotto.domain;

import java.util.stream.Stream;

public enum LottoPrize {

    NOT_MATCHED(0, false, 0),
    FIFTH_PRIZE(3, false, 5000),
    FOURTH_PRIZE(4, false, 50000),
    THIRD_PRIZE(5, false, 1500000),
    SECOND_PRIZE(5, true, 30000000),
    FIRST_PRIZE(6, false, 2000000000);

    private final int matchCount;

    private final boolean hasBonus;

    private final int amount;

    LottoPrize(final int matchCount, final boolean hasBonus, final int amount) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.amount = amount;
    }


    public static LottoPrize getLottoPrize(final int matchCount, final boolean hasBonus) {
        if (matchCount == SECOND_PRIZE.matchCount && hasBonus) {
            return SECOND_PRIZE;
        }
        return Stream.of(values())
                .filter(prize -> prize.matchCount == matchCount)
                .findFirst()
                .orElse(NOT_MATCHED);
    }

    public int getAmount() {
        return amount;
    }

    public long multiply(final long count) {
        return amount * count;
    }
}

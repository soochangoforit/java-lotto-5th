package lotto.domain;

import java.util.stream.Stream;

public enum LottoPrize {

    _NOT_MATCHED(0, false, 0),
    _5TH_PRIZE(3, false, 5000),
    _4TH_PRIZE(4, false, 50000),
    _3RD_PRIZE(5, false, 1500000),
    _2ND_PRIZE(5, true, 30000000),
    _1ST_PRIZE(6, false, 2000000000);

    private final int matchCount;

    private final boolean hasBonus;

    private final int prize;

    LottoPrize(final int matchCount, final boolean hasBonus, final int prize) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.prize = prize;
    }


    public static LottoPrize getPrizeFrom(final int matchCount, final boolean hasBonus) {
        if (matchCount == getSecondPrizeMatchCount() && hasBonus) {
            return _2ND_PRIZE;
        }
        return Stream.of(values())
                .filter(prize -> prize.matchCount == matchCount)
                .findFirst()
                .orElse(_NOT_MATCHED);
    }

    private static int getSecondPrizeMatchCount() {
        return _2ND_PRIZE.matchCount;
    }
}

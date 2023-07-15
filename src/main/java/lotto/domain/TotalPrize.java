package lotto.domain;

import java.util.Map;

public class TotalPrize {

    private final Map<LottoPrize, Long> totalPrize;

    public TotalPrize(final Map<LottoPrize, Long> totalPrize) {
        this.totalPrize = totalPrize;
    }
}

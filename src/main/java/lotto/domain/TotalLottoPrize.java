package lotto.domain;

import java.util.Map;
import java.util.Objects;

public class TotalLottoPrize {

    private final Map<LottoPrize, Long> lottoPrizes;

    public TotalLottoPrize(final Map<LottoPrize, Long> lottoPrizes) {
        this.lottoPrizes = lottoPrizes;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TotalLottoPrize that = (TotalLottoPrize) o;
        return Objects.equals(lottoPrizes, that.lottoPrizes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoPrizes);
    }
}

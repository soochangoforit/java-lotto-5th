package lotto.domain;

import java.util.Map;
import java.util.Objects;

public class TotalPrize {

    private final Map<LottoPrize, Long> totalPrize;

    public TotalPrize(final Map<LottoPrize, Long> totalPrize) {
        this.totalPrize = totalPrize;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TotalPrize that = (TotalPrize) o;
        return Objects.equals(totalPrize, that.totalPrize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPrize);
    }
}

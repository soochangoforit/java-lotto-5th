package lotto.domain;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class TotalLottoPrize {

    private final Map<LottoPrize, Long> lottoPrizes;

    private TotalLottoPrize(final Map<LottoPrize, Long> lottoPrizes) {
        this.lottoPrizes = lottoPrizes;
    }

    public static TotalLottoPrize from(final Map<LottoPrize, Long> lottoPrizes) {
        return new TotalLottoPrize(lottoPrizes);
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

    public WinningRate calculateWinningRate(final Money playerMoney) {
        long totalPrizeMoney = lottoPrizes.entrySet().stream()
                .mapToLong(entry -> entry.getKey().multiply(entry.getValue()))
                .sum();

        float winningRate = ((float) totalPrizeMoney / playerMoney.getAmount()) * 100;

        return WinningRate.from(winningRate);
    }


    public Map<LottoPrize, Long> getLottoPrizes() {
        return new EnumMap<>(lottoPrizes);
    }
}

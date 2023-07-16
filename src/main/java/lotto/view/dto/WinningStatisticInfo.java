package lotto.view.dto;

import java.util.Map;

import lotto.domain.LottoPrize;

public class WinningStatisticInfo {

    private final Map<LottoPrize, Long> totalLottoPrizes;
    private final float winningRate;

    private WinningStatisticInfo(final Map<LottoPrize, Long> totalLottoPrizes, final float winningRate) {
        this.totalLottoPrizes = totalLottoPrizes;
        this.winningRate = winningRate;
    }

    public static WinningStatisticInfo from(final Map<LottoPrize, Long> totalLottoPrizes, final float winningRate) {
        return new WinningStatisticInfo(totalLottoPrizes, winningRate);
    }

    public Map<LottoPrize, Long> getLottoPrizes() {
        return totalLottoPrizes;
    }

    public float getWinningRate() {
        return winningRate;
    }

    public Long getCount(final LottoPrize lottoPrize) {
        return totalLottoPrizes.getOrDefault(lottoPrize, 0L);
    }
}

package lotto.view.dto;

import java.util.Map;

import lotto.domain.LottoPrize;

public class WinningStatisticResponse {

    private final Map<LottoPrize, Long> totalLottoPrizes;
    private final float winningRate;

    private WinningStatisticResponse(final Map<LottoPrize, Long> totalLottoPrizes, final float winningRate) {
        this.totalLottoPrizes = totalLottoPrizes;
        this.winningRate = winningRate;
    }

    public static WinningStatisticResponse from(final Map<LottoPrize, Long> totalLottoPrizes, final float winningRate) {
        return new WinningStatisticResponse(totalLottoPrizes, winningRate);
    }


    public float getWinningRate() {
        return winningRate;
    }

    public Long getCount(final LottoPrize lottoPrize) {
        return totalLottoPrizes.getOrDefault(lottoPrize, 0L);
    }
}

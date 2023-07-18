package lotto.domain;

import java.util.Objects;

public class WinningRate {

    private final float rate;

    private WinningRate(float rate) {
        this.rate = rate;
    }

    public static WinningRate from(float rate) {
        float roundedRate = roundTo(rate);
        return new WinningRate(roundedRate);
    }

    private static float roundTo(final float rate) {
        return Math.round(rate * 10) / 10f;
    }

    public float getRate() {
        return rate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WinningRate that = (WinningRate) o;
        return Float.compare(that.rate, rate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate);
    }
}

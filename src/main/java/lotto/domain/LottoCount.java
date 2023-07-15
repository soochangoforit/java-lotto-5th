package lotto.domain;

import static lotto.domain.ErrorMessage.CAN_NOT_UNDER_ZERO;

import java.util.Objects;

public class LottoCount {

    private final long count;

    public LottoCount(long count) {
        validate(count);
        this.count = count;
    }

    private void validate(long count) {
        if (isUnderZero(count)) {
            throw new IllegalArgumentException(CAN_NOT_UNDER_ZERO);
        }
    }

    private static boolean isUnderZero(final long count) {
        return count <= 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoCount that = (LottoCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    public long getCount() {
        return count;
    }

}

package lotto.domain;

import static lotto.domain.ErrorMessage.CAN_NOT_UNDER_ZERO;

import java.util.Objects;

public class TicketCount {

    private final long count;

    public TicketCount(long count) {
        validate(count);
        this.count = count;
    }

    private void validate(long ticketCount) {
        if (isUnderZero(ticketCount)) {
            throw new IllegalArgumentException(CAN_NOT_UNDER_ZERO);
        }
    }

    private static boolean isUnderZero(final long ticketCount) {
        return ticketCount <= 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TicketCount that = (TicketCount) o;
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

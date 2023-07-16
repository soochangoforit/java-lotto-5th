package lotto.domain;

import static lotto.domain.ErrorMessage.ONLY_LOTTO_NUMBER_IN_RANGE;

import java.util.Objects;

public class LottoNumber {

    public static final int MIN_NUMBER = 1;

    public static final int MAX_NUMBER = 45;

    private final int number;

    private LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    public static LottoNumber from(int number) {
        return new LottoNumber(number);
    }

    public int getNumber() {
        return number;
    }

    private void validate(int number) {
        if (!isInRange(number)) {
            throw new IllegalArgumentException(ONLY_LOTTO_NUMBER_IN_RANGE);
        }
    }

    private static boolean isInRange(final int number) {
        return number >= MIN_NUMBER && number <= MAX_NUMBER;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}

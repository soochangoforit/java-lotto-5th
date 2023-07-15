package lotto.domain;

import static lotto.domain.ErrorMessage.BONUS_BALL_IS_DUPLICATED;

public class DuplicateValidator {

    private DuplicateValidator() {
    }

    public static void validate(final Lotto winningLotto, final LottoNumber bonusLotto) {
        if (winningLotto.hasSameNumberWith(bonusLotto)) {
            throw new IllegalArgumentException(BONUS_BALL_IS_DUPLICATED);
        }
    }
}

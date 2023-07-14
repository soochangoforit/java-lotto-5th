package lotto.domain;

public class DuplicateValidator {

    private DuplicateValidator() {
    }

    public static void validate(final Lotto winningLotto, final LottoNumber bonusLotto) {
        if (winningLotto.hasSameNumber(bonusLotto)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }
}

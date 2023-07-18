package lotto.domain;

public class WinningLottoInfo {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    private WinningLottoInfo(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validateDuplicateLottoNumber(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLottoInfo from(final Lotto winningLotto, final LottoNumber bonusNumber) {
        return new WinningLottoInfo(winningLotto, bonusNumber);
    }

    private void validateDuplicateLottoNumber(final Lotto winningLotto, final LottoNumber bonusNumber) {
        winningLotto.validateDuplicate(bonusNumber);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}

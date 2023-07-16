package lotto.domain;

import static java.util.stream.Collectors.toList;
import static lotto.domain.ErrorMessage.BONUS_BALL_IS_DUPLICATED;
import static lotto.domain.ErrorMessage.OVER_LOTTO_SIZE;

import java.util.Arrays;
import java.util.List;

public class Lotto {

    public static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<Integer> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = convertFrom(lottoNumbers);
    }

    public static Lotto from(List<Integer> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public static Lotto from(int... lottoNumbers) {
        return new Lotto(Arrays.stream(lottoNumbers)
                .boxed()
                .collect(toList()));
    }

    private List<LottoNumber> convertFrom(final List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::from)
                .collect(toList());
    }

    private void validate(List<Integer> lottoNumbers) {
        if (!isRightSize(lottoNumbers) || !isUnique(lottoNumbers)) {
            throw new IllegalArgumentException(OVER_LOTTO_SIZE);
        }
    }

    private boolean isRightSize(final List<Integer> lottoNumbers) {
        return lottoNumbers.size() == LOTTO_SIZE;
    }

    private boolean isUnique(final List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .distinct()
                .count() == LOTTO_SIZE;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .collect(toList());
    }


    public boolean containsNumber(final LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public LottoPrize calculateLottoPrize(final Lotto winningLotto, final LottoNumber bonusLottoNumber) {
        int matchCount = countMatch(winningLotto);
        boolean containsBonusNumber = containsNumber(bonusLottoNumber);
        return LottoPrize.getLottoPrize(matchCount, containsBonusNumber);
    }

    private int countMatch(final Lotto winningLotto) {
        return (int) lottoNumbers.stream()
                .filter(winningLotto::containsNumber)
                .count();
    }

    public void validateDuplicate(final LottoNumber bonusLotto) {
        if (containsNumber(bonusLotto)) {
            throw new IllegalArgumentException(BONUS_BALL_IS_DUPLICATED);
        }
    }
}

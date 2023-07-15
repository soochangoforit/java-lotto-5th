package lotto.domain;

import static java.util.stream.Collectors.toList;
import static lotto.domain.ErrorMessage.OVER_LOTTO_SIZE;

import java.util.List;

public class Lotto {

    public static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = convertFrom(numbers);
    }

    private List<LottoNumber> convertFrom(final List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(toList());
    }

    private void validate(List<Integer> numbers) {
        if (!isRightSize(numbers) || !isUnique(numbers)) {
            throw new IllegalArgumentException(OVER_LOTTO_SIZE);
        }
    }

    private boolean isRightSize(final List<Integer> numbers) {
        return numbers.size() == LOTTO_SIZE;
    }

    private boolean isUnique(final List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count() == LOTTO_SIZE;
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .collect(toList());
    }


    public boolean hasSameNumberWith(final LottoNumber bonusLotto) {
        return numbers.contains(bonusLotto);
    }

    public LottoPrize calculateResult(final Lotto winningLotto, final LottoNumber bonusLotto) {
        int matchCount = countMatch(winningLotto);
        boolean hasBonus = hasSameNumberWith(bonusLotto);
        return LottoPrize.getPrizeFrom(matchCount, hasBonus);
    }

    private int countMatch(final Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::hasSameNumberWith)
                .count();
    }
}

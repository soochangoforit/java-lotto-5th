package lotto.domain;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public List<Integer> generateNumbers(final int min, final int max, final int size) {
        return Randoms.pickUniqueNumbersInRange(min, max, size);
    }
}

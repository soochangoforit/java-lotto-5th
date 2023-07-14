package lotto.domain;

import java.util.List;

public interface NumberGenerator {

    List<Integer> generateNumbers(int min, int max, int size);
}

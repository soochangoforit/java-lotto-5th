package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class RandomNumberGeneratorTest {

    private final NumberGenerator numberGenerator = new RandomNumberGenerator();

    @Test
    void generateNumbers는_숫자_사이의_값만_생성한다() {
        List<Integer> numbers = numberGenerator.generateNumbers(1, 45, 6);

        numbers.forEach(number -> assertThat(number).isBetween(1, 45));
    }

    @Test
    void generateNumbers는_중복되지_않은_값만_생성한다() {
        List<Integer> numbers = numberGenerator.generateNumbers(1, 45, 6);

        assertThat(numbers).doesNotHaveDuplicates();
    }

    @Test
    void generateNumbers는_입력받은_크기만큼_값을_생성한다() {
        List<Integer> numbers = numberGenerator.generateNumbers(1, 45, 6);

        assertThat(numbers).hasSize(6);
    }
}

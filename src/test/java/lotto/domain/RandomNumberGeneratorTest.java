package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RandomNumberGeneratorTest {

    private final NumberGenerator numberGenerator = new RandomNumberGenerator();

    public static Stream<Arguments> randomNumberGeneratorTestParameters() {
        return Stream.of(
                Arguments.of(1, 45, 6),
                Arguments.of(1, 45, 7),
                Arguments.of(1, 45, 8),
                Arguments.of(1, 45, 9),
                Arguments.of(1, 45, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("randomNumberGeneratorTestParameters")
    void generateNumbers는_숫자_사이의_값만_생성한다(int min, int max, int size) {
        List<Integer> numbers = numberGenerator.generateNumbers(min, max, size);

        numbers.forEach(number -> assertThat(number).isBetween(min, max));
    }

    @ParameterizedTest
    @MethodSource("randomNumberGeneratorTestParameters")
    void generateNumbers는_중복되지_않은_값만_생성한다(int min, int max, int size) {
        List<Integer> numbers = numberGenerator.generateNumbers(min, max, size);

        assertThat(numbers).doesNotHaveDuplicates();
    }

    @ParameterizedTest
    @MethodSource("randomNumberGeneratorTestParameters")
    void generateNumbers는_입력받은_크기만큼_값을_생성한다(int min, int max, int size) {
        List<Integer> numbers = numberGenerator.generateNumbers(min, max, size);

        assertThat(numbers).hasSize(size);
    }
}

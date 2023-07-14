package lotto.view.dto;

import static java.util.stream.Collectors.toList;
import static lotto.domain.ErrorMessage.IS_NOT_BLANK;
import static lotto.domain.ErrorMessage.IS_NOT_CONSIST_OF_NUMBER;
import static lotto.domain.ErrorMessage.OVER_LOTTO_SIZE;
import static lotto.domain.Lotto.LOTTO_SIZE;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WinningLottoDto {

    private static final String WINNING_LOTTO_DELIMITER = ",";
    private static final Pattern numberPattern = Pattern.compile("^[0-9]*$");

    private final List<Integer> winningLotto;

    private WinningLottoDto(final List<Integer> winningLotto) {
        this.winningLotto = winningLotto;
    }

    public static WinningLottoDto from(String rawWinningLottoNumbers) {
        validate(rawWinningLottoNumbers);
        List<Integer> winningNumbers = covertFrom(rawWinningLottoNumbers);
        return new WinningLottoDto(winningNumbers);
    }

    private static List<Integer> covertFrom(final String rawWinningLottoNumbers) {
        return Stream.of(rawWinningLottoNumbers.split(WINNING_LOTTO_DELIMITER))
                .map(Integer::parseInt)
                .collect(toList());
    }

    private static void validate(String rawWinningLottoNumbers) {
        if (rawWinningLottoNumbers.isBlank()) {
            throw new IllegalArgumentException(IS_NOT_BLANK);
        }
        if (isNotLottoSize(rawWinningLottoNumbers)) {
            throw new IllegalArgumentException(OVER_LOTTO_SIZE);
        }
        if (isNotConsistOfNumber(rawWinningLottoNumbers)) {
            throw new IllegalArgumentException(IS_NOT_CONSIST_OF_NUMBER);
        }
    }

    private static boolean isNotConsistOfNumber(final String rawWinningLottoNumbers) {
        return Stream.of(rawWinningLottoNumbers.split(WINNING_LOTTO_DELIMITER))
                .anyMatch(number -> !numberPattern.matcher(number).matches());
    }

    private static boolean isNotLottoSize(final String rawWinningLottoNumbers) {
        String[] splitRawWinningNumbers = rawWinningLottoNumbers.split(WINNING_LOTTO_DELIMITER);
        return splitRawWinningNumbers.length != LOTTO_SIZE;
    }


    public List<Integer> getWinningNumbers() {
        return winningLotto;
    }
}

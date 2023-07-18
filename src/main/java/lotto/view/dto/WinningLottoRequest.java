package lotto.view.dto;

import static java.util.stream.Collectors.toList;
import static lotto.domain.ErrorMessage.IS_NOT_BLANK;
import static lotto.domain.ErrorMessage.IS_NOT_CONSIST_OF_NUMBER;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WinningLottoRequest {

    private static final String WINNING_LOTTO_DELIMITER = ",";
    private static final Pattern numberPattern = Pattern.compile("^[0-9]*$");

    private final List<Integer> winningLotto;

    private WinningLottoRequest(final List<Integer> winningLotto) {
        this.winningLotto = winningLotto;
    }

    public static WinningLottoRequest from(String rawWinningLottoNumbers) {
        validate(rawWinningLottoNumbers);
        List<Integer> winningNumbers = covertFrom(rawWinningLottoNumbers);
        return new WinningLottoRequest(winningNumbers);
    }

    private static List<Integer> covertFrom(final String rawWinningLottoNumbers) {
        return Stream.of(rawWinningLottoNumbers.split(WINNING_LOTTO_DELIMITER))
                .map(Integer::parseInt)
                .collect(toList());
    }

    private static void validate(String winningLottoNumbers) {
        if (winningLottoNumbers.isBlank()) {
            throw new IllegalArgumentException(IS_NOT_BLANK);
        }

        if (!isNumber(winningLottoNumbers)) {
            throw new IllegalArgumentException(IS_NOT_CONSIST_OF_NUMBER);
        }
    }

    private static boolean isNumber(final String rawWinningLottoNumbers) {
        return Stream.of(rawWinningLottoNumbers.split(WINNING_LOTTO_DELIMITER))
                .anyMatch(number -> numberPattern.matcher(number).matches());
    }


    public List<Integer> getWinningLotto() {
        return winningLotto;
    }
}

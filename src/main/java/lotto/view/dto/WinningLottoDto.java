package lotto.view.dto;

import static java.util.stream.Collectors.toList;
import static lotto.domain.ErrorMessage.OVER_LOTTO_SIZE;
import static lotto.domain.ErrorMessage.WINNING_LOTTO_NOT_BLANK;
import static lotto.domain.Lotto.LOTTO_SIZE;

import java.util.List;
import java.util.stream.Stream;

public class WinningLottoDto {

    private static final String WINNING_LOTTO_DELIMITER = ",";

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
            throw new IllegalArgumentException(WINNING_LOTTO_NOT_BLANK);
        }
        if (isNotLottoSize(rawWinningLottoNumbers)) {
            throw new IllegalArgumentException(OVER_LOTTO_SIZE);
        }
    }

    private static boolean isNotLottoSize(final String rawWinningLottoNumbers) {
        String[] splitRawWinningNumbers = rawWinningLottoNumbers.split(WINNING_LOTTO_DELIMITER);
        return splitRawWinningNumbers.length != LOTTO_SIZE;
    }


}

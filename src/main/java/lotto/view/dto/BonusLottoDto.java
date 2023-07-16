package lotto.view.dto;

import static lotto.domain.ErrorMessage.IS_NOT_BLANK;
import static lotto.domain.ErrorMessage.IS_NOT_CONSIST_OF_NUMBER;
import static lotto.domain.ErrorMessage.IS_NOT_ONE_BONUS_BALL;

import java.util.regex.Pattern;

public class BonusLottoDto {

    private static final int BONUS_LOTTO_SIZE = 1;
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    private final int bonusNumber;

    private BonusLottoDto(final int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public static BonusLottoDto from(final String rawBonusLottoNumber) {
        validate(rawBonusLottoNumber);
        return new BonusLottoDto(Integer.parseInt(rawBonusLottoNumber));
    }

    private static void validate(final String bonusLottoNumber) {
        if (bonusLottoNumber.isBlank()) {
            throw new IllegalArgumentException(IS_NOT_BLANK);
        }

        if (!hasOneNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException(IS_NOT_ONE_BONUS_BALL);
        }

        if (isNotConsistOfNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException(IS_NOT_CONSIST_OF_NUMBER);
        }
    }

    private static boolean hasOneNumber(final String rawBonusLottoNumber) {
        return rawBonusLottoNumber.length() == BONUS_LOTTO_SIZE;
    }

    private static boolean isNotConsistOfNumber(final String rawBonusLottoNumber) {
        return !NUMBER_PATTERN.matcher(rawBonusLottoNumber).matches();
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}

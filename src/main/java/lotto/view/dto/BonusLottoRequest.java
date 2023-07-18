package lotto.view.dto;

import static lotto.domain.ErrorMessage.IS_NOT_BLANK;
import static lotto.domain.ErrorMessage.IS_NOT_CONSIST_OF_NUMBER;

import java.util.regex.Pattern;

public class BonusLottoRequest {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    private final int bonusNumber;

    private BonusLottoRequest(final int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public static BonusLottoRequest from(final String rawBonusLottoNumber) {
        validate(rawBonusLottoNumber);
        return new BonusLottoRequest(Integer.parseInt(rawBonusLottoNumber));
    }

    private static void validate(final String bonusLottoNumber) {
        if (bonusLottoNumber.isBlank()) {
            throw new IllegalArgumentException(IS_NOT_BLANK);
        }

        if (isNotConsistOfNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException(IS_NOT_CONSIST_OF_NUMBER);
        }
    }

    private static boolean isNotConsistOfNumber(final String rawBonusLottoNumber) {
        return !NUMBER_PATTERN.matcher(rawBonusLottoNumber).matches();
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

}

package lotto.view.dto;

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

    private static void validate(final String rawBonusLottoNumber) {
        if (rawBonusLottoNumber.isBlank()) {
            throw new IllegalArgumentException("빈문자열과 공백은 입력할 수 없습니다.");
        }

        if (!hasOneNumber(rawBonusLottoNumber)) {
            throw new IllegalArgumentException("보너스 볼은 1개만 입력할 수 있습니다.");
        }

        if (isNotConsistOfNumber(rawBonusLottoNumber)) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

    private static boolean hasOneNumber(final String rawBonusLottoNumber) {
        return rawBonusLottoNumber.length() == BONUS_LOTTO_SIZE;
    }

    private static boolean isNotConsistOfNumber(final String rawBonusLottoNumber) {
        return !NUMBER_PATTERN.matcher(rawBonusLottoNumber).matches();
    }
}

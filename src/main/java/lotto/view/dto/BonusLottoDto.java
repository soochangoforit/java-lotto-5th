package lotto.view.dto;

public class BonusLottoDto {

    private static final int BONUS_LOTTO_SIZE = 1;
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
    }

    private static boolean hasOneNumber(final String rawBonusLottoNumber) {
        return rawBonusLottoNumber.length() == BONUS_LOTTO_SIZE;
    }
}

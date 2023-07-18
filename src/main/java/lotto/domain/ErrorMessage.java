package lotto.domain;

public final class ErrorMessage {

    public static final String ONLY_CAN_DIVIDE_BY_LOTTO_PRICE = "구입 금액은 1000원 단위로 입력 가능합니다.";
    public static final String ONLY_LOTTO_NUMBER_IN_RANGE = "로또 번호는 1 ~ 45 사이의 숫자만 가능합니다.";

    public static final String OVER_LOTTO_SIZE = "로또 번호는 6개만 가능합니다.";

    public static final String CAN_NOT_UNDER_ZERO = "사고자 하는 개수는 0보다 작을 수 없습니다.";

    public static final String IS_NOT_BLANK = "빈문자열과 공백은 입력할 수 없습니다.";

    public static final String IS_NOT_CONSIST_OF_NUMBER = "숫자만 입력 가능합니다.";

    public static final String BONUS_BALL_IS_DUPLICATED = "보너스 볼은 당첨 번호와 중복될 수 없습니다.";


    private ErrorMessage() {
    }
}

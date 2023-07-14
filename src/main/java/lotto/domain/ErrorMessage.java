package lotto.domain;

public final class ErrorMessage {

    public static final String ONLY_NUMBER_MESSAGE = "숫자만 입력 가능합니다.";
    public static final String ONLY_CAN_DIVIDE_BY_LOTTO_PRICE = "구입 금액은 1000원 단위로 입력 가능합니다.";
    public static final String ONLY_LOTTO_NUMBER_IN_RANGE = "로또 번호는 1 ~ 45 사이의 숫자만 가능합니다.";

    private ErrorMessage() {
    }
}

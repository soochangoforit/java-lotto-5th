package lotto.view.dto;

import java.util.regex.Pattern;

public class InputMoneyRequest {
    private static final Pattern NUMBER_REGEX = Pattern.compile("^[0-9]*$");
    private static final String ONLY_NUMBER_MESSAGE = "구입 금액은 숫자만 입력 가능합니다.";

    private final int inputMoney;


    public InputMoneyRequest(final String inputMoney) {
        validate(inputMoney);
        this.inputMoney = Integer.parseInt(inputMoney);
    }

    private void validate(final String lottoMoney) {
        if (isBlank(lottoMoney) || !isNumber(lottoMoney)) {
            throw new IllegalArgumentException(ONLY_NUMBER_MESSAGE);
        }
    }

    private boolean isBlank(final String lottoMoney) {
        return lottoMoney.isBlank();
    }

    private boolean isNumber(final String lottoMoney) {
        return NUMBER_REGEX.matcher(lottoMoney).matches();
    }

    public int divide(final int lottoPrice) {
        return inputMoney / lottoPrice;
    }
}

package lotto.view.dto;

import static lotto.domain.ErrorMessage.IS_NOT_CONSIST_OF_NUMBER;

import java.util.regex.Pattern;

public class InputMoneyRequest {
    private static final Pattern NUMBER_REGEX = Pattern.compile("^[0-9]*$");

    private final String inputMoney;


    public InputMoneyRequest(final String inputMoney) {
        validate(inputMoney);
        this.inputMoney = inputMoney;
    }

    private void validate(final String lottoMoney) {
        if (isBlank(lottoMoney) || !isNumber(lottoMoney)) {
            throw new IllegalArgumentException(IS_NOT_CONSIST_OF_NUMBER);
        }
    }


    private boolean isBlank(final String lottoMoney) {
        return lottoMoney.isBlank();
    }

    private boolean isNumber(final String lottoMoney) {
        return NUMBER_REGEX.matcher(lottoMoney).matches();
    }

    public String getInputMoney() {
        return inputMoney;
    }


}

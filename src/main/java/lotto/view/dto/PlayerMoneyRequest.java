package lotto.view.dto;

import static lotto.domain.ErrorMessage.IS_NOT_CONSIST_OF_NUMBER;

import java.util.regex.Pattern;

public class PlayerMoneyRequest {
    private static final Pattern NUMBER_REGEX = Pattern.compile("^[0-9]*$");

    private final long playerMoney;

    private PlayerMoneyRequest(final long playerMoney) {
        validate(String.valueOf(playerMoney));
        this.playerMoney = playerMoney;
    }


    public static PlayerMoneyRequest from(final String rawPlayerMoney) {
        return new PlayerMoneyRequest(Long.parseLong(rawPlayerMoney));
    }

    private void validate(final String playerMoney) {
        if (isBlank(playerMoney) || !isNumber(playerMoney)) {
            throw new IllegalArgumentException(IS_NOT_CONSIST_OF_NUMBER);
        }
    }


    private boolean isBlank(final String playerMoney) {
        return playerMoney.isBlank();
    }

    private boolean isNumber(final String playerMoney) {
        return NUMBER_REGEX.matcher(playerMoney).matches();
    }

    public long getPlayerMoney() {
        return playerMoney;
    }


}

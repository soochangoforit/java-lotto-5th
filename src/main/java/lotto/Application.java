package lotto;

import lotto.controller.LottoGameController;
import lotto.domain.NumberGenerator;
import lotto.domain.RandomNumberGenerator;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoGameController lottoGameController = new LottoGameController(numberGenerator);
        lottoGameController.run();
    }
}

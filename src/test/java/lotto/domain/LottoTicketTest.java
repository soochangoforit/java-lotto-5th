package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTicketTest {

    private static LottoTicket singleTicketWithNumbers(int... numbers) {
        List<Integer> lottoNumbers = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toList());

        return new LottoTicket(List.of(new Lotto(lottoNumbers)));
    }

    private static Lotto winningLottoWithNumbers(int... numbers) {
        List<Integer> winningLottoNumbers = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toList());

        return new Lotto(winningLottoNumbers);
    }

    private static LottoNumber bonusNumber(int number) {
        return new LottoNumber(number);
    }

    private static TotalPrize totalPrize(Map<LottoPrize, Long> prizes) {
        return new TotalPrize(prizes);
    }


    public static Stream<Arguments> lottoTicket() {
        return Stream.of(
                winningFirstPrize(),
                winningSecondPrize(),
                winningThirdPrize(),
                winningFourthPrize(),
                winningFifthPrize(),
                notAnyPrize()
        );
    }

    private static Arguments winningFirstPrize() {
        return Arguments.of(
                singleTicketWithNumbers(1, 2, 3, 4, 5, 6),
                winningLottoWithNumbers(1, 2, 3, 4, 5, 6),
                bonusNumber(7),
                totalPrize(Map.of(LottoPrize._1ST_PRIZE, 1L))
        );
    }

    private static Arguments winningSecondPrize() {
        return Arguments.of(
                singleTicketWithNumbers(1, 2, 3, 4, 5, 6),
                winningLottoWithNumbers(1, 2, 3, 4, 5, 7),
                bonusNumber(6),
                totalPrize(Map.of(LottoPrize._2ND_PRIZE, 1L))
        );
    }

    private static Arguments winningThirdPrize() {
        return Arguments.of(
                singleTicketWithNumbers(1, 2, 3, 4, 5, 6),
                winningLottoWithNumbers(1, 2, 3, 4, 5, 7),
                bonusNumber(8),
                totalPrize(Map.of(LottoPrize._3RD_PRIZE, 1L))
        );
    }

    private static Arguments winningFourthPrize() {
        return Arguments.of(
                singleTicketWithNumbers(1, 2, 3, 4, 5, 6),
                winningLottoWithNumbers(1, 2, 3, 4, 7, 8),
                bonusNumber(9),
                totalPrize(Map.of(LottoPrize._4TH_PRIZE, 1L))
        );
    }

    private static Arguments winningFifthPrize() {
        return Arguments.of(
                singleTicketWithNumbers(1, 2, 3, 4, 5, 6),
                winningLottoWithNumbers(1, 2, 3, 7, 8, 9),
                bonusNumber(10),
                totalPrize(Map.of(LottoPrize._5TH_PRIZE, 1L))
        );
    }

    private static Arguments notAnyPrize() {
        return Arguments.of(
                singleTicketWithNumbers(1, 2, 3, 4, 5, 6),
                winningLottoWithNumbers(1, 2, 7, 8, 9, 10),
                bonusNumber(11),
                totalPrize(Map.of(LottoPrize._NOT_MATCHED, 1L))
        );
    }


    @ParameterizedTest()
    @MethodSource("lottoTicket")
    void getTotalPrize는_당첨번호와_보너스번호에_따른_당첨결과를_얻는다(
            LottoTicket lottoTicket, Lotto winningLotto, LottoNumber bonusLotto, TotalPrize expectedTotalPrize
    ) {
        TotalPrize actualTotalPrize = lottoTicket.getTotalPrize(winningLotto, bonusLotto);

        assertThat(actualTotalPrize).isEqualTo(expectedTotalPrize);
    }
}

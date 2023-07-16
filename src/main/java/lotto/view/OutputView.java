package lotto.view;

import java.util.Arrays;

import lotto.domain.LottoPrize;
import lotto.view.dto.LottoTicketInfoDto;
import lotto.view.dto.WinningStatisticInfo;

public enum OutputView {

    INSTANCE;

    private static final String LOTTO_TICKET_SIZE_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String WINNING_STATISTIC_TITLE = "당첨 통계\n---";
    private static final String PRIZE_AMOUNT_FORMAT = "%,d";
    private static final String SECOND_PRIZE_FORMAT = "%d개 일치, 보너스 볼 일치 (%s원) - %d개\n";
    private static final String COMMON_PRIZE_FORMAT = "%d개 일치 (%s원) - %d개\n";
    private static final String WINNING_RATE_FORMAT = "%,.1f";
    private static final String WINNING_RATE_MESSAGE = "총 수익률은 %s%%입니다.\n";


    public void printLottoTicket(final LottoTicketInfoDto lottoTicket) {
        System.out.println();
        System.out.printf(LOTTO_TICKET_SIZE_MESSAGE, lottoTicket.size());
        lottoTicket.getLottoTicket().forEach(System.out::println);
    }


    public void printWinningStatisticResult(final WinningStatisticInfo statisticInfo) {
        printWinningStatisticsTitle();
        printWinningStatistics(statisticInfo);
        printWinningRate(statisticInfo);
    }

    private static void printWinningStatisticsTitle() {
        System.out.println(WINNING_STATISTIC_TITLE);
    }

    private static void printWinningStatistics(final WinningStatisticInfo statisticInfo) {
        Arrays.stream(LottoPrize.values())
                .forEach(lottoPrize -> printPrizeStatus(lottoPrize, statisticInfo.getCount(lottoPrize)));
    }

    private static void printPrizeStatus(final LottoPrize lottoPrize, final Long count) {
        String amount = String.format(PRIZE_AMOUNT_FORMAT, lottoPrize.getAmount());
        if (lottoPrize == LottoPrize.SECOND_PRIZE) {
            System.out.printf(SECOND_PRIZE_FORMAT, lottoPrize.getMatchCount(), amount, count);
            return;
        }
        System.out.printf(COMMON_PRIZE_FORMAT, lottoPrize.getMatchCount(), amount, count);
    }

    private static void printWinningRate(final WinningStatisticInfo statisticInfo) {
        String rate = String.format(WINNING_RATE_FORMAT, statisticInfo.getWinningRate());
        System.out.printf(WINNING_RATE_MESSAGE, rate);
    }


}

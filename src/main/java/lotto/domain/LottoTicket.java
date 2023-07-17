package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoTicket {

    private final List<Lotto> lottos;

    public LottoTicket(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }

    public TotalLottoPrize getTotalPrize(final Lotto winningLotto, final LottoNumber bonusLottoNumber) {
        Map<LottoPrize, Long> totalLottoPrize = getLottoTotalPrize(winningLotto, bonusLottoNumber);
        return TotalLottoPrize.from(totalLottoPrize);
    }

    private Map<LottoPrize, Long> getLottoTotalPrize(final Lotto winningLotto, final LottoNumber bonusLottoNumber) {
        return lottos.stream()
                .map(lotto -> lotto.calculateLottoPrize(winningLotto, bonusLottoNumber))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public List<List<Integer>> getAllLottoNumbers() {
        return lottos.stream()
                .map(Lotto::getLottoNumbers)
                .collect(Collectors.toList());
    }
}

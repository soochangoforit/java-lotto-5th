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

    public List<Lotto> getLottos() {
        return lottos;
    }

    public TotalPrize getTotalPrize(final Lotto winningLotto, final LottoNumber bonusLotto) {
        Map<LottoPrize, Long> totalPrize = getLottoTotalPrize(winningLotto, bonusLotto);
        return new TotalPrize(totalPrize);
    }

    private Map<LottoPrize, Long> getLottoTotalPrize(final Lotto winningLotto, final LottoNumber bonusLotto) {
        return lottos.stream()
                .map(lotto -> lotto.calculateResult(winningLotto, bonusLotto))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}

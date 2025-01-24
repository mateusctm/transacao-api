package com.desafio.transacao_api.services;

import com.desafio.transacao_api.exceptions.UnprocessableEntity;
import com.desafio.transacao_api.model.Tansacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    public final ArrayList<Tansacao> tansacoes = new ArrayList<>();

    public void createTransacao(Tansacao tansacao) throws UnprocessableEntity {

        if (tansacao.getValor() < 0 || tansacao.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new UnprocessableEntity("Vaolor ou DataHora ivalidos");
        }
        tansacoes.add(tansacao);
    }

    public void deleteTransacao() {
        tansacoes.clear();
    }

    public DoubleSummaryStatistics eststistica(Integer time) {

        DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics();
        for (Tansacao tansacao : tansacoes) {
            if (tansacao.getDataHora().isAfter(OffsetDateTime.now().minusSeconds(time))) {
                doubleSummaryStatistics.accept(tansacao.getValor());
            }
        }

        return doubleSummaryStatistics;
    }
}

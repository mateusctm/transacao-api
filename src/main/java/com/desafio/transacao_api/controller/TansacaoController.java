package com.desafio.transacao_api.controller;

import com.desafio.transacao_api.exceptions.UnprocessableEntity;
import com.desafio.transacao_api.model.Tansacao;
import com.desafio.transacao_api.services.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("transacao")
@RequiredArgsConstructor
public class TansacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> createTransacao(@RequestBody Tansacao tansacao) {

        try {
            transacaoService.createTransacao(tansacao);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UnprocessableEntity unprocessableEntity) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransacao() {

        transacaoService.deleteTransacao();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<DoubleSummaryStatistics> getEstatistica(@RequestParam(value = "time", defaultValue = "60", required = false) Integer time) {

        return ResponseEntity.ok(transacaoService.eststistica(time));
    }
}

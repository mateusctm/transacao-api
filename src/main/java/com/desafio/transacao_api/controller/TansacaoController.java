package com.desafio.transacao_api.controller;

import com.desafio.transacao_api.exceptions.UnprocessableEntity;
import com.desafio.transacao_api.model.Tansacao;
import com.desafio.transacao_api.services.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transacao")
@RequiredArgsConstructor
public class TansacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Object> createTransacao(@RequestBody Tansacao tansacao) {
        try {
            transacaoService.createTransacao(tansacao);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UnprocessableEntity unprocessableEntity) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}

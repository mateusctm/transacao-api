package com.desafio.transacao_api.exceptions;

public class UnprocessableEntity extends RuntimeException {
    public UnprocessableEntity(String message) {
        super(message);
    }
}

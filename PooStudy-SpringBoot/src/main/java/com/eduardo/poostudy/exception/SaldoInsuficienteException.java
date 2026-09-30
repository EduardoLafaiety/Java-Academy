package com.eduardo.poostudy.exception;

// Esta Excecao Representa Uma Tentativa De Retirar Mais Dinheiro Do Que Existe Na Conta

public class SaldoInsuficienteException extends RuntimeException {

    // Este Construtor Recebe A Mensagem Que Explica O Erro
    public SaldoInsuficienteException(String mensagem) {

        // Super Envia A Mensagem Para O Construtor Da Classe RuntimeException
        super(mensagem);
    }
}

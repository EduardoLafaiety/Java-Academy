package com.eduardo.poostudy.exception;

// Esta Excecao Representa Uma Busca Por Uma Conta Que Nao Existe

public class ContaNaoEncontradaException extends RuntimeException {

    // Este Construtor Recebe O Numero Da Conta Que Nao Foi Encontrada
    public ContaNaoEncontradaException(int numeroDaConta) {

        // Super Envia Uma Mensagem Para O Construtor Da Classe RuntimeException
        super("Conta " + numeroDaConta + " Nao Encontrada");
    }
}

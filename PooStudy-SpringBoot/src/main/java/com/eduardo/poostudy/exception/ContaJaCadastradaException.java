package com.eduardo.poostudy.exception;

// Esta Excecao Representa Uma Tentativa De Cadastrar Duas Contas Com O Mesmo Numero

public class ContaJaCadastradaException extends RuntimeException {

    // Este Construtor Recebe O Numero Que Ja Existe No Banco
    public ContaJaCadastradaException(int numeroDaConta) {

        // Super Envia Uma Mensagem Para O Construtor Da Classe RuntimeException
        super("A Conta " + numeroDaConta + " Ja Esta Cadastrada");
    }
}

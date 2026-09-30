package com.eduardo.poostudy.model;

// Esta Classe Representa Um Cliente Do Banco

public class Cliente {

    // Private Protege O Nome Contra Acesso Direto Fora Da Classe
    private final String nome;

    // Private Protege O CPF Contra Acesso Direto Fora Da Classe
    private final String cpf;

    // Este Construtor Recebe Os Dados Necessarios Para Criar Um Cliente
    public Cliente(String nome, String cpf) {

        // This Nome Representa O Atributo Deste Objeto Cliente
        // Nome Representa O Parametro Recebido Pelo Construtor
        this.nome = nome;

        // This Cpf Representa O Atributo Deste Objeto Cliente
        // Cpf Representa O Parametro Recebido Pelo Construtor
        this.cpf = cpf;
    }

    // Este Getter Permite Consultar O Nome Sem Expor O Atributo Diretamente
    public String getNome() {

        // Return Devolve O Nome Para Quem Chamou O Metodo
        return nome;
    }

    // Este Getter Permite Consultar O CPF Sem Expor O Atributo Diretamente
    public String getCpf() {

        // Return Devolve O CPF Para Quem Chamou O Metodo
        return cpf;
    }

    // Override Indica Que Estamos Criando Nossa Propria Versao Do ToString Herdado De Object
    @Override
    public String toString() {

        // Return Devolve Uma Representacao Em Texto Deste Cliente
        return "Cliente [Nome: " + nome + ", CPF: " + cpf + "]";
    }
}

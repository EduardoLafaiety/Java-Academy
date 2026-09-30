package com.eduardo.poostudy.model;

// Extends Indica Que ContaPoupanca E Um Tipo De ContaBancaria

public class ContaPoupanca extends ContaBancaria {

    // Este Atributo Existe Apenas Em Objetos ContaPoupanca
    private final double rendimento;

    // Este Construtor Recebe Os Dados Comuns Da Conta E O Dado Especifico Da Poupanca
    public ContaPoupanca(Cliente titularDaConta, int numeroDaConta, double saldo, double rendimento) {

        // Super Chama O Construtor Da Classe Mae Para Inicializar Titular Numero E Saldo
        super(titularDaConta, numeroDaConta, saldo);

        // Esta Validacao Impede Um Rendimento Negativo Neste Exemplo
        if (rendimento < 0) {

            // Throw Informa Que O Rendimento Recebido Nao E Valido
            throw new IllegalArgumentException("O Rendimento Nao Pode Ser Negativo");
        }

        // This Rendimento Inicializa O Atributo Especifico Deste Objeto ContaPoupanca
        this.rendimento = rendimento;
    }

    // Este Getter Permite Consultar O Rendimento Da Poupanca
    public double getRendimento() {

        // Return Devolve O Rendimento Guardado Neste Objeto
        return rendimento;
    }

    // Override Indica Que ContaPoupanca Cria Uma Versao Especializada De ExibirDados
    @Override
    public void exibirDados() {

        // Super ExibirDados Reaproveita Primeiro A Versao Da Classe Mae
        super.exibirDados();

        // Esta Linha Acrescenta O Dado Especifico Da ContaPoupanca
        System.out.println("Rendimento: " + rendimento);
    }

    // Override Implementa O Metodo Abstrato Exigido Pela Classe Mae
    @Override
    public double calcularTarifa() {

        // Return Devolve Dois Por Cento Do Saldo Como Regra Didatica Deste Projeto
        return saldo * 0.02;
    }
}

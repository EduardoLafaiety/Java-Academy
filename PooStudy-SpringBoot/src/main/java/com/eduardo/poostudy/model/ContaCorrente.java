package com.eduardo.poostudy.model;

// Extends Indica Que ContaCorrente E Um Tipo De ContaBancaria

// Implements Indica Que ContaCorrente Assume O Contrato Definido Por Tributavel
public class ContaCorrente extends ContaBancaria implements Tributavel {

    // Este Atributo Existe Apenas Em Objetos ContaCorrente
    private final double taxaManutencao;

    // Este Construtor Recebe Os Dados Comuns Da Conta E O Dado Especifico Da ContaCorrente
    public ContaCorrente(Cliente titularDaConta, int numeroDaConta, double saldo, double taxaManutencao) {

        // Super Chama O Construtor Da Classe Mae Para Inicializar Titular Numero E Saldo
        super(titularDaConta, numeroDaConta, saldo);

        // Esta Validacao Impede Uma Taxa De Manutencao Negativa
        if (taxaManutencao < 0) {

            // Throw Informa Que A Taxa Recebida Nao E Valida
            throw new IllegalArgumentException("A Taxa De Manutencao Nao Pode Ser Negativa");
        }

        // This TaxaManutencao Inicializa O Atributo Especifico Deste Objeto ContaCorrente
        this.taxaManutencao = taxaManutencao;
    }

    // Este Getter Permite Consultar A Taxa De Manutencao
    public double getTaxaManutencao() {

        // Return Devolve A Taxa De Manutencao Desta Conta
        return taxaManutencao;
    }

    // Este Metodo Representa Um Comportamento Especifico Da ContaCorrente
    public void cobrarTaxa() {

        // Debitar Reaproveita A Regra De Retirada Definida Na Classe Mae
        debitar(taxaManutencao);
    }

    // Override Indica Que ContaCorrente Cria Uma Versao Especializada De ExibirDados
    @Override
    public void exibirDados() {

        // Super ExibirDados Reaproveita Primeiro A Versao Da Classe Mae
        super.exibirDados();

        // Esta Linha Acrescenta O Dado Especifico Da ContaCorrente
        System.out.println("Taxa De Manutencao: " + taxaManutencao);
    }

    // Override Implementa O Metodo Abstrato Exigido Por ContaBancaria
    @Override
    public double calcularTarifa() {

        // Esta Condicao Define Uma Tarifa Fixa Para Saldos Menores Ou Iguais A Quinhentos
        if (saldo <= 500) {

            // Return Devolve A Tarifa Calculada
            return 10;
        }

        // Esta Condicao Define Uma Tarifa De Um Por Cento Para Saldos Ate Cinco Mil
        if (saldo <= 5000) {

            // Return Devolve Um Por Cento Do Saldo
            return saldo * 0.01;
        }

        // Return Devolve Meio Por Cento Do Saldo Para Valores Acima De Cinco Mil
        return saldo * 0.005;
    }

    // Override Implementa O Contrato Definido Pela Interface Tributavel
    @Override
    public double calcularImposto() {

        // Return Devolve Dois Por Cento Do Saldo Como Exemplo De Imposto
        return saldo * 0.02;
    }
}

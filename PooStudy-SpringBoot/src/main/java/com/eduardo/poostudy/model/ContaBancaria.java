package com.eduardo.poostudy.model;

import com.eduardo.poostudy.exception.SaldoInsuficienteException;

// Abstract Indica Que Esta Classe Serve Como Base Para Outros Tipos De Conta

// Ela Nao Pode Ser Instanciada Diretamente Com New ContaBancaria
public abstract class ContaBancaria {

    // Esta Composicao Indica Que Uma Conta Bancaria Tem Um Cliente Como Titular
    private final Cliente titularDaConta;

    // Private Protege O Numero Da Conta Contra Alteracoes Diretas
    private final int numeroDaConta;

    // Protected Permite Que As Classes Filhas Utilizem O Saldo Durante O Estudo De Heranca
    protected double saldo;

    // Este Construtor Inicializa A Parte Comum De Todas As Contas Bancarias
    public ContaBancaria(Cliente titularDaConta, int numeroDaConta, double saldo) {

        // Esta Validacao Impede A Criacao De Uma Conta Sem Titular
        if (titularDaConta == null) {

            // Throw Interrompe A Execucao E Lanca Uma Excecao Para Informar O Problema
            throw new IllegalArgumentException("O Titular Da Conta Nao Pode Ser Nulo");
        }

        // Esta Validacao Impede Numeros De Conta Invalidos
        if (numeroDaConta <= 0) {

            // Throw Informa Que O Numero Recebido Nao E Valido
            throw new IllegalArgumentException("O Numero Da Conta Deve Ser Maior Que Zero");
        }

        // Esta Validacao Impede Saldo Inicial Negativo
        if (saldo < 0) {

            // Throw Informa Que O Saldo Inicial Nao E Valido
            throw new IllegalArgumentException("O Saldo Inicial Nao Pode Ser Negativo");
        }

        // This TitularDaConta Representa O Atributo Deste Objeto
        this.titularDaConta = titularDaConta;

        // This NumeroDaConta Representa O Atributo Deste Objeto
        this.numeroDaConta = numeroDaConta;

        // This Saldo Representa O Atributo Deste Objeto
        this.saldo = saldo;
    }

    // Este Metodo Representa A Entrada De Dinheiro Na Conta
    public void receber(double valorRecebido) {

        // Esta Validacao Impede Depositos Com Valor Zero Ou Negativo
        if (valorRecebido <= 0) {

            // Throw Informa Que O Valor Recebido Nao E Valido
            throw new IllegalArgumentException("O Valor Recebido Deve Ser Maior Que Zero");
        }

        // Esta Operacao Soma O Valor Recebido Ao Saldo Atual
        saldo += valorRecebido;
    }

    // Este Metodo Protegido Centraliza A Logica De Retirada De Dinheiro Da Conta
    protected void debitar(double valor) {

        // Esta Validacao Impede Debitos Com Valor Zero Ou Negativo
        if (valor <= 0) {

            // Throw Informa Que O Valor Do Debito Nao E Valido
            throw new IllegalArgumentException("O Valor Do Debito Deve Ser Maior Que Zero");
        }

        // Esta Condicao Verifica Se Existe Saldo Suficiente Para O Debito
        if (valor > saldo) {

            // Esta Excecao Representa A Regra De Negocio De Saldo Insuficiente
            throw new SaldoInsuficienteException("Saldo Insuficiente Para Realizar A Operacao");
        }

        // Esta Operacao Retira O Valor Do Saldo Atual
        saldo -= valor;
    }

    // Este Metodo Realiza Uma Transferencia Entre Dois Objetos ContaBancaria
    public void transferirPara(double valorTransferido, ContaBancaria contaDestino) {

        // Esta Validacao Impede Uma Transferencia Para Uma Conta Nula
        if (contaDestino == null) {

            // Throw Informa Que A Conta Destino E Obrigatoria
            throw new IllegalArgumentException("A Conta Destino Nao Pode Ser Nula");
        }

        // Este Metodo Retira O Dinheiro Da Conta Atual Aplicando As Validacoes Necessarias
        debitar(valorTransferido);

        // Este Metodo Adiciona O Mesmo Valor Na Conta De Destino
        contaDestino.receber(valorTransferido);
    }

    // Este Getter Permite Consultar O Titular Da Conta
    public Cliente getTitularDaConta() {

        // Return Devolve O Objeto Cliente Guardado Nesta Conta
        return titularDaConta;
    }

    // Este Getter Permite Consultar O Numero Da Conta
    public int getNumeroDaConta() {

        // Return Devolve O Numero Desta Conta
        return numeroDaConta;
    }

    // Este Getter Permite Consultar O Saldo Atual Da Conta
    public double getSaldo() {

        // Return Devolve O Saldo Atual
        return saldo;
    }

    // Este Metodo Exibe Os Dados Comuns Que Toda Conta Possui
    public void exibirDados() {

        // Esta Linha Busca O Nome Dentro Do Objeto Cliente Guardado Como Titular
        System.out.println("Nome Do Titular: " + titularDaConta.getNome());

        // Esta Linha Busca O CPF Dentro Do Objeto Cliente Guardado Como Titular
        System.out.println("CPF Do Titular: " + titularDaConta.getCpf());

        // Esta Linha Exibe O Numero Da Conta
        System.out.println("Numero Da Conta: " + numeroDaConta);

        // Esta Linha Exibe O Saldo Atual
        System.out.println("Saldo Da Conta: " + saldo);
    }

    // Override Indica Que Estamos Sobrescrevendo O Metodo ToString Herdado De Object
    @Override
    public String toString() {

        // Return Devolve Uma Representacao Em Texto Desta Conta
        return "ContaBancaria [Titular: " + titularDaConta.getNome()
                + ", CPF: " + titularDaConta.getCpf()
                + ", Numero: " + numeroDaConta
                + ", Saldo: R$ " + saldo
                + "]";
    }

    // Este Metodo Abstrato Obriga Cada Tipo Concreto De Conta A Definir Sua Propria Tarifa
    public abstract double calcularTarifa();
}

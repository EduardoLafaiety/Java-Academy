package com.eduardo.poostudy;

import com.eduardo.poostudy.exception.ContaNaoEncontradaException;
import com.eduardo.poostudy.exception.SaldoInsuficienteException;
import com.eduardo.poostudy.model.Cliente;
import com.eduardo.poostudy.model.ContaBancaria;
import com.eduardo.poostudy.model.ContaCorrente;
import com.eduardo.poostudy.model.ContaPoupanca;
import com.eduardo.poostudy.model.Tributavel;
import com.eduardo.poostudy.service.Banco;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta Classe Contem O Ponto De Entrada Do Programa Spring Boot
@SpringBootApplication
public class Main implements CommandLineRunner {

    // O Spring Entrega O Service Banco Pelo Construtor
    private final Banco banco;

    // Este Construtor Recebe O Banco Criado E Gerenciado Pelo Spring
    public Main(Banco banco) {
        this.banco = banco;
    }

    // O Metodo Main Continua Sendo O Primeiro Metodo Executado Pela JVM
    public static void main(String[] args) {

        // SpringApplication Inicializa O Contexto Do Spring Boot
        SpringApplication.run(Main.class, args);
    }

    // O Metodo Run Executa A Mesma Logica Que Antes Ficava Diretamente No Main
    @Override
    public void run(String... args) {

        // Criamos Um Cliente Que Depois Sera Usado Por Uma Conta
        Cliente eduardo = new Cliente("Eduardo", "111.111.111-11");

        // Criamos Outro Cliente Para Demonstrar Objetos Independentes
        Cliente agatha = new Cliente("Agatha Lafaiety", "222.222.222-22");

        // Criamos Um Terceiro Cliente Para A Conta Poupanca
        Cliente pedro = new Cliente("Pedro Muniz", "333.333.333-33");

        // Polimorfismo Permite Usar O Tipo ContaBancaria Para Referenciar Um Objeto ContaCorrente
        ContaBancaria contaEduardo = new ContaCorrente(eduardo, 123, 1000, 50);

        // Esta Referencia Tambem Usa O Tipo Abstrato Para Apontar Para Outra ContaCorrente
        ContaBancaria contaAgatha = new ContaCorrente(agatha, 321, 2500, 80);

        // Esta Referencia Usa O Mesmo Tipo ContaBancaria Para Apontar Para Uma ContaPoupanca
        ContaBancaria contaPedro = new ContaPoupanca(pedro, 255, 30000, 300);

        // Aqui Usamos O Tipo Da Interface Para Demonstrar Polimorfismo Por Contrato
        Tributavel itemTributavel = new ContaCorrente(eduardo, 777, 5000, 40);

        // Esta Linha Mostra Que Pela Interface Podemos Chamar O Metodo Definido No Contrato
        System.out.println("Imposto Do Item Tributavel: " + itemTributavel.calcularImposto());

        // O Main Pede Ao Banco Para Cadastrar A Primeira Conta
        banco.cadastrarConta(contaEduardo);

        // O Main Pede Ao Banco Para Cadastrar A Segunda Conta
        banco.cadastrarConta(contaAgatha);

        // O Main Pede Ao Banco Para Cadastrar A Conta Poupanca
        banco.cadastrarConta(contaPedro);

        // Esta Linha Exibe Todas As Contas Que O Banco Conhece
        banco.exibirTodasAsContas();

        // Try Delimita Um Trecho Que Pode Gerar Uma Excecao
        try {

            // Esta Linha Busca Uma Conta Pelo Numero E Recebe O Objeto Encontrado
            ContaBancaria contaEncontrada = banco.buscarContaPorNumero(321);

            // Esta Linha Exibe O Objeto Encontrado Utilizando O ToString
            System.out.println("Conta Encontrada: " + contaEncontrada);

            // Esta Linha Representa Um Update Do Saldo Sem Usar Setter Direto
            banco.depositar(321, 500);

            // Esta Linha Mostra O Saldo Depois Do Deposito
            System.out.println("Saldo Da Conta 321: " + banco.buscarContaPorNumero(321).getSaldo());

            // Esta Linha Realiza Uma Transferencia Entre Duas Contas Cadastradas
            banco.transferir(321, 123, 200);

            // Esta Linha Mostra O Saldo Atualizado Da Conta De Origem
            System.out.println("Saldo Da Conta 321 Apos Transferencia: " + banco.buscarContaPorNumero(321).getSaldo());

            // Esta Linha Mostra O Saldo Atualizado Da Conta De Destino
            System.out.println("Saldo Da Conta 123 Apos Transferencia: " + banco.buscarContaPorNumero(123).getSaldo());

            // Esta Linha Tenta Buscar Uma Conta Que Nao Existe Para Demonstrar Exceptions
            banco.buscarContaPorNumero(9999);

        // Catch Captura A Excecao De Conta Nao Encontrada E Permite Tratar O Problema
        } catch (ContaNaoEncontradaException exception) {

            // Esta Linha Exibe A Mensagem Produzida Pela Excecao
            System.out.println("Erro: " + exception.getMessage());

        // Catch Captura Uma Possivel Excecao De Saldo Insuficiente
        } catch (SaldoInsuficienteException exception) {

            // Esta Linha Exibe A Mensagem Produzida Pela Excecao
            System.out.println("Erro: " + exception.getMessage());
        }

        // Esta Linha Demonstra Set E Devolve Os CPFs Sem Repeticao
        System.out.println("CPFs Unicos: " + banco.listarCpfsUnicosDosTitulares());

        // Esta Linha Remove Uma Conta Pelo Numero
        banco.removerConta(255);

        // Esta Linha Exibe As Contas Restantes Depois Do Delete
        banco.exibirTodasAsContas();
    }
}

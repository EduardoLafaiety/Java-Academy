package com.eduardo.poostudy.service;

import com.eduardo.poostudy.exception.ContaJaCadastradaException;
import com.eduardo.poostudy.exception.ContaNaoEncontradaException;
import com.eduardo.poostudy.model.ContaBancaria;
import com.eduardo.poostudy.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


// Esta Classe Representa A Camada Que Coordena As Operacoes Do Banco
// Ela Nao Precisa Saber Como Os Dados Sao Guardados Porque Essa Responsabilidade Pertence Ao Repository
@Service
public class Banco {

    // O Banco Depende Do Contrato ContaRepository E Nao De Uma Implementacao Especifica
    private final ContaRepository contaRepository;

    // Este Construtor Recebe Um Repository Pronto Para Ser Utilizado
    public Banco(ContaRepository contaRepository) {

        // Esta Validacao Impede A Criacao De Um Banco Sem Repository
        if (contaRepository == null) {

            // Throw Informa Que A Dependencia E Obrigatoria
            throw new IllegalArgumentException("O Repository Nao Pode Ser Nulo");
        }

        this.contaRepository = contaRepository;
    }

    // Este Metodo Cadastra Uma Nova Conta No Banco
    public void cadastrarConta(ContaBancaria conta) {

        // Esta Validacao Impede O Cadastro De Uma Conta Nula
        if (conta == null) {

            // Throw Informa Que Uma Conta E Obrigatoria
            throw new IllegalArgumentException("A Conta Nao Pode Ser Nula");
        }

        // Esta Condicao Verifica Se O Numero Da Conta Ja Esta Cadastrado
        if (contaRepository.existePorNumero(conta.getNumeroDaConta())) {

            // Esta Excecao Representa A Regra De Negocio De Conta Duplicada
            throw new ContaJaCadastradaException(conta.getNumeroDaConta());
        }

        // O Banco Pede Ao Repository Para Salvar A Conta
        contaRepository.salvar(conta);
    }

    // Este Metodo Busca Uma Conta E Devolve O Objeto Encontrado
    public ContaBancaria buscarContaPorNumero(int numeroDaConta) {

        // O Repository Faz A Busca E Pode Devolver Null Quando Nao Encontrar
        ContaBancaria contaEncontrada = contaRepository.buscarPorNumero(numeroDaConta);

        // Esta Condicao Verifica Se Nenhum Objeto Foi Encontrado
        if (contaEncontrada == null) {

            // Esta Excecao Informa Que A Conta Procurada Nao Existe
            throw new ContaNaoEncontradaException(numeroDaConta);
        }

        // Return Entrega O Objeto ContaBancaria Para Quem Chamou O Metodo
        return contaEncontrada;
    }

    // Este Metodo Devolve Todas As Contas Cadastradas
    public List<ContaBancaria> listarContas() {

        // Return Entrega A List Produzida Pelo Repository
        return contaRepository.listarTodas();
    }

    // Este Metodo Exibe Todas As Contas Utilizando Um For Each
    public void exibirTodasAsContas() {

        // Este For Each Percorre Uma ContaBancaria Por Vez Dentro Da List Retornada Pelo Repository
        for (ContaBancaria conta : contaRepository.listarTodas()) {

            // Polimorfismo Faz Cada Objeto Executar Sua Propria Versao De ExibirDados Quando Houver Override
            conta.exibirDados();

            // Esta Linha Apenas Separa Visualmente As Contas No Console
            System.out.println("------------------------------");
        }
    }

    // Este Metodo Remove Uma Conta Pelo Numero
    public void removerConta(int numeroDaConta) {

        // Buscar Primeiro Faz A Validacao E Lanca Excecao Caso A Conta Nao Exista
        buscarContaPorNumero(numeroDaConta);

        // O Repository Remove A Conta Depois Que Confirmamos Sua Existencia
        contaRepository.removerPorNumero(numeroDaConta);
    }

    // Este Metodo Representa O Update Do Saldo Por Meio De Um Comportamento Da Propria Conta
    public void depositar(int numeroDaConta, double valor) {

        // Este Metodo Busca E Devolve O Objeto Da Conta Que Deve Ser Atualizada
        ContaBancaria conta = buscarContaPorNumero(numeroDaConta);

        // A Conta Altera Seu Proprio Estado Aplicando Suas Regras
        conta.receber(valor);

        // Salvar Novamente Representa A Persistencia Do Estado Atualizado
        // Em Memoria O Mesmo Objeto Ja Esta Atualizado Mas Esta Linha Prepara A Ideia Para O PostgreSQL
        contaRepository.salvar(conta);
    }

    // Este Metodo Coordena Uma Transferencia Entre Duas Contas
    public void transferir(int numeroOrigem, int numeroDestino, double valor) {

        // Esta Linha Busca A Conta Que Vai Enviar O Dinheiro
        ContaBancaria contaOrigem = buscarContaPorNumero(numeroOrigem);

        // Esta Linha Busca A Conta Que Vai Receber O Dinheiro
        ContaBancaria contaDestino = buscarContaPorNumero(numeroDestino);

        // A Propria Conta De Origem Executa A Regra De Transferencia
        contaOrigem.transferirPara(valor, contaDestino);

        // O Repository Salva O Novo Estado Da Conta De Origem
        contaRepository.salvar(contaOrigem);

        // O Repository Salva O Novo Estado Da Conta De Destino
        contaRepository.salvar(contaDestino);
    }

    // Este Metodo Demonstra Set Criando Uma Colecao De CPFs Sem Repeticao
    public Set<String> listarCpfsUnicosDosTitulares() {

        // HashSet E Uma Implementacao De Set Que Nao Aceita Valores Duplicados
        Set<String> cpfsUnicos = new HashSet<>();

        // Este For Each Percorre Todas As Contas Cadastradas
        for (ContaBancaria conta : contaRepository.listarTodas()) {

            // Esta Linha Busca O Cliente Da Conta E Depois Busca O CPF Desse Cliente
            String cpf = conta.getTitularDaConta().getCpf();

            // Add Adiciona O CPF E Ignora Repeticoes Porque Set Mantem Valores Unicos
            cpfsUnicos.add(cpf);
        }

        // Return Devolve O Set Pronto Para Quem Chamou O Metodo
        return cpfsUnicos;
    }
}

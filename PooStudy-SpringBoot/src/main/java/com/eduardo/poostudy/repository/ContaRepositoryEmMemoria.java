package com.eduardo.poostudy.repository;

import com.eduardo.poostudy.model.ContaBancaria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


// Esta Classe Implementa O Repository Utilizando Apenas A Memoria Do Programa
// Depois Ela Podera Ser Substituida Por Uma Implementacao Que Converse Com PostgreSQL
@Repository
public class ContaRepositoryEmMemoria implements ContaRepository {

    // Map Guarda Pares No Formato Chave E Valor
    // A Chave Sera O Numero Da Conta E O Valor Sera O Objeto ContaBancaria
    private final Map<Integer, ContaBancaria> contasPorNumero;

    // Este Construtor Cria O Map Vazio Quando O Repository Nasce
    public ContaRepositoryEmMemoria() {

        // LinkedHashMap E Uma Implementacao De Map Que Mantem A Ordem De Insercao
        this.contasPorNumero = new LinkedHashMap<>();
    }

    // Override Implementa O Metodo Exigido Pela Interface ContaRepository
    @Override
    public void salvar(ContaBancaria conta) {

        // Put Usa O Numero Da Conta Como Chave E Guarda O Objeto Como Valor
        contasPorNumero.put(conta.getNumeroDaConta(), conta);
    }

    // Override Implementa A Busca Pelo Numero Da Conta
    @Override
    public ContaBancaria buscarPorNumero(int numeroDaConta) {

        // Get Procura Diretamente O Valor Ligado A Chave Recebida
        return contasPorNumero.get(numeroDaConta);
    }

    // Override Implementa A Listagem De Todas As Contas
    @Override
    public List<ContaBancaria> listarTodas() {

        // Values Devolve Os Valores Do Map E ArrayList Cria Uma List Com Esses Objetos
        return new ArrayList<>(contasPorNumero.values());
    }

    // Override Implementa A Remocao Pelo Numero Da Conta
    @Override
    public boolean removerPorNumero(int numeroDaConta) {

        // Remove Devolve O Objeto Removido Ou Null Quando A Chave Nao Existe
        return contasPorNumero.remove(numeroDaConta) != null;
    }

    // Override Implementa A Verificacao De Existencia Da Conta
    @Override
    public boolean existePorNumero(int numeroDaConta) {

        // ContainsKey Verifica Se O Map Possui A Chave Informada
        return contasPorNumero.containsKey(numeroDaConta);
        // Cu
    }
}

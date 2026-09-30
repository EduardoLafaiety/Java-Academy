package com.eduardo.poostudy.repository;

import com.eduardo.poostudy.model.ContaBancaria;
import java.util.List;


// Esta Interface Define O Contrato De Persistencia Das Contas
// Hoje Teremos Uma Implementacao Em Memoria E Depois Poderemos Criar Uma Implementacao Com PostgreSQL
public interface ContaRepository {

    // Este Metodo Salva Ou Atualiza Uma Conta No Local De Armazenamento
    void salvar(ContaBancaria conta);

    // Este Metodo Busca Uma Conta Pelo Numero E Pode Retornar Null Quando Ela Nao Existe
    ContaBancaria buscarPorNumero(int numeroDaConta);

    // Este Metodo Devolve Todas As Contas Como Uma List
    List<ContaBancaria> listarTodas();

    // Este Metodo Remove Uma Conta Pelo Numero E Informa Se A Remocao Aconteceu
    boolean removerPorNumero(int numeroDaConta);

    // Este Metodo Informa Se Ja Existe Uma Conta Com O Numero Recebido
    boolean existePorNumero(int numeroDaConta);
}

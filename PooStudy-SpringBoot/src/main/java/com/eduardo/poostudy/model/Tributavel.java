package com.eduardo.poostudy.model;

// Interface Representa Um Contrato Para Objetos Que Sabem Calcular Imposto
public interface Tributavel {


    // Toda Classe Que Implementar Tributavel Deve Fornecer Este Comportamento
    double calcularImposto();
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author vinic
 */
public class Participante extends Usuario {
    // Atributo específico do Participante para armazenar o total de pontos do ranking
    private int pontosAcumulados;

    // Construtor que recebe nome e email (repassados para a superclasse) e inicializa os pontos em 0
    public Participante(String nome, String email) {
        super(nome, email);
        this.pontosAcumulados = 0; // Todo participante começa o campeonato com zero pontos
    }

    // Métodos Getters e Setters para o atributo de pontos
    public int getPontosAcumulados() {return pontosAcumulados;}
    public void setPontosAcumulados(int pontosAcumulados) {this.pontosAcumulados = pontosAcumulados;}
    
    // Método auxiliar para adicionar novos pontos à pontuação existente do participante
    public void adicionarPontos(int pontos) {
        this.pontosAcumulados += pontos;
    }

    @Override
    public String toString() {
        return "[Participante] " + super.toString() + " | Pontos: " + pontosAcumulados;
    }
}

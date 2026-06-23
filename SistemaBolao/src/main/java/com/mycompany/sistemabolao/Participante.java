/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinic
 */
public class Participante extends Usuario {

    // Atributo específico do Participante para armazenar o total de pontos do ranking
    private int pontosAcumulados;

    // Lista de palpites registrados pelo participante
    private List<Palpite> palpites;

    // Construtor que recebe nome e email (repassados para a superclasse) e inicializa os pontos em 0
    public Participante(String nome, String email) {
        super(nome, email);
        this.pontosAcumulados = 0; // Todo participante começa o campeonato com zero pontos
        this.palpites = new ArrayList<>();
    }

    // Registra um novo palpite para este participante
    public void registrarPalpite(Palpite palpite) {
        this.palpites.add(palpite);
    }

    // Retorna o palpite feito em um jogo específico, ou null se não houver
    public Palpite getPalpitePorJogo(Jogo jogo) {
        for (Palpite p : palpites) {
            if (p.getJogo() == jogo) {
                return p;
            }
        }
        return null;
    }

    // Métodos Getters e Setters para o atributo de pontos
    public int getPontosAcumulados() { return pontosAcumulados; }
    public void setPontosAcumulados(int pontosAcumulados) { this.pontosAcumulados = pontosAcumulados; }

    // Método auxiliar para adicionar novos pontos à pontuação existente do participante
    public void adicionarPontos(int pontos) {
        this.pontosAcumulados += pontos;
    }

    public List<Palpite> getPalpites() {
        return palpites;
    }

    @Override
    public String toString() {
        return "[Participante] " + super.toString()
             + " | Pontos: " + pontosAcumulados
             + " | Palpites: " + palpites.size();
    }
}
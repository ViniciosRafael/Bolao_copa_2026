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
// Participante estende Usuario e adiciona pontos e palpites específicos do bolão
public class Participante extends Usuario {

    // Total de pontos conquistados pelo participante no ranking
    private int pontosAcumulados;

    // Palpites registrados pelo participante durante o bolão
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

    // Retorna o número de pontos acumulados pelo participante
    public int getPontosAcumulados() { return pontosAcumulados; }
    public void setPontosAcumulados(int pontosAcumulados) { this.pontosAcumulados = pontosAcumulados; }

    // Incrementa a pontuação do participante de forma acumulativa
    public void adicionarPontos(int pontos) {
        this.pontosAcumulados += pontos;
    }

    // Retorna a lista de palpites deste participante
    public List<Palpite> getPalpites() {
        return palpites;
    }

    // Este método fornece a descrição em texto do participante.
    // Primeiro ele adiciona o prefixo [Participante] para identificar o tipo.
    // Em seguida, reutiliza a representação da superclasse Usuario com super.toString().
    // Por fim, acrescenta o total de pontos acumulados e a quantidade de palpites.
    // A anotação @Override garante que essa versão substitui o toString() herdado.
    @Override
    public String toString() {
        return "[Participante] " + super.toString()
             + " | Pontos: " + pontosAcumulados
             + " | Palpites: " + palpites.size();
    }
}
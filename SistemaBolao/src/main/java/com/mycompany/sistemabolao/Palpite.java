/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author laura
 */
public class Palpite {

    // Identificador único do palpite
    private int id;

    // Referências que contextualizam o palpite: quem apostou e em qual jogo
    private Participante participante;
    private Jogo jogo;

    // Placar previsto pelo participante
    private int placarMandantePrevisto;
    private int placarVisitantePrevisto;
    private int pontuacaoObtida;

    // Jogador apostado pelo participante para marcar gol no jogo (pode ser null)
    private String jogadorApostado;


    public Palpite(int id, Participante participante, Jogo jogo,
                   int placarMandantePrevisto, int placarVisitantePrevisto) {
        this.id = id;
        this.participante = participante;
        this.jogo = jogo;
        this.placarMandantePrevisto = placarMandantePrevisto;
        this.placarVisitantePrevisto = placarVisitantePrevisto;
        this.pontuacaoObtida = 0;
        this.jogadorApostado = null;
    }

    public Palpite(int id, Participante participante, Jogo jogo,
                   int placarMandantePrevisto, int placarVisitantePrevisto,
                   String jogadorApostado) {
        this.id = id;
        this.participante = participante;
        this.jogo = jogo;
        this.placarMandantePrevisto = placarMandantePrevisto;
        this.placarVisitantePrevisto = placarVisitantePrevisto;
        this.pontuacaoObtida = 0;
        this.jogadorApostado = jogadorApostado;
    }

    public int calcularPontuacao(Pontuacao[] regras, ResultadoGols resultado) {
        int total = 0;

        // Passa por cada regra e soma os pontos que cada uma dá
        // Agora usa o próprio jogo interno, sem precisar recebê-lo como parâmetro
        for (Pontuacao regra : regras) {
            total += regra.calcular(this, this.jogo, resultado);
        }

        // Salva a pontuação calculada no próprio palpite
        this.pontuacaoObtida = total;
        return total;
    }

    public int getId() {
        return id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public int getPlacarMandantePrevisto() {
        return placarMandantePrevisto;
    }

    public void setPlacarMandantePrevisto(int placarMandantePrevisto) {
        this.placarMandantePrevisto = placarMandantePrevisto;
    }

    public int getPlacarVisitantePrevisto() {
        return placarVisitantePrevisto;
    }

    public void setPlacarVisitantePrevisto(int placarVisitantePrevisto) {
        this.placarVisitantePrevisto = placarVisitantePrevisto;
    }

    public int getPontuacaoObtida() {
        return pontuacaoObtida;
    }

    public String getJogadorApostado() {
        return jogadorApostado;
    }

    public void setJogadorApostado(String jogadorApostado) {
        this.jogadorApostado = jogadorApostado;
    }

    @Override
    public String toString() {
        String jogador = jogadorApostado != null ? " | Jogador: " + jogadorApostado : "";
        return "Palpite #" + id
             + " | " + participante.getNome()
             + " → " + jogo.toString()
             + " | Placar previsto: " + placarMandantePrevisto + " x " + placarVisitantePrevisto
             + jogador
             + " | Pontos: " + pontuacaoObtida;
    }
}
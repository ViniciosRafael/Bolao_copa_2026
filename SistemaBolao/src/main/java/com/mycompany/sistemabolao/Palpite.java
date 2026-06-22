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

    // Placar previsto pelo participante
    private int placarMandantePrevisto;
    private int placarVisitantePrevisto;
    private int pontuacaoObtida;


    public Palpite(int id, int placarMandantePrevisto, int placarVisitantePrevisto) {
        this.id = id;
        this.placarMandantePrevisto = placarMandantePrevisto;
        this.placarVisitantePrevisto = placarVisitantePrevisto;
        this.pontuacaoObtida = 0; 
    }

    public int calcularPontuacao(Pontuacao[] regras, Jogo jogo, ResultadoGols resultado) {
        int total = 0;

        // Passa por cada regra e soma os pontos que cada uma dá
        for (Pontuacao regra : regras) {
            total += regra.calcular(this, jogo, resultado);
        }

        // Salva a pontuação calculada no próprio palpite
        this.pontuacaoObtida = total;
        return total;
    }

    public int getId() {
        return id;
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


    @Override
    public String toString() {
        return "Palpite #" + id + " | " 
             + placarMandantePrevisto + " x " + placarVisitantePrevisto
             + " | Pontos: " + pontuacaoObtida;
    }
}

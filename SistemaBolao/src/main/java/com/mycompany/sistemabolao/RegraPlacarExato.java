/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author vinic
 */
public class RegraPlacarExato implements Pontuacao {
    
    private static final int PONTOS_PLACAR_EXATO = 5; // Valor de exemplo

    @Override
    public int calcular(Palpite palpite, Jogo jogo, ResultadoGols resultado) {
        boolean acertouMandante = palpite.getPlacarMandantePrevisto() == jogo.getPlacarMandante();
        boolean acertouVisitante = palpite.getPlacarVisitantePrevisto() == jogo.getPlacarVisitante();
        
        if (acertouMandante && acertouVisitante) {
            return PONTOS_PLACAR_EXATO;
        }
        
        return 0;
    }
}
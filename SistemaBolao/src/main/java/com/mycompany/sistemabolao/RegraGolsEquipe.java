/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author vinic
 */
public class RegraGolsEquipe implements Pontuacao {
    private static final int PONTOS_POR_EQUIPE = 2; // Valor de exemplo

    @Override
    public int calcular(Palpite palpite, Jogo jogo, ResultadoGols resultado) {
        int pontos = 0;
        
        // Acertou os gols que o mandante faria
        if (palpite.getPlacarMandantePrevisto() == jogo.getPlacarMandante()) {
            pontos += PONTOS_POR_EQUIPE;
        }
        
        // Acertou os gols que o visitante faria
        if (palpite.getPlacarVisitantePrevisto() == jogo.getPlacarVisitante()) {
            pontos += PONTOS_POR_EQUIPE;
        }
        
        return pontos;
    }
}

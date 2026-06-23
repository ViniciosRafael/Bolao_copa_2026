/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author vinic
 */
public class RegraVencedor implements Pontuacao {

    private static final int PONTOS_VENCEDOR = 3; // Valor de exemplo

    @Override
    public int calcular(Palpite palpite, Jogo jogo, ResultadoGols resultado) {
        int saldoReal = jogo.getPlacarMandante() - jogo.getPlacarVisitante();
        int saldoPalpite = palpite.getPlacarMandantePrevisto() - palpite.getPlacarVisitantePrevisto();
        
        // Se ambos os saldos forem positivos (Mandante venceu), 
        // ambos negativos (Visitante venceu),
        // ou ambos iguais a zero (Empate)
        if ((saldoReal > 0 && saldoPalpite > 0) || 
            (saldoReal < 0 && saldoPalpite < 0) || 
            (saldoReal == 0 && saldoPalpite == 0)) {
            return PONTOS_VENCEDOR;
        }
        
        return 0;
    }
}

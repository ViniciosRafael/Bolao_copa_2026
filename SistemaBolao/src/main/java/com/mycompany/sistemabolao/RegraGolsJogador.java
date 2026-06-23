/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author laura
 */
public class RegraGolsJogador implements Pontuacao {

    // Pontos dados por cada gol marcado pelo jogador apostado
    private static final int PONTOS_POR_GOL = 2;

    /**
     * Pontua com base no jogador apostado pelo participante no palpite.
     * Para cada gol que o jogador apostado marcar no jogo real,
     * o participante recebe PONTOS_POR_GOL pontos.
     * Se nenhum jogador foi apostado, a regra não pontua.
     * @param palpite
     */
    @Override
    public int calcular(Palpite palpite, Jogo jogo, ResultadoGols resultado) {
        String jogadorApostado = palpite.getJogadorApostado();

        // Sem jogador apostado → regra não se aplica
        if (jogadorApostado == null || jogadorApostado.isBlank()) {
            return 0;
        }

        // Busca quantos gols o jogador apostado marcou de verdade
        int golsReais = resultado.getGols(jogadorApostado);

        // Pontua por cada gol do jogador apostado
        return golsReais * PONTOS_POR_GOL;
    }
}
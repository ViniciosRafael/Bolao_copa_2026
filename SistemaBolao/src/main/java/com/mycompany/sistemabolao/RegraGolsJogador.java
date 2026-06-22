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

    // Pontos dados por cada gol acertado
    private static final int PONTOS_POR_GOL_ACERTADO = 2;

    @Override
    public int calcular(Palpite palpite, Jogo jogo, ResultadoGols resultado) {
        int pontos = 0;

        // Verifica se o total de gols do mandante foi acertado
        int golsMandanteReal = jogo.getPlacarMandante();
        int golsVisitanteReal = jogo.getPlacarVisitante();

        // Acertou o placar do mandante?
        if (palpite.getPlacarMandantePrevisto() == golsMandanteReal) {
            pontos += PONTOS_POR_GOL_ACERTADO;
        }

        // Acertou o placar do visitante?
        if (palpite.getPlacarVisitantePrevisto() == golsVisitanteReal) {
            pontos += PONTOS_POR_GOL_ACERTADO;
        }

        // Acertou o placar EXATO (mandante e visitante)?
        boolean acertouExato = palpite.getPlacarMandantePrevisto() == golsMandanteReal
                            && palpite.getPlacarVisitantePrevisto() == golsVisitanteReal;
        if (acertouExato) {
            pontos += 5; // Bônus por placar exato
        }

        for (GolsPorJogador gols : resultado.getGolsDoJogo()) {
            pontos += gols.getQuantidadeGols();
        }

        return pontos;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laura
 */
public class ResultadoGols {

    // Lista com os gols de cada jogador que marcou no jogo
    private List<GolsPorJogador> golsDoJogo;

    public ResultadoGols() {
        this.golsDoJogo = new ArrayList<>();
    }
     
    public void adicionarGols(GolsPorJogador gols) {
        golsDoJogo.add(gols);
    }

    public int getGols(String nomeJogador) {
        for (GolsPorJogador gols : golsDoJogo) {
            if (gols.getNomeJogador().equalsIgnoreCase(nomeJogador)) {
                return gols.getQuantidadeGols();
            }
        }
        return 0;
    }

    public List<GolsPorJogador> getGolsDoJogo() {
        return golsDoJogo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ResultadoGols:\n");
        for (GolsPorJogador g : golsDoJogo) {
            sb.append("  ").append(g).append("\n");
        }
        return sb.toString();
    }
}
package com.mycompany.sistemabolao;

import java.util.List;

/**
 * Responsável por toda a exibição de resultados do sistema.
 * Mantém a lógica de apresentação separada da lógica de negócio.
 *
 * @author vinic
 */
public class ExibicaoSistema {

    public void exibirSelecoes(List<Selecao> selecoes) {
        System.out.println("\n--- SELEÇÕES CADASTRADAS ---");
        if (selecoes.isEmpty()) {
            System.out.println("  Nenhuma seleção cadastrada.");
            return;
        }
        for (Selecao s : selecoes) {
            System.out.println("  " + s);
            for (Jogador j : s.getJogadores()) {
                System.out.println("      " + j);
            }
        }
    }

    public void exibirJogos(List<Jogo> jogos) {
        System.out.println("\n--- JOGOS CADASTRADOS ---");
        if (jogos.isEmpty()) {
            System.out.println("  Nenhum jogo cadastrado.");
            return;
        }
        for (int i = 0; i < jogos.size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + jogos.get(i));
        }
    }

    public void exibirParticipantes(List<Participante> participantes) {
        System.out.println("\n--- PARTICIPANTES CADASTRADOS ---");
        if (participantes.isEmpty()) {
            System.out.println("  Nenhum participante cadastrado.");
            return;
        }
        for (Participante p : participantes) {
            System.out.println("  " + p);
        }
    }

    public void exibirPalpites(List<Participante> participantes) {
        System.out.println("\n--- PALPITES REGISTRADOS ---");
        boolean algumPalpite = false;
        for (Participante p : participantes) {
            if (!p.getPalpites().isEmpty()) {
                algumPalpite = true;
                System.out.println("  " + p.getNome() + ":");
                for (Palpite palpite : p.getPalpites()) {
                    System.out.println("      " + palpite);
                }
            }
        }
        if (!algumPalpite) {
            System.out.println("  Nenhum palpite registrado.");
        }
    }

    public void exibirRanking(List<Participante> participantes) {
        System.out.println("\n--- RANKING DO BOLÃO ---");
        Ranking ranking = new Ranking();
        ranking.atualizarRanking(participantes);
        ranking.exibirRanking();
    }

    public void exibirTudo(List<Selecao> selecoes, List<Jogo> jogos,
                           List<Participante> participantes) {
        System.out.println("\n===========================================");
        System.out.println("         VISÃO GERAL DO BOLÃO             ");
        System.out.println("===========================================");
        exibirSelecoes(selecoes);
        exibirJogos(jogos);
        exibirParticipantes(participantes);
        exibirPalpites(participantes);
        exibirRanking(participantes);
        System.out.println("\n===========================================\n");
    }
}
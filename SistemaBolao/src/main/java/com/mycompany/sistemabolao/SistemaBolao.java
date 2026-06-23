package com.mycompany.sistemabolao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal do Bolão Copa 2026.
 * Demonstra o fluxo completo: cadastro → palpites → resultado → pontuação → ranking.
 *
 * @author vinic
 */
public class SistemaBolao {

    public static void main(String[] args) {
        System.out.println("       BEM-VINDO AO BOLÃO COPA 2026       ");

        // ---------------------------------------------------------------
        // 1. CADASTRO DAS SELEÇÕES E JOGADORES
        // ---------------------------------------------------------------
        System.out.println(">>> [1] Cadastrando seleções e jogadores...");

        Selecao brasil = new Selecao("Brasil", "BRA");
        brasil.adicionarJogador(new Jogador("Vinicius Jr",  "Atacante",  7, 1.5));
        brasil.adicionarJogador(new Jogador("Rodrygo",      "Atacante", 11, 1.2));
        brasil.adicionarJogador(new Jogador("Alisson",      "Goleiro",   1, 1.0));

        Selecao argentina = new Selecao("Argentina", "ARG");
        argentina.adicionarJogador(new Jogador("Messi",     "Atacante", 10, 2.0));
        argentina.adicionarJogador(new Jogador("Di Maria",  "Atacante", 11, 1.3));
        argentina.adicionarJogador(new Jogador("Martinez",  "Goleiro",   1, 1.0));

        System.out.println("  Seleção A: " + brasil);
        System.out.println("  Jogadores: " + brasil.getJogadores());
        System.out.println("  Seleção B: " + argentina);
        System.out.println("  Jogadores: " + argentina.getJogadores() + "\n");
        System.out.println(">>> [2] Cadastrando jogo...");

        Jogo jogo = new Jogo(brasil, argentina, LocalDateTime.of(2026, 7, 19, 20, 0));
        System.out.println("  Jogo: " + jogo + "\n");
        System.out.println(">>> [3] Cadastrando participantes...");

        Participante joao  = new Participante("João",  "joao@email.com");
        Participante maria = new Participante("Maria", "maria@email.com");
        Participante pedro = new Participante("Pedro", "pedro@email.com");

        List<Participante> participantes = new ArrayList<>();
        participantes.add(joao);
        participantes.add(maria);
        participantes.add(pedro);

        participantes.forEach(p -> System.out.println("  " + p));
        System.out.println();
        System.out.println(">>> [4] Registrando palpites...");

        // João: Brasil 2 x 1 Argentina, aposta em Vinicius Jr
        Palpite palpiteJoao = new Palpite(1, joao, jogo, 2, 1, "Vinicius Jr");
        joao.registrarPalpite(palpiteJoao);

        // Maria: Brasil 1 x 1 Argentina, aposta em Messi
        Palpite palpiteMaria = new Palpite(2, maria, jogo, 1, 1, "Messi");
        maria.registrarPalpite(palpiteMaria);

        // Pedro: Brasil 3 x 0 Argentina, aposta em Rodrygo
        Palpite palpitePedro = new Palpite(3, pedro, jogo, 3, 0, "Rodrygo");
        pedro.registrarPalpite(palpitePedro);

        System.out.println("  " + palpiteJoao);
        System.out.println("  " + palpiteMaria);
        System.out.println("  " + palpitePedro);
        System.out.println();

        System.out.println(">>> [5] Definindo resultado real do jogo...");

        // Resultado: Brasil 2 x 1 Argentina
        jogo.definirResultado(2, 1);

        // Gols reais por jogador
        ResultadoGols resultado = new ResultadoGols();
        resultado.adicionarGols(new GolsPorJogador("Vinicius Jr", 2));
        resultado.adicionarGols(new GolsPorJogador("Messi", 1));

        System.out.println("  Resultado: " + jogo);
        System.out.println("  " + resultado);
        System.out.println(">>> [6] Calculando pontuações...");

        Pontuacao[] regras = {
            new RegraVencedor(),
            new RegraGolsEquipe(),
            new RegraPlacarExato(),
            new RegraGolsJogador()
        };

        for (Participante p : participantes) {
            Palpite palpite = p.getPalpitePorJogo(jogo);
            if (palpite != null) {
                int pontos = palpite.calcularPontuacao(regras, resultado);
                p.adicionarPontos(pontos);
                System.out.println("  " + palpite);
            }
        }
        System.out.println(">>> [7] Ranking final:");

        Ranking ranking = new Ranking();
        ranking.atualizarRanking(participantes);
        ranking.exibirRanking();
    }
}
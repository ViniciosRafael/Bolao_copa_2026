package com.mycompany.sistemabolao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Responsável pelo menu interativo do Bolão Copa 2026.
 * Gerencia o fluxo de cadastros, palpites e resultados via console.
 *
 * @author vinic
 */
public class MenuSistema{

    private final Scanner scanner = new Scanner(System.in);
    private final ExibicaoSistema exibicao = new ExibicaoSistema();

    private final List<Selecao>      selecoes      = new ArrayList<>();
    private final List<Jogo>         jogos         = new ArrayList<>();
    private final List<Participante> participantes = new ArrayList<>();

    private final Pontuacao[] regras = {
        new RegraVencedor(),
        new RegraGolsEquipe(),
        new RegraPlacarExato(),
        new RegraGolsJogador()
    };

    // ---------------------------------------------------------------
    // MENU PRINCIPAL
    // ---------------------------------------------------------------
    public void iniciar() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===========================================");
            System.out.println("         BOLÃO COPA 2026 — MENU           ");
            System.out.println("===========================================");
            System.out.println("  [1] Cadastrar seleção");
            System.out.println("  [2] Cadastrar jogo");
            System.out.println("  [3] Cadastrar participante");
            System.out.println("  [4] Registrar palpite");
            System.out.println("  [5] Registrar resultado de jogo");
            System.out.println("  [6] Visualizar tudo");
            System.out.println("  [0] Sair");
            System.out.println("===========================================");
            System.out.print("  Escolha uma opção: ");

            opcao = lerInt();
            switch (opcao) {
                case 1 -> cadastrarSelecao();
                case 2 -> cadastrarJogo();
                case 3 -> cadastrarParticipante();
                case 4 -> registrarPalpite();
                case 5 -> registrarResultado();
                case 6 -> exibicao.exibirTudo(selecoes, jogos, participantes);
                case 0 -> System.out.println("\n  Até a próxima! \n");
                default -> System.out.println("  Opção inválida. Tente novamente.");
            }
        }
    }

    // ---------------------------------------------------------------
    // [1] CADASTRAR SELEÇÃO
    // ---------------------------------------------------------------
    private void cadastrarSelecao() {
        System.out.println("\n--- CADASTRAR SELEÇÃO ---");
        System.out.print("  Nome da seleção: ");
        String nome = scanner.nextLine().trim();
        System.out.print("  Sigla (ex: BRA): ");
        String sigla = scanner.nextLine().trim().toUpperCase();

        Selecao selecao = new Selecao(nome, sigla);

        System.out.print("  Quantos jogadores deseja cadastrar? ");
        int qtd = lerInt();
        for (int i = 0; i < qtd; i++) {
            System.out.println("  Jogador " + (i + 1) + ":");
            System.out.print("    Nome: ");
            String nomeJ = scanner.nextLine().trim();
            System.out.print("    Posição: ");
            String posicao = scanner.nextLine().trim();
            System.out.print("    Número da camisa: ");
            int camisa = lerInt();
            System.out.print("    Peso de pontuação (ex: 1.0 padrão, 1.5 artilheiro): ");
            double peso = lerDouble();
            selecao.adicionarJogador(new Jogador(nomeJ, posicao, camisa, peso));
        }

        selecoes.add(selecao);
        System.out.println("  Seleção " + selecao + " cadastrada com sucesso!");
    }

    // ---------------------------------------------------------------
    // [2] CADASTRAR JOGO
    // ---------------------------------------------------------------
    private void cadastrarJogo() {
        System.out.println("\n--- CADASTRAR JOGO ---");
        if (selecoes.size() < 2) {
            System.out.println("  Cadastre pelo menos 2 seleções antes de criar um jogo.");
            return;
        }

        exibicao.exibirSelecoes(selecoes);

        System.out.print("  Número da seleção mandante: ");
        Selecao mandante = selecionarSelecao();
        if (mandante == null) return;

        System.out.print("  Número da seleção visitante: ");
        Selecao visitante = selecionarSelecao();
        if (visitante == null) return;

        if (mandante == visitante) {
            System.out.println("  Mandante e visitante não podem ser a mesma seleção.");
            return;
        }

        System.out.print("  Ano (ex: 2026): ");  int ano = lerInt();
        System.out.print("  Mês (1-12): ");       int mes = lerInt();
        System.out.print("  Dia: ");              int dia = lerInt();
        System.out.print("  Hora (0-23): ");      int hora = lerInt();
        System.out.print("  Minuto (0-59): ");    int minuto = lerInt();

        Jogo jogo = new Jogo(mandante, visitante, LocalDateTime.of(ano, mes, dia, hora, minuto));
        jogos.add(jogo);
        System.out.println("   Jogo cadastrado: " + jogo);
    }

    // ---------------------------------------------------------------
    // [3] CADASTRAR PARTICIPANTE
    // ---------------------------------------------------------------
    private void cadastrarParticipante() {
        System.out.println("\n--- CADASTRAR PARTICIPANTE ---");
        System.out.print("  Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("  Email: ");
        String email = scanner.nextLine().trim();

        participantes.add(new Participante(nome, email));
        System.out.println("   Participante " + nome + " cadastrado com sucesso!");
    }

    // ---------------------------------------------------------------
    // [4] REGISTRAR PALPITE
    // ---------------------------------------------------------------
    private void registrarPalpite() {
        System.out.println("\n--- REGISTRAR PALPITE ---");
        if (jogos.isEmpty()) {
            System.out.println(" Nenhum jogo cadastrado.");
            return;
        }
        if (participantes.isEmpty()) {
            System.out.println("  Nenhum participante cadastrado.");
            return;
        }

        exibicao.exibirParticipantes(participantes);
        System.out.print("  Número do participante: ");
        Participante participante = selecionarParticipante();
        if (participante == null) return;

        exibicao.exibirJogos(jogos);
        System.out.print("  Número do jogo: ");
        Jogo jogo = selecionarJogo();
        if (jogo == null) return;

        if (jogo.isFinalizado()) {
            System.out.println("   Este jogo já foi finalizado. Não é possível registrar palpite.");
            return;
        }

        if (participante.getPalpitePorJogo(jogo) != null) {
            System.out.println(participante.getNome() + " já possui palpite para este jogo.");
            return;
        }

        System.out.print("  Gols " + jogo.getSelecaoA().getNome() + " (mandante): ");
        int golsMandante = lerInt();
        System.out.print("  Gols " + jogo.getSelecaoB().getNome() + " (visitante): ");
        int golsVisitante = lerInt();

        System.out.print("  Jogador apostado para marcar gol (Enter para pular): ");
        String jogadorApostado = scanner.nextLine().trim();

        int id = participante.getPalpites().size() + 1;
        Palpite palpite = jogadorApostado.isEmpty()
            ? new Palpite(id, participante, jogo, golsMandante, golsVisitante)
            : new Palpite(id, participante, jogo, golsMandante, golsVisitante, jogadorApostado);

        participante.registrarPalpite(palpite);
        System.out.println("   Palpite registrado: " + palpite);
    }

    // ---------------------------------------------------------------
    // [5] REGISTRAR RESULTADO
    // ---------------------------------------------------------------
    private void registrarResultado() {
        System.out.println("\n--- REGISTRAR RESULTADO ---");
        if (jogos.isEmpty()) {
            System.out.println("   Nenhum jogo cadastrado.");
            return;
        }

        exibicao.exibirJogos(jogos);
        System.out.print("  Número do jogo: ");
        Jogo jogo = selecionarJogo();
        if (jogo == null) return;

        if (jogo.isFinalizado()) {
            System.out.println("  Este jogo já possui resultado registrado.");
            return;
        }

        System.out.print("  Gols " + jogo.getSelecaoA().getNome() + " (mandante): ");
        int golsA = lerInt();
        System.out.print("  Gols " + jogo.getSelecaoB().getNome() + " (visitante): ");
        int golsB = lerInt();
        jogo.definirResultado(golsA, golsB);

        ResultadoGols resultado = new ResultadoGols();
        System.out.print("  Quantos jogadores marcaram gol? ");
        int qtd = lerInt();
        for (int i = 0; i < qtd; i++) {
            System.out.print("  Nome do jogador " + (i + 1) + ": ");
            String nomeJ = scanner.nextLine().trim();
            System.out.print("  Quantos gols marcou? ");
            int gols = lerInt();
            resultado.adicionarGols(new GolsPorJogador(nomeJ, gols));
        }

        // Calcula pontuação de todos os participantes que apostaram neste jogo
        for (Participante p : participantes) {
            Palpite palpite = p.getPalpitePorJogo(jogo);
            if (palpite != null) {
                int pontos = palpite.calcularPontuacao(regras, resultado);
                p.adicionarPontos(pontos);
            }
        }

        System.out.println("  Resultado registrado: " + jogo);
        System.out.println("  Pontuações dos participantes atualizadas!");
    }

    // ---------------------------------------------------------------
    // HELPERS DE SELEÇÃO
    // ---------------------------------------------------------------
    private Selecao selecionarSelecao() {
        int idx = lerInt() - 1;
        if (idx < 0 || idx >= selecoes.size()) {
            System.out.println("   Opção inválida.");
            return null;
        }
        return selecoes.get(idx);
    }

    private Jogo selecionarJogo() {
        int idx = lerInt() - 1;
        if (idx < 0 || idx >= jogos.size()) {
            System.out.println(" Opção inválida.");
            return null;
        }
        return jogos.get(idx);
    }

    private Participante selecionarParticipante() {
        int idx = lerInt() - 1;
        if (idx < 0 || idx >= participantes.size()) {
            System.out.println("   Opção inválida.");
            return null;
        }
        return participantes.get(idx);
    }

    // ---------------------------------------------------------------
    // HELPERS DE LEITURA
    // ---------------------------------------------------------------
    private int lerInt() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("  Valor inválido. Digite um número inteiro: ");
            }
        }
    }

    private double lerDouble() {
        while (true) {
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("  Valor inválido. Digite um número (ex: 1.5): ");
            }
        }
    }
}
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

    // Lê as entradas digitadas pelo usuário no console
    private final Scanner scanner = new Scanner(System.in);
    // Usado para exibir listas e informações formatadas do bolão
    private final ExibicaoSistema exibicao = new ExibicaoSistema();

    // Armazena as seleções cadastradas pelo usuário
    private final List<Selecao>      selecoes      = new ArrayList<>();
    // Armazena os jogos cadastrados no sistema
    private final List<Jogo>         jogos         = new ArrayList<>();
    // Armazena os participantes do bolão
    private final List<Participante> participantes = new ArrayList<>();

    // Conjunto de regras de pontuação aplicadas aos palpites
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
        // Inicia a variável de opção com valor que força a entrada no loop
        int opcao = -1;
        // Mantém o menu em execução até o usuário escolher sair (opção 0)
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

            // Lê a opção numérica escolhida pelo usuário
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

        // Cria uma nova seleção com nome e sigla informados
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
            // Adiciona cada jogador à seleção recém-criada
            selecao.adicionarJogador(new Jogador(nomeJ, posicao, camisa, peso));
        }

        // Salva a seleção no cadastro do sistema
        selecoes.add(selecao);
        System.out.println("  Seleção " + selecao + " cadastrada com sucesso!");
    }

    // ---------------------------------------------------------------
    // [2] CADASTRAR JOGO
    // ---------------------------------------------------------------
    private void cadastrarJogo() {
        System.out.println("\n--- CADASTRAR JOGO ---");
        if (selecoes.size() < 2) {
            // Não permite criar jogo sem pelo menos duas seleções cadastradas
            System.out.println("  Cadastre pelo menos 2 seleções antes de criar um jogo.");
            return;
        }

        // Exibe as seleções já cadastradas para o usuário escolher
        exibicao.exibirSelecoes(selecoes);

        System.out.print("  Número da seleção mandante: ");
        Selecao mandante = selecionarSelecao();
        if (mandante == null) return;

        System.out.print("  Número da seleção visitante: ");
        Selecao visitante = selecionarSelecao();
        if (visitante == null) return;

        // Verifica se o usuário não escolheu a mesma seleção para os dois lados
        if (mandante == visitante) {
            System.out.println("  Mandante e visitante não podem ser a mesma seleção.");
            return;
        }

        System.out.print("  Ano (ex: 2026): ");  int ano = lerInt();
        System.out.print("  Mês (1-12): ");       int mes = lerInt();
        System.out.print("  Dia: ");              int dia = lerInt();
        System.out.print("  Hora (0-23): ");      int hora = lerInt();
        System.out.print("  Minuto (0-59): ");    int minuto = lerInt();

        // Cria o jogo com data/hora e equipes informadas
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

        // Cria um novo participante e adiciona à lista de participantes
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

        // Exibe participantes existentes para o usuário escolher
        exibicao.exibirParticipantes(participantes);
        System.out.print("  Número do participante: ");
        Participante participante = selecionarParticipante();
        if (participante == null) return;

        // Exibe jogos existentes para selecionar em qual partida o palpite será feito
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
        // Cria o palpite com ou sem jogador apostado
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

        // Exibe os jogos para o usuário selecionar o jogo finalizado
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

        // Para cada participante, calcula a pontuação do palpite baseado no resultado real
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
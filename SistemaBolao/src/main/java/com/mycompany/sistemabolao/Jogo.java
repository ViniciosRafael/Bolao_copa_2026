/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author vinic
 */
public class Jogo {

    // Atributos privados para encapsulamento
    private Selecao selecaoA;
    private Selecao selecaoB;
    private int golsSelecaoA;
    private int golsSelecaoB;
    private LocalDateTime dataHora; // Substituído java.util.Date pelo moderno java.time.LocalDateTime
    private boolean finalizado; // Indica se o administrador já inseriu o placar real

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Construtor para criar o jogo (inicialmente sem placar definido e não finalizado)
    public Jogo(Selecao selecaoA, Selecao selecaoB, LocalDateTime dataHora) {
        this.selecaoA = selecaoA;
        this.selecaoB = selecaoB;
        this.dataHora = dataHora;
        this.golsSelecaoA = 0;
        this.golsSelecaoB = 0;
        this.finalizado = false; // Só vira true quando o admin encerrar o jogo
    }

    // Método para o Administrador definir o resultado final do jogo
    public void definirResultado(int golsA, int golsB) {
        this.golsSelecaoA = golsA;
        this.golsSelecaoB = golsB;
        this.finalizado = true;
    }

    // Métodos Getters e Setters
    public Selecao getSelecaoA() { return selecaoA; }
    public void setSelecaoA(Selecao selecaoA) { this.selecaoA = selecaoA; }

    public Selecao getSelecaoB() { return selecaoB; }
    public void setSelecaoB(Selecao selecaoB) { this.selecaoB = selecaoB; }

    public int getGolsSelecaoA() { return golsSelecaoA; }
    public int getGolsSelecaoB() { return golsSelecaoB; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public boolean isFinalizado() { return finalizado; }

    public int getPlacarMandante() { return golsSelecaoA; }
    public int getPlacarVisitante() { return golsSelecaoB; }

    @Override
    public String toString() {
        String placar = finalizado ? golsSelecaoA + " x " + golsSelecaoB : "Agendado";
        String data = dataHora != null ? " [" + dataHora.format(FORMATTER) + "]" : "";
        return selecaoA.getNome() + " vs " + selecaoB.getNome() + data + " (" + placar + ")";
    }
}
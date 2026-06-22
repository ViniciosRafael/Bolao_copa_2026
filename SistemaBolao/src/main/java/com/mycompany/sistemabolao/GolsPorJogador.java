/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author laura
 */
public class GolsPorJogador {

    private String nomeJogador;
    private int quantidadeGols;


    public GolsPorJogador(String nomeJogador, int quantidadeGols) {
        this.nomeJogador = nomeJogador;
        this.quantidadeGols = quantidadeGols;
    }


    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getQuantidadeGols() {
        return quantidadeGols;
    }

    public void setQuantidadeGols(int quantidadeGols) {
        this.quantidadeGols = quantidadeGols;
    }

    @Override
    public String toString() {
        return nomeJogador + ": " + quantidadeGols + " gol(s)";
    }
}

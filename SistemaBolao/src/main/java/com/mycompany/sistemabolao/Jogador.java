/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author vinic
 */
public class Jogador {

    // Atributos privados para encapsulamento
    private String nome;
    private String posicao;
    private int numeroCamisa;   // Presente no UML mas ausente no código original
    private double pesoPontuacao; // Multiplicador de bônus ao apostar neste jogador (ex: 1.0 = normal, 1.5 = artilheiro favorito)

    // Construtor completo
    public Jogador(String nome, String posicao, int numeroCamisa, double pesoPontuacao) {
        this.nome = nome;
        this.posicao = posicao;
        this.numeroCamisa = numeroCamisa;
        this.pesoPontuacao = pesoPontuacao;
    }

    // Construtor simplificado — peso padrão 1.0 (sem bônus)
    public Jogador(String nome, String posicao, int numeroCamisa) {
        this(nome, posicao, numeroCamisa, 1.0);
    }

    // Métodos Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getPosicao() { return posicao; }
    public void setPosicao(String posicao) { this.posicao = posicao; }

    public int getNumeroCamisa() { return numeroCamisa; }
    public void setNumeroCamisa(int numeroCamisa) { this.numeroCamisa = numeroCamisa; }

    public double getPesoPontuacao() { return pesoPontuacao; }
    public void setPesoPontuacao(double pesoPontuacao) { this.pesoPontuacao = pesoPontuacao; }

    @Override
    public String toString() {
        return "#" + numeroCamisa + " " + nome + " (" + posicao + ")"
             + (pesoPontuacao != 1.0 ? " [x" + pesoPontuacao + "]" : "");
    }
}
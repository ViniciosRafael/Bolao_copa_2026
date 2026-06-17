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
    private String posicao; // Inicialmente como String para simplificar seu modelo atual

    // Construtor
    public Jogador(String nome, String posicao) {
        this.nome = nome;
        this.posicao = posicao;
    }

    // Métodos Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    @Override
    public String toString() {
        return nome + " (" + posicao + ")";
    }
}

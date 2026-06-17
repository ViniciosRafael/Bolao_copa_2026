/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinic
 */
public class Selecao {
    // Atributos privados para encapsulamento
    private String nome;
    private List<Jogador> jogadores; // Lista que armazenará os atletas desta seleção

    // Construtor
    public Selecao(String nome) {
        this.nome = nome;
        this.jogadores = new ArrayList<>(); // Inicializa a lista vazia
    }

    // Método para o Administrador adicionar jogadores à seleção
    public void adicionarJogador(Jogador jogador) {
        this.jogadores.add(jogador);
    }

    // Métodos Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}

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
    private String sigla; // Ex: "BRA", "ARG", "FRA" — ausente no código original, presente no UML
    private List<Jogador> jogadores; // Lista que armazenará os atletas desta seleção

    // Construtor
    public Selecao(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
        this.jogadores = new ArrayList<>(); // Inicializa a lista vazia
    }

    // Método para o Administrador adicionar jogadores à seleção
    public void adicionarJogador(Jogador jogador) {
        this.jogadores.add(jogador);
    }

    // Métodos Getters e Setters
    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getSigla() {return sigla;}

    public void setSigla(String sigla) {this.sigla = sigla;}

    public List<Jogador> getJogadores() {return jogadores;}

    @Override
    public String toString() {
        return nome + " (" + sigla + ")";
    }
}
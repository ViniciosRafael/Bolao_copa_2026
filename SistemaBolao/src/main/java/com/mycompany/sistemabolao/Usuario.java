/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author vinic
 */
// Classe base abstrata que define os dados e comportamento comuns dos usuários
public abstract class Usuario {
    // Atributos privados comuns para encapsulamento
    private String nome;
    private String email;

    // Construtor completo usado por classes filhas como Administrador e Participante
    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // Métodos Getters e Setters para permitir o acesso controlado aos atributos
    public String getNome() {return nome;}
    public String getEmail() {return email;}

    public void setNome(String nome) {this.nome = nome;}
    public void setEmail(String email) {this.email = email;}

    // Representação textual básica do usuário, usada em exibição e logs
    @Override
    public String toString() {
        return "Nome: " + nome + " | Email: " + email;
    }
}

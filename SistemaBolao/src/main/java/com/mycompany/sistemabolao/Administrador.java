/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author vinic
 */
// Classe concreta que representa um usuário administrador do sistema
// Atualmente, o Administrador herda todos os atributos e comportamentos de Usuario
// sem adicionar campos extras, mas serve para distinguir perfis de usuário.
public class Administrador extends Usuario {
    // Construtor que herda os atributos da classe mãe (Usuario)
    public Administrador(String nome, String email) {
        super(nome, email);
    }

    // Adiciona um prefixo para identificar o tipo de usuário em saídas de texto
    @Override
    public String toString() {
        return "[Administrador] " + super.toString();
    }
}

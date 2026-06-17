/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author vinic
 */
public class Administrador extends Usuario {
    // Construtor que herda os atributos da classe mãe (Usuario)
    public Administrador(String nome, String email) {
        super(nome, email);
    }

    @Override
    public String toString() {
        return "[Administrador] " + super.toString();
    }
}

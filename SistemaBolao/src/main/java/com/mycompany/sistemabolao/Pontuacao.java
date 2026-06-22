/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

/**
 *
 * @author laura
 */

public interface Pontuacao {

    //Calcula a pontuação obtida para um palpite, dado um resultado real.
  
    int calcular(Palpite palpite, Jogo jogo, ResultadoGols resultado);
}

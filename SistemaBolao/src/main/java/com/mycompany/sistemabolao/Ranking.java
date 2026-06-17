/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabolao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author vinic
 */
public class Ranking {
    // Lista de participantes que farão parte do ranking
    private List<Participante> classificados;

    // Construtor
    public Ranking() {
        this.classificados = new ArrayList<>();
    }

    // Método para atualizar a lista com os participantes atuais do sistema
    public void atualizarRanking(List<Participante> participantes) {
        // Limpa a lista antiga e copia os participantes atuais
        this.classificados = new ArrayList<>(participantes);
        
        // Ordena a lista do maior para o menor ponto usando um Comparator
        Collections.sort(this.classificados, new Comparator<Participante>() {
            @Override
            public int compare(Participante p1, Participante p2) {
                return Integer.compare(p2.getPontosAcumulados(), p1.getPontosAcumulados());
            }
        });
    }

    // Método para exibir o ranking formatado na consola
    public void exibirRanking() {
        System.out.println("\nRANKING DO BOLÃO");
        if (classificados.isEmpty()) {
            System.out.println("Nenhum participante registado ou pontos ainda não calculados.");
            return;
        }
        
        int posicao = 1;
        for (Participante p : classificados) {
            System.out.println(posicao + "º Lugar: " + p.getNome() + " - " + p.getPontosAcumulados() + " pts");
            posicao++;
        }
    }

    // Getter para obter a lista ordenada se precisar noutra parte do sistema
    public List<Participante> getClassificados() {
        return classificados;
    }
}

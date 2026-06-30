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
 * Classe responsável por gerar e exibir o ranking de participantes.
 *
 * Cada participante do ranking é ordenado por pontos acumulados.
 * O ranking é recalculado a partir da lista de participantes do sistema.
 *
 * @author vinic
 */
public class Ranking {
    // Lista ordenada de participantes que serão exibidos no ranking
    private List<Participante> classificados;

    // Construtor que inicia a lista vazia de classificados
    public Ranking() {
        this.classificados = new ArrayList<>();
    }

    // Método para atualizar a lista com os participantes atuais do sistema
    public void atualizarRanking(List<Participante> participantes) {
        // Copia os participantes recebidos para evitar modificar a lista original
        this.classificados = new ArrayList<>(participantes);
        
        // Ordena a lista do maior para o menor ponto usando um Comparator
        Collections.sort(this.classificados, new Comparator<Participante>() {
            @Override
            public int compare(Participante p1, Participante p2) {
                // Compara os pontos acumulados dos participantes
                return Integer.compare(p2.getPontosAcumulados(), p1.getPontosAcumulados());
            }
        });
    }

    // Exibe o ranking no console, mostrando a posição e os pontos de cada participante
    public void exibirRanking() {
        System.out.println("\nRANKING DO BOLÃO");
        if (classificados.isEmpty()) {
            // Caso não haja participantes classificados, mostra mensagem de aviso
            System.out.println("Nenhum participante registado ou pontos ainda não calculados.");
            return;
        }
        
        int posicao = 1;
        for (Participante p : classificados) {
            // Imprime a posição, nome e pontos de cada participante
            System.out.println(posicao + "º Lugar: " + p.getNome() + " - " + p.getPontosAcumulados() + " pts");
            posicao++;
        }
    }

    // Retorna a lista ordenada de classificados para uso em outras partes do sistema
    public List<Participante> getClassificados() {
        return classificados;
    }
}

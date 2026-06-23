package com.mycompany.sistemabolao;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Ponto de entrada do Bolão Copa 2026.
 *
 * @author vinic
 */
public class SistemaBolao {

    public static void main(String[] args) throws Exception {
        // Garante exibição correta de acentos e caracteres especiais
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        new MenuSistema().iniciar();
    }
}
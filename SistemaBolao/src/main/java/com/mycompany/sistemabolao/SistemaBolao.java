package com.mycompany.sistemabolao;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
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
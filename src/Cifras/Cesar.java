package Cifras;

import Cifras.Criptografo;

public class Cesar implements Criptografo {

    private int passo;
    // Inicio e fim baseado na tabela Ascii.
    private final int inicio = 31;
    private final int fim = 122;

    public Cesar(){
        this.passo = 3;
        return;
    }
    public Cesar(int passo){
        this.passo = passo;
        return;
    }

    @Override
    public String encriptar(String str) {
        char [] letras = str.toCharArray();
        String frase = "";
        for(char letra : letras){
            int ascii = ((int)letra + passo);
            char l = ascii > fim ? (char)(ascii-fim+inicio) : (char)ascii;
            frase += l;
        }

        return frase;
    }

    @Override
    public String decriptar(String str) {
        char [] letras = str.toCharArray();
        String frase = "";
        for(char letra : letras){
            int ascii = ((int)letra - passo);
            char l = ascii < inicio ? (char)(ascii-inicio+fim) : (char)ascii;
            frase += l;
        }

        return frase;
    }

}

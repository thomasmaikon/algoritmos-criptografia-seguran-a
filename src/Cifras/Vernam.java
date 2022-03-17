package Cifras;

import PersonalExceptions.VernamException;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Vernam implements Criptografo{

    private String chave;

    public Vernam(String chave){
        this.chave = chave;
    }

    @Override
    public String encriptar(String str) {
        String encriptado = "";

        try{
            if(!tamanhosIguais(str))
                throw new VernamException("Entrada de criptografia invalida");

            for(int i = 0; i < str.length(); i++){

                String entradaMsg = convertStringToBinary(str.substring(i,i+1));
                String chaveCorrespondente = convertStringToBinary(chave.substring(i,i+1));

                String result = xor(entradaMsg, chaveCorrespondente);

                encriptado += converBinaryToString(result);
            }
            return encriptado;
        }catch (VernamException e){
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }

    private boolean tamanhosIguais(String str){
        return str.length() == this.chave.length();
    }

    private String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))
                            .replaceAll(" ", "0")
            );
        }
        return result.toString();

    }

    @Override
    public String decriptar(String str) {
        String decriptado = "";
        try {
            if (!tamanhosIguais(str))
                throw new VernamException("Entrada de decriptografia invalida");

            for(int i = 0; i < str.length(); i++){

                String entradaMsg = convertStringToBinary(str.substring(i,i+1));
                String chaveCorrespondente = convertStringToBinary(chave.substring(i,i+1));

                String result = xor(entradaMsg, chaveCorrespondente);

                decriptado += converBinaryToString(result);
            }
            return decriptado;
        }catch (VernamException e){
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }

    private String xor(String value, String key){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {
            sb.append(charOf(bitOf(value.charAt(i)) ^ bitOf(key.charAt(i))));
        }
        return sb.toString();
    }

    private static boolean bitOf(char in) {
        return (in == '1');
    }

    private static char charOf(boolean in) {
        return (in) ? '1' : '0';
    }


    private String converBinaryToString(String str){
        return Arrays.stream(str.split(" "))
                .map(binary -> Integer.parseInt(binary, 2))
                .map(Character::toString)
                .collect(Collectors.joining());
    }

}


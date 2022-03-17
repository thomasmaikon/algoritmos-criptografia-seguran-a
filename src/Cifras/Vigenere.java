package Cifras;

public class Vigenere implements Criptografo{

    //Inicio e Fim do Alfabeto
    private final int inicio = 65;
    private final int fim = 90;

    private String chaveEstendida;
    private String chave;

    public Vigenere(){
        this.chave = "CHAVE";
        this.chaveEstendida = "";
    }

    public Vigenere(String chave){
        this.chave = chave;
        this.chaveEstendida = "";
    }

    @Override
    public String encriptar(String str) {
        gerarChaveEstendida(str);

        int indiceChaveEstendida = 0;
        String mensagemEncriptada = "";

        for(char letra : str.toCharArray()){
            if(letra != ' '){
                int novoInicio = (int)letra;
                int distancia = Math.abs( inicio - (int)chaveEstendida.charAt(indiceChaveEstendida));
                int novoCaractere = novoInicio + distancia;
                int secreto =  novoCaractere <= fim ? novoCaractere : (novoCaractere%fim) + inicio-1; // o -1 precisa so no segundo
                char caractereSecreto = (char) secreto;
                mensagemEncriptada += caractereSecreto;
                indiceChaveEstendida++;
            }else{
                mensagemEncriptada += letra;
            }
        }

        return mensagemEncriptada;
    }

    private void gerarChaveEstendida(String str){
        char []arr = str.toCharArray();
        int count = 0; // controle para evitar caractere de espaco
        for(char letra : arr){
            if(letra != ' ') {
                int indiceChave = count % this.chave.length();
                chaveEstendida += chave.charAt(indiceChave);
                count++;
            }
        }
        int test;
    }


    @Override
    public String decriptar(String str) {
        String mensagemDecriptada = "";

        int indiceChaveEstendida = 0;

        for(char letra : str.toCharArray()){
            if(letra != ' '){
                int novoInicio = (int)chaveEstendida.charAt(indiceChaveEstendida);
                int maiorDistancia = Math.abs(novoInicio - fim) + Math.abs(inicio - (int) letra) + 1;
                int distancia =  (int) letra < novoInicio ? maiorDistancia : (int) letra - novoInicio ;
                int descoberto = inicio + distancia;
                char caractereDescoberto = (char) descoberto;
                mensagemDecriptada += caractereDescoberto;
                indiceChaveEstendida++;
            }else{
                mensagemDecriptada += letra;
            }
        }

        return mensagemDecriptada;
    }
}

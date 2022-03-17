package Cifras;

import java.util.Random;

public class RC4 extends Vernam{

    public RC4(String chave) {
        super(gerarChave(chave));
    }

    public static String gerarChave(String mensagem) {
        int	limiteInferior = 33;
        int	limiteSuperior = 122;
        int	tamanhoChave = mensagem.length();
        Random	random = new Random();

        String chave = random.ints(limiteInferior, limiteSuperior + 1)
                .limit(tamanhoChave)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return chave;
    }
}

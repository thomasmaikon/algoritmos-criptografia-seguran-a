package Testes;

import Cifras.AES256;
import Cifras.Criptografo;
import Cifras.Vigenere;
import Model.Usuario;
import Server.Servidor;
import Services.Mensagem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AES {

    @Test
    void encriptarAES256Char(){
        Criptografo cripto = new AES256();
        assertEquals("psr++RUVGAOIiJY6U15NnA==",cripto.encriptar("W"));
    }

    @Test
    void encriptarAES256String(){
        Criptografo cripto = new AES256();
        assertEquals("IsSaW/9VoGNoXhY/nYlqLw==",cripto.encriptar("ATACARBASENORTE"));
    }

    @Test
    void decriptarAES256String(){
        Criptografo cripto = new AES256();
        String encriptado = cripto.encriptar("ATACAREMOS");
        assertEquals("ATACAREMOS",cripto.decriptar(encriptado));
    }

    @Test
    void enviarMensagem() {
        Criptografo cripto = new AES256();
        Usuario thomas = new Usuario("192.168.0.1", cripto);
        Usuario maikon = new Usuario("192.168.0.3", cripto);

        Servidor.iniciarServidor();

        Servidor.adicionarUsuario(thomas);
        Servidor.adicionarUsuario(maikon);

        thomas.enviarMensagem("192.168.0.3","VAMOS ATACAR DE MANHA AMANHA");

        Assertions.assertEquals(new Mensagem("192.168.0.1","VAMOS ATACAR DE MANHA AMANHA").exibir(), maikon.getMensagens());

    }
}

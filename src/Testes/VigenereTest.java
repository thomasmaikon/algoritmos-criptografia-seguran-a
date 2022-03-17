package Testes;

import Cifras.Cesar;
import Cifras.Criptografo;
import Cifras.Vigenere;
import Model.Usuario;
import Server.Servidor;
import Services.Mensagem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VigenereTest {

    @Test
    void encriptarVigenereChar(){
        Criptografo cripto = new Vigenere();
        assertEquals("Y",cripto.encriptar("W"));
    }

    @Test
    void encriptarVigenereString(){
        Criptografo cripto = new Vigenere("FOGO");
        assertEquals("FHGQFFHOXSTCWHK",cripto.encriptar("ATACARBASENORTE"));
        assertEquals("TZG QTAU JFW",cripto.encriptar("OLA COMO VAI"));
    }

    @Test
    void decriptarVigenereString(){
        Criptografo cripto = new Vigenere("BICICLETA");
        assertEquals("BBCKCCIFOT",cripto.encriptar("ATACAREMOS"));
        assertEquals("ATACAREMOS",cripto.decriptar("BBCKCCIFOT"));
    }

    @Test
    void enviarMensagem() {
        Criptografo cripto = new Vigenere();
        Usuario thomas = new Usuario("192.168.0.1", cripto);
        Usuario maikon = new Usuario("192.168.0.3", cripto);

        Servidor.iniciarServidor();

        Servidor.adicionarUsuario(thomas);
        Servidor.adicionarUsuario(maikon);

        thomas.enviarMensagem("192.168.0.3","VAMOS ATACAR DE MANHA AMANHA");

        Assertions.assertEquals(new Mensagem("192.168.0.1","VAMOS ATACAR DE MANHA AMANHA").exibir(), maikon.getMensagens());

    }
}
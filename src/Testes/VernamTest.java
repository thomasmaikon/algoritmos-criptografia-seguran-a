package Testes;

import Cifras.Criptografo;
import Cifras.Vernam;
import Cifras.Vigenere;
import Model.Usuario;
import PersonalExceptions.VernamException;
import Server.Servidor;
import Services.Mensagem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VernamTest {

    @Test
    void encriptarVernamChar(){
        Criptografo cripto = new Vernam("A");
        assertEquals("\f",cripto.encriptar("M"));
    }

    @Test
    void encriptarVernamString(){
        Criptografo cripto = new Vernam("ACD");
        assertEquals("\f\u0007\u0002",cripto.encriptar("MDF"));
    }

    @Test
    void decriptarVernamString(){
        Criptografo cripto = new Vernam("ACD");
        assertEquals("\f\u0007\u0002",cripto.encriptar("MDF"));
        assertEquals("MDF",cripto.decriptar("\f\u0007\u0002"));
    }

    @Test
    void enviarMensagem() {
        Criptografo cripto = new Vernam("ABC");
        Usuario thomas = new Usuario("192.168.0.1", cripto);
        Usuario maikon = new Usuario("192.168.0.3", cripto);

        Servidor.iniciarServidor();

        Servidor.adicionarUsuario(thomas);
        Servidor.adicionarUsuario(maikon);

        thomas.enviarMensagem("192.168.0.3","OLA");

        Assertions.assertEquals(new Mensagem("192.168.0.1","OLA").exibir(), maikon.getMensagens());

    }
}
package Testes;

import Cifras.Cesar;
import Cifras.Criptografo;
import Model.Usuario;
import Server.Servidor;
import Services.Mensagem;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class CesarTest {

    @org.junit.jupiter.api.Test
    void encriptarCesarChar(){
        Criptografo cripto = new Cesar();
        assertEquals("Z",cripto.encriptar("W"));
    }

    @org.junit.jupiter.api.Test
    void encriptarCesarString(){
        Criptografo cripto = new Cesar();
        assertEquals("rod",cripto.encriptar("ola"));
        assertEquals("rod/#frpr#ydlB",cripto.encriptar("ola, como vai?"));
    }

    @org.junit.jupiter.api.Test
    void decriptarCesarString(){
        Criptografo cripto = new Cesar();
        assertEquals("W",cripto.decriptar("Z"));
        assertEquals("ola, como vai?",cripto.decriptar("rod/#frpr#ydlB"));
    }


    @org.junit.jupiter.api.Test
    void enviarMensagem() {
        Criptografo cripto = new Cesar();
        Usuario thomas = new Usuario("192.168.0.1", cripto);
        Usuario maikon = new Usuario("192.168.0.3", cripto);

        Servidor.iniciarServidor();

        Servidor.adicionarUsuario(thomas);
        Servidor.adicionarUsuario(maikon);

        thomas.enviarMensagem("192.168.0.3","Ola, como vai maikon");
        maikon.enviarMensagem("192.168.0.1", "Vou bem!!");

        Assertions.assertEquals(new Mensagem("192.168.0.1","Ola, como vai maikon").exibir(), maikon.getMensagens());
        Assertions.assertEquals(new Mensagem("192.168.0.3","Vou bem!!").exibir(), thomas.getMensagens());
    }
}
package Testes;

import Cifras.Cesar;
import Cifras.Criptografo;
import Cifras.RC4;
import Cifras.Vernam;
import Model.Usuario;
import Server.Servidor;
import Services.Mensagem;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class RC4Test {
    @Test
    void encriptarRC4Char(){
        Criptografo cripto = new RC4("A");
        assertEquals(" ",cripto.encriptar("W"));
    }

    @Test
    void encriptarRC4String(){
        Criptografo cripto = new RC4("ABC");
        assertEquals("+\u000Eo",cripto.encriptar("OLA"));
    }

    @Test
    void decritarRC4String(){
        Criptografo cripto = new RC4("ABC");
        String encriptado = cripto.encriptar("OLA");
        assertEquals("OLA",cripto.decriptar(encriptado));
    }

    @Test
    void enviarMensagem() {
        Criptografo cripto = new RC4("ABC");
        Usuario thomas = new Usuario("192.168.0.1", cripto);
        Usuario maikon = new Usuario("192.168.0.3", cripto);

        Servidor.iniciarServidor();

        Servidor.adicionarUsuario(thomas);
        Servidor.adicionarUsuario(maikon);

        thomas.enviarMensagem("192.168.0.3","OLA");

        Assertions.assertEquals(new Mensagem("192.168.0.1","OLA").exibir(), maikon.getMensagens());

    }
}
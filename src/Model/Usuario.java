package Model;

import Cifras.Criptografo;
import Server.Servidor;
import Services.Mensagem;
import Services.Requisicao;

import java.util.ArrayList;

public class Usuario {
    private String ip;
    private Criptografo criptografo;

    private ArrayList<Mensagem> mensagens;

    public Usuario(String ip, Criptografo criptografo){
        this.ip = ip;
        this.criptografo = criptografo;
        this.mensagens = new ArrayList<Mensagem>();
    }

    public void enviarMensagem(String ipDestino, String msg){
        Mensagem mensagem = new Mensagem(this.ip, criptografo.encriptar(msg));
        Requisicao req = new Requisicao(ipDestino, mensagem);
        Servidor server = Servidor.getServidor();
        server.enviarRequisicao(req);

    }

    public String getIp() {
        return ip;
    }

    public void receberMensagem(Mensagem msg){
        mensagens.add(msg);
    }

    public String getMensagens(){
        String msgs = "";
        for(Mensagem m : mensagens){
            msgs = m.exibir(criptografo);
        }
        return msgs;
    }
}

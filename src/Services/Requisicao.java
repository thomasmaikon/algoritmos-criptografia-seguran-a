package Services;

public class Requisicao {

    private String ipDestino;
    private Mensagem mensagem;

    public Requisicao(String destino, Mensagem msg){
        ipDestino = destino;
        mensagem = msg;
    }

    public String getIpDestino() {
        return ipDestino;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

}

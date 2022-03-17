package Services;

import Cifras.Criptografo;

public class Mensagem {
    private String origem;
    private String conteudo;

    public Mensagem(String origem, String conteudo){
        this.origem = origem;
        this.conteudo = conteudo;
    }

    public String getOrigem() {
        return origem;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String exibir(Criptografo criptografo){
        return "Endereco [" + getOrigem() + "]" +
                "\n\tConteudo: "+ criptografo.decriptar(this.getConteudo());
    }

    public String exibir(){
        return "Endereco [" + getOrigem() + "]" +
                "\n\tConteudo: "+ this.getConteudo();
    }
}

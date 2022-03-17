package Server;

import Model.Usuario;
import Services.Requisicao;

import java.util.ArrayList;

public class Servidor {

    private static Servidor instance;
    private static ArrayList<Usuario> usuarios;

    private Servidor(){
        usuarios = new ArrayList<Usuario>();
    }

    public static void iniciarServidor(){
        if(instance == null) {
            instance = new Servidor();
        }
    }

    public static Servidor getServidor(){
        if(instance == null) {
            instance = new Servidor();
        }
        return instance;
    }

    public static void adicionarUsuario(Usuario usr){
        usuarios.add(usr);
    }

    public void enviarRequisicao(Requisicao request){
        for(Usuario user : usuarios){
            if(user.getIp().equals(request.getIpDestino())){
                user.receberMensagem(request.getMensagem());
            }
        }
    }
}

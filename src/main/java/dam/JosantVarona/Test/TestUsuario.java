package dam.JosantVarona.Test;


import dam.JosantVarona.model.Usuario;
import dam.JosantVarona.service.ServiceUser;

public class TestUsuario {
    public static void main(String[] args) {
        //Usuario u = new Usuario("Jose","g.gmail.com","1234");
        //Usuario a = new Usuario("Antonio","a.gmail.com","4567");
        Usuario a = new Usuario("Juan","o.gmail.com","4567");
        //UsuarioDAO dao = new UsuarioDAO();
        //dao.insertUsuario(a);
        ServiceUser serviceUser = new ServiceUser();
        boolean guardado = serviceUser.registerUser(a);
        //Boolean buscar = serviceUser.userExists("g.gmail.com");
        if (guardado) {
            //System.out.println("Usuario encontrado");
            System.out.println("Usuario guardado");
        }
        //System.out.println(buscar.getNombre());
    }
}

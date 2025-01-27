package dam.JosantVarona.Test;

import dam.JosantVarona.DAO.UsuarioDAO;
import dam.JosantVarona.model.Usuario;

public class TestUsuario {
    public static void main(String[] args) {
        //Usuario u = new Usuario("Jose","g.gmail.com","1234");
        //Usuario a = new Usuario("Antonio","a.gmail.com","4567");
        UsuarioDAO dao = new UsuarioDAO();
        //dao.insertUsuario(a);
        Usuario buscar = dao.buscarUsuario("g.gmail.com");
        System.out.println(buscar.getNombre());
    }
}

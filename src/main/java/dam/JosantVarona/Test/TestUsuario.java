package dam.JosantVarona.Test;


import dam.JosantVarona.DAO.UsuarioDAO;
import dam.JosantVarona.model.Usuario;
import dam.JosantVarona.service.ServiceUser;

public class TestUsuario {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        String email = "hola";
        Usuario u =dao.buscarUsuario(email);
        System.out.println(u);
        //u.setEmail("a");
        //dao.updateUsuario(u);
        dao.deleteUsuario(u);
    }
}

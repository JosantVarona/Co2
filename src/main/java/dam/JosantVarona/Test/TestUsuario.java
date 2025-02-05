package dam.JosantVarona.Test;


import dam.JosantVarona.DAO.UsuarioDAO;
import dam.JosantVarona.model.Usuario;

import java.util.Arrays;
import java.util.List;

public class TestUsuario {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        String email = "a";
        Usuario u =dao.buscarUsuario(email);
        System.out.println(u);
        //u.setEmail("a");
        //dao.updateUsuario(u);
        //dao.deleteUsuario(u);
        /*ecomendacionesDAO rdao = new RecomendacionesDAO();
        List<Recomendacion> personalizadas = rdao.RecomendacionesUser(u);
        for(Recomendacion r : personalizadas){
            System.out.println(r);
        }*/
        List<Object[]> ranking = dao.impactoranking();
        for (Object[] o : ranking) {
            System.out.println(Arrays.toString(o));
        }
    }
}

package dam.JosantVarona.Test;

import dam.JosantVarona.DAO.HuellaDAO;
import dam.JosantVarona.DAO.UsuarioDAO;
import dam.JosantVarona.model.Huella;
import dam.JosantVarona.model.Usuario;

import java.math.BigDecimal;

public class TestHuella {
    public static void main(String[] args) {
        Usuario u = new Usuario();
        UsuarioDAO daoU = new UsuarioDAO();
        u = daoU.buscarUsuario("a");
        /*Actividad a = new Actividad();
        ActividadDAO daoA = new ActividadDAO();
        a =daoA.findName("Conducir coche");
        //a.setId(2);

        BigDecimal valor = new BigDecimal(3.14);
        Huella huella = new Huella(u,a,valor,"kg",LocalDate.now());
        dao.insertHuella(huella);
        HuellaDAO dao = new HuellaDAO();
        List<Huella> huellaUser = dao.listHuellasUser(u);
        for (Huella h : huellaUser) {
            System.out.println(h);
        }*/
        HuellaDAO dao = new HuellaDAO();
        Huella huella = dao.findHuellaById(9);
        System.out.println(huella);
        huella.setValor(new BigDecimal(123.45));
        dao.updateHuella(huella);

    }
}

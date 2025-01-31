package dam.JosantVarona.Test;

import dam.JosantVarona.DAO.ActividadDAO;
import dam.JosantVarona.DAO.HuellaDAO;
import dam.JosantVarona.DAO.UsuarioDAO;
import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Huella;
import dam.JosantVarona.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestHuella {
    public static void main(String[] args) {
        Usuario u = new Usuario();
        UsuarioDAO daoU = new UsuarioDAO();
        u = daoU.buscarUsuario("a");
        Actividad a = new Actividad();
        ActividadDAO daoA = new ActividadDAO();
        a =daoA.findName("Conducir coche");
        //a.setId(2);
        HuellaDAO dao = new HuellaDAO();
        BigDecimal valor = new BigDecimal(3.14);
        Huella huella = new Huella(u,a,valor,"kg",LocalDate.now());
        dao.insertHuella(huella);
    }
}

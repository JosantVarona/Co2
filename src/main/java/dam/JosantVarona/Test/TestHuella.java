package dam.JosantVarona.Test;

import dam.JosantVarona.DAO.ActividadDAO;
import dam.JosantVarona.DAO.HuellaDAO;
import dam.JosantVarona.DAO.UsuarioDAO;
import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Huella;
import dam.JosantVarona.model.Usuario;

import java.math.BigDecimal;

public class TestHuella {
    public static void main(String[] args) {

        /*
        //a.setId(2);

        BigDecimal valor = new BigDecimal(3.14);
        Huella huella = new Huella(u,a,valor,"kg",LocalDate.now());
        dao.insertHuella(huella);
        HuellaDAO dao = new HuellaDAO();
        List<Huella> huellaUser = dao.listHuellasUser(u);
        for (Huella h : huellaUser) {
            System.out.println(h);
            Huella huella = dao.findHuellaById(9);
        System.out.println(huella);
        huella.setValor(new BigDecimal(123.45));
        dao.updateHuella(huella);
        }*/
        HuellaDAO dao = new HuellaDAO();
        Usuario u = new Usuario();
        UsuarioDAO daoU = new UsuarioDAO();
        u = daoU.buscarUsuario("a");
        Actividad a = new Actividad();
        ActividadDAO daoA = new ActividadDAO();
        a =daoA.findName("Consumo de agua potable");
        BigDecimal valor;
        if (dao.impactoHuella(a,u)== null){
            System.out.println("No huella consumada");
        }else {
            valor = dao.impactoHuella(a,u);
            System.out.println(valor);
        }
    }
}

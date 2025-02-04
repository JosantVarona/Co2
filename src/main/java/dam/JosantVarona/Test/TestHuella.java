package dam.JosantVarona.Test;

import dam.JosantVarona.DAO.ActividadDAO;
import dam.JosantVarona.DAO.HuellaDAO;
import dam.JosantVarona.DAO.UsuarioDAO;
import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Huella;
import dam.JosantVarona.model.Usuario;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TestHuella {
    public static void main(String[] args) {

        /*
        //a.setId(2);

        }*/
        HuellaDAO dao = new HuellaDAO();
        Usuario u = new Usuario();
        UsuarioDAO daoU = new UsuarioDAO();
        u = daoU.buscarUsuario("a");
        Actividad a = new Actividad();
        ActividadDAO daoA = new ActividadDAO();
        /*a =daoA.findName("Consumo de agua potable");
        BigDecimal valor;
        if (dao.impactoHuella(a,u)== null){
            System.out.println("No huella consumada");
        }else {
            valor = dao.impactoHuella(a,u);
            System.out.println(valor);
        }*/
        HuellaDAO daoH = new HuellaDAO();
        List<Object[]> valor=daoH.impactoMensual(u,2025,1);
        for (Object[] fila : valor) {
            System.out.println(Arrays.toString(fila));
        }
    }
}

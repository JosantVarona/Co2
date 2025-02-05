package dam.JosantVarona.Test;

import com.sun.tools.javac.Main;
import dam.JosantVarona.DAO.ActividadDAO;
import dam.JosantVarona.DAO.RecomendacionesDAO;
import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Recomendacion;

import java.util.List;

public class TestActividades {
    public static void main(String[] args) {
        ActividadDAO dao = new ActividadDAO();
        RecomendacionesDAO recom = new RecomendacionesDAO();
        /*List<Actividad> actividads =dao.listActividades();
        for (Actividad actividad : actividads) {
            System.out.println(actividad);
        }
        /*Actividad actividad = dao.findName("Conducir coche");
        System.out.println(actividad);*/
        Actividad actividad = dao.findId(1);
        System.out.println(actividad);
        List<Recomendacion> recomendacionList =recom.RecomendacionesActividad(actividad);
        for (Recomendacion recomendacion : recomendacionList) {
            System.out.println(recomendacion);
        }
    }
}

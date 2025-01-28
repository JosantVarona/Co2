package dam.JosantVarona.Test;

import com.sun.tools.javac.Main;
import dam.JosantVarona.DAO.ActividadDAO;
import dam.JosantVarona.model.Actividad;

import java.util.List;

public class TestActividades {
    public static void main(String[] args) {
        ActividadDAO dao = new ActividadDAO();
        List<Actividad> actividads =dao.listActividades();
        for (Actividad actividad : actividads) {
            System.out.println(actividad);
        }
    }
}

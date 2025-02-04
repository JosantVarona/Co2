package dam.JosantVarona.Test;

import dam.JosantVarona.DAO.ActividadDAO;
import dam.JosantVarona.DAO.HabitosDAO;
import dam.JosantVarona.DAO.UsuarioDAO;
import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Habito;
import dam.JosantVarona.model.HabitoId;
import dam.JosantVarona.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public class TestHabitos {
    public static void main(String[] args) {
        /*




        dao3.insertHabitos(h);*/
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.buscarUsuario("a");
        HabitosDAO dao3 = new HabitosDAO();
        /*List<Habito> habitosUser = dao3.habitosUser(usuario);
        for (Habito habito : habitosUser) {
            System.out.println(habito);
        }*/
        ActividadDAO dao1 = new ActividadDAO();
        Actividad actividad = dao1.findName("Conducir coche");
        Habito h = new Habito(usuario,actividad,2,"diario", LocalDate.now());
        HabitoId habitoId = new HabitoId();
        habitoId.setIdActividad(actividad.getId());
        habitoId.setIdUsuario(usuario.getId());
        h.setId(habitoId);
        dao3.updateHabitos(h);
    }
}

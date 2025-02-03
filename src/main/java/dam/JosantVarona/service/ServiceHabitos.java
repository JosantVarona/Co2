package dam.JosantVarona.service;

import dam.JosantVarona.DAO.HabitosDAO;
import dam.JosantVarona.model.Habito;
import dam.JosantVarona.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ServiceHabitos {
    private HabitosDAO habitosDAO = new HabitosDAO();
    public boolean insertHabitos(Habito habito) {
        boolean result = false;
        if (habito != null) {
            habitosDAO.insertHabitos(habito);
            result = true;
        }
        return result;
    }
    public List<Habito> habitosUser(Usuario usuario) {
        List<Habito> habitos= new ArrayList<>();
        if (usuario != null) {
            habitos = habitosDAO.habitosUser(usuario);
        }
        return habitos;
    }
    public boolean deleteHabitos(Habito habito) {
        boolean result = false;
        if (habito != null) {
            habitosDAO.delteHabitos(habito);
            result = true;
        }
        return result;
    }
    public boolean exitHabitos(Habito habito, Usuario usuario) {
        boolean result = false;
        List<Habito> habitos= habitosDAO.habitosUser(usuario);
        if (habito != null) {
            for (Habito h : habitos) {
                if (h.getIdActividad().getId().equals(habito.getIdActividad().getId())) {
                    result = true;
                }
            }
        }
        return result;
    }
}

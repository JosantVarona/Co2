package dam.JosantVarona.service;

import dam.JosantVarona.DAO.ActividadDAO;
import dam.JosantVarona.model.Actividad;

import java.util.List;

public class ServiceActividad {
    private ActividadDAO actividadDAO= new ActividadDAO();
    public List<Actividad> allActividades(){
        List<Actividad> actividads = actividadDAO.listActividades();
        return actividads;
    }
}

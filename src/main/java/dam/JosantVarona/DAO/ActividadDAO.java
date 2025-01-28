package dam.JosantVarona.DAO;

import dam.JosantVarona.Connection.Connect;
import dam.JosantVarona.model.Actividad;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {
    public List<Actividad> listActividades(){
        List<Actividad> actividades = new ArrayList<>();
        Session sesion = Connect.getInstance().getSession();
        sesion.beginTransaction();
        Query query = sesion.createQuery("from Actividad", Actividad.class);
        actividades = query.list();
        sesion.getTransaction().commit();
        sesion.close();
        return actividades;
    }
}

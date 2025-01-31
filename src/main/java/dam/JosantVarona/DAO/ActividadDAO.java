package dam.JosantVarona.DAO;

import dam.JosantVarona.Connection.Connect;
import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {

    private static final String FINDNAME= "FROM Actividad WHERE nombre = :nombre";

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
    public Actividad findName(String nombre) {
        Actividad result = null;
        Session session = Connect.getInstance().getSession();
        session.beginTransaction();
        result = session.createQuery(FINDNAME, Actividad.class)
                .setParameter("nombre", nombre)
                .uniqueResult();
        session.getTransaction().commit();
        return result;
    }
}

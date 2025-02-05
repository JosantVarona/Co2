package dam.JosantVarona.DAO;

import dam.JosantVarona.Connection.Connect;
import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Recomendacion;
import dam.JosantVarona.model.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RecomendacionesDAO {
    private static final String RECOMEDACIONES= "SELECT r " +
            "FROM Recomendacion r " +
            "JOIN r.idCategoria c " +
            "JOIN c.actividads a " +
            "JOIN a.huellas h " +
            "WHERE h.idUsuario = :idUsuario";
    private static final String RECOMENDACTIVI="SELECT r " +
            "FROM  Actividad a " +
            "JOIN a.idCategoria c " +
            "JOIN c.recomendacions r " +
            "WHERE a.id = :idActividad";
    public List<Recomendacion> RecomendacionesUser(Usuario usuario) {
        List<Recomendacion> recomedaciones;
        Session session = Connect.getInstance().getSession();
        session.beginTransaction();
        Query query = session.createQuery(RECOMEDACIONES);
        query.setParameter("idUsuario", usuario);
        recomedaciones = query.list();
        session.getTransaction().commit();
        session.close();
        return recomedaciones;
    }
    public List<Recomendacion> RecomendacionesActividad(Actividad actividad) {
        List<Recomendacion> recomendaciones;
        Session session = Connect.getInstance().getSession();
        session.beginTransaction();
        Query query = session.createQuery(RECOMENDACTIVI);
        query.setParameter("idActividad", actividad.getId());
        recomendaciones = query.list();
        session.getTransaction().commit();
        session.close();
        return recomendaciones;
    }
}

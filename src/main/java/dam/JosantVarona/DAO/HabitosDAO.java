package dam.JosantVarona.DAO;

import dam.JosantVarona.Connection.Connect;
import dam.JosantVarona.model.Habito;
import dam.JosantVarona.model.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HabitosDAO {
    private static final String HABITOSUSER ="FROM Habito WHERE idUsuario=:idUsuario";
    public void insertHabitos(Habito habito) {
        Session session = Connect.getInstance().getSession();
        session.beginTransaction();
        session.save(habito);
        session.getTransaction().commit();
        session.close();
    }
    public List<Habito> habitosUser(Usuario usuario) {
        List<Habito> habitos = new ArrayList<>();
        Session session = Connect.getInstance().getSession();
        session.beginTransaction();
        Query consulta = session.createQuery(HABITOSUSER);
        consulta.setParameter("idUsuario", usuario);
        habitos = consulta.list();
        session.getTransaction().commit();
        session.close();
        return habitos;
    }
    public void delteHabitos(Habito habito) {
        Session session = Connect.getInstance().getSession();
        session.beginTransaction();
        session.delete(habito);
        session.getTransaction().commit();
        session.close();
    }
}

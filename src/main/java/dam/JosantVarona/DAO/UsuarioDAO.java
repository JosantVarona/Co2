package dam.JosantVarona.DAO;

import dam.JosantVarona.Connection.Connect;
import dam.JosantVarona.model.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {

    private static final String BUSCARGMAIL ="FROM Usuario u WHERE u.email = :email";
    private static final String RANKING = "SELECT SUM(h.valor * c.factorEmision), u.nombre " +
            "FROM Huella h " +
            "JOIN h.idActividad a " +
            "JOIN a.idCategoria c " +
            "JOIN h.idUsuario u " +
            "GROUP BY u.nombre " +
            "ORDER BY SUM(h.valor * c.factorEmision)";

    public void insertUsuario(Usuario usuario) {

        Session session = Connect.getInstance().getSession();
        Usuario us = new Usuario();
        us.setNombre(usuario.getNombre());
        us.setContraseña(usuario.getContraseña());
        us.setEmail(usuario.getEmail());
        us.setFechaRegistro(Instant.now());
        session.beginTransaction();
        session.save(us);
        session.getTransaction().commit();
        session.close();
    }
    public Usuario buscarUsuario(String email) {
        Usuario result = null;
        Session session = Connect.getInstance().getSession();
        session.beginTransaction();
        result = session.createQuery(BUSCARGMAIL, Usuario.class)
                .setParameter("email", email)
                .uniqueResult();
        session.getTransaction().commit();
        return result;
    }
    public void updateUsuario(Usuario usuario) {
        Session session = Connect.getInstance().getSession();
        session.beginTransaction();
        session.merge(usuario);
        session.getTransaction().commit();
        session.close();
    }
    /*public void deleteUsuario(Usuario usuario) {
        Session session = Connect.getInstance().getSession();
        session.beginTransaction();
        Usuario usuarioMerged = (Usuario) session.merge(usuario);
        session.delete(usuarioMerged);
        session.getTransaction().commit();
        session.close();
    }*/
    public List<Object[]> impactoranking(){
        Session session = Connect.getInstance().getSession();
        session.beginTransaction();
        List<Object[]> resultados = new ArrayList<>();
        Query consulta = session.createQuery(RANKING);
        resultados = consulta.list();
        session.getTransaction().commit();
        session.close();
        return resultados;
    }
}

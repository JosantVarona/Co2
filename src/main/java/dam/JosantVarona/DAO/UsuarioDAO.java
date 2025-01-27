package dam.JosantVarona.DAO;

import dam.JosantVarona.Connection.Connect;
import dam.JosantVarona.model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String BUSCARGMAIL ="FROM Usuario u WHERE u.email = :email";

    public void insertUsuario(Usuario usuario) {
        Session session = Connect.getInstance().getSession();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try {
            if (usuario != null) {
                Usuario us = new Usuario();
                us.setNombre(usuario.getNombre());
                us.setContraseña(usuario.getContraseña());
                us.setEmail(usuario.getEmail());
                us.setFechaRegistro(Instant.now());

                session.beginTransaction();
                session.save(us);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    public Usuario buscarUsuario(String email) {
        Usuario result = null;
        Session session = null;
        try {
            session = Connect.getInstance().getSession();
            session.beginTransaction();

            result = session.createQuery(BUSCARGMAIL, Usuario.class)
                    .setParameter("email", email)
                    .uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return result;
    }
}

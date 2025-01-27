package dam.JosantVarona.DAO;

import dam.JosantVarona.Connection.Connect;
import dam.JosantVarona.model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.time.Instant;


public class UsuarioDAO {

    private static final String BUSCARGMAIL ="FROM Usuario u WHERE u.email = :email";

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
}

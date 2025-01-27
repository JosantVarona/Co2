package dam.JosantVarona.Connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connect {
    private static Connect instance;
    private SessionFactory sessionFactory;

    public static Connect getInstance() {
        if (instance == null) {
            instance = new Connect();
        }
        return instance;
    }
    private Connect() {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Error in Connection");
        }
    }
    public void close() {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}

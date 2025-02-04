package dam.JosantVarona.DAO;

import dam.JosantVarona.Connection.Connect;
import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Huella;
import dam.JosantVarona.model.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HuellaDAO {
    private static final String FINDBYUSER="FROM Huella WHERE idUsuario =:idUsuario";
    private static final String IMPACTOAMBIENTE="SELECT SUM(h.valor * c.factorEmision) " +
            "FROM Huella h " +
            "JOIN h.idActividad a " +
            "JOIN a.idCategoria c " +
            "WHERE h.idUsuario = :idUsuario AND h.idActividad = :idActividad";
    private static final String IMPACTOMES = "SELECT WEEK(h.fecha), SUM(h.valor * c.factorEmision) " +
            "FROM Huella h " +
            "JOIN h.idActividad a " +
            "JOIN a.idCategoria c " +
            "WHERE h.idUsuario = :idUsuario " +
            "AND YEAR(h.fecha) = :anio " +
            "AND MONTH(h.fecha) = :mes " +
            "GROUP BY WEEK(h.fecha) " +
            "ORDER BY WEEK(h.fecha)";

    public void insertHuella(Huella huella) {
        Huella huella1 = new Huella();
        Session sesion = Connect.getInstance().getSession();
        sesion.beginTransaction();
        huella1.setValor(huella.getValor());
        huella1.setUnidad(huella.getUnidad());
        huella1.setIdUsuario(huella.getIdUsuario());
        huella1.setIdActividad(huella.getIdActividad());
        huella1.setFecha(huella.getFecha());
        sesion.save(huella1);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public List<Huella> listHuellasUser(Usuario usuario) {
        List<Huella> huellas;
        Session sesion = Connect.getInstance().getSession();
        sesion.beginTransaction();
        Query consulta = sesion.createQuery(FINDBYUSER);
        consulta.setParameter("idUsuario", usuario);
        huellas = consulta.list();
        sesion.getTransaction().commit();
        sesion.close();
        return huellas;
    }
    public void deleteHuella(Huella huella) {
        Session sesion = Connect.getInstance().getSession();
        sesion.beginTransaction();
        sesion.delete(huella);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public Huella findHuellaById(int id) {
        Session sesion = Connect.getInstance().getSession();
        sesion.beginTransaction();
        Huella huella = (Huella) sesion.get(Huella.class, id);
        sesion.getTransaction().commit();
        sesion.close();
        return huella;
    }
    public Huella updateHuella(Huella huella) {
        Session sesion = Connect.getInstance().getSession();
        sesion.beginTransaction();
        sesion.update(huella);
        sesion.getTransaction().commit();
        sesion.close();
        return huella;
    }
    public BigDecimal impactoHuella(Actividad actividad, Usuario usuario) {
        BigDecimal valor;
        Session sesion = Connect.getInstance().getSession();
        sesion.beginTransaction();
        Query consulta = sesion.createQuery(IMPACTOAMBIENTE);
        consulta.setParameter("idUsuario", usuario);
        consulta.setParameter("idActividad", actividad);
        valor = (BigDecimal) consulta.uniqueResult();
        sesion.getTransaction().commit();
        sesion.close();
        return valor;
    }
    public List<Object[]> impactoMensual(Usuario usuario, Integer anio, Integer mes) {
        List<Object[]> resultados = new ArrayList<>();
        Session sesion = Connect.getInstance().getSession();
        sesion.beginTransaction();

        Query consulta = sesion.createQuery(IMPACTOMES);
        consulta.setParameter("idUsuario", usuario);
        consulta.setParameter("anio", anio);
        consulta.setParameter("mes", mes);

        resultados = consulta.list();

        sesion.getTransaction().commit();
        sesion.close();

        return resultados;
    }


}

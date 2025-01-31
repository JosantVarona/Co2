package dam.JosantVarona.DAO;

import dam.JosantVarona.Connection.Connect;
import dam.JosantVarona.model.Huella;
import org.hibernate.Session;

public class HuellaDAO {

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
}

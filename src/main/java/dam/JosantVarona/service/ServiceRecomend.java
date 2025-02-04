package dam.JosantVarona.service;

import dam.JosantVarona.DAO.RecomendacionesDAO;
import dam.JosantVarona.model.Recomendacion;
import dam.JosantVarona.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ServiceRecomend {
    private RecomendacionesDAO recomendacionesDAO;
    public List<Recomendacion> recomedUser(Usuario usuario) {
        recomendacionesDAO = new RecomendacionesDAO();
        List<Recomendacion> recomendacions= null;
        if (usuario != null) {
            recomendacions =recomendacionesDAO.RecomendacionesUser(usuario);
        }
        return recomendacions;
    }
}

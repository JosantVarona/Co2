package dam.JosantVarona.service;

import dam.JosantVarona.DAO.HuellaDAO;
import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Huella;
import dam.JosantVarona.model.Usuario;

import java.math.BigDecimal;
import java.util.List;

public class ServiceHuella {
    private HuellaDAO huellaDAO = new HuellaDAO();
    public boolean huellaInsertada(Huella huella) {
        boolean result = false;
        if (huella != null) {
            if (huella.getId() == null){
                huellaDAO.insertHuella(huella);
                result = true;
            }
        }
        return result;
    }
    public List<Huella> huellaUsuario(Usuario user) {
        List<Huella> result = null;
        if (user != null) {
            result = huellaDAO.listHuellasUser(user);
        }
        return result;
    }
    public boolean huellaDeleta(Huella huella) {
        boolean result = false;
        if (huella != null) {
            huellaDAO.deleteHuella(huella);
            result = true;
        }
        return result;
    }
    public boolean huellaUpdateada(Huella huella) {
        boolean result = false;
        if (huella != null || huella.getId() != null) {
            huellaDAO.updateHuella(huella);
        }
        return result;
    }
    public BigDecimal impactoHuella(Actividad actividad, Usuario user) {
        BigDecimal valor = null;
        if (actividad != null && user != null) {
            if (huellaDAO.impactoHuella(actividad,user) != null) {
                valor = huellaDAO.impactoHuella(actividad,user);
            }
        }
        return valor;
    }
}

package dam.JosantVarona.service;

import dam.JosantVarona.DAO.HuellaDAO;
import dam.JosantVarona.model.Huella;

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
}

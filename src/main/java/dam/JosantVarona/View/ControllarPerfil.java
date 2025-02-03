package dam.JosantVarona.View;

import dam.JosantVarona.App;
import dam.JosantVarona.Connection.UserSesion;
import dam.JosantVarona.DAO.HuellaDAO;
import dam.JosantVarona.model.Usuario;
import dam.JosantVarona.service.ServiceActividad;
import dam.JosantVarona.service.ServiceHuella;
import dam.JosantVarona.service.ServiceUser;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllarPerfil extends Controller implements Initializable{
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCuenta;
    @FXML
    private TextField pass;
    @FXML
    private Label id1Activi;
    @FXML
    private Label id2Activi;
    @FXML
    private Label id3Activi;
    @FXML
    private Label id4Activi;
    @FXML
    private Label id5Activi;
    @FXML
    private Label id6Activi;
    @FXML
    private Label id7Activi;
    @FXML
    private Label id8Activi;
    @FXML
    private Label id9Activi;

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtNombre.setText(UserSesion.getInstancia().getUsuarioIniciado().getNombre());
        txtCuenta.setText(UserSesion.getInstancia().getUsuarioIniciado().getEmail());
        pass.setText(UserSesion.getInstancia().getUsuarioIniciado().getContraseña());
        id1Activi.setText(comprobarImpacto(1));
        id2Activi.setText(comprobarImpacto(2));
        id3Activi.setText(comprobarImpacto(3));
        id4Activi.setText(comprobarImpacto(4));
        id5Activi.setText(comprobarImpacto(5));
        id6Activi.setText(comprobarImpacto(6));
        id7Activi.setText(comprobarImpacto(7));
        id8Activi.setText(comprobarImpacto(8));
        id9Activi.setText(comprobarImpacto(9));
    }
    @FXML
    private void closeWindow(Event event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void updateUser() throws Exception {
        ServiceUser serviceUser = new ServiceUser();
        UserSesion.getInstancia().getUsuarioIniciado().setNombre(txtNombre.getText());
        UserSesion.getInstancia().getUsuarioIniciado().setEmail(txtCuenta.getText());
        UserSesion.getInstancia().getUsuarioIniciado().setContraseña(pass.getText());
        serviceUser.updateUsuario(UserSesion.getInstancia().getUsuarioIniciado());
        App.currenController.changeScene(Scenes.MAIN,null);
    }
    /*@FXML
    private void deleteUser() throws Exception {
        ServiceUser serviceUser = new ServiceUser();
        serviceUser.deleteUsuario(UserSesion.getInstancia().getUsuarioIniciado());
        App.currenController.changeScene(Scenes.LOGIN,null);
    }*/
    private String comprobarImpacto(Integer id) {
        String impacto = "-----";
        ServiceActividad serviceActividad = new ServiceActividad();
        ServiceHuella huella = new ServiceHuella();
        if (id != null) {
            if (huella.impactoHuella(serviceActividad.buscarId(id),UserSesion.getInstancia().getUsuarioIniciado())!=null){
                BigDecimal valor = huella.impactoHuella(serviceActividad.buscarId(id),UserSesion.getInstancia().getUsuarioIniciado());
                impacto = valor.toString();
            }
        }
        return impacto;
    }
}

package dam.JosantVarona.View;

import dam.JosantVarona.App;
import dam.JosantVarona.Connection.UserSesion;
import dam.JosantVarona.service.ServiceUser;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllarPerfil extends Controller implements Initializable{
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCuenta;
    @FXML
    private TextField pass;
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
}

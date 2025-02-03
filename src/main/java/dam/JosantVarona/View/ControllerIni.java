package dam.JosantVarona.View;

import dam.JosantVarona.App;
import dam.JosantVarona.Connection.UserSesion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerIni extends Controller implements Initializable {
    @FXML
    private Label tfName;
    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfName.setText(UserSesion.getInstancia().getUsuarioIniciado().getNombre());
    }
    @FXML
    private void goTologin() throws Exception {
        App.currenController.changeScene(Scenes.LOGIN, null);
    }
    @FXML
    private void goTohuellas() throws Exception {
        App.currenController.changeScene(Scenes.HUELLASH, null);
    }
    @FXML
    private void goToPerfil() throws Exception {
        App.currenController.openModalv(Scenes.PERFIL, "Perfil", this,null);
    }
    @FXML
    private void goToHabitos() throws Exception {
        App.currenController.changeScene(Scenes.HABITOS,null);
    }
}

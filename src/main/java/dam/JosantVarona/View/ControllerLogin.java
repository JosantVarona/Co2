package dam.JosantVarona.View;

import dam.JosantVarona.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin extends Controller implements Initializable {

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void GoMainRegister() throws Exception {
        //Logica de recoger datos y guardarlos
        App.currenController.changeScene(Scenes.MAIN,null);
    }
    @FXML
    public void GoMainLogin() throws Exception {
        //Logica de recoger datos y comprobarlos
        App.currenController.changeScene(Scenes.MAIN,null);
    }

}

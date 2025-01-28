package dam.JosantVarona.View;

import dam.JosantVarona.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controllerhuellas extends Controller implements Initializable {
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
    private void goToMain() throws Exception {
        App.currenController.changeScene(Scenes.MAIN, null);
    }
    @FXML
    private void goToAddHuella() throws Exception {
        App.currenController.openModalv(Scenes.INSERHUELLA,"Añadiendo huella",this,null);
    }

}
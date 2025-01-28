package dam.JosantVarona.View;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerInserHuella extends Controller implements Initializable {
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
    private void closeWindow(Event event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}

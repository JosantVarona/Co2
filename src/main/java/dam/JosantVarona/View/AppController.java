package dam.JosantVarona.View;

import dam.JosantVarona.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController extends Controller implements Initializable {
    @FXML
    private BorderPane borderPane;

    private Controller centerController;

    @Override
    public void onOpen(Object input) throws Exception {
        changeScene(Scenes.START,null);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void changeScene(Scenes scenes, Object data) throws Exception {
        View view = loadFXML(scenes);
        borderPane.setCenter(view.scene);
        this.centerController = view.controller;
        this.centerController.onOpen(data);

    }
    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getURL();
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        Controller c = loader.getController();
        View view = new View();
        view.scene =p;
        view.controller=c;
        return view;
    }
}

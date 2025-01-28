package dam.JosantVarona;

import dam.JosantVarona.View.AppController;
import dam.JosantVarona.View.Scenes;
import dam.JosantVarona.View.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    public static AppController currenController;

    @Override
    public void start(Stage stage) throws Exception {
        View view = AppController.loadFXML(Scenes.ROOT);
        scene = new Scene(view.scene, 1500, 750);
        currenController = (AppController) view.controller;
        currenController.onOpen(null);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
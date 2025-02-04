package dam.JosantVarona.View;


import dam.JosantVarona.App;
import dam.JosantVarona.Connection.UserSesion;
import dam.JosantVarona.model.Habito;
import dam.JosantVarona.model.Usuario;
import dam.JosantVarona.service.ServiceHabitos;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerHabitos extends Controller implements Initializable{
    @FXML
    private TableView<Habito> habitos;
    @FXML
    private TableColumn<Habito, Integer> frecuencia;
    @FXML
    private TableColumn<Habito, String> tipo;
    @FXML
    private TableColumn<Habito, String> fecha;
    @FXML
    private TableColumn<Habito, String> actividad;
    @FXML
    private TableColumn<Habito, Void> delete;
    private ObservableList<Habito> userHabitos;

    @Override
    public void onOpen(Object input) throws Exception {
        cargarHabitos();
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        frecuencia.setCellValueFactory(new PropertyValueFactory<>("frecuencia"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        fecha.setCellValueFactory(cellData -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new SimpleStringProperty(cellData.getValue().getUltimaFecha().format(formatter));
        });
        actividad.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getIdActividad().getNombre()));
        delete.setCellFactory(param -> new TableCell<>() {
            private final Button btnEliminar = new Button("Eliminar");

            {
                btnEliminar.setOnAction(event -> {
                    Habito habito = getTableView().getItems().get(getIndex());
                    eliminarhabito(habito);
                });

                btnEliminar.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnEliminar);
                }
            }
        });
    }
    @FXML
    private void goToMain() throws Exception {
        App.currenController.changeScene(Scenes.MAIN,null);
    }
    @FXML
    private void goToInsertHabitos() throws Exception {
        App.currenController.openModalv(Scenes.INSERTHABITOS,"Insertar habito",this,null);
    }
    private void cargarHabitos() {
        ServiceHabitos serviceHabitos = new ServiceHabitos();
        Usuario usuario = UserSesion.getInstancia().getUsuarioIniciado();
        List<Habito> showHabitos = serviceHabitos.habitosUser(usuario);
        this.userHabitos = FXCollections.observableArrayList(showHabitos);
        habitos.setItems(userHabitos);
        habitos.refresh();
    }
    private void eliminarhabito(Habito habito) {
        if (habito != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Seguro que quieres eliminar esta huella?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    ServiceHabitos serviceHabitos = new ServiceHabitos();
                    boolean eliminado = serviceHabitos.deleteHabitos(habito);

                    if (eliminado) {
                        cargarHabitos();
                    }
                }
            });
        }
    }
    @FXML
    private void updateHabitos() throws Exception {
        Habito habito = habitos.getSelectionModel().getSelectedItem();
        if (habito != null) {
            App.currenController.openModalv(Scenes.INSERTHABITOS,"Actulizar dato",this,habito);
        }
    }
}

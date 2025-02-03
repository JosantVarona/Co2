package dam.JosantVarona.View;

import dam.JosantVarona.App;
import dam.JosantVarona.Connection.UserSesion;
import dam.JosantVarona.model.Huella;
import dam.JosantVarona.model.Usuario;
import dam.JosantVarona.service.ServiceHuella;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.math.BigDecimal;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class Controllerhuellas extends Controller implements Initializable {

    @FXML
    private TableView<Huella> huellas;
    @FXML
    private TableColumn<Huella, String> columnValor;
    @FXML
    private TableColumn<Huella, String> columnUnidad;
    @FXML
    private TableColumn<Huella, String> columnFecha;
    @FXML
    private TableColumn<Huella, Void> columnDelete;
    @FXML
    private TableColumn<Huella, String> columnActividad;

    private ObservableList<Huella> userHuellas;

    @Override
    public void onOpen(Object input) throws Exception {
        cargarHuellas();
    }

    @Override
    public void onClose(Object output) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnActividad.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getIdActividad().getNombre()));
        columnValor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getValor().toString()) // Convertimos BigDecimal a String
        );

        columnUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));

        columnFecha.setCellValueFactory(cellData -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new SimpleStringProperty(cellData.getValue().getFecha().format(formatter));
        });

        columnDelete.setCellFactory(param -> new TableCell<>() {
            private final Button btnEliminar = new Button("Eliminar");

            {
                btnEliminar.setOnAction(event -> {
                    Huella huella = getTableView().getItems().get(getIndex());
                    eliminarHuella(huella);
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

        huellas.setEditable(true);

        columnValor.setCellFactory(TextFieldTableCell.forTableColumn());

        columnValor.setOnEditCommit(event -> {
            String newValue = event.getNewValue();

            if (newValue.matches("\\d+(\\.\\d+)?")) {
                Huella huellaEditada = event.getRowValue();
                huellaEditada.setValor(new BigDecimal(newValue));

                actualizarHuella(huellaEditada);
            } else {
                mostrarAlerta("El valor ingresado no es válido. Solo se permiten números.");
                columnValor.getTableView().refresh();
            }
        });

        columnValor.setOnEditCancel(event -> columnValor.getTableView().refresh());

        cargarHuellas();
    }

    @FXML
    private void goToMain() throws Exception {
        App.currenController.changeScene(Scenes.MAIN, null);
    }

    @FXML
    private void goToAddHuella() throws Exception {
        App.currenController.openModalv(Scenes.INSERHUELLA, "Añadiendo huella", this, null);
    }

    private void eliminarHuella(Huella huella) {
        if (huella != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Seguro que quieres eliminar esta huella?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    ServiceHuella serviceHuella = new ServiceHuella();
                    boolean eliminado = serviceHuella.huellaDeleta(huella);

                    if (eliminado) {
                        cargarHuellas();
                    }
                }
            });
        }
    }

    private void actualizarHuella(Huella huella) {
        if (huella != null) {
            ServiceHuella serviceHuella = new ServiceHuella();
            boolean actualizado = serviceHuella.huellaUpdateada(huella);

            if (actualizado) {
                huellas.refresh();
            }
        }
    }

    private void cargarHuellas() {
        ServiceHuella serviceHuella = new ServiceHuella();
        Usuario usuario = UserSesion.getInstancia().getUsuarioIniciado();
        List<Huella> showHuellas = serviceHuella.huellaUsuario(usuario);
        this.userHuellas = FXCollections.observableArrayList(showHuellas);
        huellas.setItems(userHuellas);
        huellas.refresh();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR, mensaje, ButtonType.OK);
        alert.showAndWait();
    }
}

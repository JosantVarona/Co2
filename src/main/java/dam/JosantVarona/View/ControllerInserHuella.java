package dam.JosantVarona.View;

import dam.JosantVarona.Connection.UserSesion;
import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Huella;
import dam.JosantVarona.service.ServiceActividad;
import dam.JosantVarona.service.ServiceHuella;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerInserHuella extends Controller implements Initializable {
    @FXML
    private ComboBox<String> cbActividad;
    @FXML
    private Label factor;
    @FXML
    private Label unidad;
    @FXML
    private Label categoria;
    @FXML
    private DatePicker date;
    @FXML
    private TextField valor;

    private ServiceActividad serviceActivi = new ServiceActividad();

    private List<Actividad> actividades = serviceActivi.allActividades();

    @Override
    public void onOpen(Object input) throws Exception {


        List<String> boxAcctividades = new ArrayList<>();
        for (Actividad actividad : actividades) {
            boxAcctividades.add(actividad.getNombre());
        }
        ObservableList<String> boxAcctividadesObservable = FXCollections.observableArrayList(boxAcctividades);
        cbActividad.setItems(boxAcctividadesObservable);
        cbActividad.setOnAction(this::datosActividad);
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


    private void datosActividad(ActionEvent event) {
        String nombreActividad = cbActividad.getValue();
        if (nombreActividad != null) {
            for (Actividad actividad : actividades) {
                if (actividad.getNombre().equals(nombreActividad)) {
                    factor.setText(String.valueOf(actividad.getIdCategoria().getFactorEmision()));
                    unidad.setText(String.valueOf(actividad.getIdCategoria().getUnidad()));
                    categoria.setText(String.valueOf(actividad.getIdCategoria().getNombre()));
                }
            }
        }
    }
    @FXML
    private void GuardarHuella() {
        Huella huella = new Huella();
        String nombreActividad = cbActividad.getValue();
        ServiceHuella huellaService = new ServiceHuella();

        if (date != null) {
            LocalDate selectedDate = date.getValue();
            LocalDate currentDate = LocalDate.now();

            System.out.println("Selected Date: " + selectedDate);
            System.out.println("Current Date: " + currentDate);

            if (selectedDate != null && currentDate.isAfter(selectedDate)) {
                if (nombreActividad != null) {
                    for (Actividad actividad : actividades) {
                        if (actividad.getNombre().equals(nombreActividad)) {
                            huella.setValor(BigDecimal.valueOf(Double.parseDouble(valor.getText())));
                            huella.setIdActividad(actividad);
                            huella.setIdUsuario(UserSesion.getInstancia().getUsuarioIniciado());
                            huella.setUnidad(unidad.getText());
                            huella.setFecha(selectedDate);
                            huellaService.huellaInsertada(huella);
                            System.out.println(huella);
                        }
                    }
                }
            } else {
                System.out.println("Datos invalidos");
            }
        } else {
            System.out.println("Fecha no seleccionada");
        }
    }

}

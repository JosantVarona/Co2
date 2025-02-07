package dam.JosantVarona.View;

import dam.JosantVarona.App;
import dam.JosantVarona.Connection.UserSesion;
import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Habito;
import dam.JosantVarona.model.HabitoId;
import dam.JosantVarona.service.ServiceActividad;
import dam.JosantVarona.service.ServiceHabitos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerInsertHabitos extends Controller implements Initializable {
    @FXML
    private ComboBox<String> tipoHabito;
    @FXML
    private ComboBox<String> cbActividad;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextField frecuencia;
    @FXML
    private Button inserbutton;
    @FXML
    private Button updatebutton;
    @FXML
    private Label activi;

    private ServiceActividad serviceActivi = new ServiceActividad();

    private List<Actividad> actividades = serviceActivi.allActividades();

    private Habito habitoupdate = null;
    @Override
    public void onOpen(Object input) throws Exception {
        habitoupdate = (Habito) input;
        System.out.println(habitoupdate);

        if (habitoupdate != null) {
            inserbutton.setVisible(false);
            cbActividad.setVisible(false);
            activi.setVisible(false);
            tipoHabito.setValue(habitoupdate.getTipo());
            frecuencia.setText(habitoupdate.getFrecuencia().toString());
            fecha.setValue(habitoupdate.getUltimaFecha());
        }else {
            updatebutton.setVisible(false);
        }

        List<String> boxAcctividades = new ArrayList<>();
        for (Actividad actividad : actividades) {
            boxAcctividades.add(actividad.getNombre());
        }
        ObservableList<String> boxAcctividadesObservable = FXCollections.observableArrayList(boxAcctividades);
        cbActividad.setItems(boxAcctividadesObservable);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoHabito.setItems(FXCollections.observableArrayList("Diário","Semanal","Mensual","Anual"));
        if (habitoupdate == null) {
            tipoHabito.setValue("Tipo");
        }


    }
    @FXML
    private void closeWindow(Event event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void insertHabito() throws Exception {
        Habito habito = new Habito();
        String tipo = tipoHabito.getValue();
        ServiceHabitos serviceHabitos = new ServiceHabitos();
        String nombreActividad = cbActividad.getValue();
        LocalDate selectedDate = null;

            selectedDate = fecha.getValue();

            if (selectedDate != null) {

                LocalDate currentDate = LocalDate.now();
                if (currentDate.isAfter(selectedDate)) {

                    if (nombreActividad != null && !nombreActividad.isEmpty() && !tipo.contains("Tipo")&& frecuencia.getText().matches("\\d+")) {
                        for (Actividad actividad : actividades) {
                            if (actividad.getNombre().equals(nombreActividad)) {
                                habito.setIdUsuario(UserSesion.getInstancia().getUsuarioIniciado());
                                habito.setIdActividad(actividad);
                                habito.setFrecuencia(Integer.valueOf(frecuencia.getText()));
                                habito.setTipo(tipo);
                                habito.setUltimaFecha(selectedDate);
                                HabitoId habitoId = new HabitoId();
                                habitoId.setIdActividad(actividad.getId());
                                habitoId.setIdUsuario(UserSesion.getInstancia().getUsuarioIniciado().getId());
                                habito.setId(habitoId);
                                if (!serviceHabitos.exitHabitos(habito,UserSesion.getInstancia().getUsuarioIniciado())) {
                                    serviceHabitos.insertHabitos(habito);
                                    App.currenController.changeScene(Scenes.HABITOS, null);
                                }else {
                                    AppController.alertDatosIncorretos();
                                }

                            }
                        }
                    }else {
                        AppController.alertDatosIncorretos();
                    }
                } else {
                    AppController.alertDatosIncorretos();
                }
            } else {
                AppController.selectFech();

            }
    }
    @FXML
    private void actualizarHabito() throws Exception {
        String tipo = tipoHabito.getValue();
        ServiceHabitos serviceHabitos = new ServiceHabitos();

        if (fecha != null) {
            LocalDate selectedDate = fecha.getValue();
            LocalDate currentDate = LocalDate.now();

            System.out.println("Selected Date: " + selectedDate);
            System.out.println("Current Date: " + currentDate);

            if (selectedDate != null && currentDate.isAfter(selectedDate)) {

                if ( frecuencia.getText().matches("\\d+")) {

                            habitoupdate.setFrecuencia(Integer.valueOf(frecuencia.getText()));
                            habitoupdate.setTipo(tipo);
                            habitoupdate.setUltimaFecha(selectedDate);
                            HabitoId habitoId = new HabitoId();
                            habitoId.setIdActividad(habitoupdate.getIdActividad().getId());
                            habitoId.setIdUsuario(habitoupdate.getIdUsuario().getId());
                            habitoupdate.setId(habitoId);
                            serviceHabitos.updateHabitos(habitoupdate);
                            App.currenController.changeScene(Scenes.HABITOS, null);
                }else {
                    AppController.alertDatosIncorretos();
                }
            } else {
                AppController.alertDatosIncorretos();
            }
        } else {
            AppController.selectFech();
        }
    }
}

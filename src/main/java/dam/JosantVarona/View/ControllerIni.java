package dam.JosantVarona.View;

import dam.JosantVarona.App;
import dam.JosantVarona.Connection.UserSesion;
import dam.JosantVarona.DAO.HuellaDAO;
import dam.JosantVarona.model.Recomendacion;
import dam.JosantVarona.model.Usuario;
import dam.JosantVarona.service.ServiceHuella;
import dam.JosantVarona.service.ServiceRecomend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class ControllerIni extends Controller implements Initializable {
    @FXML
    private TableView<Recomendacion> recomendacionTable;
    @FXML
    private TableColumn<Recomendacion, String> descripcionColumn;
    @FXML
    private Label tfName;
    @FXML
    private StackedBarChart<String, Number> stackedBarChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    ComboBox <Integer> comboMes;
    @FXML
    ComboBox <Integer> comboAnio;


    private ObservableList<Recomendacion> recomendaciones;
    @Override
    public void onOpen(Object input) throws Exception {
        ServiceRecomend serviceRecomend = new ServiceRecomend();
        Usuario usuario = UserSesion.getInstancia().getUsuarioIniciado();
        List<Recomendacion> showRecomed = serviceRecomend.recomedUser(usuario);
        this.recomendaciones = FXCollections.observableArrayList(showRecomed);
        recomendacionTable.setItems(recomendaciones);

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfName.setText(UserSesion.getInstancia().getUsuarioIniciado().getNombre());
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        comboMes.getItems().addAll(IntStream.rangeClosed(1,12).boxed().toArray(Integer[]::new));
        int anioActual = LocalDate.now().getYear();
        comboAnio.getItems().addAll(IntStream.rangeClosed(2000,anioActual).boxed().toArray(Integer[]::new));
    }
    @FXML
    private void goTologin() throws Exception {
        UserSesion.getInstancia().logOut();
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
    private void cargarImpactoMensual(Integer anio, Integer mes) {
        stackedBarChart.getData().clear();
        Usuario usuario = UserSesion.getInstancia().getUsuarioIniciado();
        ServiceHuella serviceHuella = new ServiceHuella();

        List<Object[]> impactoMensual = serviceHuella.impactoMensual(usuario, anio, mes);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Impacto Semanal");

        for (Object[] fila : impactoMensual) {
            Integer semana = (Integer) fila[0];
            Number impacto = (Number) fila[1];
            series.getData().add(new XYChart.Data<>("Semana " + semana, impacto));
        }

        stackedBarChart.getData().add(series);
    }
    @FXML
    private void showImpactoMensual() {
        Integer mes = comboMes.getValue();
        Integer anio = comboAnio.getValue();
        System.out.println(anio);
        if (mes != null && anio != null) {
            cargarImpactoMensual(anio, mes);
        }else {
            System.out.println("selecciona los campos");
        }
    }
}

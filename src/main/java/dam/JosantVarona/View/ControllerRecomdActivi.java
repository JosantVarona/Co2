package dam.JosantVarona.View;


import dam.JosantVarona.model.Actividad;
import dam.JosantVarona.model.Habito;
import dam.JosantVarona.model.Recomendacion;
import dam.JosantVarona.service.ServiceRecomend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ControllerRecomdActivi extends Controller implements Initializable {
    @FXML
    private TableView<Recomendacion> tablaRecomendacion;
    @FXML
    private TableColumn<Recomendacion, String> colDecripcion;
    @FXML
    private TableColumn<Recomendacion, BigDecimal> colImpacto;
    private ObservableList<Recomendacion> recomendaciones;
    @Override
    public void onOpen(Object input) throws Exception {
        ServiceRecomend serviceRecomend = new ServiceRecomend();
        Habito habito = (Habito) input;
        Actividad ac = habito.getIdActividad();
        List<Recomendacion> recomd = serviceRecomend.recomendActiviadad(ac);
        this.recomendaciones = FXCollections.observableList(recomd);
        tablaRecomendacion.setItems(recomendaciones);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDecripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colImpacto.setCellValueFactory(new PropertyValueFactory<>("impactoEstimado"));
    }
}

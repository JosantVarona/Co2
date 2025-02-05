package dam.JosantVarona.View;

import dam.JosantVarona.App;
import dam.JosantVarona.Connection.UserSesion;
import dam.JosantVarona.model.Usuario;
import dam.JosantVarona.service.ServiceUser;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerLogin extends Controller implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private TextField cuenta;
    @FXML
    private PasswordField pass;
    @FXML
    private TableView<Object[]> table;
    @FXML
    private TableColumn<Object[], String> user;
    @FXML
    private TableColumn<Object[], BigDecimal> impacto;
    @FXML
    private TableColumn<Object[], Integer> posicion;

    private ObservableList<Object[]> ranking;

    @Override
    public void onOpen(Object input) throws Exception {
        ServiceUser serviceUser = new ServiceUser();
        List<Object[]> users = serviceUser.ranking();
        this.ranking = FXCollections.observableArrayList(users);
        table.setItems(ranking);
    }

    @Override
    public void onClose(Object output) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        posicion.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(table.getItems().indexOf(cellData.getValue()) + 1).asObject()
        );
        impacto.setCellValueFactory(cellData -> new SimpleObjectProperty<>((BigDecimal) cellData.getValue()[0]));
        user.setCellValueFactory(cellData -> new SimpleObjectProperty<>((String) cellData.getValue()[1]));
    }
    @FXML
    public void GoMainRegister() throws Exception {
        ServiceUser serviceUser = new ServiceUser();
        if (serviceUser.userExists(recogerDatosUsuarioRegister().getEmail())){
            boolean guardado = serviceUser.registerUser(recogerDatosUsuarioRegister());
            if (guardado){
                System.out.println("Usuario registrado");
                UserSesion.getInstancia().logIn(serviceUser.usuarioSesion(recogerDatosUsuarioRegister().getEmail()));
                App.currenController.changeScene(Scenes.MAIN,null);
            }
        }else {
            System.out.println("Usuario no encontrado");
        }
    }
    @FXML
    public void GoMainLogin() throws Exception {
        ServiceUser serviceUser = new ServiceUser();
        Usuario usuario = recogerDatosUsuarioLogin();
        if (usuario != null) {
            if (serviceUser.userLogin(usuario)){
                System.out.println("Usuario login");
                UserSesion.getInstancia().logIn(serviceUser.usuarioSesion(usuario.getEmail()));
                App.currenController.changeScene(Scenes.MAIN,null);
            }else {
                System.out.println("Usuario no encontrado");
            }
        }


    }
    private Usuario recogerDatosUsuarioRegister() {
        Usuario result = new Usuario();
        if (!username.getText().isEmpty() || !password.getText().isEmpty() || !email.getText().isEmpty()){
            result.setNombre(username.getText());
            result.setContraseña(password.getText());
            result.setEmail(email.getText());
        }
        return result;
    }
    private Usuario recogerDatosUsuarioLogin() {
        Usuario result = null;
        if (!cuenta.getText().isEmpty() || !pass.getText().isEmpty()){
            result = new Usuario();
            result.setEmail(cuenta.getText());
            result.setContraseña(pass.getText());
        }
        return result;
    }

}

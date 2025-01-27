package dam.JosantVarona.View;

import dam.JosantVarona.App;
import dam.JosantVarona.model.Usuario;
import dam.JosantVarona.service.ServiceUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
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
    public void GoMainRegister() throws Exception {
        ServiceUser serviceUser = new ServiceUser();
        if (serviceUser.userExists(recogerDatosUsuarioRegister().getEmail())){
            boolean guardado = serviceUser.registerUser(recogerDatosUsuarioRegister());
            if (guardado){
                System.out.println("Usuario registrado");
                App.currenController.changeScene(Scenes.MAIN,null);
            }
        }else {
            System.out.println("los campos no existe o el usuario ya esta");
        }
    }
    @FXML
    public void GoMainLogin() throws Exception {
        ServiceUser serviceUser = new ServiceUser();
        Usuario usuario = recogerDatosUsuarioLogin();
        if (usuario != null) {
            if (serviceUser.userLogin(usuario)){
                System.out.println("Usuario login");
                App.currenController.changeScene(Scenes.MAIN,null);
            }
        }
        System.out.println("Necestas introducir datos");

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

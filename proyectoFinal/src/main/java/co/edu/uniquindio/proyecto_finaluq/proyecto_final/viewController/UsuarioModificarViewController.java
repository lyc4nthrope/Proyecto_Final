package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.SGREApplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;


public class UsuarioModificarViewController {
    SGREApplication principal = new SGREApplication();
    UsuarioRegistrarViewController usuarioRegistrarViewController = new UsuarioRegistrarViewController();


    @FXML
    private Button btnAgregarReserva;

    @FXML
    private Button btnCancelarReserva;

    @FXML
    private Button btnModificarDatos;

    @FXML
    private Button btnModificarEspacios;

    @FXML
    private Button btnSalir;

    @FXML
    private ComboBox<?> cmboxEvento;

    @FXML
    private Label correoText;

    @FXML
    private Label ContraseñaText2;

    @FXML
    private Label contraseñaText;

    @FXML
    private Label idText;

    @FXML
    private Label nombreText;

    @FXML
    private TableView<?> tableUsuariosUse;

    @FXML
    private TableColumn<?, ?> tcEspacios;

    @FXML
    private TableColumn<?, ?> tcEstado;

    @FXML
    private TableColumn<?, ?> tcEvento;

    @FXML
    private TableColumn<?, ?> tcFecha;

    @FXML
    private TextField txtEspacios;


    @FXML
    void onButtonCancelarAction(ActionEvent event) throws IOException {
        principal.changeScene("UsuarioUseView.fxml", null, null);
    }

    private void limpiarCamposUsuario() {
        idText.setText("");
        nombreText.setText("");
        correoText.setText("");
        contraseñaText.setText("");
        ContraseñaText2.setText("");
    }

    @FXML
    void onButtonModificarAction(ActionEvent event) throws IOException {
        if (contraseñaText == ContraseñaText2){
            limpiarCamposUsuario();
            nombreText.getText();
            idText.getText();
            correoText.getText();
            contraseñaText.getText();
            ContraseñaText2.getText();

        }


    }
}


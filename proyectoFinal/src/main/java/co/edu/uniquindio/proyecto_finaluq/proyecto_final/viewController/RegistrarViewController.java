package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarViewController {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtConfimarContraseña;

    @FXML
    private TextField txtContraseña;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!txtNombre.getText().trim().isEmpty() && !txtId.getText().trim().isEmpty() && !txtCorreo.getText().trim().isEmpty() && !txtContraseña.getText().trim().isEmpty() && !txtConfimarContraseña.getText().trim().isEmpty()){
                    if (!txtContraseña.getText().equals(txtConfimarContraseña)){
                        registrarUsuario(event, txtNombre.getText(), txtId.getText(), txtCorreo.getText(), txtContraseña.getText());
                    }else {
                        System.out.println("Las contraseñas no coinciden");
                        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
                        alert.setContentText("Las contraseñas tienen que ser iguales");
                        alert.show();
                    }
                }else {
                    System.out.println("Por favor llene toda la informacion");
                    Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setContentText("Por favor llene toda la informacion");
                    alert.show();
                }
            }
        });
        btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeScene(event, "Inicio.fxml", null, null);
            }
        });
    }

    private void registrarUsuario(ActionEvent event, String nombre, String id, String correo, String contraseña) {

    }

    private void changeScene(ActionEvent event, String fxmlFile, String correo, String contrasenia) {
        /*Parent root = null;

        try {
            root = FXMLLoader.load(InicioViewController.class.getResource(fxmlFile));
        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();*/
    }


}


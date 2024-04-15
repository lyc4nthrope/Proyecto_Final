package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.SGREApplication;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.UsuarioController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarViewController{

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


    @FXML
    private void registrarUsuario(ActionEvent event) throws IOException {
        if (!txtNombre.getText().trim().isEmpty() && !txtId.getText().trim().isEmpty() && !txtCorreo.getText().trim().isEmpty() && !txtContraseña.getText().trim().isEmpty() && !txtConfimarContraseña.getText().trim().isEmpty()){
            if (!txtContraseña.getText().equals(txtConfimarContraseña.getText())){
            SGREApplication.mostrarMensaje("Error","Contraseñas no coinciden", "Las contraseñas no coinciden asegurese de que ingreso correctamente", Alert.AlertType.ERROR );
            }else {
                // registrar usuario con comprobacion
                UsuarioController usuarioController = new UsuarioController();
                if (usuarioController.agregarUsuario(obtenerUsuario())){
                    vaciarCampos();
                    SGREApplication.mostrarMensaje("Registro completo", "El registro se realizo correctamente", "Se registro correctamente, ahora inicia sesion!", Alert.AlertType.INFORMATION);
                    volver();
                }else{
                    SGREApplication.mostrarMensaje("Error al registrar", "Hubo un error al registrar", "Se genero un error al registrar, posiblemente tu cedula ya este registrado o tu correo ya este en uso", Alert.AlertType.ERROR);
                }
            }
        }else {
            SGREApplication.mostrarMensaje("Error", "Campos vacios", "Algun campo no ha sido llenado, por favor ingrese todos los datos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void volver() throws IOException {
        SGREApplication.changeScene("Inicio.fxml",null,null);
    }


    private UsuarioDto obtenerUsuario(){
        String nombreUsuario = txtNombre.getText();
        String cedulaUsuario = txtId.getText();
        String correoUsuario = txtCorreo.getText();
        String contraseñaUsuario = txtContraseña.getText();
        return new UsuarioDto(cedulaUsuario,nombreUsuario,correoUsuario,contraseñaUsuario);
    }

    private void vaciarCampos(){
        txtNombre.setText("");
        txtContraseña.setText("");
        txtCorreo.setText("");
        txtId.setText("");
        txtConfimarContraseña.setText("");
    }
}


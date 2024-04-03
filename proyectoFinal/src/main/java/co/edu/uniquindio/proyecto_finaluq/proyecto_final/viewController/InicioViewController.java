package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.InicioController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioViewController {
    InicioController inicioController = new InicioController();
    @FXML
    public TextField txtContrasenia;

    @FXML
    public TextField txtCorreo;

    public void switchUsuarioUse(ActionEvent event, UsuarioDto usuario) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UsuarioUseView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        UseUsuarioController controller = loader.getController();
        controller.sesionUsuario=usuario;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchEmpleadoUse(ActionEvent event, EmpleadoDto empleadoDto) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(".fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // controller = loader.getController();
        //controller.sesionUsuario=usuario;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void iniciarSesion(ActionEvent event) throws IOException {
        String correoAux= txtCorreo.getText();
        String contraseniaAux = txtContrasenia.getText();
        if (datosUsuarioValidos(correoAux,contraseniaAux) || datosEmpleadoValidos(correoAux,contraseniaAux)) {
            if (inicioController.inicioSesion(correoAux, contraseniaAux)) {
                UsuarioDto usuarioDtoAux = inicioController.sesionUsuario(correoAux, contraseniaAux);
                EmpleadoDto empleadoDtoAux = inicioController.sesionEmpleado(correoAux, contraseniaAux);
                if (usuarioDtoAux != null) {
                    switchUsuarioUse(event, usuarioDtoAux);
                }
                if (empleadoDtoAux != null) {
                    switchEmpleadoUse(event, empleadoDtoAux);
                }
            }
        }
    }

    @FXML
    void onRegistrarseButtonAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UsuarioRegistrarView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean datosUsuarioValidos(String correo, String contraseña) {
        String mensaje = "";
        if(correo == null || correo.equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(contraseña == null || contraseña.equals(""))
            mensaje += "El ID es invalido \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private boolean datosEmpleadoValidos(String correo, String contraseña) {
        String mensaje = "";
        if(correo == null || correo.equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(contraseña == null || contraseña.equals(""))
            mensaje += "El ID es invalido \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    public void salir(){
System.exit(0);
    }
}

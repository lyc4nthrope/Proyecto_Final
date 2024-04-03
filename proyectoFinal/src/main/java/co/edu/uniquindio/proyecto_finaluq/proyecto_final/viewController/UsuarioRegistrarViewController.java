package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.InicioController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class UsuarioRegistrarViewController {

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

    @FXML
    void onAceptarButtonClick(ActionEvent event) {
        crearUsuario();
        showInicio();
    }

    @FXML
    void onCancelarButtonClick(ActionEvent event) {
        showInicio();
    }

    private void crearUsuario(){
        UsuarioDto usuarioDto = construirUsuarioDto();
        if(datosValidos(usuarioDto)){
            if(usuarioControllerService.agregarUsuario(usuarioDto)){
                listaUsuariosDto.add(usuarioDto);
                mostrarMensaje("Notificación usuario", "Usuario creado", "El usuario se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposUsuario();
            }else{
                mostrarMensaje("Notificación usuario", "Usuario no creado", "El usuario no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación usuario", "Usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    private boolean datosValidos(UsuarioDto usuarioDto) {
        String mensaje = "";
        if(usuarioDto.nombre() == null || usuarioDto.nombre().equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(usuarioDto.id() == null || usuarioDto.id() .equals(""))
            mensaje += "El ID es invalido \n" ;
        if(usuarioDto.correo() == null || usuarioDto.correo().equals(""))
            mensaje += "El correo es invalido \n" ;
        if(usuarioDto.contraseña() == null || usuarioDto.contraseña().equals(""))
            mensaje += "El correo es invalido \n" ;
        if(usuarioDto.confirmarContraseña() == null || usuarioDto.confirmarContraseña().equals(""))
            mensaje += "El correo es invalido \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void limpiarCamposUsuario() {
        txtNombre.setText("");
        txtId.setText("");
        txtCorreo.setText("");
        txtContraseña.setText("");
        txtConfimarContraseña.setText("");
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    void showInicio() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewController/Inicio.fxml"));
        Parent root = loader.load();
        InicioController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init;
        stage.show();
        this.stage.close();
    }
}

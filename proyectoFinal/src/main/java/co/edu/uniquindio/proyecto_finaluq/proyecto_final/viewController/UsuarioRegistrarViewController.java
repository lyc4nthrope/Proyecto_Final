package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    }

    @FXML
    void onCancelarButtonClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        usuarioRegistrarControllerService = new UsuarioRegistrarController();
    }

    private void obtenerUsuarios() {
        listaUsuariosDto.addAll(usuarioControllerService.obtenerUsuarios());
    }

    @FXML
    void nuevoUsuarioAction(ActionEvent event) {
        txtId.setText("Ingrese el ID");
        txtNombre.setText("Ingrese el nombre");
        txtCorreo.setText("Ingrese el correo electronico");
        txtContraseña.setText("Ingrese la contraseña");
        txtConfimarContraseña.setText("Ingrese la contraseña de nuevo");

    }

    private void crearUsuario() {
        //1. Capturar los datos
        UsuarioDto usuarioDto = construirUsuarioDto();
        //2. Validar la información
        if(datosValidos(usuarioDto)){
            if(usuarioControllerService.agregarUsuario(usuarioDto)){
                listaUsuariosDto.add(usuarioDto);
                mostrarMensaje("Notificación empleado", "Empleado creado", "El empleado se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposUsuario();
            }else{
                mostrarMensaje("Notificación empleado", "Empleado no creado", "El empleado no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    private UsuarioDto construirUsuarioDto() {
        return new UsuarioDto(
                txtId.getText(),
                txtNombre.getText(),
                "",
                txtCorreo.getText(),
                txtContraseña.getText(),
                txtConfimarContraseña.getText(),
                );
    }

    private void limpiarCamposUsuario() {
        txtId.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtContraseña.setText("");
        txtConfimarContraseña.setText("");
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

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}

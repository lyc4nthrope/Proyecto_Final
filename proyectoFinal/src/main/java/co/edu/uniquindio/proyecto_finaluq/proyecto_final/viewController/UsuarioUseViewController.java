package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsuarioUseViewController {

    UsuarioUseController usuarioUseControllerService;
    ObservableList<UsuarioUseDto> listaUsuariosUseDto = FXCollections.observableArrayList();
    UsuarioUseDto usuarioUseSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtEvento;

    @FXML
    private TextField txtFecha;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtEspacios;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregarReserva;

    @FXML
    private Button btnCancelarReserva;

    @FXML
    private Button btnModificarEspacios;

    @FXML
    private Button btnModificarDatos;

    @FXML
    private Button btnSalir;

    @FXML
    private TableView<UsuarioUseDto> tableUsuariosUse;

    @FXML
    private TableColumn<UsuarioDto, String> tcEvento;

    @FXML
    private TableColumn<UsuarioDto, String> tcFecha;

    @FXML
    private TableColumn<UsuarioDto, String> tcEstado;

    @FXML
    private TableColumn<UsuarioDto, String> tcEspacios;

    @FXML
    void initialize() {
        usuarioUseControllerService = new UsuarioUseController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        obtenerUsuarios();
        tableUsuariosUse.getItems().clear();
        tableUsuariosUse.setItems(listaUsuariosUseDto);
        listenerSelection();
    }

    private void initDataBinding() {
        tcEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().evento()));
        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fecha()));
        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estado()));
        tcEspacios.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().espacios()));
    }

    private void obtenerUsuariosUse() {
        listaUsuariosUseDto.addAll(usuarioUseControllerService.obtenerUsuariosUse());
    }

    private void listenerSelection() {
        tableUsuariosUse.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioUseSeleccionado = newSelection;
            mostrarInformacionUsuarioUse(usuarioUseSeleccionado);
        });
    }

    private void mostrarInformacionUsuarioUse(UsuarioUseDto usuarioUseSeleccionado) {
        if(usuarioSeleccionado != null){
            txtEvento.setText(usuarioUseSeleccionado.evento());
            txtFecha.setText(usuarioUseSeleccionado.fecha());
            txtEstado.setText(usuarioUseSeleccionado.estado());
            txtEspacios.setText(String.valueOf(usuarioUseSeleccionado.espacios()));
        }
    }

    @FXML
    void nuevoUsuarioUseAction(ActionEvent event) {
        txtEvento.setText("Ingrese el evento");
        txtFecha.setText("Ingrese la fecha");
        txtEstado.setText("Ingrese el estado");
        txtEspacios.setText("Ingrese los espacio");
    }

    @FXML
    void agregarUsuarioUseAction(ActionEvent event) {
        crearUsuarioUse();
    }

    @FXML
    void eliminarUsuarioUseAction(ActionEvent event) {
        eliminarUsuarioUse();
    }


    @FXML
    void actualizarUsuarioUseAction(ActionEvent event) {
        actualizarUsuarioUse();
    }

    private void crearUsuarioUse() {
        //1. Capturar los datos
        UsuarioUseDto usuarioUseDto = construirUsuarioUseDto();
        //2. Validar la información
        if(datosValidos(usuarioUseDto)){
            if(usuarioUseControllerService.agregarUsuarioUse(usuarioUseDto)){
                listaUsuariosUseDto.add(usuarioUseDto);
                mostrarMensaje("Notificación empleado", "Empleado creado", "El empleado se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposUsuario();
            }else{
                mostrarMensaje("Notificación empleado", "Empleado no creado", "El empleado no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    private void eliminarUsuarioUse() {
        boolean usuarioUseEliminado = false;
        if(usuarioUseSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de elmininar al usuario?")){
                usuarioUseEliminado = usuarioUseControllerService.eliminarUsuarioUse(usuarioUseSeleccionado.evento());
                if(usuarioUseEliminado == true){
                    listaUsuariosUseDto.remove(usuarioUseSeleccionado);
                    usuarioUseSeleccionado = null;
                    tableUsuariosUse.getSelectionModel().clearSelection();
                    limpiarCamposUsuario();
                    mostrarMensaje("Notificación usuario", "Usuario eliminado", "El usuario se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación usuario", "Usuario no eliminado", "El usuario no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación usuario", "Usuario no seleccionado", "Seleccionado un usuario de la lista", Alert.AlertType.WARNING);
        }
    }

    private void actualizarUsuarioUse() {
        boolean clienteActualizado = false;
        //1. Capturar los datos
        String idActual = usuarioUseSeleccionado.espacios();
        UsuarioUseDto usuarioUseDto = construirUsuarioUseDto();
        //2. verificar el empleado seleccionado
        if(usuarioUseSeleccionado != null){
            //3. Validar la información
            if(datosValidos(usuarioUseSeleccionado)){
                clienteActualizado = usuarioUseControllerService.actualizarUsuarioUse(idActual,usuarioUseDto);
                if(clienteActualizado){
                    listaUsuariosUseDto.remove(usuarioUseSeleccionado);
                    listaUsuariosUseDto.add(usuarioUseDto);
                    tableUsuariosUse.refresh();
                    mostrarMensaje("Notificación usuario", "Usuario actualizado", "El usuario se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposUsuario();
                }else{
                    mostrarMensaje("Notificación usuario", "Usuario no actualizado", "El usuario no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                }
            }else{
                mostrarMensaje("Notificación usuario", "Usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }
    }

    private UsuarioUseDto construirUsuarioUseDto() {
        return new UsuarioDto(
                txtEvento.getText(),
                txtFecha.getText(),
                "",
                txtEstado.getText(),
                txtEspacios.getText(),
                );
    }

    private void limpiarCamposUsuarioUse() {
        txtEvento.setText("");
        txtFecha.setText("");
        txtEstado.setText("");
        txtEspacios.setText("");
    }

    private boolean datosValidos(UsuarioUseDto usuarioUseDto) {
        String mensaje = "";
        if(usuarioUseDto.evento() == null || usuarioUseDto.evento().equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(usuarioUseDto.fecha() == null || usuarioUseDto.fecha() .equals(""))
            mensaje += "El ID es invalido \n" ;
        if(usuarioUseDto.estado() == null || usuarioUseDto.estado().equals(""))
            mensaje += "El correo es invalido \n" ;
        if(usuarioUseDto.espacios() == null || usuarioUseDto.espacios().equals(""))
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

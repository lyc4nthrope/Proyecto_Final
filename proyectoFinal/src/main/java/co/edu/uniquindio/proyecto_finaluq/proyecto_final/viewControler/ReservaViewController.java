package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewControler;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReservaViewController {

    ReservaController reservaControllerService;
    ObservableList<ReservaDto> listaReservasDto = FXCollections.observableArrayList();
    ReservaDto reservaSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtEvento;

    @FXML
    private TextField txtFechaSolicitud;

    @FXML
    private TextField txtEstado;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<EmpleadoDto> tableEmpleados;

    @FXML
    private TableColumn<EmpleadoDto, String> tcId;

    @FXML
    private TableColumn<EmpleadoDto, String> tcUsuario;

    @FXML
    private TableColumn<EmpleadoDto, String> tcEvento;

    @FXML
    private TableColumn<EmpleadoDto, String> tcFechaSolicitud;

    @FXML
    private TableColumn<EmpleadoDto, String> tcEstado;

    @FXML
    void initialize() {
        reservaControllerService = new ReservaController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        obtenerEventos();
        tableReservas.getItems().clear();
        tableReservas.setItems(listaReservasDto);
        listenerSelection();
    }

    private void initDataBinding() {
        tcId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        tcUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        tcFechaSolicitud.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fecha()));
        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().capacidad()));
    }

    private void obtenerEventos() {
        listaReservasDto.addAll(reservaControllerService.obtenerReservas());
    }

    private void listenerSelection() {
        tableReservas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            reservaSeleccionado = newSelection;
            mostrarInformacionEvento(reservaSeleccionado);
        });
    }

    private void mostrarInformacionReserva(ReservaDto reservaSeleccionado) {
        if(reservaSeleccionado != null){
            txtId.setText(reservaSeleccionado.id());
            txtUsuario.setText(reservaSeleccionado.usuario());
            txtEvento.setText(reservaSeleccionado.evento());
            txtFechaSolicitud.setText(reservaSeleccionado.fecheSolicitud());
            txtEstado.setText(reservaSeleccionado.estado());
        }
    }

    @FXML
    void nuevoEventoAction(ActionEvent event) {
        txtId.setText("Ingrese el ID");
        txtUsuario.setText("Ingrese el usuario");
        txtEvento.setText("Ingrese el nombre del evento");
        txtFechaSolicitud.setText("Ingrese la fecha de la solicitud");
        txtEstado.setText("Ingrese el estado");
    }

    @FXML
    void agregarEventoAction(ActionEvent event) {
        crearEvento();
    }

    @FXML
    void eliminarEventoAction(ActionEvent event) {
        eliminarEvento();
    }


    @FXML
    void actualizarEventoAction(ActionEvent event) {
        actualizarEvento();
    }

    private void crearEvento() {
        //1. Capturar los datos
        EventoDto eventoDto = construirEventoDto();
        //2. Validar la información
        if(datosValidos(eventoDto)){
            if(eventoControllerService.agregarEvento(eventoDto)){
                listaEventosDto.add(eventoDto);
                mostrarMensaje("Notificación evento", "Evento creado", "El evento se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposEmpleado();
            }else{
                mostrarMensaje("Notificación evento", "Evento no creado", "El evento no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación evento", "Evento no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    private void eliminarEvento() {
        boolean eventoEliminado = false;
        if(eventoSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de elmininar al evento?")){
                eventoEliminado = eventoControllerService.eliminarEvento(eventoSeleccionado.id());
                if(eventoEliminado == true){
                    listaEventosDto.remove(eventoSeleccionado);
                    empleadoSeleccionado = null;
                    tableEventos.getSelectionModel().clearSelection();
                    limpiarCamposEmpleado();
                    mostrarMensaje("Notificación evento", "Evento eliminado", "El evento se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación evento", "Evento no eliminado", "El evento no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación evento", "Evento no seleccionado", "Seleccionado un evento de la lista", Alert.AlertType.WARNING);
        }
    }

    private void actualizarEvento() {
        boolean clienteActualizado = false;
        //1. Capturar los datos
        String idActual = eventoSeleccionado.id();
        EventoDto eventoDto = construirEventoDto();
        //2. verificar el empleado seleccionado
        if(eventoSeleccionado != null){
            //3. Validar la información
            if(datosValidos(eventoSeleccionado)){
                clienteActualizado = eventoControllerService.actualizarEvento(idActual,eventoDto);
                if(clienteActualizado){
                    listaEventosDto.remove(eventoSeleccionado);
                    listaEventosDto.add(eventoDto);
                    tableEmpleados.refresh();
                    mostrarMensaje("Notificación evento", "Evento actualizado", "El evento se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposEmpleado();
                }else{
                    mostrarMensaje("Notificación evento", "Evento no actualizado", "El evento no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                }
            }else{
                mostrarMensaje("Notificación evento", "Evento no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }
    }

    private EventoDto construirEventoDto() {
        return new EventoDto(
                txtId.getText(),
                txtNombre.getText(),
                "",
                txtDescripcion.getText(),
                txtFecha.getText(),
                txtCapacidad.getText(),
                txtEmpleado.getText(),
                txtReservas.getText(),
                );
    }

    private void limpiarCamposEvento() {
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtFecha.setText("");
        txtCapacidad.setText("");
        txtEmpleado.setText("");
        txtReservas.setText("");
    }

    private boolean datosValidos(EventoDto empleadoDto) {
        String mensaje = "";
        if(empleadoDto.nombre() == null || empleadoDto.nombre().equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(empleadoDto.id() == null || empleadoDto.id() .equals(""))
            mensaje += "El ID es invalido \n" ;
        if(empleadoDto.fecha() == null || empleadoDto.fecha().equals(""))
            mensaje += "El correo es invalido \n" ;
        if(empleadoDto.capacidad() == null || empleadoDto.capacidad() .equals(""))
            mensaje += "Necesita ingresar una capacidad maxima \n" ;
        if(empleadoDto.empleado() == null || empleadoDto.empleado() .equals(""))
            mensaje += "Debe tener un empleado encargado \n" ;
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

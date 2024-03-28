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

public class EventoViewController {

    EventoController eventoControllerService;
    ObservableList<EventoDto> listaEventosDto = FXCollections.observableArrayList();
    EventoDto eventoSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtFecha;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private TextField txtEmpleado;

    @FXML
    private TextField txtReservas;

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
    private TableColumn<EmpleadoDto, String> tcNombre;

    @FXML
    private TableColumn<EmpleadoDto, String> tcDescripcion;

    @FXML
    private TableColumn<EmpleadoDto, String> tcFecha;

    @FXML
    private TableColumn<EmpleadoDto, String> tcCapacidad;

    @FXML
    private TableColumn<EmpleadoDto, String> tcEmpleado;

    @FXML
    private TableColumn<EmpleadoDto, String> tcReservas;

    @FXML
    void initialize() {
        eventoControllerService = new EventoController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        obtenerEventos();
        tableEventos.getItems().clear();
        tableEventos.setItems(listaEventosDto);
        listenerSelection();
    }

    private void initDataBinding() {
        tcId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fecha()));
        tcCapacidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().capacidad()));
        tcEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().empleado()));
        tcReservas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().reservas()));
    }

    private void obtenerEventos() {
        listaEventosDto.addAll(eventoControllerService.obtenerEventos());
    }

    private void listenerSelection() {
        tableEventos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            eventoSeleccionado = newSelection;
            mostrarInformacionEvento(eventoSeleccionado);
        });
    }

    private void mostrarInformacionEvento(EventoDto eventoSeleccionado) {
        if(eventoSeleccionado != null){
            txtId.setText(eventoSeleccionado.id());
            txtNombre.setText(eventoSeleccionado.nombre());
            txtDescripcion.setText(eventoSeleccionado.descripcion());
            txtFecha.setText(eventoSeleccionado.fecha());
            txtCapacidad.setText(eventoSeleccionado.capacidad());
            txtEmpleado.setText(eventoSeleccionado.empleado());
            txtReservas.setText(eventoSeleccionado.reservas());
        }
    }

    @FXML
    void nuevoEventoAction(ActionEvent event) {
        txtId.setText("Ingrese el ID");
        txtNombre.setText("Ingrese el nombre");
        txtDescripcion.setText("Ingrese la descripcion del evento");
        txtFecha.setText("Ingrese la fecha del evento");
        txtCapacidad.setText("Ingrese la capacidad maxima");
        txtEmpleado.setText("Ingrese el empleado asignado");
        txtReservas.setText("Ingrese las reservas");
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
                limpiarCamposEvento();
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
                    limpiarCamposEvento();
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
                    limpiarCamposEvento();
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

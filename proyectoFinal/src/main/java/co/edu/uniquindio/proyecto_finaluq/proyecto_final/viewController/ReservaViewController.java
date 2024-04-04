package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;


import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.ReservaController;
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
    private TableView<ReservaDto> tableReservas;

    @FXML
    private TableColumn<ReservaDto, String> tcId;

    @FXML
    private TableColumn<ReservaDto, String> tcUsuario;

    @FXML
    private TableColumn<ReservaDto, String> tcReserva;

    @FXML
    private TableColumn<ReservaDto, String> tcFechaSolicitud;

    @FXML
    private TableColumn<ReservaDto, String> tcEstado;

    @FXML
//    void initialize() {
//        reservaControllerService = new ReservaController();
//        intiView();
//    }

//    private void intiView() {
//        initDataBinding();
//        obtenerEventos();
//        tableReservas.getItems().clear();
//        tableReservas.setItems(listaReservasDto);
//        listenerSelection();
//    }

//    private void initDataBinding() {
//        tcId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
//        tcUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
//        tcReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().usuario()));
//        tcFechaSolicitud.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaSolicitud()));
//        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estado()));
//    }

    private void obtenerEventos() {
        listaReservasDto.addAll(reservaControllerService.obtenerReserva());
    }

//    private void listenerSelection() {
//        tableReservas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            reservaSeleccionado = newSelection;
//            mostrarInformacionEvento(reservaSeleccionado);
//        });
//    }

    private void mostrarInformacionReserva(ReservaDto reservaSeleccionado) {
        if(reservaSeleccionado != null){
            txtId.setText(reservaSeleccionado.id());
            txtUsuario.setText(String.valueOf(reservaSeleccionado.usuario()));
            txtEvento.setText(String.valueOf(reservaSeleccionado.evento()));
            txtFechaSolicitud.setText(String.valueOf(reservaSeleccionado.fechaSolicitud()));
            txtEstado.setText(reservaSeleccionado.estado());
        }
    }

    @FXML
    void nuevoReservaAction(ActionEvent event) {
        txtId.setText("Ingrese el ID");
        txtUsuario.setText("Ingrese el usuario");
        txtEvento.setText("Ingrese el nombre del evento");
        txtFechaSolicitud.setText("Ingrese la fecha de la solicitud");
        txtEstado.setText("Ingrese el estado");
    }

    @FXML
//    void agregarReservaAction(ActionEvent event) {
//        crearReserva();
//    }
//
//    @FXML
//    void eliminarReservaAction(ActionEvent event) {
//        eliminarReserva();
//    }
//
//
//    @FXML
//    void actualizarReservaAction(ActionEvent event) {
//        actualizarReserva();
//    }
//
//    private void crearEvento() {
//        //1. Capturar los datos
//        ReservaDto reservaDto = construirEventoDto();
//        //2. Validar la información
//        if(datosValidos(reservaDto)){
//            if(reservaControllerService.agregarEvento(reservaDto)){
//                listaReservaDto.add(reservaDto);
//                mostrarMensaje("Notificación reserva", "Reserva creada", "El reserva se ha creado con éxito", Alert.AlertType.INFORMATION);
//                limpiarCamposReserva();
//            }else{
//                mostrarMensaje("Notificación reserva", "Reserva no creado", "El reserva no se ha creado con éxito", Alert.AlertType.ERROR);
//            }
//        }else{
//            mostrarMensaje("Notificación reserva", "Reserva no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
//        }
//
//    }

    private void eliminarReserva() {
        boolean eventoReserva = false;
        if(reservaSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de elmininar la reserva?")){
                boolean reservaEliminado = reservaControllerService.eliminarReserva(reservaSeleccionado.id());
                if(reservaEliminado == true){
                    listaReservasDto.remove(reservaSeleccionado);
                    reservaSeleccionado = null;
                    tableReservas.getSelectionModel().clearSelection();
                    limpiarCamposReserva();
                    mostrarMensaje("Notificación reserva", "Reserva eliminado", "El reserva se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación reserva", "Reserva no eliminado", "El reserva no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación reserva", "Reserva no seleccionado", "Seleccionado un reserva de la lista", Alert.AlertType.WARNING);
        }
    }

//    private void actualizarReserva() {
//        boolean clienteActualizado = false;
//        //1. Capturar los datos
//        String idActual = reservaSeleccionado.id();
//        ReservaDto reservaDto = construirReservaDto();
//        //2. verificar el empleado seleccionado
//        if(reservaSeleccionado != null){
//            //3. Validar la información
//            if(datosValidos(reservaSeleccionado)){
//                clienteActualizado = reservaControllerService.actualizarReserva(idActual,reservaDto);
//                if(clienteActualizado){
//                    listaReservasDto.remove(reservaSeleccionado);
//                    listaReservasDto.add(reservaDto);
//                    tableReservas.refresh();
//                    mostrarMensaje("Notificación reserva", "Reserva actualizado", "El reserva se ha actualizado con éxito", Alert.AlertType.INFORMATION);
//                    limpiarCamposReserva();
//                }else{
//                    mostrarMensaje("Notificación reserva", "Reserva no actualizado", "El reserva no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
//                }
//            }else{
//                mostrarMensaje("Notificación reserva", "Reserva no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
//            }
//
//        }
//    }

//    private ReservaDto construirReservaDto() {
//        return new ReservaDto(
//                txtId.getText(),
//                txtUsuario.getText(),
//                "",
//                txtEvento.getText(),
//                txtFechaSolicitud.getText(),
//                txtEstado.getText()
//        );
//    }

    private void limpiarCamposReserva() {
        txtId.setText("");
        txtUsuario.setText("");
        txtEvento.setText("");
        txtFechaSolicitud.setText("");
        txtEstado.setText("");
    }

    private boolean datosValidos(ReservaDto reservaDto) {
        String mensaje = "";
        if(reservaDto.usuario() == null || reservaDto.usuario().equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(reservaDto.id() == null || reservaDto.id() .equals(""))
            mensaje += "El ID es invalido \n" ;
        if(reservaDto.fechaSolicitud() == null || reservaDto.fechaSolicitud().equals(""))
            mensaje += "El correo es invalido \n" ;
        if(reservaDto.estado() == null || reservaDto.estado() .equals(""))
            mensaje += "Necesita ingresar una capacidad maxima \n" ;
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

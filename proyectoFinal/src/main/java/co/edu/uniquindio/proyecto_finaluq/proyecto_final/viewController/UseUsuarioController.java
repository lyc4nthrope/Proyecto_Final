package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.SGREApplication;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.EventoController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.ReservaController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EventoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils.SGREUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class UseUsuarioController implements Initializable {
    ReservaController reservaController;
    UsuarioDto sesionUsuario;
     ReservaDto reservaSeleccionada;
    List<ReservaDto> reservasAsignadas;
    ObservableList <ReservaDto>reservasDto;

    public  void setSesionUsuario(UsuarioDto usuarioDto){this.sesionUsuario=usuarioDto;}

    @FXML
    private ComboBox<?> cmboxEvento;

    @FXML
    private Label correoUsuario;

    @FXML
    private Label idUsuario;

    @FXML
    private Label nombreUsuario;

    @FXML
    private TableView<ReservaDto> tbReservas;

    @FXML
    private TableColumn<ReservaDto, String> tcEspacios;

    @FXML
    private TableColumn<ReservaDto, String> tcEstado;

    @FXML
    private TableColumn<ReservaDto, String> tcEvento;

    @FXML
    private TableColumn<ReservaDto, String> tcFecha;

    @FXML
    private TextField txtEspacios;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDatosUsuario();
        reservaController = new ReservaController();
        reservasAsignadas = reservaController.getReservasUsuario(sesionUsuario.id());
        setComboBox();
        tbReservas.getItems().clear();
        setTablaReserva();
    }

    private void setDatosUsuario(){
        this.sesionUsuario=SGREUtils.getUsuarioEnSesion();
        nombreUsuario.setText(sesionUsuario.nombre());
        idUsuario.setText(sesionUsuario.id());
        correoUsuario.setText(sesionUsuario.correo());
    }

    private void setComboBox(){
        EventoController eventoController = new EventoController();
        ArrayList<EventoDto> eventosDto =eventoController.obtenerEventos();
        ArrayList<String> nombreEventosAux = nombresEventos(eventosDto,new ArrayList<>(),0);
        ObservableList nombreEventos = FXCollections.observableArrayList(nombreEventosAux);
        cmboxEvento.setItems(nombreEventos);
    }

    private ArrayList<String> nombresEventos(ArrayList<EventoDto> eventosDto,ArrayList<String> nombreEventos,int i){
        if (i>=eventosDto.size()){
            return nombreEventos;
        }else {
            nombreEventos.add(eventosDto.get(i).nombreEvento()+" - "+eventosDto.get(i).id());
        }
        return nombresEventos(eventosDto,nombreEventos,i+1);
    }
    private void setTablaReserva(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        reservasDto = FXCollections.observableArrayList(reservasAsignadas);
        tcEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().evento().nombreEvento()));
        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaSolicitud().format(formatter)));
        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estado()));
        tcEspacios.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().espaciosSolicitados())));
        tbReservas.setItems(reservasDto);
    }

    private List getList(List lista){
        return (lista==null || lista.size() == 0) ? new ArrayList<>() : new ArrayList<>(lista);
    }
//    private void listenerSelection() {
//
//        tbReservas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            reservaSeleccionada = newSelection;
//            mostrarInformacionReserva(reservaSeleccionada);
//        });
//    }


    @FXML
    public void seleccionar(javafx.scene.input.MouseEvent mouseEvent) {
        reservaSeleccionada= this.tbReservas.getSelectionModel().getSelectedItem();
        if(reservaController!=null){
            this.txtEspacios.setText(Integer.toString(reservaSeleccionada.espaciosSolicitados()));
        }
    }

    private void mostrarInformacionReserva(ReservaDto reservaDto){
        if (reservaDto!=null){
            txtEspacios.setText(String.valueOf(reservaDto.espaciosSolicitados()));
        }

    }

    @FXML
    private void aniadirReserva(){
        try {
            int espaciosSoli = Integer.parseInt(txtEspacios.getText());
            EventoDto eventoSoli = obtenerEvento();
            if (!reservaRegistrada(eventoSoli,false,0)){
                String id = String.valueOf(crearId());
                ReservaDto reservaDto = new ReservaDto(id,sesionUsuario,eventoSoli,LocalDateTime.now(),"PENDIENTE",espaciosSoli);
                if (reservaController.agregarReserva(reservaDto)) {
                    reservasAsignadas.add(reservaDto);
                    reservasDto.add(reservaDto);
                    limpiarCampos();
                }
            }else {
                SGREApplication.mostrarMensaje("error","no se solicito reserva","la reserva a este evento ya existe", Alert.AlertType.ERROR);
            }
        }catch (Exception e){
            SGREApplication.mostrarMensaje("error","error", "datos de reserva", Alert.AlertType.ERROR);
        }

    }

    @FXML
    public void borrarReserva(){
        boolean reservaEliminada = false;
        if(reservaSeleccionada != null){
            if(SGREApplication.mostrarMensajeConfirmacion("¿Estas seguro de Cancelar la Reserva?")){
                reservaEliminada = reservaController.eliminarReserva(reservaSeleccionada.id());
                if(reservaEliminada == true){
                    reservasDto.remove(reservaSeleccionada);
                    reservaSeleccionada = null;
                    tbReservas.getSelectionModel().clearSelection();
                    limpiarCampos();
                    SGREApplication.mostrarMensaje("Notificación Reserva", "Reserva eliminado", "El Reserva se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                }else{
                    SGREApplication.mostrarMensaje("Notificación Reserva", "Reserva no eliminado", "El Reserva no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            SGREApplication.mostrarMensaje("Notificación Reserva", "Reserva no seleccionado", "Seleccionado un Reserva de la lista", Alert.AlertType.WARNING);
        }
    }

    private int crearId(){
        int id =(int) (Math.random()*999999);
        if (reservaController.existeReserva(String.valueOf(id))){
            return crearId();
        }
        return id;
    }

    private EventoDto obtenerEvento(){
        int indiceEvento = cmboxEvento.getSelectionModel().getSelectedIndex();
        EventoController eventoController = new EventoController();
        ArrayList<EventoDto> eventosDto =eventoController.obtenerEventos();
        return eventosDto.get(indiceEvento);
    }
    private boolean reservaRegistrada(EventoDto eventoDto, boolean registrada, int i){
        if (registrada || i>=reservasAsignadas.size()){
            return registrada;
        }else {
            if (reservasAsignadas.get(i).evento().equals(eventoDto)){
                registrada=true;
            }
        }
        return reservaRegistrada(eventoDto,registrada,i+1);
    }

    public void limpiarCampos(){
        cmboxEvento.setValue(null);
        txtEspacios.setText("");
    }

    @FXML
    private void modificarReserva(){
        if (reservaSeleccionada!=null){
            try {
                int espaciosAux = Integer.parseInt(txtEspacios.getText());
                ReservaDto reservaAux= new ReservaDto(reservaSeleccionada.id(), reservaSeleccionada.usuario(), reservaSeleccionada.evento(),LocalDateTime.now(),"PENDIENTE", espaciosAux);
                if (reservaController.actualizarReserva(reservaSeleccionada.id(),reservaAux)){
                    reservasDto.remove(reservaSeleccionada);
                    reservasDto.add(reservaAux);
                    tbReservas.refresh();
                    reservaSeleccionada=null;
                    SGREApplication.mostrarMensaje("Notificación Reserva", "Reserva actualizada", "El Reserva se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCampos();
                }else {
                    SGREApplication.mostrarMensaje("Notificación Reserva", "Reserva no actualizada", "El Reserva no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                }
            }catch (Exception e){
                SGREApplication.mostrarMensaje("error","error con datos", "datos mal ingresado", Alert.AlertType.WARNING);
            }
        }else {
            SGREApplication.mostrarMensaje("error","reserva no seleccionada","seleccione una reserva nuevamnente", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cierreSesion() throws IOException {
        SGREUtils.setUsuarioEnSesion(null);
        SGREApplication.changeScene("Inicio.fxml",null,null);
    }

    @FXML
    private void modificarDatos() throws IOException {
        String contraseniaConfir = JOptionPane.showInputDialog("Confirme que es usted ingresando su contrasenia por favor");
       if(contraseniaConfir.equals(sesionUsuario.contrasenia())){
           SGREApplication.changeScene("UsuarioModificarView.fxml",null,null);
       }else {
           SGREApplication.mostrarMensaje("Error de confirmacion", "La contrasenia es incorrecta","Por favor verifique que esta ingresando correctamente la contrasenia", Alert.AlertType.ERROR);
       }

    }


}

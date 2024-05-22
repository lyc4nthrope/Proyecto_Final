package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.SGREApplication;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.EventoController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.ReservaController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.UsuarioController;
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

public class UsuarioSesionModificarView implements Initializable {

    ReservaController reservaController;
    UsuarioDto sesionUsuario;
    ReservaDto reservaSeleccionada;
    List<ReservaDto> reservasAsignadas;

    UsuarioController usuarioController = new UsuarioController();
    ObservableList<ReservaDto> reservasDtoMod;
    @FXML
    private ComboBox<String> cmboxEvento;

    @FXML
    private TextField txtCedulaUsuario;

    @FXML
    private TextField txtContnraseniaUsuario;

    @FXML
    private TextField txtContraConfirmarUsuario;

    @FXML
    private TextField txtCorreoUsuario;

    @FXML
    private TextField txtEspacios;

    @FXML
    private TextField txtNombreUsuario;
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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDatosUsuario();
        reservaController = new ReservaController();
        reservasAsignadas = reservaController.getReservasUsuario(sesionUsuario.id());
        setComboBox();
        tbReservas.getItems().clear();
        setTablaReserva();
        listenerSelection();
    }

    private void setDatosUsuario(){
        this.sesionUsuario=SGREUtils.getUsuarioEnSesion();
        txtNombreUsuario.setText(sesionUsuario.nombre());
        txtCedulaUsuario.setText(sesionUsuario.id());
        txtCorreoUsuario.setText(sesionUsuario.correo());
        txtContnraseniaUsuario.setText(sesionUsuario.contrasenia());
        txtContraConfirmarUsuario.setText(sesionUsuario.contrasenia());
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
        reservasDtoMod = FXCollections.observableArrayList(getList(reservasAsignadas));
        tcEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().evento().nombreEvento()));
        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaSolicitud().format(formatter)));
        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estado()));
        tcEspacios.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().espaciosSolicitados())));
        tbReservas.setItems(reservasDtoMod);
    }

    private List getList(List lista){
        return (lista==null || lista.size() == 0) ? new ArrayList<>() : new ArrayList<>(lista);
    }
    private void listenerSelection() {
        tbReservas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            reservaSeleccionada = newSelection;
            mostrarInformacionReserva(reservaSeleccionada);
        });
    }

    private void mostrarInformacionReserva(ReservaDto reservaDto){
        if (reservaDto!=null){
            txtEspacios.setText(String.valueOf(reservaDto.espaciosSolicitados()));
        }

    }

    private void aniadirReserva(){
        int espaciosSoli = Integer.parseInt(txtEspacios.getText());
        EventoDto eventoSoli = obtenerEvento();
        if (!reservaRegistrada(eventoSoli,false,0)){
            String id = String.valueOf(crearId());
            ReservaDto reservaDto = new ReservaDto(id,sesionUsuario,eventoSoli,LocalDateTime.now(),"PENDIENTE",espaciosSoli);
            if (reservaController.agregarReserva(reservaDto)) {
                reservasAsignadas.add(reservaDto);
                reservasDtoMod.add(reservaDto);
                limpiarCampos();
            }
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

    private void modificarReserva(){
        if (reservaSeleccionada!=null){
            int espaciosAux = Integer.parseInt(txtEspacios.getText());
        }
    }

    @FXML
    private void cancelar() throws IOException {
        SGREApplication.changeScene("UsuarioUseView.fxml",null,null);
    }
    @FXML
    private void guardarModificar() throws IOException {
        if(datosValidos() && txtContnraseniaUsuario.getText().equals(txtContraConfirmarUsuario.getText())){
            if(usuarioController.actualizarUsuario(SGREUtils.getUsuarioEnSesion().id(), obtenerUsuario())){
                SGREUtils.setUsuarioEnSesion(obtenerUsuario());
                SGREApplication.changeScene("UsuarioUseView.fxml", SGREUtils.getUsuarioEnSesion(),null);
                SGREApplication.mostrarMensaje("Modificacion Completa","La modificacion fue hecha con exito","Sus datos fueron modificados correctamente", Alert.AlertType.INFORMATION);
            }else {
                SGREApplication.mostrarMensaje("Error en la Modificacion","Datos ingresados erroneos","Algun dato ya esta registrado verifique ingreso la modificacion de forma correcta", Alert.AlertType.ERROR);
            }

        }else {
            SGREApplication.mostrarMensaje("Error en la Modificacion","Algun dato esta erroneo","Por favor verifica que las contrasenias coincidan\nSino verifique que todos los campos esten relleno", Alert.AlertType.ERROR);
        }

    }

    private UsuarioDto obtenerUsuario(){
        String nombreUsuario = txtNombreUsuario.getText();
        String cedulaUsuario = txtCedulaUsuario.getText();
        String correoUsuario = txtCorreoUsuario.getText();
        String contraseñaUsuario = txtContnraseniaUsuario.getText();
        return new UsuarioDto(cedulaUsuario,nombreUsuario,correoUsuario,contraseñaUsuario);
    }

    private boolean datosValidos(){
        boolean valido = true;
        if (txtNombreUsuario.getText().isBlank() || txtCedulaUsuario.getText().isBlank()
            || txtCorreoUsuario.getText().isBlank() || txtContnraseniaUsuario.getText().isBlank()
            || txtContraConfirmarUsuario.getText().isBlank()){
            valido=false;
        }
        return valido;
    }
}

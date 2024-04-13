package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.SGREApplication;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.EventoController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.ReservaController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EventoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import javafx.event.ActionEvent;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.mappers.SGREMapper;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils.SGREUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UseUsuarioViewController implements Initializable {
    SGREApplication principal = new SGREApplication();


    @FXML
    AnchorPane panelUsuarioUse;
    ReservaController reservaController;
    UsuarioDto sesionUsuario;
     ReservaDto reservaSeleccionada;

    ObservableList<ReservaDto> reservasDto;
    public  void setSesionUsuario(UsuarioDto usuarioDto){this.sesionUsuario=usuarioDto;}

    @FXML
    private Button btnAniadirReserva;

    @FXML
    private Button btnCancelarReserva;

    @FXML
    private Button btnModificarDatos;

    @FXML
    private Button btnModificarEspacios;

    @FXML
    private Button btnSalir;

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
    private TableColumn<ReservaDto, Integer> tcEspacios;

    @FXML
    private TableColumn<ReservaDto, String> tcEstado;

    @FXML
    private TableColumn<ReservaDto, String> tcEvento;

    @FXML
    private TableColumn<ReservaDto, LocalDateTime> tcFecha;

    @FXML
    private TextField txtEspacios;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservaController = new ReservaController();
        setDatosUsuario();
        setComboBox();
        tbReservas.getItems().clear();
        setTablaReserva();
        listenerSelection();
    }

    private void setDatosUsuario(){
        SGREMapper mapper =SGREMapper.INSTANCE;
        this.sesionUsuario=mapper.usuarioToUsuarioDto(SGREUtils.getUsuarioEnSesion());
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
        reservasDto = FXCollections.observableArrayList(getList(sesionUsuario.reservasAsignados()));
        tcEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().evento().nombreEvento()));
        tcFecha.setCellValueFactory(new PropertyValueFactory<ReservaDto, LocalDateTime>("fechaSolicitud"));
        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estado()));
        tcEspacios.setCellValueFactory(new PropertyValueFactory<ReservaDto, Integer>("espaciosSolicitados"));
        tbReservas.setItems(reservasDto);
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
                sesionUsuario.reservasAsignados().add(reservaDto);
                reservasDto.add(reservaDto);
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
        if (registrada || i>=sesionUsuario.reservasAsignados().size()){
            return registrada;
        }else {
            if (sesionUsuario.reservasAsignados().get(i).evento().equals(eventoDto)){
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
    public void onButtonModificarAction(ActionEvent event) throws IOException {
        principal.changeScene("UsuarioModificarView.fxml", null, null);
    }
}

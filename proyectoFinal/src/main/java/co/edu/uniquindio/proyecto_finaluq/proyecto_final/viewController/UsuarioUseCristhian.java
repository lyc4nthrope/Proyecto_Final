package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UsuarioUseCristhian {

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
    private TableView<?> tbReservas;

    @FXML
    private TableColumn<?, ?> tcEspacios;

    @FXML
    private TableColumn<?, ?> tcEstado;

    @FXML
    private TableColumn<?, ?> tcEvento;

    @FXML
    private TableColumn<?, ?> tcFecha;

    @FXML
    private TextField txtEspacios;

}

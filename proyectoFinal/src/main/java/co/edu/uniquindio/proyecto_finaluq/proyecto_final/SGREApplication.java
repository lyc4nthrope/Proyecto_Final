package co.edu.uniquindio.proyecto_finaluq.proyecto_final;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils.SGREUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;


public class SGREApplication extends Application{
    private static Stage scene;


    @Override
    public void start(Stage primera) throws IOException {
        scene = primera;
        Parent root = FXMLLoader.load(SGREApplication.class.getResource("Inicio.fxml"));
        primera.setScene( new Scene(root, 750,500));
        primera.show();
    }

    public static void changeScene(String url, UsuarioDto usuarioDto, EmpleadoDto empleadoDto) throws IOException {
        URL urlRedirect = SGREApplication.class.getResource(url);
        FXMLLoader loader = new FXMLLoader(urlRedirect);

        if (usuarioDto!=null){
            SGREUtils.setUsuarioEnSesion(usuarioDto);
        }else if (empleadoDto!=null){

            }else{



        }

        Parent root = loader.load();
        scene.getScene().setRoot(root);
    }

    public  static void  mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
    public static boolean mostrarMensajeConfirmacion(String mensaje) {
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
    public static void main(String[] args) {
        launch();
    }
}

package co.edu.uniquindio.proyecto_finaluq.proyecto_final;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.ModelFactoryController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.mappers.SGREMapper;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Usuario;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils.SGREUtils;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController.UseUsuarioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.mapstruct.Mapper;

import java.io.IOException;
import java.net.URL;


public class SGREApplication extends Application{
    private static Stage scene;


    UseUsuarioController controllerUsuarioMap = new UseUsuarioController();

    @Override
    public void start(Stage primera) throws IOException {
        scene = primera;
        Parent root = FXMLLoader.load(SGREApplication.class.getResource("Inicio.fxml"));
        primera.setScene( new Scene(root, 750,500));
        primera.show();
    }

    public void changeScene(String url, UsuarioDto usuarioDto, EmpleadoDto empleadoDto) throws IOException {
        URL urlRedirect = SGREApplication.class.getResource(url);
        FXMLLoader loader = new FXMLLoader(urlRedirect);

        if (usuarioDto!=null){
            SGREMapper mapper =SGREMapper.INSTANCE;
            Usuario usuarioSesion =mapper.usuarioDtoToUsuario(usuarioDto);
            SGREUtils.setUsuarioEnSesion(usuarioSesion);
        }else if (empleadoDto!=null){

            }else{



        }

        Parent root = loader.load();
        scene.getScene().setRoot(root);
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
    public static void main(String[] args) {
        launch();
    }
}

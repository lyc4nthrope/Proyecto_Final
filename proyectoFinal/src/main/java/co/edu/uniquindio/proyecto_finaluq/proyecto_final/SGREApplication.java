package co.edu.uniquindio.proyecto_finaluq.proyecto_final;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.InicioController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController.UseUsuarioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SGREApplication extends Application{
    private static Stage scene;

    @Override
    public void start(Stage primera) throws IOException {
        scene = primera;
        primera.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        primera.setScene( new Scene(root, 445,219));
        primera.show();
    }

    public void changeScene(String url, UsuarioDto usuarioDto, EmpleadoDto empleadoDto) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        if (usuarioDto != null) {
            UseUsuarioController useUsuarioController = loader.getController();
            useUsuarioController.setSesionUsuario(usuarioDto);
        }//else{
        scene.getScene().setRoot(root);
        Stage stage= (Stage) scene.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public static void main(String[] args) {
        launch();
    }

}

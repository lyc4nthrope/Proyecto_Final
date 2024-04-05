package co.edu.uniquindio.proyecto_finaluq.proyecto_final;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.InicioController;
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

    public void changeScene(String url) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(url));
        scene.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }

}

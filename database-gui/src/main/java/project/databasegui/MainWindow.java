package project.databasegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application
{
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        scene = new Scene(loadFXML("main-window"), 1280, 720);

        stage.setTitle("Aplikacija za evidentiranje profesionalnih CS2 meceva");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String name) throws IOException
    {
        scene.setRoot(loadFXML(name));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
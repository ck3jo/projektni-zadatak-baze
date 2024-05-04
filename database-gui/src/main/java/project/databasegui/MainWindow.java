package project.databasegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Objects;

public class MainWindow extends Application
{
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        scene = new Scene(loadFXML("main-window"), 1280, 720);
        
        stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("icons/512x512.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("icons/256x256.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("icons/128x128.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("icons/64x64.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("icons/32x32.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("icons/16x16.png"))));

        stage.setTitle("FakeHLTV.org");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNIFIED);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String name) throws IOException { scene.setRoot(loadFXML(name)); }

    private static Parent loadFXML(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) { launch(); }
}
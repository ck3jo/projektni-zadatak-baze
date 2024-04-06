package project.databasegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class MainWindow extends Application
{
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        scene = new Scene(loadFXML("main-window"), 1280, 720);

        //512x512 icon
        stage.getIcons().add(new Image("https://cdn2.steamgriddb.com/icon/e1bd06c3f8089e7552aa0552cb387c92/32/512x512.png"));
        //256x256 icon
        stage.getIcons().add(new Image("https://cdn2.steamgriddb.com/icon/e1bd06c3f8089e7552aa0552cb387c92/32/256x256.png"));
        //128x128 icon
        stage.getIcons().add(new Image("https://cdn2.steamgriddb.com/icon/e1bd06c3f8089e7552aa0552cb387c92/32/128x128.png"));
        //64x64 icon
        stage.getIcons().add(new Image("https://cdn2.steamgriddb.com/icon/e1bd06c3f8089e7552aa0552cb387c92/8/64x64.png"));
        //32x32 icon
        stage.getIcons().add(new Image("https://cdn2.steamgriddb.com/icon/e1bd06c3f8089e7552aa0552cb387c92/8/32x32.png"));
        //16x16 icon
        stage.getIcons().add(new Image("https://cdn2.steamgriddb.com/icon/e1bd06c3f8089e7552aa0552cb387c92/8/16x16.png"));

        stage.setTitle("Aplikacija za evidentiranje profesionalnih CS2 meceva");
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
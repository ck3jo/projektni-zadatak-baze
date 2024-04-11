package project.databasegui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SettingsController
{
    public TextField inputDatabaseURL;
    public TextField inputDatabaseUsername;
    public TextField inputDatabasePassword;

    //fxml inheritances below

    @FXML
    public void returnToMain() throws IOException { MainWindow.setRoot("main-window"); }
}

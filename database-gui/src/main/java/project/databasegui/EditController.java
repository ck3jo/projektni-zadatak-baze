package project.databasegui;

import javafx.fxml.FXML;
import java.io.IOException;

public class EditController
{
    @FXML
    public void returnToMain() throws IOException { MainWindow.setRoot("main-window"); }
}

package project.databasegui;

import javafx.fxml.FXML;
import java.io.IOException;

public class MainController
{
    @FXML
    public void exitApp() throws IOException
    {
        System.exit(0);
    }

    @FXML
    public void goToAdd() throws IOException
    {
        MainWindow.setRoot("add-window");
    }

    @FXML
    public void goToEdit() throws IOException
    {
        MainWindow.setRoot("edit-window");
    }

    @FXML
    public void goToDelete() throws IOException
    {
        MainWindow.setRoot("delete-window");
    }
}
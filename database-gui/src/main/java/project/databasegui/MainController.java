package project.databasegui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MainController
{
    public boolean validateConfig() throws FileNotFoundException
    {
        Scanner readConfig = new Scanner(new File("app.config"));
        String url = readConfig.nextLine();
        String user = readConfig.nextLine();

        if (url.equals("url: ") || user.equals("user: "))
        {
            showErrorAlert("Postavite podešavanja prvo!");
            return false;
        }

        if(!url.substring(5).startsWith("jdbc:mysql//"))
        {
            showErrorAlert("Nepravilan protokol URL-a.");
            return false;
        }
        else return true;
    }

    private static void showErrorAlert(String s)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, s);
        alert.setHeaderText("Greška!");
        alert.showAndWait();
    }

    // fxml inheritances below

    @FXML
    public void exitApp() { System.exit(0); }

    @FXML
    public void goToAdd() throws IOException
    {
        boolean proceed = validateConfig();
        if (proceed) MainWindow.setRoot("add-window");
    }

    @FXML
    public void goToEdit() throws IOException
    {
        boolean proceed = validateConfig();
        if (proceed) MainWindow.setRoot("edit-window");
    }

    @FXML
    public void goToDelete() throws IOException
    {
        boolean proceed = validateConfig();
        if (proceed) MainWindow.setRoot("delete-window");
    }

    @FXML
    public void goToSettings() throws IOException
    {
        MainWindow.setRoot("settings-window");
    }
}
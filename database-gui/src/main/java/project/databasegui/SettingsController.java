package project.databasegui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class SettingsController
{
    public TextField inputDatabaseURL;
    public TextField inputDatabaseUsername;
    public TextField inputDatabasePassword;
    private final String fileName = "app.config";
    private final File config = new File(fileName);

    public void createConfig() throws IOException
    {
        try
        {
            if (!config.createNewFile())
            {
                if (config.isFile()) {}
                else throw new FileNotFoundException(fileName);
            }
        }
        catch (FileNotFoundException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nemoguće napraviti config fajl: " + e.getMessage());
            alert.setHeaderText("Greška!");
            alert.showAndWait();
        }
    }

    //fxml inheritances below

    @FXML
    public void writeConfig() throws IOException
    {
        if (!config.isFile())
        {
            createConfig();
            writeToFile();
        }
        else
        {
            writeToFile();
        }
    }

    private void writeToFile() throws IOException
    {
        try (FileWriter fw = new FileWriter(fileName))
        {
            fw.write("url: " + inputDatabaseURL.getText() + "\n");
            fw.write("user: " + inputDatabaseUsername.getText() + "\n");
            if (inputDatabasePassword.getText() == null || inputDatabasePassword.getText().isEmpty())
            {
                fw.write("pass: §" + "\n");
            }
            else fw.write("pass: " + inputDatabasePassword.getText() + "\n");
            fw.write("eof" + "\n");
            showSuccessAlert();
        }
    }

    private static void showSuccessAlert()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspešno zapisivanje podataka.");
        alert.setHeaderText("Uspeh!");
        alert.showAndWait();
    }

    @FXML
    public void returnToMain() throws IOException { MainWindow.setRoot("main-window"); }
}

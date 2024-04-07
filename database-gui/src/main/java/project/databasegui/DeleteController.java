package project.databasegui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.sql.*;

public class DeleteController
{
    @FXML
    public void returnToMain() throws IOException { MainWindow.setRoot("main-window"); }

    public void testSQLConnectivity() throws SQLException
    {
        String sqlQuery = "SELECT * FROM Mecevi";
        String url = "jdbc:mysql://localhost:3306/database-project";
        String username = "root";
        String password = "";

        Alert connAlert = new Alert(Alert.AlertType.INFORMATION, "Povezivanje ka bazi...", ButtonType.CLOSE);
        connAlert.setHeaderText("Povezivanje...");
        connAlert.show();

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            connAlert.close();
            Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION, "Povezivanje ka bazi uspešno!", ButtonType.OK);
            successAlert.setHeaderText("Uspešno povezivanje!");
            successAlert.showAndWait();
        }
    }
}

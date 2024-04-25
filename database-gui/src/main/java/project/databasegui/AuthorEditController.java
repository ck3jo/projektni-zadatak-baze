package project.databasegui;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AuthorEditController implements Initializable
{
    public String url = "jdbc:mysql://localhost:3306/database-project";
    public String user = "root";
    public String pass = "";

    public int rowToEdit;
    public String origName;
    public String origNick;
    public String origSurname;

    public TextField nameTextField;
    public TextField nickTextField;
    public TextField surnameTextField;

    public Button buttonConfirmEdit;

    public AuthorEditController(int rowToEdit, String name, String nick, String surname)
    {
        this.rowToEdit = rowToEdit;
        this.origName = name;
        this.origNick = nick;
        this.origSurname = surname;

        this.nameTextField = new TextField();
        this.nickTextField = new TextField();
        this.surnameTextField = new TextField();
        this.buttonConfirmEdit = new Button();
    }

    public void confirmEdit(int rowToEdit)
    {
        System.out.println(rowToEdit);
        String name = nameTextField.getText();
        String nick = nickTextField.getText();
        String surname = surnameTextField.getText();
        String sqlQuery = "UPDATE autori SET Ime = ?, Nadimak = ?, Prezime = ? WHERE IDAutora = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, name);
            ps.setString(2, nick);
            ps.setString(3, surname);
            ps.setInt(4, rowToEdit);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspesna izmena podataka!");
                alert.showAndWait();
            }
        }
        catch (SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Greska pri izmeni podataka.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.nameTextField.setText(origName);
        this.nickTextField.setText(origNick);
        this.surnameTextField.setText(origSurname);
    }
}
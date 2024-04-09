package project.databasegui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.*;

public class AddController
{
    //author inputs
    public TextField inputAuthorName;
    public TextField inputAuthorNick;
    public TextField inputAuthorSurname;

    //player inputs
    public TextField inputPlayerName;
    public TextField inputPlayerNick;
    public TextField inputPlayerSurname;
    public DatePicker inputPlayerBirthDate;
    public TextField inputPlayerTeamName;
    public TextField inputPlayerRating;
    public TextField inputPlayerMajorTrophies;
    public TextField inputPlayerMajorMVPs;

    //match inputs
    public TextField inputMatchFirstTeamName;
    public TextField inputMatchSecondTeamName;
    public TextField inputMatchTournamentName;
    public TextField inputMatchNumMaps;
    public TextField inputMatchScore;
    public DatePicker inputMatchDate;

    //team inputs
    public TextField inputTeamName;
    public TextField inputTeamLogo;
    public TextField inputTeamRanking;

    //transfer inputs
    public TextField inputTransferPlayerNick;
    public TextField inputTransferOldTeamName;
    public TextField inputTransferNewTeamName;
    public DatePicker inputTransferDate;

    //coach inputs
    public TextField inputCoachName;
    public TextField inputCoachNick;
    public TextField inputCoachSurname;
    public TextField inputCoachTeamName;

    //tournament inputs
    public TextField inputTournamentName;
    public DatePicker inputTournamentStartDate;
    public DatePicker inputTournamentEndDate;
    public TextField inputTournamentLocation;
    public TextField inputTournamentPrizePool;
    public CheckBox inputTournamentSizeYesNo;

    //news inputs
    public TextField inputNewsTitle;
    public DatePicker inputNewsDate;
    public TextField inputNewsAuthorName;

    String url = "jdbc:mysql://localhost:3306/database-project";
    String username = "root";
    String password = "";

    private void sanitizeInput(String s)
    {
        if (s.matches(".\\d.*"))
        {
            StringBuilder sanitizedName = new StringBuilder();
            for (int i = 0; i < s.length(); ++i)
            {
                char c = s.charAt(i);
                if (!Character.isDigit(c)) sanitizedName.append(c);
            }
            s = sanitizedName.toString();
        }
    }

    // fxml inheritances below

    @FXML
    public void returnToMain() throws IOException { MainWindow.setRoot("main-window"); }

    @FXML
    public void sendAuthorDataToDatabase() throws IOException, SQLException
    {
        String authorName = inputAuthorName.getText();
        String authorNick = inputAuthorNick.getText();
        String authorSurname = inputAuthorSurname.getText();

        sanitizeInput(authorName);
        sanitizeInput(authorSurname);

        String sqlQuery = "INSERT INTO autori(Ime, Nadimak, Prezime) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, authorName);
            ps.setString(2, authorNick);
            ps.setString(3, authorSurname);
            ResultSet rs = ps.executeQuery();
        }
    }

    @FXML
    public void sendPlayerDataToDatabase() throws IOException, SQLException
    {
        String sqlQuery = "INSERT INTO autori(Ime, Nadimak, Prezime) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            //ps.setString();
        }
    }

    @FXML
    public void sendMatchDataToDatabase() throws IOException, SQLException
    {
        String sqlQuery = "INSERT INTO autori(Ime, Nadimak, Prezime) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            //ps.setString();
        }
    }

    @FXML
    public void sendTeamDataToDatabase() throws IOException, SQLException
    {
        String sqlQuery = "INSERT INTO autori(Ime, Nadimak, Prezime) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            //ps.setString();
        }
    }

    @FXML
    public void sendTransferDataToDatabase() throws IOException, SQLException
    {
        String sqlQuery = "INSERT INTO autori(Ime, Nadimak, Prezime) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            //ps.setString();
        }
    }

    @FXML
    public void sendTournamentDataToDatabase() throws IOException, SQLException
    {
        String sqlQuery = "INSERT INTO autori(Ime, Nadimak, Prezime) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            //ps.setString();
        }
    }

    @FXML
    public void sendCoachDataToDatabase() throws IOException, SQLException
    {
        String sqlQuery = "INSERT INTO autori(Ime, Nadimak, Prezime) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            //ps.setString();
        }
    }

    @FXML
    public void sendNewsDataToDatabase() throws IOException, SQLException
    {
        String sqlQuery = "INSERT INTO autori(Ime, Nadimak, Prezime) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            //ps.setString();
        }
    }
}

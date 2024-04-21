package project.databasegui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class AddController
{
    public Scanner readConfig = new Scanner("config.txt");

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
    public TextField inputTeamRanking;
    public TextField inputTeamMajorTrophies;
    public ChoiceBox<String> inputTeamRegion;

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

    private String url;
    private String user;
    private String pass;

    private static void showSuccessAlert()
    {
        Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION, "Uspešno ubacivanje podataka!", ButtonType.OK);
        successAlert.setHeaderText("Uspeh!");
        successAlert.showAndWait();
    }

    private String sanitizeInputForNumbers(String s)
    {
        String tmpString = s;

        if (s.length() == 45) tmpString = s.substring(0, 44);

        if (tmpString.matches("\\d*."))
        {
            StringBuilder newString = new StringBuilder();

            for (int i = 0; i < newString.length(); ++i)
            {
                char c = tmpString.charAt(i);
                if (!Character.isDigit(c)) newString.append(c);
            }

            tmpString = newString.toString();
        }

        return tmpString;
    }

    private int getTeamIDFromName(String teamName) throws SQLException
    {
        int teamID;
        String sqlQuery = "SELECT IDTima FROM timovi WHERE ImeTima = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, teamName);
            ResultSet rs = ps.executeQuery();
            teamID = rs.getInt("IDTima");
        }

        return teamID;
    }

    private int getTournamentIDFromName(String tournamentName) throws SQLException
    {
        int tournamentID;
        String sqlQuery = "SELECT IDTurnira FROM turniri WHERE Ime = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, tournamentName);
            ResultSet rs = ps.executeQuery();
            tournamentID = rs.getInt("IDTurnira");
        }

        return tournamentID;
    }

    private int getPlayerIDFromNick(String playerNick) throws SQLException
    {
        int playerID;
        String sqlQuery = "SELECT IDIgraca FROM igraci WHERE Nadimak = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, playerNick);
            ResultSet rs = ps.executeQuery();
            playerID = rs.getInt("IDIgraca");
        }

        return playerID;
    }

    private int getAuthorIDFromNick(String authorNick) throws SQLException
    {
        int authorID;
        String sqlQuery = "SELECT IDAutora from autori WHERE Nadimak = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, authorNick);
            ResultSet rs = ps.executeQuery();
            authorID = rs.getInt("IDAutora");
        }

        return authorID;
    }

    private void clearAuthorForm()
    {
        inputAuthorName.setText("");
        inputAuthorNick.setText("");
        inputAuthorSurname.setText("");
    }

    private void clearPlayerForm()
    {
        inputPlayerName.setText("");
        inputPlayerNick.setText("");
        inputPlayerSurname.setText("");
        inputPlayerBirthDate.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        inputPlayerTeamName.setText("");
        inputPlayerRating.setText("");
        inputPlayerMajorTrophies.setText("");
        inputPlayerMajorMVPs.setText("");
    }

    private void clearMatchForm()
    {
        inputMatchFirstTeamName.setText("");
        inputMatchSecondTeamName.setText("");
        inputMatchTournamentName.setText("");
        inputMatchNumMaps.setText("");
        inputMatchScore.setText("");
        inputMatchDate.setValue(new Date(System.currentTimeMillis()).toLocalDate());
    }

    private void clearTeamForm()
    {
        inputTeamName.setText("");
        inputTeamRanking.setText("");
        inputTeamMajorTrophies.setText("");
        inputTeamRegion.setValue("");
    }

    private void clearTransferForm()
    {
        inputTransferPlayerNick.setText("");
        inputTransferOldTeamName.setText("");
        inputTransferNewTeamName.setText("");
        inputTransferDate.setValue(new Date(System.currentTimeMillis()).toLocalDate());
    }

    private void clearCoachForm()
    {
        inputCoachName.setText("");
        inputCoachNick.setText("");
        inputCoachSurname.setText("");
        inputCoachTeamName.setText("");
    }

    private void clearTournamentForm()
    {
        inputTournamentName.setText("");
        inputTournamentStartDate.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        inputTournamentEndDate.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        inputTournamentLocation.setText("");
        inputTournamentPrizePool.setText("");
        inputTournamentSizeYesNo.setSelected(false);
    }

    private void clearNewsForm()
    {
        inputNewsTitle.setText("");
        inputNewsDate.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        inputNewsAuthorName.setText("");
    }

    // fxml inheritances below

    @FXML
    public void returnToMain() throws IOException { MainWindow.setRoot("main-window"); }

    @FXML
    public void sendAuthorDataToDatabase() throws SQLException
    {
        String authorName = sanitizeInputForNumbers(inputAuthorName.getText());
        String authorNick = inputAuthorNick.getText();
        String authorSurname = sanitizeInputForNumbers(inputAuthorSurname.getText());

        String sqlQuery = "INSERT INTO autori(Ime, Nadimak, Prezime) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setString(1, authorName);
            ps.setString(2, authorNick);
            ps.setString(3, authorSurname);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                clearAuthorForm();
                showSuccessAlert();
            }
        }
    }

    @FXML
    public void sendPlayerDataToDatabase() throws SQLException
    {
        String playerName = sanitizeInputForNumbers(inputPlayerName.getText());
        String playerNick = inputPlayerNick.getText();
        String playerSurname = sanitizeInputForNumbers(inputPlayerSurname.getText());
        LocalDate playerBirthDate = Date.valueOf(inputPlayerBirthDate.getValue()).toLocalDate();
        int playerTeamID = getTeamIDFromName(inputPlayerTeamName.getText());
        double playerRating = Double.parseDouble(inputPlayerRating.getText());
        int playerMajorTrophies = Integer.parseInt(inputPlayerMajorTrophies.getText());
        int playerMajorMVPs = Integer.parseInt(inputPlayerMajorMVPs.getText());

        String sqlQuery = "INSERT INTO igraci(Ime, Nadimak, Prezime, DatumRodjenja, IDTima, Rejting, MajorTrofeji, MajorMVP) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setString(1, playerName);
            ps.setString(2, playerNick);
            ps.setString(3, playerSurname);
            ps.setDate(4, Date.valueOf(playerBirthDate));
            ps.setInt(5, playerTeamID);
            ps.setDouble(6, playerRating);
            ps.setInt(7, playerMajorTrophies);
            ps.setInt(8,playerMajorMVPs);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                clearPlayerForm();
                showSuccessAlert();
            }
        }
    }

    @FXML
    public void sendMatchDataToDatabase() throws SQLException
    {
        int matchFirstTeamID = getTeamIDFromName(inputMatchFirstTeamName.getText());
        int matchSecondTeamID = getTeamIDFromName(inputMatchSecondTeamName.getText());
        int matchTournamentID = getTournamentIDFromName(inputMatchTournamentName.getText());
        int matchNumberOfMaps = Integer.parseInt(inputMatchNumMaps.getText());
        String matchScore = inputMatchScore.getText();
        LocalDate matchDate = Date.valueOf(inputMatchDate.getValue()).toLocalDate();

        String sqlQuery = "INSERT INTO mecevi(PrviTim, DrugiTim, IDTurnira, BrojMapa, Rezultat, DatumMeca) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setInt(1, matchFirstTeamID);
            ps.setInt(2, matchSecondTeamID);
            ps.setInt(3, matchTournamentID);
            ps.setInt(4, matchNumberOfMaps);
            ps.setString(5, matchScore);
            ps.setDate(6, Date.valueOf(matchDate));

            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                clearMatchForm();
                showSuccessAlert();
            }
        }
    }

    @FXML
    public void sendTeamDataToDatabase() throws SQLException
    {
        String teamName = inputTeamName.getText();
        int teamRanking = Integer.parseInt(inputTeamRanking.getText());
        int teamMajorTrophies = Integer.parseInt(inputTeamMajorTrophies.getText());
        String teamRegion = inputTeamRegion.getValue();

        String sqlQuery = "INSERT INTO timovi(ImeTima, RangPozicija, MajorTrofeji, Region) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setString(1, teamName);
            ps.setInt(2, teamRanking);
            ps.setInt(3, teamMajorTrophies);
            ps.setString(4, teamRegion);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                clearTeamForm();
                showSuccessAlert();
            }
        }
    }

    @FXML
    public void sendTransferDataToDatabase() throws SQLException
    {
        int playerID = getPlayerIDFromNick(inputTransferPlayerNick.getText());
        int oldTeamID = getTeamIDFromName(inputTransferOldTeamName.getText());
        int newTeamID = getTeamIDFromName(inputTransferNewTeamName.getText());
        LocalDate transferDate = Date.valueOf(inputTransferDate.getValue()).toLocalDate();

        String sqlQuery = "INSERT INTO transferi(IDIgraca, IDStarogTima, IDNovogTima, DatumTransfera) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setInt(1, playerID);
            ps.setInt(2, oldTeamID);
            ps.setInt(3, newTeamID);
            ps.setDate(4, Date.valueOf(transferDate));

            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                clearTransferForm();
                showSuccessAlert();
            }
        }
    }

    @FXML
    public void sendTournamentDataToDatabase() throws SQLException
    {
        String tournamentName = inputTournamentName.getText();
        LocalDate startDate = Date.valueOf(inputTournamentStartDate.getValue()).toLocalDate();
        LocalDate endDate = Date.valueOf(inputTournamentEndDate.getValue()).toLocalDate();
        String tournamentLocation = inputTournamentLocation.getText();
        int tournamentPrizePool = Integer.parseInt(inputTournamentPrizePool.getText());
        int tournamentIsBig = inputTournamentSizeYesNo.isSelected() ? 1 : 0;

        String sqlQuery = "INSERT INTO turniri(ime, datumpocetka, datumzavrsetka, mestoigranja, nagradnifond, veciturnir) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setString(1, tournamentName);
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));
            ps.setString(4, tournamentLocation);
            ps.setInt(5, tournamentPrizePool);
            ps.setInt(6, tournamentIsBig);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                clearTournamentForm();
                showSuccessAlert();
            }
        }
    }

    @FXML
    public void sendCoachDataToDatabase() throws SQLException
    {
        String coachName = inputCoachName.getText();
        String coachNick = inputCoachNick.getText();
        String coachSurname = inputCoachSurname.getText();
        int coachTeamID = getTeamIDFromName(inputCoachTeamName.getText());

        String sqlQuery = "INSERT INTO treneri(Ime, Nadimak, Prezime, IDTima) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setString(1, coachName);
            ps.setString(2, coachNick);
            ps.setString(3, coachSurname);
            ps.setInt(4, coachTeamID);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                clearCoachForm();
                showSuccessAlert();
            }
        }
    }

    @FXML
    public void sendNewsDataToDatabase() throws SQLException
    {
        String newsTitle = inputNewsTitle.getText();
        LocalDate newsDate = Date.valueOf(inputNewsDate.getValue()).toLocalDate();
        int newsAuthorID = getAuthorIDFromNick(inputNewsAuthorName.getText());

        String sqlQuery = "INSERT INTO vesti(Naslov, DatumObjavljivanja, IDAutora) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setString(1, newsTitle);
            ps.setDate(2, Date.valueOf(newsDate));
            ps.setInt(3, newsAuthorID);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                clearNewsForm();
                showSuccessAlert();
            }
        }
    }
}

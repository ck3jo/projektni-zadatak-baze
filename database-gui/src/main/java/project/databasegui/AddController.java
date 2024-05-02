package project.databasegui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.synedra.validatorfx.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AddController implements Initializable
{
    public Scanner config = new Scanner(new File("app.config"));

    //author inputs
    public TextField inputAuthorName;
    public Validator inputAuthorNameValidator = new Validator();
    public TextField inputAuthorNick;
    public TextField inputAuthorSurname;
    public Validator inputAuthorSurnameValidator = new Validator();
    public Button buttonSendAuthorData;

    //player inputs
    public TextField inputPlayerName;
    public Validator inputPlayerNameValidator = new Validator();
    public TextField inputPlayerNick;
    public TextField inputPlayerSurname;
    public Validator inputPlayerSurnameValidator = new Validator();
    public DatePicker inputPlayerBirthDate;
    public TextField inputPlayerNationality;
    public Validator inputPlayerNationalityValidator = new Validator();
    public TextField inputPlayerTeamName;
    public TextField inputPlayerRating;
    public Validator inputPlayerRatingValidator = new Validator();
    public TextField inputPlayerMajorTrophies;
    public Validator inputPlayerMajorTrophiesValidator = new Validator();
    public TextField inputPlayerMajorMVPs;
    public Validator inputPlayerMajorMVPsValidator = new Validator();
    public Button buttonSendPlayerData;

    //match inputs
    public TextField inputMatchFirstTeamName;
    public TextField inputMatchSecondTeamName;
    public TextField inputMatchTournamentName;
    public TextField inputMatchNumMaps;
    public Validator inputMatchNumMapsValidator = new Validator();
    public TextField inputMatchScore;
    public Validator inputMatchScoreValidator = new Validator();
    public DatePicker inputMatchDate;
    public Button buttonSendMatchData;

    //team inputs
    public TextField inputTeamName;
    public TextField inputTeamRanking;
    public Validator inputTeamRankingValidator = new Validator();
    public TextField inputTeamMajorTrophies;
    public Validator inputTeamMajorTrophiesValidator = new Validator();
    public ChoiceBox<String> inputTeamRegion;
    public Button buttonSendTeamData;

    //transfer inputs
    public TextField inputTransferPlayerNick;
    public TextField inputTransferOldTeamName;
    public TextField inputTransferNewTeamName;
    public DatePicker inputTransferDate;
    public Button buttonSendTransferData;

    //coach inputs
    public TextField inputCoachName;
    public Validator inputCoachNameValidator = new Validator();
    public TextField inputCoachNick;
    public TextField inputCoachSurname;
    public Validator inputCoachSurnameValidator = new Validator();
    public TextField inputCoachTeamName;
    public Button buttonSendCoachData;

    //tournament inputs
    public TextField inputTournamentName;
    public DatePicker inputTournamentStartDate;
    public DatePicker inputTournamentEndDate;
    public TextField inputTournamentLocation;
    public Validator inputTournamentLocationValidator = new Validator();
    public TextField inputTournamentPrizePool;
    public Validator inputTournamentPrizePoolValidator = new Validator();
    public CheckBox inputTournamentSizeYesNo;
    public Button buttonSendTournamentData;

    //news inputs
    public TextField inputNewsTitle;
    public DatePicker inputNewsDate;
    public TextField inputNewsAuthorName;
    public Button buttonSendNewsData;

    private String url;
    private String user;
    private String pass;

    public AddController() throws FileNotFoundException {}

    public void readConfig()
    {
        if (config.hasNextLine())
        {
            url = config.nextLine().substring(5);
        }
        else url = "jdbc:mysql://localhost:3306/database-project";

        if (config.hasNextLine())
        {
            user = config.nextLine().substring(6);
        }
        else user = "root";

        if (config.hasNextLine())
        {
            String tmp = config.nextLine();
            if (tmp.equals("pass: §")) pass = "";
            else pass = tmp.substring(6);
        }
    }

    private static Double tryParseDouble(String str)
    {
        try { return Double.parseDouble(str); }
        catch (NumberFormatException e) { return null; }
    }

    private static Integer tryParseInt(String str)
    {
        try { return Integer.parseInt(str); }
        catch (NumberFormatException e) { return null; }
    }

    private static void showSuccessAlert()
    {
        Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION, "Uspešno ubacivanje podataka!", ButtonType.OK);
        successAlert.setHeaderText("Uspeh!");
        successAlert.showAndWait();
    }

    private static void showWrongInputAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Postoji greška u unetim podacima.");
        alert.setHeaderText("Greška!");
        alert.showAndWait();
    }

    private Integer getTeamIDFromName(String teamName) throws SQLException
    {
        Integer teamID = null;
        String sqlQuery = "SELECT IDTima FROM timovi WHERE ImeTima = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, teamName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { teamID = rs.getInt("IDTima"); }
        }

        return teamID;
    }

    private Integer getTournamentIDFromName(String tournamentName) throws SQLException
    {
        Integer tournamentID = null;
        String sqlQuery = "SELECT IDTurnira FROM turniri WHERE Ime = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, tournamentName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { tournamentID = rs.getInt("IDTurnira"); }
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
        if (!inputAuthorNameValidator.containsErrors() && !inputAuthorSurnameValidator.containsErrors())
        {
            String authorName = inputAuthorName.getText();
            String authorNick = inputAuthorNick.getText();
            String authorSurname = inputAuthorSurname.getText();

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
        else { showWrongInputAlert(); }
    }

    @FXML
    public void sendPlayerDataToDatabase() throws SQLException
    {
        if (!inputPlayerNameValidator.containsErrors() &&
            !inputPlayerSurnameValidator.containsErrors() &&
            !inputPlayerNationalityValidator.containsErrors() &&
            !inputPlayerRatingValidator.containsErrors() &&
            !inputPlayerMajorTrophiesValidator.containsErrors() &&
            !inputPlayerMajorMVPsValidator.containsErrors())
        {
            String playerName = inputPlayerName.getText();
            String playerNick = inputPlayerNick.getText();
            String playerSurname = inputPlayerSurname.getText();
            LocalDate playerBirthDate = Date.valueOf(inputPlayerBirthDate.getValue()).toLocalDate();
            String playerNationality = inputPlayerNationality.getText();
            int playerTeamID = getTeamIDFromName(inputPlayerTeamName.getText());
            double playerRating = Double.parseDouble(inputPlayerRating.getText());
            int playerMajorTrophies = Integer.parseInt(inputPlayerMajorTrophies.getText());
            int playerMajorMVPs = Integer.parseInt(inputPlayerMajorMVPs.getText());

            String sqlQuery = "INSERT INTO igraci(Ime, Nadimak, Prezime, DatumRodjenja, Nacionalnost, IDTima, Rejting, MajorTrofeji, MajorMVP) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(url, user, pass))
            {
                PreparedStatement ps = conn.prepareStatement(sqlQuery);

                ps.setString(1, playerName);
                ps.setString(2, playerNick);
                ps.setString(3, playerSurname);
                ps.setDate(4, Date.valueOf(playerBirthDate));
                ps.setString(5, playerNationality);
                ps.setInt(6, playerTeamID);
                ps.setDouble(7, playerRating);
                ps.setInt(8, playerMajorTrophies);
                ps.setInt(9, playerMajorMVPs);

                int changedRows = ps.executeUpdate();

                if (changedRows > 0)
                {
                    clearPlayerForm();
                    showSuccessAlert();
                }
            }
        }
        else { showWrongInputAlert(); }
    }

    @FXML
    public void sendMatchDataToDatabase() throws SQLException
    {
        if (!inputMatchNumMapsValidator.containsErrors() && !inputMatchScoreValidator.containsErrors())
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
        else { showWrongInputAlert(); }
    }

    @FXML
    public void sendTeamDataToDatabase() throws SQLException
    {
        if (!inputTeamRankingValidator.containsErrors() && !inputTeamMajorTrophiesValidator.containsErrors())
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
        else { showWrongInputAlert(); }
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
        if (!inputTournamentLocationValidator.containsErrors() && inputTournamentPrizePoolValidator.containsErrors())
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
        else { showWrongInputAlert(); }
    }

    @FXML
    public void sendCoachDataToDatabase() throws SQLException
    {
        if (!inputCoachNameValidator.containsErrors() && inputCoachSurnameValidator.containsErrors())
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
        else { showWrongInputAlert(); }
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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        readConfig();

        inputAuthorNameValidator.createCheck()
            .dependsOn("name", inputAuthorName.textProperty())
            .withMethod(c -> {
                String name = c.get("name");
                if (name.matches(".*\\d.*")) { c.error("Brojevi se ne smeju pojavljivati u imenima."); }
                if (name.length() > 45) { c.error("Predugačko ime."); }
            })
            .decorates(inputAuthorName)
            .immediate();

        inputAuthorSurnameValidator.createCheck()
            .dependsOn("surname", inputAuthorSurname.textProperty())
            .withMethod(c -> {
                String surname = c.get("surname");
                if (surname.matches(".*\\d.*")) { c.error("Brojevi se ne smeju pojavljivati u prezimenima."); }
                if (surname.length() > 45) { c.error("Predugačko prezime."); }
            })
            .decorates(inputAuthorSurname)
            .immediate();

        inputPlayerNameValidator.createCheck()
            .dependsOn("name", inputPlayerName.textProperty())
            .withMethod(c -> {
                String name = c.get("name");
                if (name.matches(".*\\d.*")) { c.error("Brojevi se ne smeju pojavljivati u imenima."); }
                if (name.length() > 45) { c.error("Predugačko ime"); }
            })
            .decorates(inputPlayerName)
            .immediate();

        inputPlayerSurnameValidator.createCheck()
            .dependsOn("surname", inputPlayerSurname.textProperty())
            .withMethod(c -> {
                String surname = c.get("surname");
                if (surname.matches(".*\\d.*")) { c.error("Brojevi se ne smeju pojavljivati u prezimenima."); }
                if (surname.length() > 45) { c.error("Predugačko prezime."); }
            })
            .decorates(inputPlayerSurname)
            .immediate();

        inputPlayerNationalityValidator.createCheck()
            .dependsOn("nationality", inputPlayerNationality.textProperty())
            .withMethod(c -> {
                String nationality = c.get("nationality");
                if (nationality.matches(".*\\d.*")) { c.error("Brojevi se ne smeju pojavljivati u imenu države"); }
                if (nationality.length() > 45) { c.error("Predugačko ime države."); }
            })
            .decorates(inputPlayerNationality)
            .immediate();

        inputPlayerRatingValidator.createCheck()
            .dependsOn("rating", inputPlayerRating.textProperty())
            .withMethod(c -> {
                String input = c.get("rating");
                Double rating = tryParseDouble(input);
                if (rating == null && !input.isEmpty()) { c.error("Nepravilno unet rejting."); }
            })
            .decorates(inputPlayerRating)
            .immediate();

        inputPlayerMajorTrophiesValidator.createCheck()
            .dependsOn("majorTrophies", inputPlayerMajorTrophies.textProperty())
            .withMethod(c -> {
                String input = c.get("majorTrophies");
                Integer majorTrophies = tryParseInt(input);
                if (majorTrophies == null && !input.isEmpty()) { c.error("Nepravilno unet broj major trofeja."); }
            })
            .decorates(inputPlayerMajorTrophies)
            .immediate();

        inputPlayerMajorMVPsValidator.createCheck()
            .dependsOn("majorMVPs", inputPlayerMajorMVPs.textProperty())
            .withMethod(c -> {
                String input = c.get("majorMVPs");
                Integer majorMVPs = tryParseInt(input);
                if (majorMVPs == null && !input.isEmpty()) { c.error("Nepravilno unet broj major MVP medalja."); }
            })
            .decorates(inputPlayerMajorMVPs)
            .immediate();

        inputMatchNumMapsValidator.createCheck()
            .dependsOn("numMaps", inputMatchNumMaps.textProperty())
            .withMethod(c -> {
                String input = c.get("numMaps");
                Integer numMaps = tryParseInt(input);
                if (numMaps == null && !input.isEmpty()) { c.error("Nepravilno unet broj mapa."); }
            })
            .decorates(inputMatchNumMaps)
            .immediate();

        inputMatchScoreValidator.createCheck()
            .dependsOn("score", inputMatchScore.textProperty())
            .withMethod(c -> {
                String score = c.get("score");
                if (!score.matches("\\d:\\d") && !score.isEmpty()) { c.error("Nepravilno unet rezultat."); }
            })
            .decorates(inputMatchScore)
            .immediate();

        inputTeamRankingValidator.createCheck()
            .dependsOn("ranking", inputTeamRanking.textProperty())
            .withMethod(c -> {
                String input = c.get("ranking");
                Integer ranking = tryParseInt(input);
                if (ranking == null && !input.isEmpty()) { c.error("Nepravilno uneta rang pozicija."); }
            })
            .decorates(inputTeamRanking)
            .immediate();

        inputTeamMajorTrophiesValidator.createCheck()
            .dependsOn("majorTrophies", inputTeamMajorTrophies.textProperty())
            .withMethod(c -> {
                String input = c.get("majorTrophies");
                Integer majorTrophies = tryParseInt(input);
                if (majorTrophies == null && !input.isEmpty()) { c.error("Nepravilno unet broj major trofeja."); }
            })
            .decorates(inputTeamMajorTrophies)
            .immediate();

        inputCoachNameValidator.createCheck()
            .dependsOn("name", inputCoachName.textProperty())
            .withMethod(c -> {
                String name = c.get("name");
                if (name.matches(".*\\d.*")) { c.error("Brojevi se ne smeju pojavljivati u imenu."); }
                if (name.length() > 45) { c.error("Predugačko ime."); }
            })
            .decorates(inputCoachName)
            .immediate();

        inputCoachSurnameValidator.createCheck()
            .dependsOn("surname", inputCoachSurname.textProperty())
            .withMethod(c -> {
                String surname = c.get("surname");
                if (surname.matches(".*\\d.*")) { c.error("Brojevi se ne smeju pojavljivati u prezimenu."); }
                if (surname.length() > 45) { c.error("Predugačko prezime."); }
            })
            .decorates(inputCoachSurname)
            .immediate();

        inputTournamentLocationValidator.createCheck()
            .dependsOn("tournamentLocation", inputTournamentLocation.textProperty())
            .withMethod(c -> {
                String tournamentLocation = c.get("tournamentLocation");
                if (tournamentLocation.matches(".*\\d.*")) { c.error("Brojevi se ne smeju pojavljivati u lokaciji."); }
                if (tournamentLocation.length() > 45) { c.error("Predugačka lokacija."); }
            })
            .decorates(inputTournamentLocation)
            .immediate();

        inputTournamentPrizePoolValidator.createCheck()
            .dependsOn("prizePool", inputTournamentPrizePool.textProperty())
            .withMethod(c -> {
                String input = c.get("prizePool");
                Integer prizePool = tryParseInt(input);
                if (prizePool == null && !input.isEmpty()) { c.error("Nepravilno unet nagradni fond."); }
            })
            .decorates(inputTournamentPrizePool)
            .immediate();
    }
}

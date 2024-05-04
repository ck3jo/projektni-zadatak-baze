package project.databasegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import net.synedra.validatorfx.Validator;
import project.databasegui.tableitems.*;

public class EditController implements Initializable
{
    public Scanner config = new Scanner(new File("fakehltv.config"));

    public ObservableList<Author> allAuthors = FXCollections.observableArrayList();
    public ObservableList<Player> allPlayers = FXCollections.observableArrayList();
    public ObservableList<Match> allMatches = FXCollections.observableArrayList();
    public ObservableList<Team> allTeams = FXCollections.observableArrayList();
    public ObservableList<Transfer> allTransfers = FXCollections.observableArrayList();
    public ObservableList<Coach> allCoaches = FXCollections.observableArrayList();
    public ObservableList<Tournament> allTournaments = FXCollections.observableArrayList();
    public ObservableList<News> allNews = FXCollections.observableArrayList();

    public TableView<Author> tableViewAuthors;
    public TableColumn<Author, String> tableColumnAuthorName;
    public TableColumn<Author, String> tableColumnAuthorNick;
    public TableColumn<Author, String> tableColumnAuthorSurname;

    public TableView<Player> tableViewPlayers;
    public TableColumn<Player, String> tableColumnPlayerName;
    public TableColumn<Player, String> tableColumnPlayerNick;
    public TableColumn<Player, String> tableColumnPlayerSurname;
    public TableColumn<Player, LocalDate> tableColumnPlayerBirthDate;
    public TableColumn<Player, String> tableColumnPlayerNationality;
    public TableColumn<Player, String> tableColumnPlayerTeamName;
    public TableColumn<Player, Double> tableColumnPlayerRating;
    public TableColumn<Player, String> tableColumnPlayerMajorTrophies;
    public TableColumn<Player, String> tableColumnPlayerMajorMVPs;

    public TableView<Match> tableViewMatches;
    public TableColumn<Match, String> tableColumnMatchFirstTeamName;
    public TableColumn<Match, String> tableColumnMatchSecondTeamName;
    public TableColumn<Match, String> tableColumnMatchTournamentName;
    public TableColumn<Match, Integer> tableColumnMatchNumberOfMaps;
    public TableColumn<Match, String> tableColumnMatchScore;
    public TableColumn<Match, LocalDate> tableColumnMatchDate;

    public TableView<Team> tableViewTeams;
    public TableColumn<Team, String> tableColumnTeamName;
    public TableColumn<Team, Integer> tableColumnTeamRanking;
    public TableColumn<Team, Integer> tableColumnTeamMajorTrophies;
    public TableColumn<Team, String> tableColumnTeamRegion;

    public TableView<Transfer> tableViewTransfers;
    public TableColumn<Transfer, String> tableColumnTransferPlayerName;
    public TableColumn<Transfer, String> tableColumnTransferOldTeamName;
    public TableColumn<Transfer, String> tableColumnTransferNewTeamName;
    public TableColumn<Transfer, LocalDate> tableColumnTransferDate;

    public TableView<Coach> tableViewCoaches;
    public TableColumn<Coach, String> tableColumnCoachName;
    public TableColumn<Coach, String> tableColumnCoachNick;
    public TableColumn<Coach, String> tableColumnCoachSurname;
    public TableColumn<Coach, String> tableColumnCoachTeamName;

    public TableView<Tournament> tableViewTournaments;
    public TableColumn<Tournament, String> tableColumnTournamentName;
    public TableColumn<Tournament, LocalDate> tableColumnTournamentStartDate;
    public TableColumn<Tournament, LocalDate> tableColumnTournamentEndDate;
    public TableColumn<Tournament, String> tableColumnTournamentLocation;
    public TableColumn<Tournament, Integer> tableColumnTournamentPrizePool;
    public TableColumn<Tournament, Boolean> tableColumnTournamentIsBig;

    public TableView<News> tableViewNews;
    public TableColumn<News, String> tableColumnNewsTitle;
    public TableColumn<News, LocalDate> tableColumnNewsDate;
    public TableColumn<News, String> tableColumnNewsAuthorName;

    private String url;
    private String user;
    private String pass;

    public EditController() throws FileNotFoundException {}

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspešno menjanje podataka!");
        alert.setHeaderText("Uspeh!");
        alert.showAndWait();
    }

    private static void showErrorAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Greška pri izmeni podataka.");
        alert.setHeaderText("Greška!");
        alert.showAndWait();
    }

    private static void showWrongInputAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Postoje greške u unetim podacima.");
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

    private Integer getPlayerIDFromNick(String playerNick) throws SQLException
    {
        Integer playerID = null;
        String sqlQuery = "SELECT IDIgraca FROM igraci WHERE Nadimak = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, playerNick);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { playerID = rs.getInt("IDIgraca"); }
        }

        return playerID;
    }

    private Integer getAuthorIDFromNick(String authorNick) throws SQLException
    {
        Integer authorID = null;
        String sqlQuery = "SELECT IDAutora from autori WHERE Nadimak = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, authorNick);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { authorID = rs.getInt("IDAutora"); }
        }

        return authorID;
    }

    public void confirmAuthorEdit(int rowToEdit, String name, String nick, String surname)
    {
        String sqlQuery = "UPDATE autori SET Ime = ?, Nadimak = ?, Prezime = ? WHERE IDAutora = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, name);
            ps.setString(2, nick);
            ps.setString(3, surname);
            ps.setInt(4, rowToEdit);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0) showSuccessAlert();
        }
        catch (SQLException e) { showErrorAlert(); }
    }

    public void confirmPlayerEdit(int rowToEdit, String name, String nick, String surname, LocalDate birthDate, String nationality, int teamID, double rating, int majorTrophies, int majorMVPs)
    {
        String sqlQuery = "UPDATE igraci SET Ime = ?, Nadimak = ?, Prezime = ?, DatumRodjenja = ?, Nacionalnost = ?, IDTima = ?, Rejting = ?, MajorTrofeji = ?, MajorMVP = ? WHERE IDIgraca = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, name);
            ps.setString(2, nick);
            ps.setString(3, surname);
            ps.setDate(4, Date.valueOf(birthDate));
            ps.setString(5, nationality);
            ps.setInt(6, teamID);
            ps.setDouble(7, rating);
            ps.setInt(8, majorTrophies);
            ps.setInt(9, majorMVPs);
            ps.setInt(10, rowToEdit);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0) showSuccessAlert();
        }
        catch (SQLException e) { showErrorAlert(); }
    }

    public void confirmMatchEdit(int rowToEdit, String firstTeamName, String secondTeamName, int tournamentID, int numberOfMaps, String score, LocalDate date)
    {
        String sqlQuery = "UPDATE mecevi SET PrviTim = ?, DrugiTim = ?, IDTurnira = ?, BrojMapa = ?, Rezultat = ?, DatumMeca = ? WHERE IDMeca = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, firstTeamName);
            ps.setString(2, secondTeamName);
            ps.setInt(3, tournamentID);
            ps.setInt(4, numberOfMaps);
            ps.setString(5, score);
            ps.setDate(6, Date.valueOf(date));
            ps.setInt(7, rowToEdit);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0) showSuccessAlert();
        }
        catch (SQLException e) { showErrorAlert(); }
    }

    public void confirmTeamEdit(int rowToEdit, String name, int ranking, int majorTrophies, String region)
    {
        String sqlQuery = "UPDATE timovi SET ImeTima = ?, RangPozicija = ?, MajorTrofeji = ?, Region = ? WHERE IDTima = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, name);
            ps.setInt(2, ranking);
            ps.setInt(3, majorTrophies);
            ps.setString(4, region);
            ps.setInt(5, rowToEdit);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0) showSuccessAlert();
        }
        catch (SQLException e) { showErrorAlert(); }
    }

    public void confirmTransferEdit(int rowToEdit, int playerID, int oldTeamID, int newTeamID, LocalDate date)
    {
        String sqlQuery = "UPDATE transferi SET IDIgraca = ?, IDStarogTima = ?, IDNovogTima = ?, DatumTransfera = ? WHERE IDTransfera = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, playerID);
            ps.setInt(2, oldTeamID);
            ps.setInt(3, newTeamID);
            ps.setDate(4, Date.valueOf(date));
            ps.setInt(5, rowToEdit);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0) showSuccessAlert();
        }
        catch (SQLException e) { showErrorAlert(); }
    }

    public void confirmCoachEdit(int rowToEdit, String name, String nick, String surname, int teamID)
    {
        String sqlQuery = "UPDATE treneri SET Ime = ?, Nadimak = ?, Prezime = ?, IDTima = ? WHERE IDTrenera = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, name);
            ps.setString(2, nick);
            ps.setString(3, surname);
            ps.setInt(4, teamID);
            ps.setInt(5, rowToEdit);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0) showSuccessAlert();
        }
        catch (SQLException e) { showErrorAlert(); }
    }

    public void confirmTournamentEdit(int rowToEdit, String name, LocalDate startDate, LocalDate endDate, String location, int prizePool, boolean isBig)
    {
        String sqlQuery = "UPDATE turniri SET Ime = ?, DatumPocetka = ?, DatumZavrsetka = ?, MestoIgranja = ?, NagradniFond = ?, VeciTurnir = ? WHERE IDTurnira = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, name);
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));
            ps.setString(4, location);
            ps.setInt(5, prizePool);
            ps.setBoolean(6, isBig);
            ps.setInt(7, rowToEdit);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0) showSuccessAlert();
        }
        catch (SQLException e) { showErrorAlert(); }
    }

    public void confirmNewsEdit(int rowToEdit, String title, int authorID, LocalDate date)
    {
        String sqlQuery = "UPDATE vesti SET Naslov = ?, DatumObjavljivanja = ?, IDAutora = ? WHERE IDVesti = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, title);
            ps.setDate(2, Date.valueOf(date));
            ps.setInt(3, authorID);
            ps.setInt(4, rowToEdit);

            int changedRows = ps.executeUpdate();

            if (changedRows > 0) showSuccessAlert();
        }
        catch (SQLException e) { showErrorAlert(); }
    }

    public void addEditHandlers()
    {
        tableViewAuthors.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableViewAuthors.getSelectionModel().getSelectedItem() != null)
            {
                Author selectedAuthor = tableViewAuthors.getSelectionModel().getSelectedItem();
                int rowToEdit = tableViewAuthors.getSelectionModel().getSelectedIndex() + 1;

                Dialog<Author> dialog = new Dialog<>();
                VBox vbox = new VBox();
                TextField textFieldAuthorName = new TextField(selectedAuthor.getName());
                Validator textFieldAuthorNameValidator = new Validator();
                textFieldAuthorNameValidator.createCheck()
                        .dependsOn("name", textFieldAuthorName.textProperty())
                        .withMethod(c -> {
                           String name = c.get("name");
                           if (name.matches(".*\\d.*"))
                           {
                               c.error("Brojevi se ne smeju pojavljivati u imenima.");
                           }
                           if (name.length() > 45)
                           {
                               c.error("Predugačko ime.");
                           }
                        })
                        .decorates(textFieldAuthorName)
                        .immediate();

                TextField textFieldAuthorNick = new TextField(selectedAuthor.getNick());
                TextField textFieldAuthorSurname = new TextField(selectedAuthor.getSurname());
                Validator textFieldAuthorSurnameValidator = new Validator();
                textFieldAuthorSurnameValidator.createCheck()
                        .dependsOn("surname", textFieldAuthorNick.textProperty())
                        .withMethod(c -> {
                            String surname = c.get("surname");
                            if (surname.matches(".*\\d.*")) { c.error("Ime ne sme sadržati brojeve."); }
                            if (surname.length() > 45) { c.error("Predugačko prezime."); }
                        })
                        .decorates(textFieldAuthorSurname)
                        .immediate();

                vbox.setSpacing(10);
                vbox.setPadding(new Insets(10, 10, 10, 10));
                vbox.setPrefWidth(600);
                vbox.setPrefHeight(100);

                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.setTitle("Izmenite podatke");

                vbox.getChildren().addAll(textFieldAuthorName, textFieldAuthorNick, textFieldAuthorSurname);
                dialog.getDialogPane().setContent(vbox);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
                dialog.initStyle(StageStyle.UTILITY);

                dialog.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.YES &&
                        !textFieldAuthorNameValidator.containsErrors() &&
                        !textFieldAuthorSurnameValidator.containsErrors())
                    {
                        Author newAuthor = new Author(textFieldAuthorName.getText().trim(), textFieldAuthorNick.getText().trim(), textFieldAuthorSurname.getText().trim());
                        confirmAuthorEdit(rowToEdit, newAuthor.getName(), newAuthor.getNick(), newAuthor.getSurname());
                        return newAuthor;
                    }
                    else if (button == ButtonType.YES) { showWrongInputAlert(); }
                    return null;
                });

                Optional<Author> dialogResult = dialog.showAndWait();
                dialogResult.ifPresent(_ -> {
                    allAuthors.clear();
                    try
                    {
                        loadAuthorData();
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
            }
        });

        tableViewPlayers.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableViewAuthors.getSelectionModel().getSelectedItem() != null)
            {
                Player selectedPlayer = tableViewPlayers.getSelectionModel().getSelectedItem();
                int rowToEdit = tableViewPlayers.getSelectionModel().getSelectedIndex() + 1;

                Dialog<Player> dialog = new Dialog<>();
                VBox vbox = new VBox();
                TextField textFieldPlayerName = new TextField(selectedPlayer.getName());
                Validator textFieldPlayerNameValidator = new Validator();
                textFieldPlayerNameValidator.createCheck()
                        .dependsOn("name", textFieldPlayerName.textProperty())
                        .withMethod(c -> {
                            String name = c.get("name");
                            if (name.matches(".*\\d.*")) { c.error("Ime ne sme sadržati brojeve."); }
                            if (name.length() > 45) { c.error("Predugačko ime."); }
                        })
                        .decorates(textFieldPlayerName)
                        .immediate();

                TextField textFieldPlayerNick = new TextField(selectedPlayer.getNick());
                TextField textFieldPlayerSurname = new TextField(selectedPlayer.getSurname());
                Validator textFieldPlayerSurnameValidator = new Validator();
                textFieldPlayerSurnameValidator.createCheck()
                        .dependsOn("surname", textFieldPlayerSurname.textProperty())
                        .withMethod(c -> {
                            String surname = c.get("surname");
                            if (surname.matches(".*\\d.*")) { c.error("Prezime ne sme sadržati brojeve."); }
                            if (surname.length() > 45) { c.error("Predugačko prezime."); }
                        })
                        .decorates(textFieldPlayerSurname)
                        .immediate();

                DatePicker datePickerPlayerBirthDate = new DatePicker(selectedPlayer.getBirthDate());
                TextField textFieldPlayerNationality = new TextField(selectedPlayer.getNationality());
                Validator textFieldPlayerNationalityValidator = new Validator();
                textFieldPlayerNationalityValidator.createCheck()
                        .dependsOn("nationality", textFieldPlayerNationality.textProperty())
                        .withMethod(c -> {
                            String nationality = c.get("nationality");
                            if (nationality.matches(".*\\d.*")) { c.error("Ime države ne sme sadržati brojeve."); }
                            if (nationality.length() > 45) { c.error("Predugačko ime države."); }
                        })
                        .decorates(textFieldPlayerNationality)
                        .immediate();

                TextField textFieldPlayerTeamName = new TextField(selectedPlayer.getTeamName());
                TextField textFieldPlayerRating = new TextField(Double.toString(selectedPlayer.getRating()));
                Validator textFieldPlayerRatingValidator = new Validator();
                textFieldPlayerRatingValidator.createCheck()
                        .dependsOn("rating", textFieldPlayerRating.textProperty())
                        .withMethod(c -> {
                            Double rating = tryParseDouble(c.get("rating"));
                            if (rating == null) { c.error("Nepravilno unet rejting."); }
                        })
                        .decorates(textFieldPlayerRating)
                        .immediate();

                TextField textFieldPlayerMajorTrophies = new TextField(Integer.toString(selectedPlayer.getMajorTrophies()));
                Validator textFieldPlayerMajorTrophiesValidator = new Validator();
                textFieldPlayerMajorTrophiesValidator.createCheck()
                        .dependsOn("majorTrophies", textFieldPlayerMajorTrophies.textProperty())
                        .withMethod(c -> {
                            Integer majorTrophies = tryParseInt(c.get("majorTrophies"));
                            if (majorTrophies == null) { c.error("Nepravilno unet broj major trofeja."); }
                        })
                        .decorates(textFieldPlayerMajorTrophies)
                        .immediate();

                TextField textFieldPlayerMajorMVPs = new TextField(Integer.toString(selectedPlayer.getMajorMVPs()));
                Validator textFieldPlayerMajorMVPsValidator = new Validator();
                textFieldPlayerMajorMVPsValidator.createCheck()
                        .dependsOn("majorMVPs", textFieldPlayerMajorMVPs.textProperty())
                        .withMethod(c -> {
                            Integer majorMVPs = tryParseInt(c.get("majorMVPs"));
                            if (majorMVPs == null) { c.error("Nepravilno unet broj major MVP medalja."); }
                        })
                        .decorates(textFieldPlayerMajorMVPs)
                        .immediate();

                vbox.setSpacing(10);
                vbox.setPadding(new Insets(10, 10, 10, 10));
                vbox.setPrefWidth(600);
                vbox.setPrefHeight(300);

                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.setTitle("Izmenite podatke");

                vbox.getChildren().addAll(
                        textFieldPlayerName,
                        textFieldPlayerNick,
                        textFieldPlayerSurname,
                        datePickerPlayerBirthDate,
                        textFieldPlayerNationality,
                        textFieldPlayerTeamName,
                        textFieldPlayerRating,
                        textFieldPlayerMajorTrophies,
                        textFieldPlayerMajorMVPs
                );
                dialog.getDialogPane().setContent(vbox);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
                dialog.initStyle(StageStyle.UTILITY);

                dialog.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.YES &&
                        !textFieldPlayerNameValidator.containsErrors() &&
                        !textFieldPlayerSurnameValidator.containsErrors() &&
                        !textFieldPlayerNationalityValidator.containsErrors() &&
                        !textFieldPlayerMajorTrophiesValidator.containsErrors() &&
                        !textFieldPlayerMajorMVPsValidator.containsErrors())
                    {
                        Player newPlayer = new Player(
                                textFieldPlayerName.getText().trim(),
                                textFieldPlayerNick.getText().trim(),
                                textFieldPlayerSurname.getText().trim(),
                                datePickerPlayerBirthDate.getValue(),
                                textFieldPlayerNationality.getText().trim(),
                                textFieldPlayerTeamName.getText().trim(),
                                Double.parseDouble(textFieldPlayerRating.getText().trim()),
                                Integer.parseInt(textFieldPlayerMajorTrophies.getText().trim()),
                                Integer.parseInt(textFieldPlayerMajorMVPs.getText().trim())
                        );
                        try
                        {
                            confirmPlayerEdit(
                                    rowToEdit,
                                    newPlayer.getName(),
                                    newPlayer.getNick(),
                                    newPlayer.getSurname(),
                                    newPlayer.getBirthDate(),
                                    newPlayer.getNationality(),
                                    getTeamIDFromName(newPlayer.getTeamName()),
                                    newPlayer.getRating(),
                                    newPlayer.getMajorTrophies(),
                                    newPlayer.getMajorMVPs()
                            );
                        }
                        catch (SQLException e)
                        {
                            throw new RuntimeException(e);
                        }
                        return newPlayer;
                    }
                    else if (button == ButtonType.YES) { showWrongInputAlert(); }
                    return null;
                });

                Optional<Player> dialogResult = dialog.showAndWait();
                dialogResult.ifPresent(_ -> {
                    allPlayers.clear();
                    try
                    {
                        loadPlayerData();
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
            }
        });

        tableViewMatches.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableViewMatches.getSelectionModel().getSelectedItem() != null)
            {
                Match selectedMatch = tableViewMatches.getSelectionModel().getSelectedItem();
                int rowToEdit = tableViewMatches.getSelectionModel().getSelectedIndex() + 1;

                Dialog<Match> dialog = new Dialog<>();
                VBox vbox = new VBox();
                TextField textFieldFirstTeamName = new TextField(selectedMatch.getFirstTeamName());
                TextField textFieldSecondTeamName = new TextField(selectedMatch.getSecondTeamName());
                TextField textFieldTournamentName = new TextField(selectedMatch.getTournamentName());
                TextField textFieldNumberOfMaps = new TextField(Integer.toString(selectedMatch.getNumberOfMaps()));
                Validator textFieldNumberOfMapsValidator = new Validator();
                textFieldNumberOfMapsValidator.createCheck()
                        .dependsOn("numberOfMaps", textFieldFirstTeamName.textProperty())
                        .withMethod(c -> {
                            Integer numberOfMaps = tryParseInt(c.get("numberOfMaps"));
                            if (numberOfMaps == null) { c.error("Nepravilno unet broj mapa."); }
                        })
                        .decorates(textFieldNumberOfMaps)
                        .immediate();

                TextField textFieldScore = new TextField(selectedMatch.getScore());
                Validator textFieldScoreValidator = new Validator();
                textFieldScoreValidator.createCheck()
                        .dependsOn("score", textFieldFirstTeamName.textProperty())
                        .withMethod(c -> {
                            String score = c.get("score");
                            if (!score.matches("\\d:\\d")) { c.error("Nepravilno unet rezultat."); }
                        })
                        .decorates(textFieldScore)
                        .immediate();

                DatePicker datePickerMatchDate = new DatePicker(selectedMatch.getMatchDate());

                vbox.setSpacing(10);
                vbox.setPadding(new Insets(10, 10, 10, 10));
                vbox.setPrefWidth(600);
                vbox.setPrefHeight(400);

                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.setTitle("Izmenite podatke");

                vbox.getChildren().addAll(
                        textFieldFirstTeamName,
                        textFieldSecondTeamName,
                        textFieldTournamentName,
                        textFieldNumberOfMaps,
                        textFieldScore,
                        datePickerMatchDate
                );
                dialog.getDialogPane().setContent(vbox);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
                dialog.initStyle(StageStyle.UTILITY);

                dialog.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.YES &&
                        !textFieldNumberOfMapsValidator.containsErrors() &&
                        !textFieldScoreValidator.containsErrors())
                    {
                        Match newMatch = new Match(
                                textFieldFirstTeamName.getText().trim(),
                                textFieldSecondTeamName.getText().trim(),
                                textFieldTournamentName.getText().trim(),
                                Integer.parseInt(textFieldNumberOfMaps.getText().trim()),
                                textFieldScore.getText().trim(),
                                datePickerMatchDate.getValue()
                        );
                        try
                        {
                            confirmMatchEdit(
                                    rowToEdit,
                                    newMatch.getFirstTeamName(),
                                    newMatch.getSecondTeamName(),
                                    getTournamentIDFromName(newMatch.getTournamentName()),
                                    newMatch.getNumberOfMaps(),
                                    newMatch.getScore(),
                                    newMatch.getMatchDate()
                            );
                        }
                        catch (SQLException e)
                        {
                            throw new RuntimeException(e);
                        }
                        return newMatch;
                    }
                    else if (button == ButtonType.YES) { showWrongInputAlert(); }
                    return null;
                });

                Optional<Match> dialogResult = dialog.showAndWait();
                dialogResult.ifPresent(_ -> {
                    allPlayers.clear();
                    try
                    {
                        loadMatchData();
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
            }
        });

        tableViewTeams.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableViewTeams.getSelectionModel().getSelectedItem() != null)
            {
                Team selectedTeam = tableViewTeams.getSelectionModel().getSelectedItem();
                int rowToEdit = tableViewTeams.getSelectionModel().getSelectedIndex() + 1;

                Dialog<Team> dialog = new Dialog<>();
                VBox vbox = new VBox();
                TextField textFieldTeamName = new TextField(selectedTeam.getName());
                TextField textFieldTeamRanking = new TextField(Integer.toString(selectedTeam.getRanking()));
                Validator textFieldTeamRankingValidator = new Validator();
                textFieldTeamRankingValidator.createCheck()
                        .dependsOn("ranking", textFieldTeamRanking.textProperty())
                        .withMethod(c -> {
                            Integer ranking = tryParseInt(c.get("ranking"));
                            if (ranking == null) { c.error("Nepravilno uneta rang pozicija."); }
                        })
                        .decorates(textFieldTeamRanking)
                        .immediate();

                TextField textFieldMajorTrophies = new TextField(Integer.toString(selectedTeam.getMajorTrophies()));
                Validator textFieldMajorTrophiesValidator = new Validator();
                textFieldMajorTrophiesValidator.createCheck()
                        .dependsOn("majorTrophies", textFieldMajorTrophies.textProperty())
                        .withMethod(c -> {
                            Integer majorTrophies = tryParseInt(c.get("majorTrophies"));
                            if (majorTrophies == null) { c.error("Nepravilno unet broj major trofeja."); }
                        })
                        .decorates(textFieldMajorTrophies)
                        .immediate();

                ChoiceBox<String> choiceBoxRegion = new ChoiceBox<>();
                choiceBoxRegion.getItems().addAll("Evropa", "Severna Amerika", "Južna Amerika", "Azija", "Okeanija", "Afrika");
                choiceBoxRegion.setValue(selectedTeam.getRegion());

                vbox.setSpacing(10);
                vbox.setPadding(new Insets(10, 10, 10, 10));
                vbox.setPrefWidth(600);
                vbox.setPrefHeight(120);

                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.setTitle("Izmenite podatke");

                vbox.getChildren().addAll(
                        textFieldTeamName,
                        textFieldTeamRanking,
                        textFieldMajorTrophies,
                        choiceBoxRegion
                );
                dialog.getDialogPane().setContent(vbox);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
                dialog.initStyle(StageStyle.UTILITY);

                dialog.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.YES &&
                        !textFieldTeamRankingValidator.containsErrors() &&
                        !textFieldMajorTrophiesValidator.containsErrors())
                    {
                        Team newTeam = new Team(
                                textFieldTeamName.getText().trim(),
                                Integer.parseInt(textFieldTeamRanking.getText().trim()),
                                Integer.parseInt(textFieldMajorTrophies.getText().trim()),
                                choiceBoxRegion.getValue()
                        );
                        confirmTeamEdit(
                                rowToEdit,
                                newTeam.getName(),
                                newTeam.getRanking(),
                                newTeam.getMajorTrophies(),
                                newTeam.getRegion()
                        );
                    }
                    else if (button == ButtonType.YES) { showWrongInputAlert(); }
                    return null;
                });

                Optional<Team> dialogResult = dialog.showAndWait();
                dialogResult.ifPresent(_ -> {
                    allTeams.clear();
                    try
                    {
                        loadTeamData();
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
            }
        });

        tableViewTransfers.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableViewTransfers.getSelectionModel().getSelectedItem() != null)
            {
                Transfer selectedTransfer = tableViewTransfers.getSelectionModel().getSelectedItem();
                int rowToEdit = tableViewTransfers.getSelectionModel().getSelectedIndex() + 1;

                Dialog<Transfer> dialog = new Dialog<>();
                VBox vbox = new VBox();
                TextField textFieldTransferPlayerName = new TextField(selectedTransfer.getPlayerName());
                TextField textFieldTransferOldTeamName = new TextField(selectedTransfer.getOldTeam());
                TextField textFieldTransferNewTeamName = new TextField(selectedTransfer.getNewTeam());
                DatePicker datePickerTransferDate = new DatePicker(selectedTransfer.getDate());

                vbox.setSpacing(10);
                vbox.setPadding(new Insets(10, 10, 10, 10));
                vbox.setPrefWidth(600);
                vbox.setPrefHeight(400);

                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.setTitle("Izmenite podatke");

                vbox.getChildren().addAll(
                        textFieldTransferPlayerName,
                        textFieldTransferOldTeamName,
                        textFieldTransferNewTeamName,
                        datePickerTransferDate
                );
                dialog.getDialogPane().setContent(vbox);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
                dialog.initStyle(StageStyle.UTILITY);

                dialog.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.YES)
                    {
                        Transfer newTransfer = new Transfer(
                                textFieldTransferPlayerName.getText().trim(),
                                textFieldTransferOldTeamName.getText().trim(),
                                textFieldTransferNewTeamName.getText().trim(),
                                datePickerTransferDate.getValue()
                        );
                        try
                        {
                            confirmTransferEdit(
                                    rowToEdit,
                                    getPlayerIDFromNick(newTransfer.getPlayerName()),
                                    getTeamIDFromName(newTransfer.getOldTeam()),
                                    getTeamIDFromName(newTransfer.getNewTeam()),
                                    newTransfer.getDate()
                            );
                        }
                        catch (SQLException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                    return null;
                });

                Optional<Transfer> dialogResult = dialog.showAndWait();
                dialogResult.ifPresent(_ -> {
                    allTransfers.clear();
                    try
                    {
                        loadTransferData();
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
            }
        });

        tableViewCoaches.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableViewCoaches.getSelectionModel().getSelectedItem() != null)
            {
                Coach selectedCoach = tableViewCoaches.getSelectionModel().getSelectedItem();
                int rowToEdit = tableViewCoaches.getSelectionModel().getSelectedIndex() + 1;

                Dialog<Coach> dialog = new Dialog<>();
                VBox vbox = new VBox();
                TextField textFieldCoachName = new TextField(selectedCoach.getName());
                Validator textFieldCoachNameValidator = new Validator();
                textFieldCoachNameValidator.createCheck()
                        .dependsOn("name", textFieldCoachName.textProperty())
                        .withMethod(c -> {
                            String name = c.get("name");
                            if (name.matches(".*\\d.*")) { c.error("Ime ne sme sadržati brojeve."); }
                            else if (name.length() > 45) { c.error("Predugačko ime."); }
                        })
                        .decorates(textFieldCoachName)
                        .immediate();

                TextField textFieldCoachNick = new TextField(selectedCoach.getNick());
                TextField textFieldCoachSurname = new TextField(selectedCoach.getSurname());
                Validator textFieldCoachSurnameValidator = new Validator();
                textFieldCoachSurnameValidator.createCheck()
                        .dependsOn("surname", textFieldCoachSurname.textProperty())
                        .withMethod(c -> {
                            String surname = c.get("surname");
                            if (surname.matches(".*\\d.*")) { c.error("Prezime ne sme sadržati brojeve."); }
                            else if (surname.length() > 45) { c.error("Predugačko prezime."); }
                        })
                        .decorates(textFieldCoachSurname)
                        .immediate();

                TextField textFieldCoachTeamName = new TextField(selectedCoach.getTeamName());

                vbox.setSpacing(10);
                vbox.setPadding(new Insets(10, 10, 10, 10));
                vbox.setPrefWidth(600);
                vbox.setPrefHeight(120);

                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.setTitle("Izmenite podatke");

                vbox.getChildren().addAll(
                        textFieldCoachName,
                        textFieldCoachNick,
                        textFieldCoachSurname,
                        textFieldCoachTeamName
                );
                dialog.getDialogPane().setContent(vbox);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
                dialog.initStyle(StageStyle.UTILITY);

                dialog.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.YES &&
                        !textFieldCoachNameValidator.containsErrors() &&
                        !textFieldCoachSurnameValidator.containsErrors())
                    {
                        Coach newCoach = new Coach(
                                textFieldCoachName.getText().trim(),
                                textFieldCoachNick.getText().trim(),
                                textFieldCoachSurname.getText().trim(),
                                textFieldCoachTeamName.getText().trim()
                        );
                        try
                        {
                            confirmCoachEdit(
                                    rowToEdit,
                                    newCoach.getName(),
                                    newCoach.getNick(),
                                    newCoach.getSurname(),
                                    getTeamIDFromName(newCoach.getTeamName())
                            );
                        } catch (SQLException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                    else if (button == ButtonType.YES) { showWrongInputAlert(); }
                    return null;
                });

                Optional<Coach> dialogResult = dialog.showAndWait();
                dialogResult.ifPresent(_ -> {
                    allCoaches.clear();
                    try
                    {
                        loadCoachData();
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
            }
        });

        tableViewTournaments.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableViewTournaments.getSelectionModel().getSelectedItem() != null)
            {
                Tournament selectedTournament = tableViewTournaments.getSelectionModel().getSelectedItem();
                int rowToEdit = tableViewTournaments.getSelectionModel().getSelectedIndex() + 1;

                Dialog<Tournament> dialog = new Dialog<>();
                VBox vbox = new VBox();
                TextField textFieldTournamentName = new TextField(selectedTournament.getName());
                DatePicker datePickerTournamentStartDate = new DatePicker(selectedTournament.getStartDate());
                DatePicker datePickerTournamentEndDate = new DatePicker(selectedTournament.getEndDate());
                TextField textFieldTournamentLocation = new TextField(selectedTournament.getLocation());
                Validator textFieldTournamentLocationValidator = new Validator();
                textFieldTournamentLocationValidator.createCheck()
                        .dependsOn("location", textFieldTournamentLocation.textProperty())
                        .withMethod(c -> {
                            String location = c.get("location");
                            if (location.matches(".*\\d.*")) { c.error("Lokacija ne sme sadržati brojeve."); }
                        })
                        .decorates(textFieldTournamentLocation)
                        .immediate();

                TextField textFieldTournamentPrizePool = new TextField(Integer.toString(selectedTournament.getPrizePool()));
                Validator textFieldTournamentPrizePoolValidator = new Validator();
                textFieldTournamentPrizePoolValidator.createCheck()
                        .dependsOn("prizePool", textFieldTournamentPrizePool.textProperty())
                        .withMethod(c -> {
                            Integer prizePool = tryParseInt(c.get("prizePool"));
                            if (prizePool == null) { c.error("Nepravilno unet nagradni fond turnira."); }
                        })
                        .decorates(textFieldTournamentPrizePool)
                        .immediate();

                HBox checkBoxWholeElement = new HBox();
                Label checkBoxLabel = new Label("Veliki turnir?");
                CheckBox checkBoxTournamentIsBig = new CheckBox();
                checkBoxTournamentIsBig.setSelected(selectedTournament.getIsBig());
                checkBoxTournamentIsBig.setIndeterminate(false);

                vbox.setSpacing(10);
                vbox.setPadding(new Insets(10, 10, 10, 10));
                vbox.setPrefWidth(600);
                vbox.setPrefHeight(400);

                checkBoxWholeElement.setSpacing(5);
                checkBoxWholeElement.getChildren().addAll(checkBoxLabel, checkBoxTournamentIsBig);

                vbox.getChildren().addAll(
                        textFieldTournamentName,
                        datePickerTournamentStartDate,
                        datePickerTournamentEndDate,
                        textFieldTournamentLocation,
                        textFieldTournamentPrizePool,
                        checkBoxWholeElement
                );
                dialog.getDialogPane().setContent(vbox);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
                dialog.initStyle(StageStyle.UTILITY);

                dialog.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.YES &&
                        !textFieldTournamentLocationValidator.containsErrors() &&
                        !textFieldTournamentPrizePoolValidator.containsErrors())
                    {
                        Tournament newTournament = new Tournament(
                                textFieldTournamentName.getText().trim(),
                                datePickerTournamentStartDate.getValue(),
                                datePickerTournamentEndDate.getValue(),
                                textFieldTournamentLocation.getText().trim(),
                                Integer.parseInt(textFieldTournamentPrizePool.getText().trim()),
                                checkBoxTournamentIsBig.isSelected()
                        );
                        confirmTournamentEdit(
                                rowToEdit,
                                newTournament.getName(),
                                newTournament.getStartDate(),
                                newTournament.getEndDate(),
                                newTournament.getLocation(),
                                newTournament.getPrizePool(),
                                newTournament.getIsBig()
                        );
                    }
                    else if (button == ButtonType.YES) { showWrongInputAlert(); }
                    return null;
                });

                Optional<Tournament> dialogResult = dialog.showAndWait();
                dialogResult.ifPresent(_ -> {
                    allTournaments.clear();
                    try
                    {
                        loadTournamentData();
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
            }
        });

        tableViewNews.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableViewNews.getSelectionModel().getSelectedItem() != null)
            {
                News selectedNews = tableViewNews.getSelectionModel().getSelectedItem();
                int rowToEdit = tableViewNews.getSelectionModel().getSelectedIndex() + 1;

                Dialog<News> dialog = new Dialog<>();
                VBox vbox = new VBox();
                TextField textFieldNewsTitle = new TextField(selectedNews.getTitle());
                DatePicker datePickerPublishDate = new DatePicker(selectedNews.getDate());
                TextField textFieldAuthorName = new TextField(selectedNews.getAuthorName());

                vbox.setSpacing(10);
                vbox.setPadding(new Insets(10, 10, 10, 10));
                vbox.setPrefWidth(600);
                vbox.setPrefHeight(100);

                vbox.getChildren().addAll(
                        textFieldNewsTitle,
                        datePickerPublishDate,
                        textFieldAuthorName
                );
                dialog.getDialogPane().setContent(vbox);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
                dialog.initStyle(StageStyle.UTILITY);

                dialog.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.YES)
                    {
                        News newNews = new News(
                                textFieldNewsTitle.getText().trim(),
                                textFieldAuthorName.getText().trim(),
                                datePickerPublishDate.getValue()
                        );
                        try
                        {
                            confirmNewsEdit(
                                    rowToEdit,
                                    newNews.getTitle(),
                                    getAuthorIDFromNick(newNews.getAuthorName()),
                                    newNews.getDate()
                            );
                        }
                        catch (SQLException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                    return null;
                });

                Optional<News> dialogResult = dialog.showAndWait();
                dialogResult.ifPresent(_ -> {
                    allNews.clear();
                    try
                    {
                        loadNewsData();
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }

    private String getTeamNameFromID(int teamID) throws SQLException
    {
        String teamName = "Nije u timu";
        String sqlQuery = "SELECT ImeTima FROM timovi WHERE IDTima = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, teamID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) { teamName = rs.getString("ImeTima"); }
        }

        return teamName;
    }

    private String getTournamentNameFromID(int tournamentID) throws SQLException
    {
        String tournamentName = "Van turnira";
        String sqlQuery = "SELECT Ime FROM turniri WHERE IDTurnira = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, tournamentID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) { tournamentName = rs.getString("Ime"); }
        }

        return tournamentName;
    }

    private String getPlayerNameFromID(int playerID) throws SQLException
    {
        String playerName = "";
        String sqlQuery = "SELECT Ime FROM igraci WHERE IDIgraca = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, playerID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) { playerName = rs.getString("Ime"); }
        }

        return playerName;
    }

    private String getAuthorNameFromID(int authorID) throws SQLException
    {
        String authorName = "";
        String sqlQuery = "SELECT Nadimak FROM autori WHERE IDAutora = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, authorID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) { authorName = rs.getString("Nadimak"); }
        }

        return authorName;
    }

    public void loadAuthorData() throws SQLException
    {
        tableViewAuthors.getItems().clear();

        String sqlQuery = "SELECT * FROM autori";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next())
            {
                Author currAuthor = new Author(rs.getString(2), rs.getString(3), rs.getString(4));
                allAuthors.add(currAuthor);
            }

            tableViewAuthors.getItems().setAll(allAuthors);
        }
    }

    public void loadPlayerData() throws SQLException
    {
        tableViewPlayers.getItems().clear();

        String sqlQuery = "SELECT * FROM igraci";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next())
            {
                Player currPlayer = new Player(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate(),
                        rs.getString(6),
                        getTeamNameFromID(rs.getInt(7)),
                        rs.getDouble(8),
                        rs.getInt(9),
                        rs.getInt(10)
                );
                allPlayers.add(currPlayer);
            }
        }

        tableViewPlayers.getItems().setAll(allPlayers);
    }

    public void loadMatchData() throws SQLException
    {
        tableViewMatches.getItems().clear();

        String sqlQuery = "SELECT * FROM mecevi";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next())
            {
                Match currMatch = new Match(
                        getTeamNameFromID(rs.getInt(2)),
                        getTeamNameFromID(rs.getInt(3)),
                        getTournamentNameFromID(rs.getInt(4)),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7).toLocalDate()
                );
                allMatches.add(currMatch);
            }
        }

        tableViewMatches.getItems().setAll(allMatches);
    }

    public void loadTeamData() throws SQLException
    {
        tableViewTeams.getItems().clear();

        String sqlQuery = "SELECT * FROM timovi";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next())
            {
                Team currTeam = new Team(
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5)
                );
                allTeams.add(currTeam);
            }
        }

        tableViewTeams.getItems().setAll(allTeams);
    }

    public void loadTransferData() throws SQLException
    {
        tableViewTransfers.getItems().clear();

        String sqlQuery = "SELECT * FROM transferi";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next())
            {
                Transfer currTransfer = new Transfer(
                        getPlayerNameFromID(rs.getInt(2)),
                        getTeamNameFromID(rs.getInt(3)),
                        getTeamNameFromID(rs.getInt(4)),
                        rs.getDate(5).toLocalDate()
                );
                allTransfers.add(currTransfer);
            }
        }

        tableViewTransfers.getItems().setAll(allTransfers);
    }

    public void loadCoachData() throws SQLException
    {
        tableViewCoaches.getItems().clear();

        String sqlQuery = "SELECT * FROM treneri";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next())
            {
                Coach currCoach = new Coach(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        getTeamNameFromID(rs.getInt(5))
                );
                allCoaches.add(currCoach);
            }
        }

        tableViewCoaches.getItems().setAll(allCoaches);
    }

    public void loadTournamentData() throws SQLException
    {
        tableViewTournaments.getItems().clear();

        String sqlQuery = "SELECT * FROM turniri";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next())
            {
                Tournament currTournament = new Tournament(
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getDate(4).toLocalDate(),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7) == 1
                );
                allTournaments.add(currTournament);
            }
        }

        tableViewTournaments.getItems().setAll(allTournaments);
    }

    public void loadNewsData() throws SQLException
    {
        tableViewNews.getItems().clear();

        String sqlQuery = "SELECT * FROM vesti";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next())
            {
                News currNews = new News(
                        rs.getString(2),
                        getAuthorNameFromID(rs.getInt(4)),
                        rs.getDate(3).toLocalDate()
                );
                allNews.add(currNews);
            }
        }

        tableViewNews.getItems().setAll(allNews);
    }

    @FXML
    public void returnToMain() throws IOException { MainWindow.setRoot("main-window"); }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        readConfig();
        addEditHandlers();

        tableColumnPlayerBirthDate.setCellFactory(_ -> new TableCell<>()
        {
            @Override
            protected void updateItem(LocalDate item, boolean empty)
            {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d. MMMM uuuu.");

                super.updateItem(item, empty);
                if (empty) setText(null);
                else setText(dateFormat.format(item));
            }
        });

        tableColumnPlayerRating.setCellFactory(_ -> new TableCell<>()
        {
            @Override
            protected void updateItem(Double item, boolean empty)
            {
                DecimalFormat decimalFormat = new DecimalFormat("####0.00");

                super.updateItem(item, empty);
                if (empty) setText(null);
                else setText(decimalFormat.format(item));
            }
        });

        tableColumnMatchDate.setCellFactory(_ -> new TableCell<>()
        {
            @Override
            protected void updateItem(LocalDate item, boolean empty)
            {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d. MMMM uuuu.");

                super.updateItem(item, empty);
                if (empty) setText(null);
                else setText(dateFormat.format(item));
            }
        });

        tableColumnTournamentPrizePool.setCellFactory(_ -> new TableCell<>()
        {
            @Override
            protected void updateItem(Integer item, boolean empty)
            {
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

                super.updateItem(item, empty);
                if (empty) setText(null);
                else setText(currencyFormat.format(item));
            }
        });

        tableColumnTournamentIsBig.setCellFactory(_ -> new TableCell<>()
        {
            @Override
            protected void updateItem(Boolean item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty) setText(null);
                else setText(item ? "Jeste" : "Nije");
            }
        });

        tableColumnTransferDate.setCellFactory(_ -> new TableCell<>()
        {
            @Override
            protected void updateItem(LocalDate item, boolean empty)
            {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d. MMMM uuuu.");

                super.updateItem(item, empty);
                if (empty) setText(null);
                else setText(dateFormat.format(item));
            }
        });

        tableColumnTournamentStartDate.setCellFactory(_ -> new TableCell<>()
        {
            @Override
            protected void updateItem(LocalDate item, boolean empty)
            {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d. MMMM uuuu.");

                super.updateItem(item, empty);
                if (empty) setText(null);
                else setText(dateFormat.format(item));
            }
        });

        tableColumnTournamentEndDate.setCellFactory(_ -> new TableCell<>()
        {
            @Override
            protected void updateItem(LocalDate item, boolean empty)
            {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d. MMMM uuuu.");

                super.updateItem(item, empty);
                if (empty) setText(null);
                else setText(dateFormat.format(item));
            }
        });

        tableColumnNewsDate.setCellFactory(_ -> new TableCell<>()
        {
            @Override
            protected void updateItem(LocalDate item, boolean empty)
            {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d. MMMM uuuu.");

                super.updateItem(item, empty);
                if (empty) setText(null);
                else setText(dateFormat.format(item));
            }
        });

        tableViewAuthors.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tableViewPlayers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tableViewMatches.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tableViewTeams.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tableViewTransfers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tableViewCoaches.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tableViewTournaments.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tableViewNews.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        try
        {
            loadAuthorData();
            loadTeamData();
            loadPlayerData();
            loadCoachData();
            loadTournamentData();
            loadMatchData();
            loadNewsData();
            loadTransferData();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}

package project.databasegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import project.databasegui.tableitems.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DeleteController implements Initializable
{
    public Scanner config = new Scanner(new File("fakehltv.config"));

    public int selectedRow;

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

    public DeleteController() throws FileNotFoundException {}

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

    public void removeFromAuthors(int authorID) throws SQLException
    {
        String sqlQuery = "DELETE FROM autori WHERE IDAutora = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, authorID);
            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspešno brisanje podataka!");
                alert.setHeaderText("Uspeh!");
                alert.showAndWait();
            }
        }
    }

    public void removeFromPlayers(int playerID) throws SQLException
    {
        String sqlQuery = "DELETE FROM igraci WHERE IDIgraca = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, playerID);
            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspešno brisanje podataka!");
                alert.setHeaderText("Uspeh!");
                alert.showAndWait();
            }
        }
    }

    public void removeFromMatches(int matchID) throws SQLException
    {
        String sqlQuery = "DELETE FROM mecevi WHERE IDMeca = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, matchID);
            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspeh!");
                alert.setHeaderText("Uspeh!");
                alert.showAndWait();
            }
        }
    }

    public void removeFromTeams(int teamID) throws SQLException
    {
        String sqlQuery = "DELETE FROM timovi WHERE IDTima = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, teamID);
            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspeh!");
                alert.setHeaderText("Uspeh!");
                alert.showAndWait();
            }
        }
    }

    public void removeFromTransfers(int transferID) throws SQLException
    {
        String sqlQuery = "DELETE FROM transferi WHERE IDTransfera = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, transferID);
            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspeh!");
                alert.setHeaderText("Uspeh!");
                alert.showAndWait();
            }
        }
    }

    public void removeFromCoaches(int coachID) throws SQLException
    {
        String sqlQuery = "DELETE FROM treneri WHERE IDTrenera = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, coachID);
            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspeh!");
                alert.setHeaderText("Uspeh!");
                alert.showAndWait();
            }
        }
    }

    public void removeFromTournaments(int tournamentID) throws SQLException
    {
        String sqlQuery = "DELETE FROM turniri WHERE IDTurnira = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, tournamentID);
            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspeh!");
                alert.setHeaderText("Uspeh!");
                alert.showAndWait();
            }
        }
    }

    public void removeFromNews(int newsID) throws SQLException
    {
        String sqlQuery = "DELETE FROM vesti WHERE IDvesti = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, newsID);
            int changedRows = ps.executeUpdate();

            if (changedRows > 0)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspeh!");
                alert.setHeaderText("Uspeh!");
                alert.showAndWait();
            }
        }
    }

    public void addDeleteListeners()
    {
        tableViewAuthors.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.DELETE)
            {
                if (tableViewAuthors.getSelectionModel().getSelectedItem() != null)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Da li ste sigurni da želite da izbrišete podatak?");
                    alert.setHeaderText("Upozorenje!");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK)
                        {
                            selectedRow = tableViewAuthors.getSelectionModel().getSelectedIndex();
                            tableViewAuthors.getItems().remove(selectedRow);
                            try
                            {
                                removeFromAuthors(selectedRow + 1);
                            } catch (SQLException ex) { throw new RuntimeException(ex); }
                        }
                        else {}
                    });
                }
            }
        });

        tableViewPlayers.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.DELETE)
            {
                if (tableViewPlayers.getSelectionModel().getSelectedItem() != null)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Da li ste sigurni da želite da izbrišete podatak?");
                    alert.setHeaderText("Upozorenje!");
                    alert.showAndWait().ifPresent(response -> {
                       if (response == ButtonType.OK)
                       {
                           selectedRow = tableViewPlayers.getSelectionModel().getSelectedIndex();
                           tableViewPlayers.getItems().remove(selectedRow);
                           try
                           {
                               removeFromPlayers(selectedRow + 1);
                           } catch (SQLException ex) { throw new RuntimeException(ex); }
                       }
                       else {}
                    });
                }
            }
        });

        tableViewMatches.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.DELETE)
            {
                if (tableViewMatches.getSelectionModel().getSelectedItem() != null)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Da li ste sigurni da želite da izbrišete podatak?");
                    alert.setHeaderText("Upozorenje!");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK)
                        {
                            selectedRow = tableViewMatches.getSelectionModel().getSelectedIndex();
                            tableViewMatches.getItems().remove(selectedRow);
                            try
                            {
                                removeFromMatches(selectedRow + 1);
                            } catch (SQLException ex) { throw new RuntimeException(ex); }
                        }
                        else {}
                    });
                }
            }
        });

        tableViewTeams.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.DELETE)
            {
                if (tableViewTeams.getSelectionModel().getSelectedItem() != null)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Da li ste sigurni da želite da izbrišete podatak?");
                    alert.setHeaderText("Upozorenje!");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK)
                        {
                            selectedRow = tableViewTeams.getSelectionModel().getSelectedIndex();
                            tableViewTeams.getItems().remove(selectedRow);
                            try
                            {
                                removeFromTeams(selectedRow + 1);
                            } catch (SQLException ex) { throw new RuntimeException(ex); }
                        }
                        else {}
                    });
                }
            }
        });

        tableViewTransfers.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.DELETE)
            {
                if (tableViewTransfers.getSelectionModel().getSelectedItem() != null)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Da li ste sigurni da želite da izbrišete podatak?");
                    alert.setHeaderText("Upozorenje!");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK)
                        {
                            selectedRow = tableViewPlayers.getSelectionModel().getSelectedIndex();
                            tableViewTransfers.getItems().remove(selectedRow);
                            try
                            {
                                removeFromTransfers(selectedRow + 1);
                            } catch (SQLException ex) { throw new RuntimeException(ex); }
                        }
                        else {}
                    });
                }
            }
        });

        tableViewCoaches.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.DELETE)
            {
                if (tableViewCoaches.getSelectionModel().getSelectedItem() != null)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Da li ste sigurni da želite da izbrišete podatak?");
                    alert.setHeaderText("Upozorenje!");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK)
                        {
                            selectedRow = tableViewCoaches.getSelectionModel().getSelectedIndex();
                            tableViewCoaches.getItems().remove(selectedRow);
                            try
                            {
                                removeFromCoaches(selectedRow + 1);
                            } catch (SQLException ex) {throw new RuntimeException(ex); }
                        } else {}
                    });
                }
            }
        });

        tableViewTournaments.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.DELETE)
            {
                if (tableViewTournaments.getSelectionModel().getSelectedItem() != null)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Da li ste sigurni da želite da izbrišete podatak?");
                    alert.setHeaderText("Upozorenje!");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK)
                        {
                            selectedRow = tableViewTournaments.getSelectionModel().getSelectedIndex();
                            tableViewTournaments.getItems().remove(selectedRow);
                            try
                            {
                                removeFromTournaments(selectedRow + 1);
                            } catch (SQLException ex) { throw new RuntimeException(ex); }
                        } else {}
                    });
                }
            }
        });

        tableViewNews.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.DELETE)
            {
                if (tableViewNews.getSelectionModel().getSelectedItem() != null)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Da li ste sigurni da želite da izbrišete podatak?");
                    alert.setHeaderText("Upozorenje!");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK)
                        {
                            selectedRow = tableViewNews.getSelectionModel().getSelectedIndex();
                            tableViewNews.getItems().remove(selectedRow);
                            try
                            {
                                removeFromNews(selectedRow + 1);
                            } catch (SQLException ex) { throw new RuntimeException(ex); }
                        } else {}
                    });
                }
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
        String sqlQuery = "SELECT Nadimak FROM igraci WHERE IDIgraca = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, playerID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) { playerName = rs.getString("Nadimak"); }
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

        ObservableList<Author> allAuthors = FXCollections.observableArrayList();
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

        ObservableList<Player> allPlayers = FXCollections.observableArrayList();
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

        ObservableList<Match> allMatches = FXCollections.observableArrayList();
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

        ObservableList<Team> allTeams = FXCollections.observableArrayList();
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

        ObservableList<Transfer> allTransfers = FXCollections.observableArrayList();
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

        ObservableList<Coach> allCoaches = FXCollections.observableArrayList();
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

        ObservableList<Tournament> allTournaments = FXCollections.observableArrayList();
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

        ObservableList<News> allNews = FXCollections.observableArrayList();
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
        selectedRow = 1;

        addDeleteListeners();

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

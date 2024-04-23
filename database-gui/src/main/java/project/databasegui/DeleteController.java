package project.databasegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import project.databasegui.tableitems.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DeleteController implements Initializable
{
    public Scanner readConfig = new Scanner("config.txt");

    public int selectedRow;

    public TableView<Author> tableViewAuthors;
    public TableView<Player> tableViewPlayers;
    public TableView<Match> tableViewMatches;
    public TableView<Team> tableViewTeams;
    public TableView<Transfer> tableViewTransfers;
    public TableView<Coach> tableViewCoaches;
    public TableView<Tournament> tableViewTournaments;
    public TableView<News> tableViewNews;

    private String url = "jdbc:mysql://localhost:3306/database-project";
    private String user = "root";
    private String pass = "";

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
                        getTournamentNameFromID(rs.getInt(3)),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getDate(6).toLocalDate()
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
        selectedRow = 1;

        addDeleteListeners();

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

package project.databasegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import project.databasegui.tableitems.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DeleteController implements Initializable
{
    public Scanner readConfig = new Scanner("config.txt");

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
                        getTeamNameFromID(rs.getInt(6)),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getInt(9)
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
        String sqlQuery = "SELECT * FROM autori";

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
        String sqlQuery = "SELECT * FROM autori";

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
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}

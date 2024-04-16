package project.databasegui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import project.databasegui.tableitems.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EditController implements Initializable
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

    private String url;
    private String user;
    private String pass;

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
            }
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
                Author currAuthor = new Author(rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }
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
                Author currAuthor = new Author(rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }
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
                Author currAuthor = new Author(rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }
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
                Author currAuthor = new Author(rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }
    }

    public void loadCoachData() throws SQLException
    {
        tableViewCoaches.getItems().clear();

        String sqlQuery = "SELECT * FROM autori";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next())
            {
                Author currAuthor = new Author(rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }
    }

    public void loadTournamentData() throws SQLException
    {
        tableViewTournaments.getItems().clear();

        String sqlQuery = "SELECT * FROM autori";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next())
            {
                Author currAuthor = new Author(rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }
    }

    public void loadNewsData() throws SQLException
    {
        tableViewNews.getItems().clear();

        String sqlQuery = "SELECT * FROM autori";

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next())
            {
                Author currAuthor = new Author(rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }
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
    }
}

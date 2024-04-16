package project.databasegui;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import project.databasegui.tableitems.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteController
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

    @FXML
    public void returnToMain() throws IOException { MainWindow.setRoot("main-window"); }

    @FXML
    public void sendAdjustedAuthorTableData() throws SQLException
    {

    }
}

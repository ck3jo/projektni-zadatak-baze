package project.databasegui;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class EditController
{
    public TableView tableViewAuthors;
    public TableView tableViewPlayers;
    public TableView tableViewMatches;
    public TableView tableViewTeams;
    public TableView tableViewTransfers;
    public TableView tableViewCoaches;
    public TableView tableViewTournaments;
    public TableView tableViewNews;

    @FXML
    public void returnToMain() throws IOException { MainWindow.setRoot("main-window"); }
}

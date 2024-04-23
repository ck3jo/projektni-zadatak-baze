package project.databasegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LocalDateStringConverter;
import project.databasegui.tableitems.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EditController implements Initializable
{
    public Scanner readConfig = new Scanner("config.txt");

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

    private String url = "jdbc:mysql://localhost:3306/database-project";
    private String user = "root";
    private String pass = "";

    public void addEditHandlers()
    {
        tableColumnAuthorName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnAuthorName.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue())
        );

        tableColumnAuthorNick.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnAuthorNick.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setNick(t.getNewValue())
        );

        tableColumnAuthorSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnAuthorSurname.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSurname(t.getNewValue())
        );

        tableColumnPlayerName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnPlayerName.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue())
        );

        tableColumnPlayerNick.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnPlayerNick.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setNick(t.getNewValue())
        );

        tableColumnPlayerSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnPlayerSurname.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSurname(t.getNewValue())
        );

        tableColumnPlayerBirthDate.setCellFactory(DatePickerCell::new);
        tableColumnPlayerBirthDate.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setBirthDate(t.getNewValue())
        );

        tableColumnPlayerNationality.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnPlayerNationality.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setNationality(t.getNewValue())
        );

        tableColumnPlayerTeamName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnPlayerTeamName.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setTeamName(t.getNewValue())
        );

        tableColumnPlayerMajorTrophies.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnPlayerMajorTrophies.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setMajorTrophies(Integer.parseInt(t.getNewValue()))
        );

        tableColumnPlayerMajorMVPs.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnPlayerMajorMVPs.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setMajorMVPs(Integer.parseInt(t.getNewValue()))
        );

        tableColumnMatchFirstTeamName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMatchFirstTeamName.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setFirstTeamName(t.getNewValue())
        );

        tableColumnMatchSecondTeamName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMatchSecondTeamName.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSecondTeamName(t.getNewValue())
        );

        tableColumnMatchTournamentName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMatchTournamentName.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setTournamentName(t.getNewValue())
        );

        tableColumnMatchNumberOfMaps.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMatchNumberOfMaps.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setNumberOfMaps(t.getNewValue())
        );

        tableColumnTournamentPrizePool.setCellFactory(col -> new TableCell<Tournament, Integer>()
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

        tableColumnTournamentIsBig.setCellFactory(col -> new TableCell<Tournament, Boolean>()
        {
            @Override
            protected void updateItem(Boolean item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? null : item ? "Jeste" : "Nije");
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
        addEditHandlers();

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

package project.databasegui.tableitems;

import java.time.LocalDate;

public class Match
{
    private String firstTeamName;
    private String secondTeamName;
    private String tournamentName;
    private int numberOfMaps;
    private String score;
    private LocalDate matchDate;

    public String getFirstTeamName() { return firstTeamName; }
    public void setFirstTeamName(String firstTeamName) { this.firstTeamName = firstTeamName; }

    public String getSecondTeamName() { return secondTeamName; }
    public void setSecondTeamName(String secondTeamName) { this.secondTeamName = secondTeamName; }

    public String getTournamentName() { return tournamentName; }
    public void setTournamentName(String tournamentName) { this.tournamentName = tournamentName; }

    public int getNumberOfMaps() { return numberOfMaps; }
    public void setNumberOfMaps(int numberOfMaps) { this.numberOfMaps = numberOfMaps; }

    public String getScore() { return score; }
    public void setScore(String score) { this.score = score; }

    public LocalDate getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDate matchDate) { this.matchDate = matchDate; }

    public Match(String firstTeamName, String secondTeamName, String tournamentName, int numberOfMaps, String score, LocalDate matchDate) {
        this.firstTeamName = firstTeamName;
        this.secondTeamName = secondTeamName;
        this.tournamentName = tournamentName;
        this.numberOfMaps = numberOfMaps;
        this.score = score;
        this.matchDate = matchDate;
    }
}

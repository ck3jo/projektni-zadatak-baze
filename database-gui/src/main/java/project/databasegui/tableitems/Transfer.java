package project.databasegui.tableitems;

import java.time.LocalDate;

public class Transfer
{
    private String playerName;
    private String oldTeam;
    private String newTeam;
    private LocalDate date;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getOldTeam() {
        return oldTeam;
    }

    public void setOldTeam(String oldTeam) {
        this.oldTeam = oldTeam;
    }

    public String getNewTeam() {
        return newTeam;
    }

    public void setNewTeam(String newTeam) {
        this.newTeam = newTeam;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Transfer(String playerName, String oldTeam, String newTeam, LocalDate date) {
        this.playerName = playerName;
        this.oldTeam = oldTeam;
        this.newTeam = newTeam;
        this.date = date;
    }
}

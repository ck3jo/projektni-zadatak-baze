package project.databasegui.tableitems;

import java.time.LocalDate;

public class Player
{
    private String name;
    private String nick;
    private String surname;
    private LocalDate birthDate;
    private String teamName;
    private int majorTrophies;
    private int majorMVPs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getMajorTrophies() {
        return majorTrophies;
    }

    public void setMajorTrophies(int majorTrophies) {
        this.majorTrophies = majorTrophies;
    }

    public int getMajorMVPs() {
        return majorMVPs;
    }

    public void setMajorMVPs(int majorMVPs) {
        this.majorMVPs = majorMVPs;
    }
}

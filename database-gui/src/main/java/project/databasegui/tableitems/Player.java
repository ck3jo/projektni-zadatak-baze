package project.databasegui.tableitems;

import java.time.LocalDate;

public class Player
{
    private String name;
    private String nick;
    private String surname;
    private LocalDate birthDate;
    private String nationality;
    private String teamName;
    private double rating;
    private int majorTrophies;
    private int majorMVPs;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNick() { return nick; }
    public void setNick(String nick) { this.nick = nick; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getMajorTrophies() { return majorTrophies; }
    public void setMajorTrophies(int majorTrophies) { this.majorTrophies = majorTrophies; }

    public int getMajorMVPs() { return majorMVPs; }
    public void setMajorMVPs(int majorMVPs) { this.majorMVPs = majorMVPs; }

    public Player(String name, String nick, String surname, LocalDate birthDate, String nationality, String teamName, double rating, int majorTrophies, int majorMVPs)
    {
        this.name = name;
        this.nick = nick;
        this.surname = surname;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.teamName = teamName;
        this.rating = rating;
        this.majorTrophies = majorTrophies;
        this.majorMVPs = majorMVPs;
    }
}

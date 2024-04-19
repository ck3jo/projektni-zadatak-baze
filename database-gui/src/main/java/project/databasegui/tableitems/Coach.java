package project.databasegui.tableitems;

public class Coach
{
    private String name;
    private String nick;
    private String surname;
    private String teamName;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNick() { return nick; }
    public void setNick(String nick) { this.nick = nick; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public Coach(String name, String nick, String surname, String teamName)
    {
        this.name = name;
        this.nick = nick;
        this.surname = surname;
        this.teamName = teamName;
    }
}

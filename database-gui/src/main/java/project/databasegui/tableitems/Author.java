package project.databasegui.tableitems;

public class Author
{
    private String name;
    private String nick;
    private String surname;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNick() { return nick; }
    public void setNick(String nick) { this.nick = nick; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public Author(String name, String nick, String surname)
    {
        this.name = name;
        this.nick = nick;
        this.surname = surname;
    }
}
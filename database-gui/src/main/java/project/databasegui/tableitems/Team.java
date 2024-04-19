package project.databasegui.tableitems;

public class Team
{
    private String name;
    private String logo;
    private int ranking;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public int getRanking() { return ranking; }
    public void setRanking(int ranking) { this.ranking = ranking; }

    public Team(String name, String logo, int ranking)
    {
        this.name = name;
        this.logo = logo;
        this.ranking = ranking;
    }
}

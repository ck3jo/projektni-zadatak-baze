package project.databasegui.tableitems;

public class Team
{
    private String name;
    private int ranking;
    private int majorTrophies;
    private String region;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getRanking() { return ranking; }
    public void setRanking(int ranking) { this.ranking = ranking; }

    public int getMajorTrophies() { return majorTrophies; }
    public void setMajorTrophies(int majorTrophies) { this.majorTrophies = majorTrophies; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public Team(String name, int ranking, int majorTrophies, String region)
    {
        this.name = name;
        this.ranking = ranking;
        this.majorTrophies = majorTrophies;
        this.region = region;
    }
}

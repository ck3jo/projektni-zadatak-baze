package project.databasegui.tableitems;

import java.time.LocalDate;

public class News
{
    private String title;
    private String authorName;
    private LocalDate date;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public News(String title, String authorName, LocalDate date)
    {
        this.title = title;
        this.authorName = authorName;
        this.date = date;
    }
}

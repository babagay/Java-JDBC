package Entity;

public class Skill {

    private int id;
    private String title;
    private Rate rate;

    public Skill()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Rate getRate()
    {
        return rate;
    }

    public void setRate(Rate rate)
    {
        this.rate = rate;
    }
}

package Entity;

import java.math.BigInteger;

public class Project {


    private long id;
    private String title;
    private BigInteger cost;

    public Project()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
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

    public BigInteger getCost()
    {
        return cost;
    }

    public void setCost(BigInteger cost)
    {
        this.cost = cost;
    }
}

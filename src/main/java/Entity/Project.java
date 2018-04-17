package Entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Project {


    private long id;
    private String title;
    private BigDecimal cost;

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

    public BigDecimal getCost()
    {
        return cost;
    }

    public void setCost(BigDecimal cost)
    {
        this.cost = cost;
    }

    @Override
    public String toString()
    {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}' + "";
    }
}

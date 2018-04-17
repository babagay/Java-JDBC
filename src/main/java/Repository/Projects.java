package Repository;

import Entity.Project;
import SQL.Parameter;

import java.util.ArrayList;
import java.util.Collection;

public class Projects implements StorageRepository<Project>
{
    private ArrayList<Project> collection;

    private DAO.Projects dao;

    public Projects()
    {
        collection = new ArrayList<>( 12 );

        dao = new DAO.Projects();
    }

    @Override
    public void add(Project element)
    {

    }

    @Override
    public void remove(Project element)
    {

    }

    @Override
    public Project get(int id)
    {
        return null;
    }

    @Override
    public Collection<Project> getAll()
    {
        return dao.getAll();
    }

    @Override
    public Collection<Project> find(String sql, Collection<Parameter> parameters)
    {
        ArrayList<Project> list;

        list = dao.getBySql( sql, (ArrayList<Parameter>) parameters );

        return (ArrayList<Project>) list;
    }
}

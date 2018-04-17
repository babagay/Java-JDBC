package Repository;


import Entity.Developer;
import SQL.Parameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Developers implements StorageRepository<Developer>
{
    private ArrayList<Developer> collection;

    private DAO.Developers dao;

    public Developers()
    {
        collection = new ArrayList<>( 12 );
        dao = new DAO.Developers();
    }

    public DAO.Developers getDao()
    {
        return dao;
    }

    @Override
    public void add(Developer element)
    {

    }

    @Override
    public void remove(Developer element)
    {

    }

    @Override
    public Developer get(int id)
    {
        Developer dev = collection.stream().filter( d -> d.getId() == id ).findFirst().orElse( null );

        if ( dev != null )
            return dev;

        dev = dao.getById( id );

        if ( dev != null )
            collection.add( dev );

        return dev;
    }

    @Override
    public Collection<Developer> getAll()
    {
        return dao.getAll();
    }

    @Override
    public Collection<Developer> find(String SQL, Collection<Parameter> parameters)
    {
        ArrayList<Developer> list = null;

        list = getDao().getBySql( SQL, (ArrayList<Parameter>) parameters );

        return (ArrayList<Developer>) list;
    }

    public Object find(String SQL, Collection<Parameter> parameters, String resultExpected)
    {
        switch ( resultExpected ){
            case "SINGLE_INT":
                return getDao().getSingleIntBySql( SQL,  (ArrayList<Parameter>) parameters );
        }

        return null;
    }
}

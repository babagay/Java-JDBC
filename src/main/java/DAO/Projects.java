package DAO;

import Entity.Project;
import SQL.Parameter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class Projects extends Root<Project>
{
    @Override
    public Collection<Project> getAll()
    {
        try  {

            SQL = "SELECT id, title, cost FROM projects";

            init();

            stmt = connection.createStatement();

            ResultSet result = stmt.executeQuery( SQL );

            return constructEntityListFromResult(result);

        } catch ( Throwable e ) {
            e.printStackTrace();
        } finally {
            close();
        }

        return null;
    }

    @Override
    public Project constructEntityFromResult(ResultSet result)
    {
        Project project = new Project();

        try {
            project.setId( result.getInt( "id" ) );
            project.setTitle( result.getString( "title" ) );
            project.setCost( result.getBigDecimal( "cost" ) );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        return project;
    }



    protected ArrayList<Project> constructEntityListFromResult(ResultSet result)
    {
        ArrayList<Project> list;

        list = new ArrayList<>( 12 );

        try {
            while ( result.next() ) {
                list.add( constructEntityFromResult( result ) );
            }
        } catch ( SQLException e ){
            e.printStackTrace();
        }

        return list;
    }

}

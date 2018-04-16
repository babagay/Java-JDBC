package DAO;

import Entity.Developer;
import Entity.Sex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class Developers extends Root {

    @Override
    public Collection getAll()
    {
        try  {

            connection = connector.getConnection();

            return getAllDev();

        } catch ( Throwable e ) {
            e.printStackTrace();
        } finally {
            close();
        }

        return null;
    }

    private ArrayList<Developer> getAllDev()
    throws SQLException
    {
        SQL = "SELECT id, name, age, address, salary, sex FROM developers";

        stmt = connection.createStatement();

        ResultSet result = stmt.executeQuery( SQL );

        ArrayList<Developer> list = new ArrayList<>( 12 );

        while ( result.next() ){

            Developer developer = new Developer();
            developer.setId( result.getInt( 1 ) );
            developer.setName(  result.getString( 2 )  );
            developer.setAge( result.getInt( 3 ) );
            developer.setAddress( result.getString( 4 ) );
            developer.setSalary( result.getBigDecimal( 5 ) );

            String sexStr = result.getString( 6 );

            if ( sexStr != null )
            developer.setSex( Sex.valueOf( sexStr ) );

            list.add( developer );
        }

        return list;
    }


}

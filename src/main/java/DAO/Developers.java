package DAO;

import Entity.Developer;
import Entity.Sex;
import SQL.Parameter;

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

    @Override
    public Developer constructEntityFromResult(ResultSet result)
    {
        Developer developer = new Developer();

        try {
            developer.setId( result.getInt( 1 ) );
            developer.setName( result.getString( 2 ) );
            developer.setAge( result.getInt( 3 ) );
            developer.setAddress( result.getString( 4 ) );
            developer.setSalary( result.getBigDecimal( 5 ) );

            String sexStr = result.getString( 6 );

            if ( sexStr != null ) {
                developer.setSex( Sex.valueOf( sexStr ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        return developer;
    }




    public Developer getById(int id)
    {
        Developer developer = null;

        this.SQL = "SELECT id, name, age, address, salary, sex FROM developers WHERE id = ?";

        try  {

            init();

            pstmt.setInt( 1, id );

            ResultSet result = pstmt.executeQuery();

            result.next();

            developer = constructEntityFromResult( result );

        } catch ( Throwable e ) {
            e.printStackTrace();
        } finally {
            close();
        }

        return developer;
    }

    public int getSingleIntBySql(String SQL, ArrayList<Parameter> params)
    {
        int res = 0;

        try {

            this.SQL = SQL;

            init();

            setParams( params );

            ResultSet result = pstmt.executeQuery();

            result.next();

            res = result.getInt( 1 );

        } catch ( Throwable e ) {
            e.printStackTrace();
        } finally {
            close();
        }

        return res;
    }





    private ArrayList<Developer> getAllDev()
    throws SQLException
    {
        SQL = "SELECT id, name, age, address, salary, sex FROM developers";

        stmt = connection.createStatement();

        ResultSet result = stmt.executeQuery( SQL );

        return constructEntityListFromResult( result );
    }



}

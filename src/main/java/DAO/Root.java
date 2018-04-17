package DAO;

import SQL.Parameter;
import StorageConnect.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Root<T> {

    protected Connector connector = Connector.JDBC_CONNECTOR;

    protected Connection connection;

    protected String SQL;

    protected Statement stmt;

    protected PreparedStatement pstmt;

    public ArrayList<T> getBySql(String SQL, ArrayList<Parameter> params)
    {
        try  {

            this.SQL = SQL;

            init();

            setParams(params);

            ResultSet result = pstmt.executeQuery();

            return constructEntityListFromResult( result );

        } catch ( Throwable e ) {
            e.printStackTrace();
        } finally {
            close();
        }

        return new ArrayList<>( 1 );
    }

    protected ArrayList<T> constructEntityListFromResult(ResultSet result)
    {
        ArrayList<T> list = new ArrayList<>( 12 );

        try {
            while ( result.next() ) {
                list.add( constructEntityFromResult( result ) );
            }
        } catch ( SQLException e ){
            e.printStackTrace();
        }

        return list;
    }

    protected void close ()
    {
        try {
            stmt.close();
            pstmt.close();
            connection.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( NullPointerException npe ) {
            // npe.printStackTrace();
        }
    }

    protected void init()
    throws SQLException
    {
        connector = Connector.JDBC_CONNECTOR;

        connection = connector.getConnection();

        pstmt = connection.prepareStatement( SQL );
    }

    protected void setParams( ArrayList<Parameter> params )
    throws SQLException
    {
        int i = 0;

        for ( Parameter parameter: params ) {

            if ( params.get( i ).value instanceof Integer ) {
                Integer intParam = (Integer) params.get( i ).value;
                pstmt.setInt( i + 1, intParam );
            }
            else if ( params.get( i ).value instanceof String ) {
                String strParam = (String) params.get( i ).value;
                pstmt.setString( i + 1, strParam );
            }

            i++;
        }
    }

    public abstract Collection<T> getAll();

    public abstract T constructEntityFromResult(ResultSet result);
}

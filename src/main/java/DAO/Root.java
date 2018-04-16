package DAO;

import StorageConnect.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public abstract class Root<T> {

    protected Connector connector = Connector.JDBC_CONNECTOR;

    protected Connection connection;

    protected String SQL;

    protected Statement stmt;

    protected void close ()
    {
        try {
            connection.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    abstract Collection<T> getAll();
}

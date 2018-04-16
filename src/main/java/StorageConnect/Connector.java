package StorageConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum Connector {

    JDBC_CONNECTOR;

    Connection conn = null;

    private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "1111";

    public Connection getConnection()
    {
        connect();

        return conn;
    }

    private void connect()
    {
        if ( !(conn instanceof Connection) )

        try {
            conn = DriverManager.getConnection( url, user, password );
            System.out.println( "Connected to the PostgreSQL server successfully." );
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }
}

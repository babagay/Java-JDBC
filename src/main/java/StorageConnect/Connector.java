package StorageConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Postgres connector
 *
 * [!] We can use connection pool
 */
public enum Connector {

    JDBC_CONNECTOR;

    Connection conn = null;

    private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "1111";

    public Connection getConnection()
    {
        try {
            connect();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        return conn;
    }

    private void connect()
    throws SQLException
    {
        if ( !(conn instanceof Connection) || (conn instanceof Connection && conn.isClosed()))

        conn = DriverManager.getConnection( url, user, password );

        System.out.println( "Connected to the PostgreSQL server successfully." );
    }
}

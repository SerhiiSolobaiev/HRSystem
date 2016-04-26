package my;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class ConnectionFactory which implements singleton pattern
 */
public class ConnectionFactory {
    //static reference to itself
    private static ConnectionFactory instance = new ConnectionFactory();

    public static final String HOST = "jdbc:postgresql://localhost:5432/";
    public static final String DB_NAME = "hr_system";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "CP2netCR";
    public static final String DRIVER_CLASS = "org.postgresql.Driver";

    //private constructor
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(HOST + DB_NAME, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }
}

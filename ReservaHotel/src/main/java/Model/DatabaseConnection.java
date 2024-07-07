package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlserver://localhost:1433;"
            + "database=Reserva_Hotel_DB;"
            + "user=ReservaHotel;"
            + "password=root;"
            + "encrypt=true;"
            + "trustServerCertificate=true";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Conectado");
            return DriverManager.getConnection(URL);
            

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBC driver no se ha encontrado", e);
        } catch (SQLException e) {
            throw new SQLException("Error al conectar la base de datos", e);
        }
    }

}


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestingConection {

    public static void main(String[] args) {

        String url = "jdbc:sqlserver://localhost:1433;"
                + "database=Reserva_Hotel_DB;"
                + "user=ReservaHotel;"
                + "password=root;"
                + "encrypt=true;"
                + "trustServerCertificate=true";
        
        Connection conn = null;
        
        try {
            //Cargar el driver JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            //Establecer la conexión
            conn = DriverManager.getConnection(url);
            if(conn != null) {
                System.out.println("Conexión se ha establecido correctamente");
            }
            
        } catch (ClassNotFoundException e) { //error con el driver
            System.out.println("No se encontró driverJDBC");
            e.printStackTrace();
            
        } catch (SQLException e){ //ha fallado algun parametro de la base de datos
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
            
        }finally {
            try {
                if (conn !=null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

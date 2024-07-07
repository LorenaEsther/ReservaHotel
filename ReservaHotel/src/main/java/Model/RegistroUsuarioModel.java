
package Model;
// Security
import Security.PasswordUtils;

// Utils
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class RegistroUsuarioModel {
    public boolean registerUser (String correo, String password, String rol) {
        
        String hashedPassword = PasswordUtils.hashPassword(password);
        
        String sql = "INSERT INTO Usuarios "
                + "(Correo, Contrasena, Rol) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, correo);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, rol);
            
            int result = stmt.executeUpdate();
            return result >0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

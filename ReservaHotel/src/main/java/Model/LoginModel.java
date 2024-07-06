package Model;
//Security

import Security.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    public String[] authenticate(String username, String password) {
        String query = "SELECT UsuarioID, Contrasena, Rol FROM Usuarios WHERE correo = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("Contrasena");
                int rol = rs.getInt("Rol");
                int usuarioID = rs.getInt("UsuarioID");

                if (PasswordUtils.verifyPassword(password, storedPassword)) {
                    return new String[]{String.valueOf(usuarioID), rol == 1 ? "Administrador" : rol == 2 ? "Recepcionista" : "Gerente"};

                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}

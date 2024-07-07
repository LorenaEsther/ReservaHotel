package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionCrudModel {

    public Habitacion buscarPorNumero(int numero) {
        Habitacion habitacion = null;
        String query = "SELECT * FROM Habitaciones WHERE Numero = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery(); 
            if (rs.next()) {
                habitacion = new Habitacion(
                        rs.getInt("Numero"),
                        rs.getString("Tipo"),
                        rs.getDouble("Precio"),
                        rs.getString("Estado")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return habitacion;
    }

    public boolean actualizarHabitacion(Habitacion habitacion) {
        String query = "UPDATE Habitaciones SET Tipo = ?, Precio = ?, Estado = ? WHERE Numero = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, habitacion.getTipo());
            stmt.setDouble(2, habitacion.getPrecio());
            stmt.setString(3, habitacion.getEstado());
            stmt.setInt(4, habitacion.getNumero());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminarHabitacion(int numero) {
        String query = "DELETE FROM Habitaciones WHERE Numero = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, numero);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean agregarHabitacion(Habitacion habitacion) {
        String query = "INSERT INTO Habitaciones (Numero, Tipo, Precio, Estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, habitacion.getNumero());
            stmt.setString(2, habitacion.getTipo());
            stmt.setDouble(3, habitacion.getPrecio());
            stmt.setString(4, habitacion.getEstado());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Habitacion> getHabitaciones() {
        List<Habitacion> habitaciones = new ArrayList<>();
        String query = "SELECT * FROM Habitaciones";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Habitacion habitacion = new Habitacion(
                        rs.getInt("Numero"),
                        rs.getString("Tipo"),
                        rs.getDouble("Precio"),
                        rs.getString("Estado")
                );
                habitaciones.add(habitacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return habitaciones;
    }
    
}

package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaCrudModel {

    public boolean agregarReservas(Reserva reserva) {
        String query = "INSERT INTO  Reservas (ClienteID,HabitacionNumero,FechaInicio,FechaFin ,Estado)  VALUES(?,?,?,?,?)";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reserva.getNumero());
            stmt.setString(2, reserva.getTipo());
            stmt.setDouble(3, reserva.getPrecio());
            stmt.setString(4, reserva.getEstado());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<String[]> getReservas() {
        List<String[]> reservas = new ArrayList<>();
        String query = "SELECT b.Nombre AS Cliente, b.Dni, a.HabitacionNumero, a.FechaInicio, a.FechaFin, a.Estado "
                + "FROM Reservas a LEFT JOIN Clientes b ON a.ClienteID = b.ClienteID";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String[] row = new String[6];
                row[0] = rs.getString("Cliente");
                row[1] = rs.getString("Dni");
                row[2] = rs.getString("HabitacionNumero");
                row[3] = rs.getString("FechaInicio");
                row[4] = rs.getString("FechaFin");
                row[5] = rs.getString("Estado");
                reservas.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reservas;
    }
}

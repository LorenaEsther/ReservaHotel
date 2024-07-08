package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaCrudModel {

    public boolean agregarReservas(Reserva reserva, String dni) { 
        String clientQuery = "SELECT clienteId FROM Clientes WHERE DNI = ?";
 
        String insertQuery = "INSERT INTO Reservas (ClienteID, HabitacionNumero, FechaInicio, FechaFin, Estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
           
            try (PreparedStatement clientStmt = conn.prepareStatement(clientQuery)) {
                clientStmt.setString(1, dni);
                ResultSet clientRs = clientStmt.executeQuery();
                if (!clientRs.next()) {
                    System.out.println("No client found with DNI: " + dni);
                    return false;  
                }
                int clientId = clientRs.getInt("clienteId");
                 
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, clientId); // Set the client ID retrieved from the DNI
                    insertStmt.setInt(2, reserva.getHabitacionNumero());
                    insertStmt.setDate(3, new java.sql.Date(reserva.getFechaInicio().getTime()));
                    insertStmt.setDate(4, new java.sql.Date(reserva.getFechaFin().getTime()));
                    insertStmt.setString(5, reserva.getEstado());

                    int affectedRows = insertStmt.executeUpdate();
                    return affectedRows > 0;
                }
            }
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

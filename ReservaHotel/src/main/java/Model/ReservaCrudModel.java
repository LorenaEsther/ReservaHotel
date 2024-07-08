package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaCrudModel {

    public Reserva buscarPorReservaID(int reservaID) {
        Reserva reserva = null;
        String query = "SELECT a.ReservaID, a.ClienteID, a.HabitacionNumero, a.FechaInicio, a.FechaFin, a.Estado, b.DNI "
                + "FROM Reservas a JOIN Clientes b ON a.ClienteID = b.ClienteID "
                + "WHERE a.ReservaID = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservaID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                reserva = new Reserva(
                        rs.getInt("ReservaID"),
                        rs.getString("DNI"),
                        rs.getInt("ClienteID"),
                        rs.getInt("HabitacionNumero"),
                        rs.getDate("FechaInicio"),
                        rs.getDate("FechaFin"),
                        rs.getString("Estado")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reserva;
    }

    public List<String[]> getReservasPorDni(String dni) {
        List<String[]> reservas = new ArrayList<>();
        String query = "SELECT ReservaID, b.Nombre AS Cliente, b.Dni, a.HabitacionNumero, a.FechaInicio, a.FechaFin, a.Estado "
                    + "FROM Reservas a JOIN Clientes b ON a.ClienteID = b.ClienteID "
                    + "WHERE b.DNI = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] row = new String[7];
                row[0] = rs.getString("ReservaID");
                row[1] = rs.getString("Cliente");
                row[2] = rs.getString("Dni");
                row[3] = rs.getString("HabitacionNumero");
                row[4] = rs.getString("FechaInicio");
                row[5] = rs.getString("FechaFin");
                row[6] = rs.getString("Estado");
                reservas.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reservas;
    }

    public boolean agregarReservas(Reserva reserva) {
        String clientQuery = "SELECT clienteId FROM Clientes WHERE DNI = ?";
        String insertQuery = "INSERT INTO Reservas (ClienteID, HabitacionNumero, FechaInicio, FechaFin, Estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement clientStmt = conn.prepareStatement(clientQuery)) {
                clientStmt.setString(1, reserva.getDni());
                ResultSet clientRs = clientStmt.executeQuery();
                if (!clientRs.next()) {
                    System.out.println("No client found with DNI: " + reserva.getDni());
                    return false;
                }
                int clientId = clientRs.getInt("clienteId");

                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, clientId);
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

    public boolean actualizarReservas(Reserva reserva) {
        String clientQuery = "SELECT clienteId FROM Clientes WHERE DNI = ?";
        String updateQuery = "UPDATE Reservas SET ClienteID = ?, HabitacionNumero = ?, FechaInicio = ?, FechaFin = ?, Estado = ? WHERE ReservaID = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement clientStmt = conn.prepareStatement(clientQuery)) {
                clientStmt.setString(1, reserva.getDni());
                ResultSet clientRs = clientStmt.executeQuery();
                if (!clientRs.next()) {
                    System.out.println("No client found with DNI: " + reserva.getDni());
                    return false; // Client not found, exit method
                }
                int clientId = clientRs.getInt("clienteId");

                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                    updateStmt.setInt(1, clientId);
                    updateStmt.setInt(2, reserva.getHabitacionNumero());
                    updateStmt.setDate(3, new java.sql.Date(reserva.getFechaInicio().getTime()));
                    updateStmt.setDate(4, new java.sql.Date(reserva.getFechaFin().getTime()));
                    updateStmt.setString(5, reserva.getEstado());
                    updateStmt.setInt(6, reserva.getReservaID());
                    int affectedRows = updateStmt.executeUpdate();
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
        String query = "SELECT ReservaID,b.Nombre AS Cliente, b.Dni, a.HabitacionNumero, a.FechaInicio, a.FechaFin, a.Estado "
                + "FROM Reservas a LEFT JOIN Clientes b ON a.ClienteID = b.ClienteID";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String[] row = new String[7];
                row[0] = rs.getString("ReservaID");
                row[1] = rs.getString("Cliente");
                row[2] = rs.getString("Dni");
                row[3] = rs.getString("HabitacionNumero");
                row[4] = rs.getString("FechaInicio");
                row[5] = rs.getString("FechaFin");
                row[6] = rs.getString("Estado");
                reservas.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reservas;
    }

    public boolean eliminarReservas(int reservaID) {
        String query = "DELETE FROM Reservas WHERE ReservaID = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservaID);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

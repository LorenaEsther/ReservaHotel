package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteCrudModel {

    public Cliente buscarPorId(int clienteID) {
        Cliente cliente = null;
        String query = "SELECT * FROM Clientes WHERE ClienteID = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clienteID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("ClienteID"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("DNI"),
                        rs.getString("Email")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }

    public boolean actualizarCliente(Cliente cliente) {
        String query = "UPDATE Clientes SET Nombre = ?, Apellido = ?, DNI = ?, Email = ? WHERE ClienteID = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getDni());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getClienteID());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCliente(int clienteID) {
        String query = "DELETE FROM Clientes WHERE ClienteID = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clienteID);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean agregarCliente(Cliente cliente) {
        if (cliente.getDni().length() != 8) {
            throw new IllegalArgumentException("El DNI debe tener 8 dígitos.");
        }

        String query = "INSERT INTO Clientes (Nombre, Apellido, DNI, Email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getDni());
            stmt.setString(4, cliente.getEmail());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 2627) { // Código de error para la violación de clave única en SQL Server
                throw new IllegalArgumentException("El DNI ya existe.");
            } else {
                ex.printStackTrace();
                return false;
            }
        }
    }

    public List<Cliente> getClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Clientes";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("ClienteID"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("DNI"),
                        rs.getString("Email")
                );
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }
}

package View;

import javax.swing.table.DefaultTableModel;

public class ClienteTableModel extends DefaultTableModel {

    public ClienteTableModel() {
        super(new Object[]{"ClienteID", "Nombre", "Apellido", "DNI", "Email"}, 0);
    }

    public void addCliente(int clienteID, String nombre, String apellido, String dni, String email) {
        super.addRow(new Object[]{clienteID, nombre, apellido, dni, email});
    }
}

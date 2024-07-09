package Controller;

import Model.Cliente;
import Model.ClienteCrudModel;
import Model.ReservaCrudModel;
import View.AdministradorFrame;
import View.ClienteCrudFrame;
import View.ClienteTableModel;
import View.RecepcionistaFrame;
import java.util.List;

public class ClienteCrudController {

    private ClienteCrudFrame frame;
    private ClienteCrudModel model;
    private String userRole;

    public ClienteCrudController(ClienteCrudFrame frame, ClienteCrudModel model, String userRole) {
        this.frame = frame;
        this.model = model;
        this.userRole = userRole;
        initController();
    }

    private void initController() {
        frame.getBtnAgregar().addActionListener(e -> {
            agregarCliente(false);
            clearFields();
        });
        frame.getBtnEditar().addActionListener(e -> editarCliente());
        frame.getBtnActualizar().addActionListener(e -> {
            agregarCliente(true);
            clearFields();
        });
        frame.getBtnEliminar().addActionListener(e -> eliminarCliente());
        frame.getBtnRetroceder().addActionListener(e -> goBackReceptionist());
        frame.getBtnRetroceder().addActionListener(e -> goBackAdmin());
        cargarClientes();
    }

    public void cargarClientes() {
        List<Cliente> data = model.getClientes();
        ClienteTableModel tableModel = new ClienteTableModel();

        for (Cliente cliente : data) {
            int clienteID = cliente.getClienteID();
            String nombre = cliente.getNombre();
            String apellido = cliente.getApellido();
            String dni = cliente.getDni();
            String email = cliente.getEmail();

            tableModel.addCliente(clienteID, nombre, apellido, dni, email);
        }
        frame.getTblCliente().setModel(tableModel);
    }

    private void agregarCliente(boolean isUpdate) {
        try {
            String nombre = frame.getTxtNombre().getText();
            String apellido = frame.getTxtApellido().getText();
            String dni = frame.getTxtDni().getText();
            String email = frame.getTxtCorreo().getText();

            if (dni.length() != 8) {
                throw new IllegalArgumentException("El DNI debe tener 8 dígitos.");
            }

            Cliente cliente = new Cliente(0, nombre, apellido, dni, email);
            boolean success;

            if (isUpdate) {
                int clienteID = Integer.parseInt(frame.getTblCliente().getValueAt(frame.getTblCliente().getSelectedRow(), 0).toString());
                cliente.setClienteID(clienteID);
                success = model.actualizarCliente(cliente);
            } else {
                success = model.agregarCliente(cliente);
            }

            if (success) {
                frame.displaySucessMessage(isUpdate ? "Cliente actualizado correctamente." : "Cliente agregado correctamente.");
                cargarClientes();
            } else {
                frame.displayErrorMessage(isUpdate ? "Error actualizando el cliente." : "Error agregando el cliente.");
            }

        } catch (IllegalArgumentException e) {
            frame.displayErrorMessage(e.getMessage());
            e.printStackTrace();
        } 
        
    }

    private void eliminarCliente() {
        int selectedRow = frame.getTblCliente().getSelectedRow();
        if (selectedRow >= 0) {
            int clienteID = Integer.parseInt(frame.getTblCliente().getValueAt(selectedRow, 0).toString());
            if (model.eliminarCliente(clienteID)) {
                frame.displaySucessMessage("Cliente eliminado correctamente.");
                cargarClientes();
            } else {
                frame.displayErrorMessage("Error eliminando al cliente.");
            }
        } else {
            frame.displayErrorMessage("Seleccione un cliente de la tabla.");
        }
    }

    private void editarCliente() {
        int selectedRow = frame.getTblCliente().getSelectedRow();
        if (selectedRow >= 0) {
            int clienteID = Integer.parseInt(frame.getTblCliente().getValueAt(selectedRow, 0).toString());
            Cliente cliente = model.buscarPorId(clienteID);
            if (cliente != null) {
                frame.getTxtNombre().setText(cliente.getNombre());
                frame.getTxtApellido().setText(cliente.getApellido());
                frame.getTxtDni().setText(cliente.getDni());
                frame.getTxtCorreo().setText(cliente.getEmail());
                makeFieldsEditable(true, true);
            } else {
                frame.displayErrorMessage("Cliente no encontrado.");
            }
        } else {
            frame.displayErrorMessage("Seleccione un cliente de la tabla.");
        }
    }

    private void retrocederFrame() {
        frame.dispose();
        // Implementar la lógica necesaria para retroceder a la ventana anterior
    }

    private void makeFieldsEditable(boolean editable, boolean enableUpdateButton) {
        frame.getTxtNombre().setEditable(editable);
        frame.getTxtApellido().setEditable(editable);
        frame.getTxtDni().setEditable(editable);
        frame.getTxtCorreo().setEditable(editable);
        frame.getBtnActualizar().setEnabled(enableUpdateButton);
    }

    private void clearFields() {
        frame.getTxtNombre().setText("");
        frame.getTxtApellido().setText("");
        frame.getTxtDni().setText("");
        frame.getTxtCorreo().setText("");
    }
    
    private void showReceptionistScreen(){
        ReservaCrudModel model = new ReservaCrudModel();
        RecepcionistaFrame frameRec = new RecepcionistaFrame();
        new RecepcionistaController(frameRec, model, userRole);
        frame.setVisible(true);
    }
    
    private void goBackReceptionist(){
        frame.dispose();
        showReceptionistScreen();
    }
    
    private void showAdminScreen(){
        ReservaCrudModel model = new ReservaCrudModel();
        AdministradorFrame frameAdmin = new AdministradorFrame();
        new AdministradorController(frameAdmin, model,userRole);
        frame.setVisible(true);
    }
    
    private void goBackAdmin(){
        frame.dispose();
        showAdminScreen();
    }
    
}

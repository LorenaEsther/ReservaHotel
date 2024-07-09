
package Controller;

//Base 
import Base.App;
import Model.ClienteCrudModel;

//Model
import Model.ReservaCrudModel;
import Model.LoginModel;   
import View.ClienteCrudFrame;

//View 
import View.LoginFrame;  
import View.RecepcionistaFrame;
import View.ReservaCrudFrame;
import View.ReservasTableModel;
import java.util.List;


public class RecepcionistaController {
    private RecepcionistaFrame frame;
    private ReservaCrudModel model;
    private String userRole;

    public RecepcionistaController(RecepcionistaFrame frame, ReservaCrudModel model, String userRole) {
        this.frame = frame;
        this.model = model;
        this.userRole = userRole;
        initController();
    }
    
    private void initController() {
        frame.getBtnBuscar().addActionListener(e -> buscarReserva()); 
        frame.getBtnCrudReservas().addActionListener(e -> abrirCrudReservas()); 
        frame.getBtnCrudClientes().addActionListener(e -> abrirCrudClientes()); 
        frame.getBtnSalir().addActionListener(e -> performLogout());
        cargarReservas();
    }
    
    private boolean validarDni(String dni) {
        return dni.matches("\\d{8}");  
    }
    
    public void cargarReservas() {
        List<String[]> data = model.getReservas();
        ReservasTableModel tableModel = new ReservasTableModel();
        

        for (String[] reserva : data) {
            tableModel.addReserva(reserva[0], reserva[1], reserva[2], reserva[3], reserva[4], reserva[5], reserva[6]);
        }
        frame.getTblReservas().setModel(tableModel);
    }
    
    
    public void buscarReserva() {
        String dni = frame.getTxtDni().getText();
        if (!validarDni(dni)) {
            frame.displayErrorMessage("DNI invalido. Debe contener 8 digitos numericos");
            return;
        }
        List<String[]> data = model.getReservasPorDni(dni);
        ReservasTableModel tableModel = new ReservasTableModel();

        for (String[] reserva : data) {
            tableModel.addReserva(reserva[0], reserva[1], reserva[2], reserva[3], reserva[4], reserva[5], reserva[6]);
        }
        frame.getTblReservas().setModel(tableModel);
    }
    
    private void abrirCrudReservas() {
        ReservaCrudModel reservaCrudModel = new ReservaCrudModel();
        ReservaCrudFrame reservaCrudFrame = new ReservaCrudFrame();
        new ReservasController(reservaCrudFrame, reservaCrudModel, userRole);
        reservaCrudFrame.setVisible(true);
        frame.dispose();
    }
     
    private void abrirCrudClientes() {
        ClienteCrudModel clienteCrudModel = new ClienteCrudModel();
        ClienteCrudFrame clienteCrudFrame = new ClienteCrudFrame();
        new ClienteCrudController(clienteCrudFrame, clienteCrudModel, userRole);
        clienteCrudFrame.setVisible(true);
        frame.dispose();
    }
    
    private void showLoginScreen() {
        LoginFrame loginFrame = new LoginFrame();
        LoginModel loginModel = new LoginModel();
        new LoginController(loginFrame, loginModel, new App());
        loginFrame.setVisible(true);
    }
     
    private void performLogout() {
        frame.dispose();
        showLoginScreen();
    }
    
}

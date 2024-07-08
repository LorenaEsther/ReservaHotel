package Controller;

//Base 
import Base.App;

//Model
import Model.HabitacionCrudModel;
import Model.ReservaCrudModel;
import Model.LoginModel;
import Model.ClienteCrudModel;
import Model.ClienteCrudModel;
import Model.ReservaModel;

//View
import View.AdministradorFrame;
import View.HabitacionesTableModel;
import View.LoginFrame;
import View.HabitacionesFrame;
import View.ClienteCrudFrame;
import View.ReservasTableModel;
import View.ReservaCrudFrame;

import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class AdministradorController {

    private AdministradorFrame frame;
    private ReservaCrudModel model;
    private int usuarioID;

    public AdministradorController(AdministradorFrame frame, ReservaCrudModel model, int usuarioID) {
        this.frame = frame;
        this.model = model;
        this.usuarioID = usuarioID;

        initController();
    }

    private void initController() {
        cargarReservas();
        frame.getBtnBuscar().addActionListener(e -> buscarReserva());
        frame.getBtnSalir().addActionListener(e -> performLogout());
        frame.getBtnRetroceder().addActionListener(e -> goBackLogin());
        frame.getBtnCrudReservas().addActionListener(e -> abrirCrudReservas());
        frame.getBtnCrudClientes().addActionListener(e -> abrirCrudClientes());
        frame.getBtnCrudHabitaciones().addActionListener(e -> abrirCrudHabitacion());

    }

    private boolean validarDni(String dni) {
        return dni.matches("\\d{8}"); // Solo tengo 8 digitos
    }

    private void buscarReserva() {
        String dni = frame.getTxtDni().getText();

        if (!validarDni(dni)) {
            frame.displayErrorMessage("DNI invalido. Debe contener 8 digitos numericos");
            return;
        }

        var reservas = model.getReservasPorDni(dni);
        ReservasTableModel tableModel = new ReservasTableModel();

        for (String[] reserva : reservas) {
            tableModel.addReserva(reserva[0], reserva[1], reserva[2], reserva[3], reserva[4], reserva[5], reserva[6]);
        }
        frame.getTblReservas().setModel(tableModel);
    }

    public void cargarReservas() {
        List<String[]> data = model.getReservas();
        ReservasTableModel tableModel = new ReservasTableModel();

        for (String[] reserva : data) {
            tableModel.addReserva(reserva[0], reserva[1], reserva[2], reserva[3], reserva[4], reserva[5], reserva[6]);
        }
        frame.getTblReservas().setModel(tableModel);
    }
    
    private void abrirCrudReservas() {
        ReservaCrudModel reservaCrudModel = new ReservaCrudModel();
        ReservaCrudFrame reservaCrudFrame = new ReservaCrudFrame();
        new ReservasController(reservaCrudFrame, reservaCrudModel);
        reservaCrudFrame.setVisible(true);
        frame.dispose();
    }
    
    private void abrirCrudClientes() {
        ClienteCrudModel clienteCrudModel = new ClienteCrudModel();
        ClienteCrudFrame clienteCrudFrame = new ClienteCrudFrame();
        new ClienteCrudController(clienteCrudFrame, clienteCrudModel);
        clienteCrudFrame.setVisible(true);
        frame.dispose();
    }
    
    private void abrirCrudHabitacion() {
        HabitacionCrudModel habitacionCrudModel = new HabitacionCrudModel();
        HabitacionesFrame habitacionesCrudFrame = new HabitacionesFrame();
        new HabitacionController(habitacionesCrudFrame, habitacionCrudModel);
        habitacionesCrudFrame.setVisible(true);
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
    
    private void goBackLogin() {
        frame.dispose();
        showLoginScreen();
    }
    
}

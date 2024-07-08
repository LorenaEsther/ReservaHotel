
package Controller;

//Base 
import Base.App;

//Model
import Model.ReservaCrudModel;
import Model.LoginModel;  
import Model.HabitacionCrudModel;
import Model.RegistroUsuarioModel;

//View
import View.GerenteFrame; 
import View.LoginFrame;
import View.HabitacionesFrame; 
import View.RegistroUsuarioFrame;
import View.ReservasTableModel;
import java.util.List;


public class GerenteController {
    private GerenteFrame frame;
    private ReservaCrudModel model;

    public GerenteController(GerenteFrame frame, ReservaCrudModel model) {
        this.frame = frame;
        this.model = model;
        initController();
    }
    
    private void initController() {
        frame.getBtnBuscar().addActionListener(e -> buscarReserva());
        frame.getBtnCrudHabitaciones().addActionListener(e -> abrirCrudHabitaciones());
        frame.getBtnCrudUsuarios().addActionListener(e -> abrirCrudUsuarios()); 
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
    
    private void abrirCrudHabitaciones() {
        HabitacionesFrame frameHabitaciones = new HabitacionesFrame();
        HabitacionCrudModel modelHabitaciones = new HabitacionCrudModel(); 
        HabitacionController controller = new HabitacionController(frameHabitaciones, modelHabitaciones); 
        frameHabitaciones.setVisible(true);
        frame.dispose();
    }
    
    private void abrirCrudUsuarios() {
        RegistroUsuarioFrame frameUsuarios = new RegistroUsuarioFrame();
        RegistroUsuarioModel modelUsuario = new RegistroUsuarioModel(); 
        RegistroUsuarioController controller = new RegistroUsuarioController(frameUsuarios, modelUsuario); 
        frameUsuarios.setVisible(true);
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

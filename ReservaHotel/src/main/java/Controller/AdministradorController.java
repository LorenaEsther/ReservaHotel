
package Controller;

//Base 
import Base.App;

//Model
import Model.HabitacionCrudModel;
import Model.ReservaCrudModel;
import Model.LoginModel;
import Model.ClienteCrudModel;
import Model.ClienteCrudModel;

//View
import View.AdministradorFrame;
import View.HabitacionesTableModel;
import View.LoginFrame;
import View.HabitacionesFrame;
import View.ClienteFrame;

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
    
    private void initcontroller(){
        cargarReservas();
        
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
        
        var reservas = model.getReservas()
    }
    
    
}

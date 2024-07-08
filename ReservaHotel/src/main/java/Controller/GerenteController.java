
package Controller;

//Base 
import Base.App;

//Model
import Model.ReservaCrudModel;
import Model.LoginModel;
import Model.ClienteCrudModel; 

//View
import View.GerenteFrame;
import View.HabitacionesTableModel;
import View.LoginFrame;
import View.HabitacionesFrame;
import View.ClienteFrame;
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
        cargarReservas();
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
        String dni=frame.getTxtDni().getText();
        List<String[]> data = model.getReservasPorDni(dni);
        ReservasTableModel tableModel = new ReservasTableModel();

        for (String[] reserva : data) {
            tableModel.addReserva(reserva[0], reserva[1], reserva[2], reserva[3], reserva[4], reserva[5], reserva[6]);
        }
        frame.getTblReservas().setModel(tableModel);
    }
    
}

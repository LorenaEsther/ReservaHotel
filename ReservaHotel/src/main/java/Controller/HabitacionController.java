 
package Controller;

import Model.Habitacion;
import Model.HabitacionCrudModel;
import View.HabitacionesFrame;


public class HabitacionController {
    private HabitacionesFrame frame;
    private Habitacion model; 
    private HabitacionCrudModel crudModel; 
    
    public void initController(){ 
        frame.getBtnNuevo().addActionListener(e -> agregarHabitacion());
        frame.getBtnEditar().addActionListener(e -> actualizarHabitacion());
        frame.getBtnEliminar().addActionListener(e -> buscarHabitacion());
        frame.getBtnBuscar().addActionListener(e -> eliminarHabitacion());
        cargarHabitaciones();
    } 
    
    private void cargarHabitaciones(){
        var data = model.g(usuarioID);
        HistorialTableModel tableModel = new HistorialTableModel();
        
        for(String[] row : data){
            tableModel.addHistorial(row[0], row[1], row[2], row[3]);
        }
        
        frame.getTblHistorial().setModel(tableModel);
    }
    
    private void agregarHabitacion(){
        
    }
    
    private void actualizarHabitacion(){
        
    }
    private void eliminarHabitacion(){
        
    }
    private void buscarHabitacion(){
        
    }
}

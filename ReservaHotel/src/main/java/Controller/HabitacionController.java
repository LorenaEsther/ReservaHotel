 
package Controller;

import Model.Habitacion;
import View.HabitacionesFrame;


public class HabitacionController {
    private HabitacionesFrame frame;
    private Habitacion model; 
    
    public void initController(){ 
        frame.getBtnNuevo().addActionListener(e -> agregarHabitacion());
        frame.getBtnEditar().addActionListener(e -> actualizarHabitacion());
        frame.getBtnEliminar().addActionListener(e -> buscarHabitacion());
        frame.getBtnBuscar().addActionListener(e -> eliminarHabitacion());
        cargarHabitaciones();
    } 
    
    private void cargarHabitaciones(){
        
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

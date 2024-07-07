package Controller;

import Model.Reserva;
import Model.ReservaCrudModel;
import View.ReservasFrame;
import View.ReservasTableModel;
import java.util.List;

public class ReservasController {

    private ReservasFrame frame;
    private ReservaCrudModel model;

    public ReservasController(ReservasFrame frame, ReservaCrudModel model) {
        this.frame = frame;
        this.model = model;
        initController();
    }

    private void initController() {
        /*frame.getBtnAgregar().setEnabled(false);
        frame.getBtnBuscar().addActionListener(e -> buscarHabitacion());*/
        frame.getBtnEditar().addActionListener(e -> makeFieldsEditable(true,true));
        /*frame.getBtnActualizar().addActionListener(e -> guardarHabitacion(true));
        frame.getBtnAgregar().addActionListener(e -> guardarHabitacion(false));
        frame.getBtnEliminar().addActionListener(e -> eliminarHabitacion());*/
        frame.getBtnRetroceder().addActionListener(e -> retrocederFrame());
        cargarReserva();
    }

    public void cargarReserva() {
        List<String[]> data = model.getReservas();
        ReservasTableModel tableModel = new ReservasTableModel();

        for (String[] reserva : data) {
            tableModel.addReserva(reserva[0], reserva[1], reserva[2], reserva[3], reserva[4], reserva[5]);
        }
        frame.getTblReserva().setModel(tableModel);
    }
    
    private void guardarReserva(boolean isUpdate) {
        try {
            int numero = Integer.parseInt(frame.getTxtNumero().getText());
            double precio = Double.parseDouble(frame.getTxtPrecio().getText());
            String tipo = (String) frame.getCbxTipo().getSelectedItem();
            String estado = (String) frame.getCbxEstado().getSelectedItem();

            Habitacion habitacion = new Habitacion(numero, tipo, precio, estado);
            boolean success;

            if (isUpdate) {
                success = model.actualizarHabitacion(habitacion);
            } else {
                success = model.agregarHabitacion(habitacion);
            }

            if (success) {
                frame.displaySucessMessage(isUpdate ? "Habitación actualizada correctamente." : "Habitación agregada correctamente.");
                makeFieldsEditable(false);
                cargarHabitaciones();
            } else {
                frame.displayErrorMessage(isUpdate ? "Error actualizando la habitación." : "Error agregando la habitación.");
            }

        } catch (NumberFormatException e) {
            frame.displayErrorMessage("Por favor, ingrese números válidos.");
        } catch (Exception e) {
            frame.displayErrorMessage("Ocurrió un error inesperado.");
        }
    }
    
/*
    private void eliminarHabitacion() {
        int numero = Integer.parseInt(frame.getTxtNumero().getText());
        if (model.eliminarHabitacion(numero)) {
            frame.displaySucessMessage("Habitacion Eliminado Correctamente");
        } else {
            frame.displayErrorMessage("Error Eliminado a la Habitacion");
        }
    }

    private void buscarHabitacion() {
        int numero = Integer.parseInt(frame.getTxtNumero().getText());
        Habitacion habitacion = model.buscarPorNumero(numero);
        if (habitacion != null) {
            frame.getTxtPrecio().setText(String.valueOf(habitacion.getPrecio()));
            frame.getCbxTipo().setSelectedItem(habitacion.getTipo());
            frame.getCbxEstado().setSelectedItem(habitacion.getEstado());
            makeFieldsEditable(false);
        } else {
            frame.displayErrorMessage("Habitación no encontrada");
        }
    }
*/
    private void retrocederFrame() {
        frame.dispose();
        /*App.getInstancia().launchIntefaceBaseOnRole(usuarioID, role);*/
    }
 
    private void makeFieldsEditable(boolean editable, boolean button) {
        frame.getTxtDniCliente().setEditable(editable);
        frame.getTxtHabitacion().setEditable(editable); 
        frame.getCbxEstado().setEnabled(editable); 
        frame.getJdcFechaInicio().setEnabled(editable);
        frame.getJdcFechaFin().setEnabled(editable);
        
        frame.getBtnActualizar().setEnabled(button);
        frame.getBtnAgregar().setEnabled(button);
    }
 
    private void makeFieldsEditable(boolean editable) {
        makeFieldsEditable(editable, false);
    } 
    
}

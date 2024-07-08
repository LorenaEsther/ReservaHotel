package Controller;

import Model.Reserva;
import Model.ReservaCrudModel;
import View.ReservasFrame;
import View.ReservasTableModel;
import java.util.Date;
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
        frame.getBtnEditar().addActionListener(e -> makeFieldsEditable(true, true));
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
            String dni = frame.getTxtDniCliente().getText();
            int habitacionNumero = Integer.parseInt(frame.getTxtHabitacion().getText());
            Date fechaInicio = frame.getJdcFechaInicio().getDate();
            Date fechaFin = frame.getJdcFechaFin().getDate();
            String estado = (String) frame.getCbxEstado().getSelectedItem();

            Reserva reserva = new Reserva(0, 0, habitacionNumero, fechaInicio, fechaFin, estado); // Assuming reservaID and clienteID will be set in agregarReservas
            boolean success;

            if (isUpdate) {
                // Implement or call the method for updating reservation if necessary
                // success = model.actualizarReserva(reserva, dni);
                success=false;
            } else {
                success = model.agregarReservas(reserva, dni);
            }

            if (success) {
                frame.displaySucessMessage(isUpdate ? "Reserva actualizada correctamente." : "Reserva agregada correctamente.");
                makeFieldsEditable(false);
                cargarReserva();  
            } else {
                frame.displayErrorMessage(isUpdate ? "Error actualizando la reserva." : "Error agregando la reserva.");
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

package Controller;

import Model.ReservaModel;
import Model.ReservaCrudModel;
import View.AdministradorFrame;
import View.GerenteFrame;
import View.RecepcionistaFrame;
import View.ReservaCrudFrame;
import View.ReservasTableModel;
import java.util.Date;
import java.util.List;

public class ReservasController {

    private ReservaCrudFrame frame;
    private ReservaCrudModel model;
    private String userRole;

    public ReservasController(ReservaCrudFrame frame, ReservaCrudModel model, String userRole) {
        this.frame = frame;
        this.model = model;
        this.userRole = userRole;
        initController();
    }

    private void initController() {
        frame.getBtnAgregar().setEnabled(false);
        frame.getBtnBuscar().addActionListener(e -> buscarReserva());
        frame.getBtnEditar().addActionListener(e -> makeFieldsEditable(true, true));
        frame.getBtnActualizar().addActionListener(e -> guardarReserva(true));
        frame.getBtnAgregar().addActionListener(e -> guardarReserva(false));
        frame.getBtnEliminar().addActionListener(e -> eliminarReserva());
        frame.getBtnRetroceder().addActionListener(e -> goBack());
        cargarReserva();
    }

    public void cargarReserva() {
        List<String[]> data = model.getReservas();
        ReservasTableModel tableModel = new ReservasTableModel();

        for (String[] reserva : data) {
            tableModel.addReserva(reserva[0], reserva[1], reserva[2], reserva[3], reserva[4], reserva[5], reserva[6]);
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
 
            ReservaModel reserva = new ReservaModel(0, dni, 0, habitacionNumero, fechaInicio, fechaFin, estado); // Assuming reservaID and clienteID will be set in database methods

            boolean success;

            if (isUpdate) { 
                int reservaID = Integer.parseInt(frame.getTxtReservaID().getText()); 
                reserva.setReservaID(reservaID);  
                success = model.actualizarReservas(reserva);
            } else { 
                success = model.agregarReservas(reserva);
            }

            if (success) {
                frame.displaySucessMessage(isUpdate ? "Reserva actualizada correctamente." : "Reserva agregada correctamente.");
                makeFieldsEditable(false);
                cargarReserva();
                cleanFields();
            } else {
                frame.displayErrorMessage(isUpdate ? "Error actualizando la reserva." : "Error agregando la reserva.");
            }

        } catch (NumberFormatException e) {
            frame.displayErrorMessage("Por favor, ingrese números válidos.");
        } catch (Exception e) {
            frame.displayErrorMessage("Ocurrió un error inesperado.");
        }
    }


    
    private void eliminarReserva() {
        int numero = Integer.parseInt(frame.getTxtReservaID().getText());
        if (model.eliminarReservas(numero)) {
            frame.displaySucessMessage("Reserva Eliminado Correctamente");
            cargarReserva();
        } else {
            frame.displayErrorMessage("Reserva Eliminado a la Habitacion");
        }
        
    }

    private void buscarReserva() {
        int reservaID = Integer.parseInt(frame.getTxtReservaID().getText()); 
        ReservaModel reserva = model.buscarPorReservaID(reservaID);
        if (reserva != null) {
            frame.getTxtDniCliente().setText(reserva.getDni());
            frame.getTxtHabitacion().setText(String.valueOf(reserva.getHabitacionNumero()));
            frame.getJdcFechaInicio().setDate(reserva.getFechaInicio());
            frame.getJdcFechaFin().setDate(reserva.getFechaFin());
            frame.getCbxEstado().setSelectedItem(reserva.getEstado());
            makeFieldsEditable(false);
        } else {
            frame.displayErrorMessage("Reserva not found.");
        }
    }
     
    private void retrocederFrame() {
        frame.dispose();
        /*App.getInstancia().launchIntefaceBaseOnRole(usuarioID, role);*/
    }

    private void makeFieldsEditable(boolean editable, boolean button) {
        frame.getTxtDniCliente().setEnabled(editable);
        frame.getTxtHabitacion().setEnabled(editable);
        frame.getCbxEstado().setEnabled(editable);
        frame.getJdcFechaInicio().setEnabled(editable);
        frame.getJdcFechaFin().setEnabled(editable);
        frame.getBtnActualizar().setEnabled(button);
        frame.getBtnAgregar().setEnabled(button);
    }

    private void makeFieldsEditable(boolean editable) {
        makeFieldsEditable(editable, false);
    }

    private void cleanFields() {
        frame.getTxtDniCliente().setText("");
        frame.getTxtHabitacion().setText("");
        frame.getCbxEstado().setSelectedIndex(0);
        frame.getJdcFechaInicio().setDate(null);
        frame.getJdcFechaFin().setDate(null);
    }
    
    private void showAdminScreen(){
        ReservaCrudModel model = new ReservaCrudModel();
        AdministradorFrame frameAdmin = new AdministradorFrame();
        new AdministradorController(frameAdmin, model, userRole);
        frameAdmin.setVisible(true);
    }
    
    private void showReceptionistScreen(){
        ReservaCrudModel model = new ReservaCrudModel();
        RecepcionistaFrame frameRec = new RecepcionistaFrame();
        new RecepcionistaController(frameRec, model, userRole);
        frame.setVisible(true);
    }
    
    private void showManagerScreen(){
        ReservaCrudModel model = new ReservaCrudModel();
        GerenteFrame frameGerente = new GerenteFrame();
        new GerenteController(frameGerente, model, userRole);
        frameGerente.setVisible(true);
    }
    
    private void goBack() {
        frame.dispose();
        if ("recepcionista".equalsIgnoreCase(userRole)) {
            showReceptionistScreen();
        } else if ("administrador".equalsIgnoreCase(userRole)) {
            showAdminScreen();
        } else {
            showManagerScreen();
        }
    }

}

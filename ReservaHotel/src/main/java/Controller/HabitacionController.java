package Controller;

import Model.Habitacion;
import Model.HabitacionCrudModel;
import View.HabitacionesFrameForm;
import View.HabitacionesTableModel;
import java.util.List;

public class HabitacionController {

    private HabitacionesFrameForm frame;
    private HabitacionCrudModel model;

    public HabitacionController(HabitacionesFrameForm frame, HabitacionCrudModel model) {
        this.frame = frame;
        this.model = model;
    }

    public void initController() {
        frame.getBtnGuardar().setEnabled(false);
        frame.getBtnBuscar().addActionListener(e -> buscarHabitacion());
        frame.getBtnEditar().addActionListener(e -> makeFieldsEditable(true));
        frame.getBtnActualizar().addActionListener(e -> guardarHabitacion(true));
        frame.getBtnGuardar().addActionListener(e -> guardarHabitacion(false));
        frame.getBtnEliminar().addActionListener(e -> eliminarHabitacion());
        frame.getBtnRetroceder().addActionListener(e -> retrocederFrame());
        cargarHabitaciones();
    }

    public void cargarHabitaciones() {
        List<Habitacion> data = model.getHabitaciones();
        HabitacionesTableModel tableModel = new HabitacionesTableModel();

        for (Habitacion habitacion : data) {
            String numero = String.valueOf(habitacion.getNumero());  
            String tipo = habitacion.getTipo();
            String precio = String.format("%.2f", habitacion.getPrecio());  
            String estado = habitacion.getEstado();
 
            tableModel.addHabitacion(numero, tipo, precio, estado);
        }
        frame.getTblHabitaciones().setModel(tableModel);
    }

    private void guardarHabitacion(boolean isUpdate) {
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
        } else {
            frame.displayErrorMessage("Habitación no encontrada");
        }
    }

    private void retrocederFrame() {
        frame.dispose();
        /*App.getInstancia().launchIntefaceBaseOnRole(usuarioID, role);*/
    }

    private void makeFieldsEditable(boolean editable) {
        frame.getTxtPrecio().setEditable(editable);
        frame.getCbxEstado().setEnabled(editable);
        frame.getCbxTipo().setEnabled(editable);
    }
}

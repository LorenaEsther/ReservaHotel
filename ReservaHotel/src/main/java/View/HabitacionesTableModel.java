package View;

import javax.swing.table.DefaultTableModel;

public class HabitacionesTableModel extends DefaultTableModel {

    public HabitacionesTableModel() {
        super(new Object[]{"Tipo", "Precio", "Estado"}, 0);
    }

    public void addHistorial(String fechaVisita, String diagnostico, String tratamiento, String comentarios) {
        super.addRow(new Object[]{fechaVisita, diagnostico, tratamiento, comentarios}
        );
    }
}

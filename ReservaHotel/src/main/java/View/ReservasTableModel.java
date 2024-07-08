package View;

import javax.swing.table.DefaultTableModel;

public class ReservasTableModel extends DefaultTableModel {

    public ReservasTableModel() {
        super(new Object[]{"Id","Cliente","Dni", "HabitacionNumero", "FechaInicio","FechaFin","Estado"}, 0);
    }

    public void addReserva(String id,String Cliente, String Dni, String HabitacionNumero, String FechaInicio, String FechaFin,String Estado) {
        super.addRow(new Object[]{id,Cliente, Dni, HabitacionNumero,FechaInicio, FechaFin,Estado}
        );
    }
}

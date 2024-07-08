package View;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AdministradorFrame extends javax.swing.JFrame {

    public AdministradorFrame() {
        initComponents();
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnCrudClientes() {
        return btnCrudClientes;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JButton getBtnVerReservas() {
        return btnCrudReservas;
    }

    public JTable getTblReservas() {
        return tblReservas;
    }

    public JTextField getTxtDni() {
        return txtDni;
    }

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error Login", JOptionPane.ERROR_MESSAGE);
    }

    public void displaySucessMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReservas = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        txtDni = new javax.swing.JTextField();
        btnCrudReservas = new javax.swing.JButton();
        btnCrudClientes = new javax.swing.JButton();
        btnCrudHabitaciones = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RESERVA TELOS");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 21, 207, -1));

        tblReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblReservas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 711, 379));

        btnBuscar.setText("Buscar");
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));
        getContentPane().add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 98, -1));

        btnCrudReservas.setText("Crud Reservas");
        getContentPane().add(btnCrudReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, -1));

        btnCrudClientes.setText("Crud Clientes");
        getContentPane().add(btnCrudClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, -1, -1));

        btnCrudHabitaciones.setText("Crud Habitaciones");
        getContentPane().add(btnCrudHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, -1, -1));

        btnSalir.setText("Salir");
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, -1, -1));

        jLabel2.setText("DNI");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCrudClientes;
    private javax.swing.JButton btnCrudHabitaciones;
    private javax.swing.JButton btnCrudReservas;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReservas;
    private javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}

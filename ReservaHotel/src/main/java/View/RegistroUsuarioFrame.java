
package View;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author pc
 */
public class RegistroUsuarioFrame extends javax.swing.JFrame {

    public RegistroUsuarioFrame() {
        initComponents();
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JComboBox<String> getCbxRol() {
        return cbxRol;
    }

    public JTextField getjTextField6() {
        return jTextField6;
    }

    public JPasswordField getPswPassword() {
        return pswPassword;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JTextField getTxtDni() {
        return txtDni;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbxRol = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        pswPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 102));
        jLabel1.setText("REGISTRAR NUEVOS USUARIOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(228, 38, -1, -1));

        jLabel2.setText("DNI: ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 136, -1, -1));

        jLabel3.setText("Nombre:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 188, -1, -1));

        jLabel4.setText("Apellido:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 243, -1, -1));

        jLabel5.setText("Correo:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 313, -1, -1));

        jLabel6.setText("Contraseña:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 382, -1, -1));

        jLabel7.setText("FechaNac:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 444, -1, -1));
        getContentPane().add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 132, 218, -1));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 184, 218, -1));
        getContentPane().add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 239, 218, -1));
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 309, 218, -1));
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 440, 218, -1));

        jLabel8.setText("Rol:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 136, -1, -1));

        getContentPane().add(cbxRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 131, 105, -1));

        btnRegistrar.setText("Registrar");
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 254, 105, -1));

        btnLimpiar.setText("Limpiar");
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 343, 105, -1));

        btnSalir.setText("Salir");
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 440, 105, -1));
        getContentPane().add(pswPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 220, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbxRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JPasswordField pswPassword;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

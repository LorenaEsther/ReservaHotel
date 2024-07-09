package Controller;
//Model

import Model.RegistroUsuarioModel;
import Model.ReservaCrudModel;
import View.GerenteFrame;

//View
import View.RegistroUsuarioFrame;

// Utils
import java.util.Date;
import java.util.List;

public class RegistroUsuarioController {

    private RegistroUsuarioFrame frame;
    private RegistroUsuarioModel model;
    private List<String[]> roles;
    private String userRole;

    public RegistroUsuarioController(RegistroUsuarioFrame view, RegistroUsuarioModel model, String userRole) {
        this.frame = view;
        this.model = model;
        initController();
    }

    private void initController() {
        loadRoles();
        frame.getBtnRegistrar().addActionListener(e -> {registrar(); clearFields();});
        frame.getBtnLimpiar().addActionListener(e -> clearFields());
        frame.getBtnSalir().addActionListener(e -> salir());
        frame.getBtnRetroceder().addActionListener(e -> goBack());

    }

    private void clearFields() {
        frame.getTxtCorreo().setText("");
        frame.getPswPassword().setText("");
        frame.getCbxRol().setSelectedIndex(0);
    }

    private void loadRoles() {
        roles = List.of(
                new String[]{"1", "Administrador"},
                new String[]{"2", "Recepcionista"},
                new String[]{"3", "Gerente"}
        );

        frame.getCbxRol().removeAllItems();
        for (String[] rol : roles) {
            frame.getCbxRol().addItem(rol[1]);
        }
    }

    private void registrar() {
        String correo = frame.getTxtCorreo().getText();
        String password = new String(frame.getPswPassword().getPassword());
        int rolIndex = frame.getCbxRol().getSelectedIndex();

        if (rolIndex >= 0) {
            String rolId = roles.get(rolIndex)[0];
            String rolName = roles.get(rolIndex)[1];

            if (model.registerUser(correo, password, rolName)) {
                frame.displaySucessMessage("Usuario registrado exitosamente");
            } else {
                frame.displayErrorMessage("Error al registrar usuario");
            }
        } else {
            frame.displayErrorMessage("Debe seleccionar un rol.");
        }
    }
    
    private void salir() {
        frame.dispose();
    }
    
    private void showManagerScreen(){
        ReservaCrudModel model = new ReservaCrudModel();
        GerenteFrame frameGerente = new GerenteFrame();
        new GerenteController(frameGerente, model, userRole);
        frameGerente.setVisible(true);
    }
    
    private void goBack(){
        frame.dispose();
        showManagerScreen();
    }
    

}

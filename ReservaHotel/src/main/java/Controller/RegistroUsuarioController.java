package Controller;
//Model

import Model.RegistroUsuarioModel;

//View
import View.RegistroUsuarioFrame;

// Utils
import java.util.Date;
import java.util.List;

public class RegistroUsuarioController {

    private RegistroUsuarioFrame view;
    private RegistroUsuarioModel model;
    private List<String[]> roles;

    public RegistroUsuarioController(RegistroUsuarioFrame view, RegistroUsuarioModel model) {
        this.view = view;
        this.model = model;
        initController();
    }

    private void initController() {
        loadRoles();
        view.getBtnRegistrar().addActionListener(e -> {registrar(); clearFields();});
        view.getBtnLimpiar().addActionListener(e -> clearFields());
        view.getBtnSalir().addActionListener(e -> salir());

    }

    private void clearFields() {
        view.getTxtCorreo().setText("");
        view.getPswPassword().setText("");
        view.getCbxRol().setSelectedIndex(0);
    }

    private void loadRoles() {
        roles = List.of(
                new String[]{"1", "Administrador"},
                new String[]{"2", "Recepcionista"},
                new String[]{"3", "Gerente"}
        );

        view.getCbxRol().removeAllItems();
        for (String[] rol : roles) {
            view.getCbxRol().addItem(rol[1]);
        }
    }

    private void registrar() {
        String correo = view.getTxtCorreo().getText();
        String password = new String(view.getPswPassword().getPassword());
        int rolIndex = view.getCbxRol().getSelectedIndex();

        if (rolIndex >= 0) {
            String rolId = roles.get(rolIndex)[0];
            String rolName = roles.get(rolIndex)[1];

            if (model.registerUser(correo, password, rolName)) {
                view.displaySucessMessage("Usuario registrado exitosamente");
            } else {
                view.displayErrorMessage("Error al registrar usuario");
            }
        } else {
            view.displayErrorMessage("Debe seleccionar un rol.");
        }
    }
    
    private void salir() {
        view.dispose();
    }

}

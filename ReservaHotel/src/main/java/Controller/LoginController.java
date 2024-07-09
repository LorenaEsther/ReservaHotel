package Controller;
//Base

import Base.AppInterface;

//View
import View.LoginFrame;

//Model
import Model.LoginModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private LoginFrame loginFrame;
    private LoginModel loginModel;
    private AppInterface appInterface;

    public LoginController(LoginFrame loginFrame, LoginModel loginModel, AppInterface appInterface) {
        this.loginFrame = loginFrame;
        this.loginModel = loginModel;
        this.appInterface = appInterface;
        initController();
    }

    private void initController() {  // Añadido
        loginFrame.getBtnIngresar().addActionListener(new LoginListener());
    }

    private boolean validarUsuario(String username) {
        return username != null && !username.trim().isEmpty();
    }

    private boolean validarContrasena(String password) {
        return password != null && password.length() >= 6;
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginFrame.getTxtUsername().getText();
            char[] passwordArray = loginFrame.getPswPassword().getPassword();

            try {
                String password = new String(passwordArray);

                if (!validarUsuario(username)) {
                    loginFrame.displayErrorMessage("El nombre de usuario no puede estar vacio");
                    return;
                }
                if (!validarContrasena(password)) {
                    loginFrame.displayErrorMessage("La contraseña debe contener al menos 6 caracteres");
                    return;
                }

                String[] loginResult = loginModel.authenticate(username, password);
                if (loginResult != null) {
                    loginFrame.setVisible(false);
                    appInterface.onLoginSuccess(Integer.parseInt(loginResult[0]), loginResult[1]);
                } else {
                    loginFrame.displayErrorMessage("Usuario o Contraseña incorrecta o error de conexion");
                    appInterface.onLoginFailed(username);
                }
            } finally {
                java.util.Arrays.fill(passwordArray, '0');
            }
        }
    }
}

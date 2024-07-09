package Base;

import Controller.AdministradorController;
import Controller.GerenteController;
import Controller.LoginController;
import Controller.RecepcionistaController;
import Model.LoginModel;
import Model.RegistroUsuarioModel;
import Model.ReservaCrudModel;
import View.AdministradorFrame;
import View.GerenteFrame;
import View.LoginFrame;
import View.RecepcionistaFrame;

public class App implements AppInterface {

    private static App instancia = new App();

    public static App getInstancia() {
        return instancia;

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            LoginModel loginModel = new LoginModel();
            new LoginController(loginFrame, loginModel, new App());
            loginFrame.setVisible(true);
        });

    }

    @Override
    public void onLoginSuccess(int usuarioID, String rol) {
        if ("Gerente".equals(rol)) {
            launchGerenteInterface(usuarioID);
        } else if ("Administrador".equals(rol)) {
            launchAdminInterface(usuarioID);
        } else if ("Recepcionista".equals(rol)) {
            launchRecepcionistaInterface(usuarioID);
        }
    }

    private void launchGerenteInterface(int usuarioID) {
        ReservaCrudModel model = new ReservaCrudModel();
        GerenteFrame frame = new GerenteFrame();
        new GerenteController(frame,model );
        frame.setVisible(true);
    }

    private void launchAdminInterface(int usuarioID) {
        ReservaCrudModel model = new ReservaCrudModel();
        AdministradorFrame frame = new AdministradorFrame();
        new AdministradorController(frame, model, usuarioID);
        frame.setVisible(true);

    }

    private void launchRecepcionistaInterface(int usuarioID) {
        ReservaCrudModel model = new ReservaCrudModel();
        RecepcionistaFrame frame = new RecepcionistaFrame();
        RecepcionistaController controller = new RecepcionistaController(frame, model);
        frame.setVisible(true);
    }

    @Override
    public void onLoginFailed(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onLogout(int usuarioID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

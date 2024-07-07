 
import Controller.RegistroUsuarioController;
import Model.RegistroUsuarioModel;
import View.RegistroUsuarioFrame;

public class TestUsuarios {

    public static void main(String[] args) { 
        RegistroUsuarioFrame frame = new RegistroUsuarioFrame();
        RegistroUsuarioModel model = new RegistroUsuarioModel();
 
        RegistroUsuarioController controller = new RegistroUsuarioController(frame, model);

 
        frame.setVisible(true);
    }
}

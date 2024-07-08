 
import Controller.ClienteController;
import Model.ClienteCrudModel;
import View.ClienteFrame;

public class TestUsuarios {

    public static void main(String[] args) { 
        ClienteFrame frame = new ClienteFrame();
        ClienteCrudModel model = new ClienteCrudModel();
 
        ClienteController controller = new ClienteController(frame, model);

 
        frame.setVisible(true);
    }
}

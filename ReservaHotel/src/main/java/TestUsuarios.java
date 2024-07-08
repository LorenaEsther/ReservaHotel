 
import Controller.ClienteCrudController;
import Model.ClienteCrudModel;
import View.ClienteCrudFrame;

public class TestUsuarios {

    public static void main(String[] args) { 
        ClienteCrudFrame frame = new ClienteCrudFrame();
        ClienteCrudModel model = new ClienteCrudModel();
 
        ClienteCrudController controller = new ClienteCrudController(frame, model);

 
        frame.setVisible(true);
    }
}

 
import Controller.GerenteController;
import Model.ReservaCrudModel; 
import View.GerenteFrame; 

public class TestReservas {

    public static void main(String[] args) { 
        GerenteFrame frame = new GerenteFrame();
        ReservaCrudModel model = new ReservaCrudModel();
 
        GerenteController controller = new GerenteController(frame, model);
  
        frame.setVisible(true);
    }
}

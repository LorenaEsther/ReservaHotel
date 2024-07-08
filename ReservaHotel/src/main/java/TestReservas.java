 
import Controller.GerenteController;
import Controller.RecepcionistaController;
import Model.ReservaCrudModel; 
import View.GerenteFrame; 
import View.RecepcionistaFrame;

public class TestReservas {

    public static void main(String[] args) { 
        RecepcionistaFrame frame = new RecepcionistaFrame();
        ReservaCrudModel model = new ReservaCrudModel();
 
        RecepcionistaController controller = new RecepcionistaController(frame, model);
  
        frame.setVisible(true);
    }
}

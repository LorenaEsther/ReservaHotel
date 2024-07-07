 
import Controller.ReservasController;
import Model.ReservaCrudModel; 
import View.ReservasFrame;

public class TestReservas {

    public static void main(String[] args) { 
        ReservasFrame frame = new ReservasFrame();
        ReservaCrudModel model = new ReservaCrudModel();
 
        ReservasController controller = new ReservasController(frame, model);
  
        frame.setVisible(true);
    }
}

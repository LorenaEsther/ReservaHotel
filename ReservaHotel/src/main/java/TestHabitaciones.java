 
import Controller.HabitacionController;
import Model.HabitacionCrudModel;
import View.HabitacionesFrame;

public class TestHabitaciones {

    public static void main(String[] args) { 
        HabitacionesFrame frame = new HabitacionesFrame();
        HabitacionCrudModel model = new HabitacionCrudModel();
 
        HabitacionController controller = new HabitacionController(frame, model);

 
        frame.setVisible(true);
    }
}

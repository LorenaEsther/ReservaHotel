 
import Controller.HabitacionController;
import Model.HabitacionCrudModel;
import View.HabitacionesFrameForm;

public class TestHabitaciones {

    public static void main(String[] args) { 
        HabitacionesFrameForm frame = new HabitacionesFrameForm();
        HabitacionCrudModel model = new HabitacionCrudModel();
 
        HabitacionController controller = new HabitacionController(frame, model);
 
        controller.initController();
 
        frame.setVisible(true);
    }
}

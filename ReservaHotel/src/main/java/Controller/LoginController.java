
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
    }
    
    
}

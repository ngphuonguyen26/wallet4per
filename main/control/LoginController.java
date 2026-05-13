package main.control;
import main.view.Login;
import  java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginController {
    String txtUsername;
    String txtPassword;
    Login view;
    public LoginController(Login view) {
        this.view = view;

    }
}

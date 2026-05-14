package main;
import main.control.LoginController;
import main.view.*;
public class Main {
	public static void main(String args[])
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                     Login view = new Login();
                     LoginController login = new LoginController(view);
                     view.setVisible(true) ;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
}

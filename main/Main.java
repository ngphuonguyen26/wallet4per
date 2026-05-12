package main;
import main.view.*;
public class Main {
	public static void main(String args[])
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login log = new Login();
                    log.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
}

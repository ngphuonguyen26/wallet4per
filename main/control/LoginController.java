package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Login;
import control.RegisterController;
import view.UserGeneral;
import model.User;
import dao.UserDAO;
import view.Regist;
import javax.swing.*;

public class LoginController {

    private Login view;

    public LoginController(Login view) {
        this.view = view;

        addEvents();
    }

    private void addEvents() {

        view.getButton_Login().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        view.getButton_Register().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Regist re = new Regist();
                RegisterController register = new RegisterController(re);
                re.setVisible(true);
                view.dispose();
            }
        });

        view.getCheckBox_ShowPassword().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (view.getCheckBox_ShowPassword().isSelected()) {
                    view.getPasswordField_Password().setEchoChar((char) 0);
                } else {
                    view.getPasswordField_Password().setEchoChar('*');
                }
            }
        });
    }
    private void handleLogin() {
        String username = view.getUsername().trim();
        String password = view.getPassword().trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Hãy nhập đủ thông tin đăng nhập");

        return;
        }
        dao.UserDAO userDAO= new dao.UserDAO();
        User user = userDAO.login(username, password);
        if (user == null) {
            JOptionPane.showMessageDialog(view,"Sai tên đăng nhập hoặc mật khẩu");
        }
        else {
            UserGeneral formUser = new UserGeneral(user);
            new UserGeneralControl(formUser);
            formUser.setVisible(true);
            view.dispose();
        }
    }


}
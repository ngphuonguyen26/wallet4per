package main.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.UserDAO;
import main.view.Login;
import main.view.Regist;
import model.User;

public class RegisterController {

    private Regist view;
    private UserDAO userDAO;

    public RegisterController(Regist view) {
        this.view = view;
        this.userDAO = new UserDAO();

        addEvents();
    }

    private void addEvents() {

        view.getButton_Register().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        view.getButton_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                new LoginController(login);

                login.setVisible(true);
                view.dispose();
            }
        });
    }

    private void handleRegister() {

        String username = view.getUsername().trim();
        String fullName = view.getFullName().trim();
        String email = view.getEmail().trim();
        String password = view.getPassword().trim();

        if (username.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    view,
                    "Vui lòng nhập đầy đủ thông tin!"
            );
            return;
        }

        if (userDAO.isUsernameExist(username)) {
            JOptionPane.showMessageDialog(
                    view,
                    "Username đã tồn tại!"
            );
            return;
        }

        User user = new User();

        user.setUsername(username);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);

        boolean success = userDAO.register(user);

        if (success) {
            JOptionPane.showMessageDialog(
                    view,
                    "Đăng ký thành công!"
            );

            Login login = new Login();
            new LoginController(login);

            login.setVisible(true);
            view.dispose();

        } else {
            JOptionPane.showMessageDialog(
                    view,
                    "Đăng ký thất bại!"
            );
        }
    }
}
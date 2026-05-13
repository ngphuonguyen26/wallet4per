package main.control;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.UserDAO;
import main.view.ChangePassw;
import model.User;

public class ChangePasswordController {

    private ChangePassw view;
    private User currentUser;
    private UserDAO userDAO;

    public ChangePasswordController(ChangePassw view, User currentUser) {
        this.view = view;
        this.currentUser = currentUser;
        this.userDAO = new UserDAO();

        addEvents();
    }

    private void addEvents() {

        view.getButton_Confirm().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleChangePassword();
            }
        });

        view.getButton_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
    }

    private void handleChangePassword() {

        String oldPassword = view.getOldPassword().trim();
        String newPassword = view.getNewPassword().trim();

        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ mật khẩu!");
            return;
        }

        if (!oldPassword.equals(currentUser.getPassword())) {
            JOptionPane.showMessageDialog(view, "Mật khẩu cũ không đúng!");
            return;
        }

        boolean success = userDAO.changePassword(currentUser.getId(), newPassword);

        if (success) {
            currentUser.setPassword(newPassword);
            JOptionPane.showMessageDialog(view, "Đổi mật khẩu thành công!");
            goBack();
        } else {
            JOptionPane.showMessageDialog(view, "Đổi mật khẩu thất bại!");
        }
    }

    private void goBack() {

        CardLayout cl = (CardLayout) view.getMainChildForm().getLayout();

        cl.previous(view.getMainChildForm());

        view.getMainChildForm().remove(view.getContentPanePanel());

        view.getMainChildForm().revalidate();
        view.getMainChildForm().repaint();
    }
}
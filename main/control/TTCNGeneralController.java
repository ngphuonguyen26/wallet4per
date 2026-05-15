package control;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.UserDAO;
import view.ChangePassw;
import view.Login;
import view.TTCNGeneral;
import view.UserInfo;

public class TTCNGeneralController {

    private TTCNGeneral view;

    public TTCNGeneralController(TTCNGeneral view) {
        this.view = view;
        addEvents();
    }

    private void addEvents() {

        view.getButton_UserInfo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserInfo userInfo =
                        new UserInfo(
                                view.getMainChildForm(),
                                view.getCurrentUser()
                        );

                new UserInfoController(userInfo);

                openForm(userInfo);
            }
        });

        view.getButton_ChangePassword().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ChangePassw change =
                        new ChangePassw(view.getMainChildForm());

                new ChangePasswordController(
                        change,
                        view.getCurrentUser()
                );

                openForm(change);
            }
        });

        view.getButton_DeleteAccount().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int confirm = JOptionPane.showConfirmDialog(
                        view,
                        "Bạn có chắc muốn xóa tài khoản?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {

                    UserDAO userDAO = new UserDAO();

                    boolean success =
                            userDAO.deleteUser(
                                    view.getCurrentUser().getId()
                            );

                    if (success) {

                        JOptionPane.showMessageDialog(
                                view,
                                "Xóa tài khoản thành công!"
                        );

                        // Đóng toàn bộ cửa sổ hiện tại
                        java.awt.Window window =
                                javax.swing.SwingUtilities.getWindowAncestor(
                                        view.getMainChildForm()
                                );

                        if (window != null) {
                            window.dispose();
                        }

                        // Mở lại login
                        Login login = new Login();
                        new LoginController(login);

                        login.setVisible(true);
                    } else {

                        JOptionPane.showMessageDialog(
                                view,
                                "Xóa tài khoản thất bại!"
                        );
                    }
                }
            }
        });
    }

    private void openForm(JFrame form) {

        String id = String.valueOf(form.hashCode());

        view.getMainChildForm().add(
                form.getContentPane(),
                id
        );

        CardLayout cl =
                (CardLayout) view.getMainChildForm().getLayout();

        cl.show(view.getMainChildForm(), id);

        view.getMainChildForm().revalidate();
        view.getMainChildForm().repaint();
    }
}
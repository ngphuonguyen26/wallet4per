package main.control;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import main.view.Category;
import main.view.Login;
import main.view.StatisticsGeneral;
import main.view.TTCNGeneral;
import main.view.TransactionGeneral;
import main.view.UserGeneral;
import main.view.Wallet;

public class UserGeneralControl {

    private UserGeneral view;

    public UserGeneralControl(UserGeneral view) {
        this.view = view;
        addEvents();
    }

    private void addEvents() {

        view.getButton_UserProfile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TTCNGeneral ttcn = new TTCNGeneral(
                        view.getPanel_ChildForm(),
                        view.getCurrentUser()
                );
                new TTCNGeneralController(ttcn);

                openChildForm(ttcn);
            }
        });

        view.getButton_Wallet().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Wallet wallet = new Wallet();
                new WalletController(wallet, view.getCurrentUser().getId());
                openChildForm(wallet);
            }
        });

        view.getButton_Category().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Category category = new Category();

                // load dữ liệu
                new CategoryController(category);

                // mở form
                openChildForm(category);
            }
        });

        view.getButton_Transaction().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TransactionGeneral transaction =
                        new TransactionGeneral(view.getPanel_ChildForm(),view.getCurrentUser());

                openChildForm(transaction);
            }
        });

        view.getButton_Statistics().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StatisticsGeneral statistics =
                        new StatisticsGeneral(view.getPanel_ChildForm(),view.getCurrentUser());

                openChildForm(statistics);
            }
        });

        view.getButton_Logout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Login login = new Login();
                new LoginController(login);

                login.setVisible(true);
                view.dispose();
            }
        });
    }

    private void openChildForm(JFrame childFrame) {

        view.getPanel_ChildForm().removeAll();

        view.getPanel_ChildForm().add(
                childFrame.getContentPane(),
                "currentForm"
        );

        CardLayout cl =
                (CardLayout) view.getPanel_ChildForm().getLayout();

        cl.show(view.getPanel_ChildForm(), "currentForm");

        view.getPanel_ChildForm().revalidate();
        view.getPanel_ChildForm().repaint();
    }
}
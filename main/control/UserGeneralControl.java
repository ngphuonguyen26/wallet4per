package control;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.*;

public class UserGeneralControl {

    private UserGeneral view;

    public UserGeneralControl(UserGeneral view) {
        this.view = view;
        addEvents();
    }

    private void addEvents() {

        view.getButton_UserProfile().addActionListener(e -> {
            TTCNGeneral ttcn = new TTCNGeneral(
                    view.getPanel_ChildForm(),
                    view.getCurrentUser()
            );
            new TTCNGeneralController(ttcn);
            openChildForm(ttcn);
        });

        view.getButton_Wallet().addActionListener(e -> {
            Wallet wallet = new Wallet();
            // FIX: truyền userId vào WalletController
            new WalletController(wallet, view.getCurrentUser().getUserId());
            openChildForm(wallet);
        });

        view.getButton_Category().addActionListener(e -> {
            Category category = new Category();
            // FIX: truyền userId để lọc đúng danh mục hệ thống + riêng user
            new CategoryController(category, view.getCurrentUser().getUserId());
            openChildForm(category);
        });

        view.getButton_Transaction().addActionListener(e -> {
            TransactionGeneral transaction = new TransactionGeneral(
                    view.getPanel_ChildForm(),
                    view.getCurrentUser()
            );
            openChildForm(transaction);
        });

        view.getButton_Statistics().addActionListener(e -> {
            StatisticsGeneral statistics = new StatisticsGeneral(
                    view.getPanel_ChildForm(),
                    view.getCurrentUser()
            );
            openChildForm(statistics);
        });

        // FIX: thêm xử lý nút Quỹ tiết kiệm (trước đây bị bỏ quên)
        view.getButton_Fund().addActionListener(e -> {
            FundGeneral fund = new FundGeneral(
                    view.getPanel_ChildForm(),
                    view.getCurrentUser().getUserId()
            );
            openChildForm(fund);
        });

        view.getButton_Logout().addActionListener(e -> {
            Login login = new Login();
            new LoginController(login);
            login.setVisible(true);
            view.dispose();
        });
    }

    private void openChildForm(JFrame childFrame) {
        view.getPanel_ChildForm().removeAll();
        view.getPanel_ChildForm().add(childFrame.getContentPane(), "currentForm");

        CardLayout cl = (CardLayout) view.getPanel_ChildForm().getLayout();
        cl.show(view.getPanel_ChildForm(), "currentForm");

        view.getPanel_ChildForm().revalidate();
        view.getPanel_ChildForm().repaint();
    }
}
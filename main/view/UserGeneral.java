package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.User;

public class UserGeneral extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JPanel panel_ChildForm;

    private final JButton button_UserProfile;
    private final JButton button_Wallet;
    private final JButton button_Category;
    private final JButton button_Transaction;
    private final JButton button_Statistics;
    private final JButton button_Fund;
    private final JButton button_Logout;

    private final User currentUser;

    public UserGeneral(User user) {
        this.currentUser = user;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 150, 712, 450);
        setTitle("Quản Lý Chi Tiêu - " + user.getFullName());

        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel_Menu = new JPanel();
        getContentPane().add(panel_Menu, BorderLayout.WEST);
        panel_Menu.setLayout(new GridLayout(0, 1, 0, 0));

        button_UserProfile = new JButton("Thông tin cá nhân");
        button_UserProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_Menu.add(button_UserProfile);

        button_Wallet = new JButton("Ví");
        button_Wallet.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_Menu.add(button_Wallet);

        button_Category = new JButton("Danh mục");
        button_Category.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_Menu.add(button_Category);

        button_Transaction = new JButton("Giao dịch");
        button_Transaction.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_Menu.add(button_Transaction);

        button_Statistics = new JButton("Thống kê");
        button_Statistics.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_Menu.add(button_Statistics);

        button_Fund = new JButton("Quỹ tiết kiệm");
        button_Fund.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_Menu.add(button_Fund);

        button_Logout = new JButton("Đăng xuất");
        button_Logout.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_Menu.add(button_Logout);

        panel_ChildForm = new JPanel();
        getContentPane().add(panel_ChildForm, BorderLayout.CENTER);
        panel_ChildForm.setLayout(new CardLayout(0, 0));

        // Sự kiện được đặt trong UserGeneralControl — KHÔNG thêm ở đây
        // để tránh đăng ký listener 2 lần
    }

    public JPanel getPanel_ChildForm()      { return panel_ChildForm; }
    public JButton getButton_UserProfile()  { return button_UserProfile; }
    public JButton getButton_Wallet()       { return button_Wallet; }
    public JButton getButton_Category()     { return button_Category; }
    public JButton getButton_Transaction()  { return button_Transaction; }
    public JButton getButton_Statistics()   { return button_Statistics; }
    public JButton getButton_Fund()         { return button_Fund; }
    public JButton getButton_Logout()       { return button_Logout; }
    public User getCurrentUser()            { return currentUser; }
}
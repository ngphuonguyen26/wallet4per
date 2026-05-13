package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.CardLayout;
import model.User;
public class UserGeneral extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel_ChildForm;

	private JButton button_UserProfile;
	private JButton button_Wallet;
	private JButton button_Category;
	private JButton button_Transaction;
	private JButton button_Statistics;
	private JButton button_Logout;
	private User currentUser;
	public UserGeneral(User user) {
		this.currentUser = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 712, 450);

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

		button_Logout = new JButton("Đăng xuất");
		button_Logout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_Menu.add(button_Logout);

		JPanel panel_BlankTop = new JPanel();
		panel_Menu.add(panel_BlankTop);

		JPanel panel_BlankBottom = new JPanel();
		panel_Menu.add(panel_BlankBottom);

		panel_ChildForm = new JPanel();
		getContentPane().add(panel_ChildForm, BorderLayout.CENTER);
		panel_ChildForm.setLayout(new CardLayout(0, 0));
	}

	public JPanel getPanel_ChildForm() {
		return panel_ChildForm;
	}

	public JButton getButton_UserProfile() {
		return button_UserProfile;
	}

	public JButton getButton_Wallet() {
		return button_Wallet;
	}

	public JButton getButton_Category() {
		return button_Category;
	}

	public JButton getButton_Transaction() {
		return button_Transaction;
	}

	public JButton getButton_Statistics() {
		return button_Statistics;
	}

	public JButton getButton_Logout() {
		return button_Logout;
	}
	public User getCurrentUser() {
		return currentUser;
	}
}
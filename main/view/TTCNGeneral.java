package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.User;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;

public class TTCNGeneral extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel mainChildForm;

	private JButton button_UserInfo;
	private JButton button_ChangePassword;
	private JButton button_DeleteAccount;
	private User currentUser;
	public TTCNGeneral(JPanel childform,User user) {
		this.currentUser = user;
		this.mainChildForm = childform;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new GridLayout(4, 1, 0, 0));

		JPanel panel_Top = new JPanel();
		contentPane.add(panel_Top);

		panel_Top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_UserInfo = new JPanel();
		contentPane.add(panel_UserInfo);

		button_UserInfo = new JButton("Thông tin");
		button_UserInfo.setPreferredSize(new Dimension(200, 40));
		button_UserInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_UserInfo.add(button_UserInfo);

		JPanel panel_ChangePassword = new JPanel();
		contentPane.add(panel_ChangePassword);
		panel_ChangePassword.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		button_ChangePassword = new JButton("Đổi mật khẩu");
		button_ChangePassword.setPreferredSize(new Dimension(200, 40));
		button_ChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_ChangePassword.add(button_ChangePassword);

		JPanel panel_DeleteAccount = new JPanel();
		contentPane.add(panel_DeleteAccount);
		panel_DeleteAccount.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		button_DeleteAccount = new JButton("Xóa tài khoản");
		button_DeleteAccount.setPreferredSize(new Dimension(200, 40));
		button_DeleteAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_DeleteAccount.add(button_DeleteAccount);
	}

	public JPanel getMainChildForm() {
		return mainChildForm;
	}

	public JButton getButton_UserInfo() {
		return button_UserInfo;
	}

	public JButton getButton_ChangePassword() {
		return button_ChangePassword;
	}

	public JButton getButton_DeleteAccount() {
		return button_DeleteAccount;
	}
	public  User getCurrentUser() {
		return currentUser;
	}
}
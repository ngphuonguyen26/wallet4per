package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField_Username;
	private JPasswordField passwordField_Password;
	private JPanel panel;
	private JButton button_Login;
	private JButton button_Register;
	private JCheckBox checkBox_ShowPassword;

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 551, 276);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {35, 0, 0, 47, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[] {35, 14, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[] {
				0.0, 0.0, 0.0, 1.0, 0.0,
				1.0, 0.0, Double.MIN_VALUE, 0.0, 0.0
		};
		gbl_contentPane.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0};

		contentPane.setLayout(gbl_contentPane);

		JLabel laUsername = new JLabel("Tên đăng nhập:");
		laUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_laUsername = new GridBagConstraints();
		gbc_laUsername.gridwidth = 2;
		gbc_laUsername.ipady = 30;
		gbc_laUsername.insets = new Insets(0, 0, 5, 5);
		gbc_laUsername.gridx = 0;
		gbc_laUsername.gridy = 1;

		contentPane.add(laUsername, gbc_laUsername);

		textField_Username = new JTextField();

		GridBagConstraints gbc_textField_Username = new GridBagConstraints();
		gbc_textField_Username.gridwidth = 5;
		gbc_textField_Username.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Username.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Username.gridx = 3;
		gbc_textField_Username.gridy = 1;

		contentPane.add(textField_Username, gbc_textField_Username);
		textField_Username.setColumns(10);

		JLabel laPa = new JLabel("Mật khẩu:");
		laPa.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_laPa = new GridBagConstraints();
		gbc_laPa.gridwidth = 2;
		gbc_laPa.ipady = 30;
		gbc_laPa.insets = new Insets(0, 0, 5, 5);
		gbc_laPa.gridx = 0;
		gbc_laPa.gridy = 2;

		contentPane.add(laPa, gbc_laPa);

		passwordField_Password = new JPasswordField();

		GridBagConstraints gbc_passwordField_Password = new GridBagConstraints();
		gbc_passwordField_Password.gridwidth = 5;
		gbc_passwordField_Password.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_Password.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_Password.gridx = 3;
		gbc_passwordField_Password.gridy = 2;

		contentPane.add(passwordField_Password, gbc_passwordField_Password);
		passwordField_Password.setColumns(10);

		checkBox_ShowPassword = new JCheckBox("Hiện mật khẩu");
		checkBox_ShowPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));

		GridBagConstraints gbc_checkBox_ShowPassword = new GridBagConstraints();
		gbc_checkBox_ShowPassword.gridwidth = 2;
		gbc_checkBox_ShowPassword.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_ShowPassword.gridx = 8;
		gbc_checkBox_ShowPassword.gridy = 2;

		contentPane.add(checkBox_ShowPassword, gbc_checkBox_ShowPassword);

		panel = new JPanel();

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.weighty = 1.0;
		gbc_panel.ipady = 30;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.gridwidth = 10;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;

		contentPane.add(panel, gbc_panel);

		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));

		button_Login = new JButton("Đăng nhập");
		button_Login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(button_Login);

		button_Register = new JButton("Đăng kí");
		button_Register.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(button_Register);
	}

	public String getUsername() {
		return String.valueOf(textField_Username.getText());
	}

	public String getPassword() {
		return String.valueOf(passwordField_Password.getPassword());
	}

	public JButton getButton_Login() {
		return button_Login;
	}

	public JButton getButton_Register() {
		return button_Register;
	}

	public JCheckBox getCheckBox_ShowPassword() {
		return checkBox_ShowPassword;
	}

	public JPasswordField getPasswordField_Password() {
		return passwordField_Password;
	}
}
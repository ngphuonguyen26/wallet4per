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

import java.awt.Font;

public class Regist extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField textField_Username;
	private JTextField textField_FullName;
	private JTextField textField_Email;

	private JPasswordField passwordField_Password;

	private JPanel panel;

	private JButton button_Register;
	private JButton button_Exit;

	private JLabel label_FullName;
	private JLabel label_Email;
	private JLabel label_Password;

	public Regist() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 280);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0, 95, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[] {0, 39, 40, 38, 37, 51, 0};
		gbl_contentPane.columnWeights = new double[] {
				0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE
		};
		gbl_contentPane.rowWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE
		};

		contentPane.setLayout(gbl_contentPane);

		JLabel label_Username = new JLabel("Username:");
		label_Username.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Username = new GridBagConstraints();
		gbc_label_Username.insets = new Insets(0, 0, 5, 5);
		gbc_label_Username.gridx = 1;
		gbc_label_Username.gridy = 1;

		contentPane.add(label_Username, gbc_label_Username);

		textField_Username = new JTextField();

		GridBagConstraints gbc_textField_Username = new GridBagConstraints();
		gbc_textField_Username.insets = new Insets(0, 0, 5, 0);
		gbc_textField_Username.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Username.gridx = 3;
		gbc_textField_Username.gridy = 1;

		contentPane.add(textField_Username, gbc_textField_Username);

		textField_Username.setColumns(10);

		label_FullName = new JLabel("Tên:");
		label_FullName.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_FullName = new GridBagConstraints();
		gbc_label_FullName.insets = new Insets(0, 0, 5, 5);
		gbc_label_FullName.gridx = 1;
		gbc_label_FullName.gridy = 2;

		contentPane.add(label_FullName, gbc_label_FullName);

		textField_FullName = new JTextField();

		GridBagConstraints gbc_textField_FullName = new GridBagConstraints();
		gbc_textField_FullName.insets = new Insets(0, 0, 5, 0);
		gbc_textField_FullName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_FullName.gridx = 3;
		gbc_textField_FullName.gridy = 2;

		contentPane.add(textField_FullName, gbc_textField_FullName);

		textField_FullName.setColumns(10);

		label_Email = new JLabel("Email:");
		label_Email.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Email = new GridBagConstraints();
		gbc_label_Email.insets = new Insets(0, 0, 5, 5);
		gbc_label_Email.gridx = 1;
		gbc_label_Email.gridy = 3;

		contentPane.add(label_Email, gbc_label_Email);

		textField_Email = new JTextField();

		GridBagConstraints gbc_textField_Email = new GridBagConstraints();
		gbc_textField_Email.insets = new Insets(0, 0, 5, 0);
		gbc_textField_Email.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Email.gridx = 3;
		gbc_textField_Email.gridy = 3;

		contentPane.add(textField_Email, gbc_textField_Email);

		textField_Email.setColumns(10);

		label_Password = new JLabel("Password:");
		label_Password.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Password = new GridBagConstraints();
		gbc_label_Password.insets = new Insets(0, 0, 5, 5);
		gbc_label_Password.gridx = 1;
		gbc_label_Password.gridy = 4;

		contentPane.add(label_Password, gbc_label_Password);

		passwordField_Password = new JPasswordField();

		GridBagConstraints gbc_passwordField_Password = new GridBagConstraints();
		gbc_passwordField_Password.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_Password.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_Password.gridx = 3;
		gbc_passwordField_Password.gridy = 4;

		contentPane.add(passwordField_Password, gbc_passwordField_Password);

		panel = new JPanel();

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 5;

		contentPane.add(panel, gbc_panel);

		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));

		button_Register = new JButton("Đăng kí");
		button_Register.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(button_Register);

		button_Exit = new JButton("Thoát");
		button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(button_Exit);
	}

	public String getUsername() {
		return textField_Username.getText();
	}

	public String getFullName() {
		return textField_FullName.getText();
	}

	public String getEmail() {
		return textField_Email.getText();
	}

	public String getPassword() {
		return String.valueOf(passwordField_Password.getPassword());
	}

	public JButton getButton_Register() {
		return button_Register;
	}

	public JButton getButton_Exit() {
		return button_Exit;
	}
}
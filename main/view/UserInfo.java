package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import model.User;


public class UserInfo extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField textField_ID;
	private JTextField textField_Username;
	private JTextField textField_FullName;
	private JTextField textField_Email;
	private User currentUser;
	private JButton button_Exit;

	private JPanel mainChildForm;

	public UserInfo(JPanel childform,User user) {
		this.currentUser = user;
		this.mainChildForm = childform;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 280);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();

		gbl_contentPane.columnWidths = new int[] {0, 95, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[] {0, 39, 40, 38, 37, 20, 51, 0, 0};

		gbl_contentPane.columnWeights = new double[] {
				0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE
		};

		gbl_contentPane.rowWeights = new double[] {
				0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE
		};

		contentPane.setLayout(gbl_contentPane);

		JLabel label_ID = new JLabel("ID:");
		label_ID.setHorizontalAlignment(SwingConstants.CENTER);
		label_ID.setFont(new Font("Tahoma", Font.PLAIN, 15));

		GridBagConstraints gbc_label_ID = new GridBagConstraints();
		gbc_label_ID.insets = new Insets(0, 0, 5, 5);
		gbc_label_ID.gridx = 1;
		gbc_label_ID.gridy = 1;

		contentPane.add(label_ID, gbc_label_ID);

		textField_ID = new JTextField();

		GridBagConstraints gbc_textField_ID = new GridBagConstraints();
		gbc_textField_ID.insets = new Insets(0, 0, 5, 0);
		gbc_textField_ID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_ID.gridx = 3;
		gbc_textField_ID.gridy = 1;

		contentPane.add(textField_ID, gbc_textField_ID);
		textField_ID.setColumns(10);

		JLabel label_Username = new JLabel("Username:");
		label_Username.setFont(new Font("Tahoma", Font.PLAIN, 15));

		GridBagConstraints gbc_label_Username = new GridBagConstraints();
		gbc_label_Username.insets = new Insets(0, 0, 5, 5);
		gbc_label_Username.gridx = 1;
		gbc_label_Username.gridy = 2;

		contentPane.add(label_Username, gbc_label_Username);

		textField_Username = new JTextField();

		GridBagConstraints gbc_textField_Username = new GridBagConstraints();
		gbc_textField_Username.insets = new Insets(0, 0, 5, 0);
		gbc_textField_Username.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Username.gridx = 3;
		gbc_textField_Username.gridy = 2;

		contentPane.add(textField_Username, gbc_textField_Username);
		textField_Username.setColumns(10);

		JLabel label_FullName = new JLabel("Tên:");
		label_FullName.setFont(new Font("Tahoma", Font.PLAIN, 15));

		GridBagConstraints gbc_label_FullName = new GridBagConstraints();
		gbc_label_FullName.insets = new Insets(0, 0, 5, 5);
		gbc_label_FullName.gridx = 1;
		gbc_label_FullName.gridy = 3;

		contentPane.add(label_FullName, gbc_label_FullName);

		textField_FullName = new JTextField();

		GridBagConstraints gbc_textField_FullName = new GridBagConstraints();
		gbc_textField_FullName.insets = new Insets(0, 0, 5, 0);
		gbc_textField_FullName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_FullName.gridx = 3;
		gbc_textField_FullName.gridy = 3;

		contentPane.add(textField_FullName, gbc_textField_FullName);
		textField_FullName.setColumns(10);

		JLabel label_Email = new JLabel("Email:");
		label_Email.setFont(new Font("Tahoma", Font.PLAIN, 15));

		GridBagConstraints gbc_label_Email = new GridBagConstraints();
		gbc_label_Email.insets = new Insets(0, 0, 5, 5);
		gbc_label_Email.gridx = 1;
		gbc_label_Email.gridy = 4;

		contentPane.add(label_Email, gbc_label_Email);

		textField_Email = new JTextField();

		GridBagConstraints gbc_textField_Email = new GridBagConstraints();
		gbc_textField_Email.insets = new Insets(0, 0, 5, 0);
		gbc_textField_Email.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Email.gridx = 3;
		gbc_textField_Email.gridy = 4;

		contentPane.add(textField_Email, gbc_textField_Email);
		textField_Email.setColumns(10);

		button_Exit = new JButton("Thoát");
		button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 17));

		GridBagConstraints gbc_button_Exit = new GridBagConstraints();
		gbc_button_Exit.insets = new Insets(0, 0, 5, 0);
		gbc_button_Exit.gridx = 3;
		gbc_button_Exit.gridy = 6;

		contentPane.add(button_Exit, gbc_button_Exit);
		showUserInfo();
	}

	public JPanel getMainChildForm() {
		return mainChildForm;
	}

	public JPanel getContentPanePanel() {
		return contentPane;
	}

	public JButton getButton_Exit() {
		return button_Exit;
	}
	private void showUserInfo() {
		textField_ID.setText(String.valueOf(currentUser.getId()));
		textField_Username.setText(currentUser.getUsername());
		textField_FullName.setText(currentUser.getFullName());
		textField_Email.setText(currentUser.getEmail());

		textField_ID.setEditable(false);
		textField_Username.setEditable(false);
	}
}
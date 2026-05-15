package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;

public class ChangePassw extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPasswordField PasswordField_NewPassword;
	private JPanel panel;
	private JButton button_Confirm;
	private JButton button_Exit;
	private JPasswordField PasswordField_OldPassword;
	private JPanel mainChildForm;

	public ChangePassw(JPanel childForm) {

		this.mainChildForm = childForm;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 205);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[] {0, 0, 0, 21, 86, 0};
		gbl_contentPane.columnWeights = new double[] {0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};

		contentPane.setLayout(gbl_contentPane);

		JLabel lbOldPass = new JLabel("Mật khẩu cũ:");
		lbOldPass.setFont(new Font("Tahoma", Font.PLAIN, 15));

		GridBagConstraints gbc_lbOldPass = new GridBagConstraints();
		gbc_lbOldPass.anchor = GridBagConstraints.EAST;
		gbc_lbOldPass.insets = new Insets(0, 0, 5, 5);
		gbc_lbOldPass.gridx = 1;
		gbc_lbOldPass.gridy = 1;

		contentPane.add(lbOldPass, gbc_lbOldPass);

		PasswordField_OldPassword = new JPasswordField();

		GridBagConstraints gbc_PasswordField_OldPassword = new GridBagConstraints();
		gbc_PasswordField_OldPassword.gridwidth = 2;
		gbc_PasswordField_OldPassword.insets = new Insets(0, 0, 5, 5);
		gbc_PasswordField_OldPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_PasswordField_OldPassword.gridx = 2;
		gbc_PasswordField_OldPassword.gridy = 1;

		contentPane.add(PasswordField_OldPassword, gbc_PasswordField_OldPassword);

		JLabel lbNewPass = new JLabel("Mật khẩu mới:");
		lbNewPass.setFont(new Font("Tahoma", Font.PLAIN, 15));

		GridBagConstraints gbc_lbNewPass = new GridBagConstraints();
		gbc_lbNewPass.insets = new Insets(0, 0, 5, 5);
		gbc_lbNewPass.gridx = 1;
		gbc_lbNewPass.gridy = 2;

		contentPane.add(lbNewPass, gbc_lbNewPass);

		PasswordField_NewPassword = new JPasswordField();

		GridBagConstraints gbc_PasswordField_NewPassword = new GridBagConstraints();
		gbc_PasswordField_NewPassword.gridwidth = 2;
		gbc_PasswordField_NewPassword.insets = new Insets(0, 0, 5, 0);
		gbc_PasswordField_NewPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_PasswordField_NewPassword.gridx = 2;
		gbc_PasswordField_NewPassword.gridy = 2;

		contentPane.add(PasswordField_NewPassword, gbc_PasswordField_NewPassword);

		panel = new JPanel();

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;

		contentPane.add(panel, gbc_panel);

		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));

		button_Confirm = new JButton("Lưu");
		button_Confirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(button_Confirm);

		button_Exit = new JButton("Thoát");
		button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(button_Exit);
	}

	public String getOldPassword() {
		return String.valueOf(PasswordField_OldPassword.getPassword());
	}

	public String getNewPassword() {
		return String.valueOf(PasswordField_NewPassword.getPassword());
	}

	public JButton getButton_Confirm() {
		return button_Confirm;
	}

	public JButton getButton_Exit() {
		return button_Exit;
	}

	public JPanel getMainChildForm() {
		return mainChildForm;
	}

	public JPanel getContentPanePanel() {
		return contentPane;
	}
}
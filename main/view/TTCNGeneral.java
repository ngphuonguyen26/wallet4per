package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TTCNGeneral extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel mainChildForm;

	public TTCNGeneral(JPanel childform) {

		this.mainChildForm = childform;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new GridLayout(4, 1, 0, 0));

		// Top Panel
		JPanel panel_Top = new JPanel();

		contentPane.add(panel_Top);

		panel_Top.setLayout(
				new FlowLayout(
						FlowLayout.CENTER,
						5,
						5
				)
		);

		// User Info Panel
		JPanel panel_UserInfo = new JPanel();

		contentPane.add(panel_UserInfo);

		JButton button_UserInfo = new JButton("Thông tin");

		button_UserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UserInfo inf = new UserInfo(childform);

				openForm(inf);
			}
		});

		button_UserInfo.setPreferredSize(new Dimension(200, 40));
		button_UserInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_UserInfo.add(button_UserInfo);

		// Change Password Panel
		JPanel panel_ChangePassword = new JPanel();

		contentPane.add(panel_ChangePassword);

		panel_ChangePassword.setLayout(
				new FlowLayout(
						FlowLayout.CENTER,
						5,
						5
				)
		);

		JButton button_ChangePassword = new JButton("Đổi mật khẩu");

		button_ChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ChangePassw change = new ChangePassw(childform);

				openForm(change);
			}
		});

		button_ChangePassword.setPreferredSize(new Dimension(200, 40));
		button_ChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_ChangePassword.add(button_ChangePassword);

		// Delete Account Panel
		JPanel panel_DeleteAccount = new JPanel();

		contentPane.add(panel_DeleteAccount);

		panel_DeleteAccount.setLayout(
				new FlowLayout(
						FlowLayout.CENTER,
						5,
						5
				)
		);

		JButton button_DeleteAccount = new JButton("Xóa tài khoản");

		button_DeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// xoa tai khoan
			}
		});

		button_DeleteAccount.setPreferredSize(new Dimension(200, 40));
		button_DeleteAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_DeleteAccount.add(button_DeleteAccount);
	}

	public void openForm(JFrame form) {

		CardLayout cl = (CardLayout) mainChildForm.getLayout();

		String id = String.valueOf(form.hashCode());

		mainChildForm.add(form.getContentPane(), id);

		cl.show(mainChildForm, id);
	}
}
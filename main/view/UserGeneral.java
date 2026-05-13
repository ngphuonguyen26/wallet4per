package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Font;
import java.awt.CardLayout;

public class UserGeneral extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel_ChildForm;

	public UserGeneral() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 712, 450);

		getContentPane().setLayout(new BorderLayout(0, 0));

		// Left Menu Panel
		JPanel panel_Menu = new JPanel();

		getContentPane().add(panel_Menu, BorderLayout.WEST);

		panel_Menu.setLayout(new GridLayout(0, 1, 0, 0));

		// User Profile Button
		JButton button_UserProfile = new JButton("Thông tin cá nhân");

		button_UserProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));

		button_UserProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TTCNGeneral userProfile = new TTCNGeneral(panel_ChildForm);

				openChildForm(userProfile);
			}
		});

		panel_Menu.add(button_UserProfile);

		// Wallet Button
		JButton button_Wallet = new JButton("Ví");

		button_Wallet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Wallet wallet = new Wallet();

				openChildForm(wallet);
			}
		});

		button_Wallet.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_Menu.add(button_Wallet);

		// Category Button
		JButton button_Category = new JButton("Danh mục");

		button_Category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Category category = new Category();

				openChildForm(category);
			}
		});

		button_Category.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_Menu.add(button_Category);

		// Transaction Button
		JButton button_Transaction = new JButton("Giao dịch");

		button_Transaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TransactionGeneral transaction = 
						new TransactionGeneral(panel_ChildForm);

				openChildForm(transaction);
			}
		});

		button_Transaction.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_Menu.add(button_Transaction);

		// Statistics Button
		JButton button_Statistics = new JButton("Thống kê");

		button_Statistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StatisticsGeneral statistics = 
						new StatisticsGeneral(panel_ChildForm);

				openChildForm(statistics);
			}
		});

		button_Statistics.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_Menu.add(button_Statistics);

		// Logout Button
		JButton button_Logout = new JButton("Đăng xuất");

		button_Logout.setFont(new Font("Tahoma", Font.PLAIN, 14));

		button_Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Login login = new Login();

				login.setVisible(true);

				dispose();
			}
		});

		panel_Menu.add(button_Logout);

		// Blank Panels
		JPanel panel_BlankTop = new JPanel();

		panel_Menu.add(panel_BlankTop);

		JPanel panel_BlankBottom = new JPanel();

		panel_Menu.add(panel_BlankBottom);

		// Child Form Panel
		panel_ChildForm = new JPanel();

		getContentPane().add(panel_ChildForm, BorderLayout.CENTER);

		panel_ChildForm.setLayout(new CardLayout(0, 0));
	}

	private void openChildForm(JFrame newPanel) {

		panel_ChildForm.removeAll();

		panel_ChildForm.add(
				newPanel.getContentPane(),
				"currentForm"
		);

		CardLayout cl =
				(CardLayout) panel_ChildForm.getLayout();

		cl.show(panel_ChildForm, "currentForm");

		panel_ChildForm.revalidate();

		panel_ChildForm.repaint();
	}
}
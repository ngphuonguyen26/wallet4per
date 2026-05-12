package main.view;
import java.awt.CardLayout;
import java.awt.EventQueue;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;

public class Regist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfUName;
	private JTextField tfFName;
	private JTextField tfEmail;
	private JPanel panel;
	private JButton btExit;
	private JButton btRe;
	private JLabel lbPa;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public Regist() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 95, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 39, 40, 38, 37, 36, 51, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lbID = new JLabel("ID:");
		lbID.setHorizontalAlignment(SwingConstants.CENTER);
		lbID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lbID = new GridBagConstraints();
		gbc_lbID.insets = new Insets(0, 0, 5, 5);
		gbc_lbID.gridx = 1;
		gbc_lbID.gridy = 1;
		contentPane.add(lbID, gbc_lbID);
		
		tfID = new JTextField();
		GridBagConstraints gbc_tfID = new GridBagConstraints();
		gbc_tfID.insets = new Insets(0, 0, 5, 0);
		gbc_tfID.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfID.gridx = 3;
		gbc_tfID.gridy = 1;
		contentPane.add(tfID, gbc_tfID);
		tfID.setColumns(10);
		
		JLabel lbUserName = new JLabel("Username:");
		lbUserName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lbUserName = new GridBagConstraints();
		gbc_lbUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lbUserName.gridx = 1;
		gbc_lbUserName.gridy = 2;
		contentPane.add(lbUserName, gbc_lbUserName);
		
		tfUName = new JTextField();
		GridBagConstraints gbc_tfUName = new GridBagConstraints();
		gbc_tfUName.insets = new Insets(0, 0, 5, 0);
		gbc_tfUName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfUName.gridx = 3;
		gbc_tfUName.gridy = 2;
		contentPane.add(tfUName, gbc_tfUName);
		tfUName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Tên:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		tfFName = new JTextField();
		GridBagConstraints gbc_tfFName = new GridBagConstraints();
		gbc_tfFName.insets = new Insets(0, 0, 5, 0);
		gbc_tfFName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfFName.gridx = 3;
		gbc_tfFName.gridy = 3;
		contentPane.add(tfFName, gbc_tfFName);
		tfFName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		tfEmail = new JTextField();
		GridBagConstraints gbc_tfEmail = new GridBagConstraints();
		gbc_tfEmail.insets = new Insets(0, 0, 5, 0);
		gbc_tfEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEmail.gridx = 3;
		gbc_tfEmail.gridy = 4;
		contentPane.add(tfEmail, gbc_tfEmail);
		tfEmail.setColumns(10);
		
		lbPa = new JLabel("Password");
		lbPa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lbPa = new GridBagConstraints();
		gbc_lbPa.insets = new Insets(0, 0, 5, 5);
		gbc_lbPa.gridx = 1;
		gbc_lbPa.gridy = 5;
		contentPane.add(lbPa, gbc_lbPa);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 5;
		contentPane.add(passwordField, gbc_passwordField);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 6;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
		
		btRe = new JButton("Đăng kí");
		btRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btRe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btRe);
		
		btExit = new JButton("Thoát");
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.setVisible(true);
				dispose();
			}
		});
		btExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btExit);

	}

}

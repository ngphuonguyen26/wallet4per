package main.view;

import java.awt.CardLayout;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassw extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField pfNewPass;
	private JPanel panel;
	private JButton btConfirm;
	private JButton btExit;
	private JPasswordField pfOldPass;
	private JPanel mainChildForm;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChangePassw(JPanel childForm) {
		this.mainChildForm = childForm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 21, 86, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lbOldPass = new JLabel("Mật khẩu cũ:");
		lbOldPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lbOldPass = new GridBagConstraints();
		gbc_lbOldPass.anchor = GridBagConstraints.EAST;
		gbc_lbOldPass.insets = new Insets(0, 0, 5, 5);
		gbc_lbOldPass.gridx = 1;
		gbc_lbOldPass.gridy = 1;
		contentPane.add(lbOldPass, gbc_lbOldPass);
		
		pfOldPass = new JPasswordField();
		GridBagConstraints gbc_pfOldPass = new GridBagConstraints();
		gbc_pfOldPass.gridwidth = 2;
		gbc_pfOldPass.insets = new Insets(0, 0, 5, 5);
		gbc_pfOldPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_pfOldPass.gridx = 2;
		gbc_pfOldPass.gridy = 1;
		contentPane.add(pfOldPass, gbc_pfOldPass);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu mới:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		pfNewPass = new JPasswordField();
		GridBagConstraints gbc_pfNewPass = new GridBagConstraints();
		gbc_pfNewPass.gridwidth = 2;
		gbc_pfNewPass.insets = new Insets(0, 0, 5, 0);
		gbc_pfNewPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_pfNewPass.gridx = 2;
		gbc_pfNewPass.gridy = 2;
		contentPane.add(pfNewPass, gbc_pfNewPass);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		
		btConfirm = new JButton("Lưu");
		btConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// bt luu
			}
		});
		btConfirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(btConfirm);
		
		btExit = new JButton("Thoát");
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) mainChildForm.getLayout();
		        cl.previous(mainChildForm); 
		        mainChildForm.remove(contentPane);
		        mainChildForm.revalidate();
		        mainChildForm.repaint();
			}
		});
		btExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(btExit);

	}

}

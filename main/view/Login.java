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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Font;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfID;
	private JPasswordField tpPass;
	private JPanel panel;
	private JButton btLg;
	private JButton btRe;
	private JCheckBox cbPass;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 551, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{35, 0, 0, 47, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{35, 14, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE, 0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
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
		
		tfID = new JTextField();
		GridBagConstraints gbc_tfID = new GridBagConstraints();
		gbc_tfID.gridwidth = 5;
		gbc_tfID.insets = new Insets(0, 0, 5, 5);
		gbc_tfID.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfID.gridx = 3;
		gbc_tfID.gridy = 1;
		contentPane.add(tfID, gbc_tfID);
		tfID.setColumns(10);
		
		JLabel laPa = new JLabel("Mật khẩu:");
		laPa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_laPa = new GridBagConstraints();
		gbc_laPa.gridwidth = 2;
		gbc_laPa.ipady = 30;
		gbc_laPa.insets = new Insets(0, 0, 5, 5);
		gbc_laPa.gridx = 0;
		gbc_laPa.gridy = 2;
		contentPane.add(laPa, gbc_laPa);
		
		tpPass = new JPasswordField();
		GridBagConstraints gbc_tpPass = new GridBagConstraints();
		gbc_tpPass.gridwidth = 5;
		gbc_tpPass.insets = new Insets(0, 0, 5, 5);
		gbc_tpPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_tpPass.gridx = 3;
		gbc_tpPass.gridy = 2;
		contentPane.add(tpPass, gbc_tpPass);
		tpPass.setColumns(10);
		
		cbPass = new JCheckBox("Hiện mật khẩu");
		cbPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_cbPass = new GridBagConstraints();
		gbc_cbPass.gridwidth = 2;
		gbc_cbPass.insets = new Insets(0, 0, 5, 0);
		gbc_cbPass.gridx = 8;
		gbc_cbPass.gridy = 2;
		contentPane.add(cbPass, gbc_cbPass);
        cbPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(cbPass.isSelected())
                {
                    tpPass.setEchoChar((char) 0);
                }
                else
                {
                    tpPass.setEchoChar('*');
                }
            }
        });
		

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
		
		btLg = new JButton("Đăng nhập");
		btLg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btLg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Them dkien kiem tra logic vao
				UserGeneral formUser = new UserGeneral();
				formUser.setVisible(true);
				dispose();
			}
		});
		panel.add(btLg);
		
		btRe = new JButton("Đăng kí");
		btRe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// form dki
			}
		});
		panel.add(btRe);

	}

}

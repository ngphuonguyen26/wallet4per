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
    private JPanel childform;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public UserGeneral() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 712, 450);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btTTCN = new JButton("Thông tin cá nhân");
		btTTCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btTTCN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		TTCNGeneral cnhanchung = new TTCNGeneral(childform);
                openChildForm(cnhanchung);
            }
        });
		panel.add(btTTCN);
		
		JButton btVi = new JButton("Ví");
		btVi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wallet wa = new Wallet();
				openChildForm(wa);
			}
		});
		btVi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btVi);
		
		JButton btnNewButton_3 = new JButton("Đăng xuất");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.setVisible(true);
				dispose();
			}
		});
		
		JButton btCata = new JButton("Danh mục");
		btCata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Catalogies cata = new Catalogies();
				openChildForm(cata);
			}
		});
		btCata.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btCata);
		
		JButton btGD = new JButton("Giao dịch");
		btGD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionGeneral trans_main = new TransactionGeneral(childform);
				openChildForm(trans_main);
			}
		});
		btGD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btGD);
		
		JButton btTK = new JButton("Thống kê");
		btTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticsGeneral sta = new StatisticsGeneral(childform);
				openChildForm(sta);
			}
		});
		btTK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btTK);
		panel.add(btnNewButton_3);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		childform = new JPanel();
		getContentPane().add(childform, BorderLayout.CENTER);
		childform.setLayout(new CardLayout(0, 0));
	}
	
	 private void openChildForm(JFrame newPanel) {
         childform.removeAll();
         childform.add(newPanel.getContentPane(), "currentForm");
         CardLayout cl = (CardLayout) childform.getLayout();
         cl.show(childform, "currentForm");
         childform.revalidate();
         childform.repaint();
     }
}


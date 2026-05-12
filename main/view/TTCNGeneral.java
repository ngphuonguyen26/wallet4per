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

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TTCNGeneral(JPanel childform) {
		this.mainChildForm = childform;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		contentPane.add(panel_2);
		
		JButton btTT = new JButton("Thông tin");
		btTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfo inf = new UserInfo(childform);
				openForm(inf);
			}
		});
		btTT.setPreferredSize(new Dimension(200, 40));
		panel_2.add(btTT);
		btTT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btChangePa = new JButton("Đổi mật khẩu");
		btChangePa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassw change = new ChangePassw(childform);
				openForm(change);
			}
		});
		btChangePa.setPreferredSize(new Dimension(200, 40));
		panel_3.add(btChangePa);
		btChangePa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btDelete = new JButton("Xóa tài khoản");
		btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// xoa tai khoan
			}
		});
		btDelete.setPreferredSize(new Dimension(200, 40));
		btDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btDelete);

	}
	
	public void openForm(JFrame form)
	{
		CardLayout cl = (CardLayout) mainChildForm.getLayout();
		String id = String.valueOf(form.hashCode());
		mainChildForm.add(form.getContentPane(), id);
	    cl.show(mainChildForm, id);
	}

}

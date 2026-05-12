package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransactionGeneral extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel mainChildForm;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TransactionGeneral(JPanel childform) {
		 this.mainChildForm = childform;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JButton btAdd = new JButton("Thêm giao dịch");
		btAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTransaction add = new AddTransaction(childform);
				openForm(add);
			}
		});
		btAdd.setPreferredSize(new Dimension(200, 40));
		btAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(btAdd);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		
		JButton btTransDay = new JButton("Giao dịch ngày");
		btTransDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransDay day = new TransDay(childform);
				openForm(day);
			}
		});
		btTransDay.setPreferredSize(new Dimension(200, 40));
		btTransDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(btTransDay);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton btTrans = new JButton("Giao dịch");
		btTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransItem item = new TransItem(childform);
				openForm(item);
			}
		});
		btTrans.setPreferredSize(new Dimension(200, 40));
		btTrans.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btTrans);

	}
	
	public void openForm(JFrame form)
	{
		CardLayout cl = (CardLayout) mainChildForm.getLayout();
		 String id = String.valueOf(form.hashCode());
		 mainChildForm.add(form.getContentPane(), id);
		 cl.show(mainChildForm, id);
	}

}

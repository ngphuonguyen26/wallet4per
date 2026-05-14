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
	private model.User currentuser;
	public TransactionGeneral(JPanel childform, model.User currentuser) {

		this.mainChildForm = childform;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 304);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new GridLayout(4, 1, 0, 0));

		// Top Panel
		JPanel panel_Top = new JPanel();

		contentPane.add(panel_Top);

		// Add Transaction Panel
		JPanel panel_AddTransaction = new JPanel();

		contentPane.add(panel_AddTransaction);

		JButton button_AddTransaction = new JButton("Thêm giao dịch");

		button_AddTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AddTransaction add = new AddTransaction(childform,currentuser.getId());

				openForm(add);
			}
		});

		button_AddTransaction.setPreferredSize(new Dimension(200, 40));
		button_AddTransaction.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_AddTransaction.add(button_AddTransaction);

		// Transaction Day Panel
		JPanel panel_TransactionDay = new JPanel();

		contentPane.add(panel_TransactionDay);

		JButton button_TransactionDay = new JButton("Giao dịch ngày");

		button_TransactionDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TransDay day = new TransDay(childform);

				openForm(day);
			}
		});

		button_TransactionDay.setPreferredSize(new Dimension(200, 40));
		button_TransactionDay.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_TransactionDay.add(button_TransactionDay);

		// Transaction Panel
		JPanel panel_Transaction = new JPanel();

		contentPane.add(panel_Transaction);

		JButton button_Transaction = new JButton("Giao dịch");

		button_Transaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TransItem item = new TransItem(childform);

				openForm(item);
			}
		});

		button_Transaction.setPreferredSize(new Dimension(200, 40));
		button_Transaction.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_Transaction.add(button_Transaction);
	}

	public void openForm(JFrame form) {

		CardLayout cl = (CardLayout) mainChildForm.getLayout();

		String id = String.valueOf(form.hashCode());

		mainChildForm.add(form.getContentPane(), id);

		cl.show(mainChildForm, id);
	}
}
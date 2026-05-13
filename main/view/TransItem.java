package main.view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;

import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransItem extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table_Transactions;
	private JButton button_Exit;
	private JPanel mainChildForm;

	public TransItem(JPanel childform) {

		this.mainChildForm = childform;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 488);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		// Scroll Pane
		JScrollPane scrollPane_Transactions = new JScrollPane();

		contentPane.add(scrollPane_Transactions);

		// Transactions Table
		table_Transactions = new JTable();

		table_Transactions.setModel(
				new DefaultTableModel(
						new Object[][] {},
						new String[] {
								"ID",
								"Ví",
								"Số tiền",
								"Danh mục",
								"Loại",
								"Ghi chú",
								"Ngày"
						}
				)
		);

		scrollPane_Transactions.setViewportView(table_Transactions);

		// Exit Button
		button_Exit = new JButton("Thoát");

		button_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cl = (CardLayout) mainChildForm.getLayout();

				cl.previous(mainChildForm);

				mainChildForm.remove(contentPane);

				mainChildForm.revalidate();
				mainChildForm.repaint();
			}
		});

		button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));

		contentPane.add(button_Exit);
	}
}
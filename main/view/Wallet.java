package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Wallet extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JScrollPane scrollPane_Wallet;
	private JTable table_Wallet;

	/**
	 * Create the frame.
	 */
	public Wallet() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		scrollPane_Wallet = new JScrollPane();
		contentPane.add(scrollPane_Wallet, BorderLayout.CENTER);

		table_Wallet = new JTable();
		table_Wallet.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Tên ví", "Số dư"
			}
		));

		scrollPane_Wallet.setViewportView(table_Wallet);
	}

}
package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class Wallet extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JScrollPane scrollPane_Wallet;
	private JTable table_Wallet;
	private DefaultTableModel model_Wallet;

	public Wallet() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		scrollPane_Wallet = new JScrollPane();
		contentPane.add(scrollPane_Wallet, BorderLayout.CENTER);

		model_Wallet = new DefaultTableModel(
				new Object[][]{},
				new String[]{
						"ID", "Tên ví", "Loại ví", "Số dư"
				}
		);

		table_Wallet = new JTable(model_Wallet);
		scrollPane_Wallet.setViewportView(table_Wallet);
	}

	public DefaultTableModel getModel_Wallet() {
		return model_Wallet;
	}

	public JTable getTable_Wallet() {
		return table_Wallet;
	}
}
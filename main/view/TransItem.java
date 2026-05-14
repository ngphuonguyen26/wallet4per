package main.view;

import dao.TransactionDAO;
import model.Transaction;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.util.List;

public class TransItem extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table_Transactions;
	private JButton button_Exit;
	private JButton button_Reload;

	private JPanel mainChildForm;
	private int userId;

	private DefaultTableModel model_Transactions;
	private TransactionDAO transactionDAO;

	public TransItem(JPanel childform, int userId) {

		this.mainChildForm = childform;
		this.userId = userId;
		this.transactionDAO = new TransactionDAO();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 488);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);

		model_Transactions = new DefaultTableModel(
				new Object[][]{},
				new String[]{
						"ID",
						"Ví",
						"Số tiền",
						"Danh mục",
						"Loại",
						"Ghi chú",
						"Ngày"
				}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_Transactions = new JTable(model_Transactions);

		JScrollPane scrollPane_Transactions = new JScrollPane(table_Transactions);
		contentPane.add(scrollPane_Transactions, BorderLayout.CENTER);

		JPanel panel_Button = new JPanel();

		button_Reload = new JButton("Tải lại");
		button_Reload.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_Button.add(button_Reload);

		button_Exit = new JButton("Thoát");
		button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_Button.add(button_Exit);

		contentPane.add(panel_Button, BorderLayout.SOUTH);

		addEvents();

		loadTransactions();
	}

	private void addEvents() {
		button_Reload.addActionListener(e -> loadTransactions());

		button_Exit.addActionListener(e -> {
			CardLayout cl = (CardLayout) mainChildForm.getLayout();
			cl.previous(mainChildForm);

			mainChildForm.revalidate();
			mainChildForm.repaint();
		});
	}

	private void loadTransactions() {
		model_Transactions.setRowCount(0);

		List<Transaction> list = transactionDAO.getTransactionsByUser(userId);

		for (Transaction t : list) {
			model_Transactions.addRow(new Object[]{
					t.getTransactionId(),
					t.getWalletName(),
					t.getAmount(),
					t.getCategoryName(),
					t.getType(),
					t.getNote(),
					t.getTransactionDate()
			});
		}

		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa có giao dịch nào.");
		}
	}
}
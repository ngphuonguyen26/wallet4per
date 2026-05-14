package main.view;

import dao.TransactionDAO;
import model.Transaction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TransDay extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table_DailyTransactions;
	private JButton button_Exit;
	private JButton button_Load;
	private JTextField textField_Date;

	private JPanel mainChildForm;
	private int userId;

	private DefaultTableModel model;
	private TransactionDAO transactionDAO;

	public TransDay(JPanel childform, int userId) {

		this.mainChildForm = childform;
		this.userId = userId;
		this.transactionDAO = new TransactionDAO();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 480);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));

		JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel labelDate = new JLabel("Ngày yyyy-MM-dd:");
		labelDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelTop.add(labelDate);

		textField_Date = new JTextField(12);
		textField_Date.setText(LocalDate.now().toString());
		panelTop.add(textField_Date);

		button_Load = new JButton("Xem");
		button_Load.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelTop.add(button_Load);

		button_Exit = new JButton("Thoát");
		button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelTop.add(button_Exit);

		contentPane.add(panelTop, BorderLayout.NORTH);

		model = new DefaultTableModel(
				new Object[][]{},
				new String[]{
						"ID", "Ví", "Danh mục", "Loại", "Số tiền", "Ghi chú", "Ngày"
				}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_DailyTransactions = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table_DailyTransactions);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		addEvents();

		loadTodayTransactions();
	}

	private void addEvents() {
		button_Load.addActionListener(e -> loadTransactionsByDate());

		button_Exit.addActionListener(e -> {
			CardLayout cl = (CardLayout) mainChildForm.getLayout();
			cl.previous(mainChildForm);

			mainChildForm.revalidate();
			mainChildForm.repaint();
		});
	}

	private void loadTodayTransactions() {
		loadData(LocalDate.now());
	}

	private void loadTransactionsByDate() {
		String dateText = textField_Date.getText().trim();

		if (dateText.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày.");
			return;
		}

		try {
			LocalDate date = LocalDate.parse(dateText);
			loadData(date);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ngày không hợp lệ. Nhập dạng yyyy-MM-dd. Ví dụ: 2026-05-15");
		}
	}

	private void loadData(LocalDate date) {
		model.setRowCount(0);

		List<Transaction> list = transactionDAO.getTransactionsByDay(userId, date);

		for (Transaction t : list) {
			model.addRow(new Object[]{
					t.getTransactionId(),
					t.getWalletName(),
					t.getCategoryName(),
					t.getType(),
					t.getAmount(),
					t.getNote(),
					t.getTransactionDate()
			});
		}

		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không có giao dịch trong ngày " + date);
		}
	}
}
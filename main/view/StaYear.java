package main.view;

import dao.TransactionDAO;
import model.Transaction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StaYear extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table_YearlyStatistics;
	private JTextField textField_Year;
	private JTextField textField_Total;
	private JButton button_Exit;
	private JButton button_Load;
	private JPanel mainChildForm;

	private DefaultTableModel model_YearlyStatistics;
	private TransactionDAO transactionDAO;
	private int userId;

	public StaYear(JPanel childform, int userId) {

		this.mainChildForm = childform;
		this.userId = userId;
		this.transactionDAO = new TransactionDAO();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{80, 180, 100, 100, 100, 100, 0};
		gbl_contentPane.rowHeights = new int[]{35, 330, 40, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel label_Year = new JLabel("Năm:");
		label_Year.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Year = new GridBagConstraints();
		gbc_label_Year.insets = new Insets(0, 0, 5, 5);
		gbc_label_Year.gridx = 0;
		gbc_label_Year.gridy = 0;
		contentPane.add(label_Year, gbc_label_Year);

		textField_Year = new JTextField();
		textField_Year.setText(String.valueOf(LocalDate.now().getYear()));

		GridBagConstraints gbc_textField_Year = new GridBagConstraints();
		gbc_textField_Year.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Year.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Year.gridx = 1;
		gbc_textField_Year.gridy = 0;
		contentPane.add(textField_Year, gbc_textField_Year);

		button_Load = new JButton("Xem");
		button_Load.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_button_Load = new GridBagConstraints();
		gbc_button_Load.insets = new Insets(0, 0, 5, 5);
		gbc_button_Load.gridx = 2;
		gbc_button_Load.gridy = 0;
		contentPane.add(button_Load, gbc_button_Load);

		model_YearlyStatistics = new DefaultTableModel(
				new Object[][]{},
				new String[]{
						"ID", "Ví", "Số tiền", "Danh mục", "Loại", "Ghi chú", "Ngày"
				}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_YearlyStatistics = new JTable(model_YearlyStatistics);

		JScrollPane scrollPane_Statistics = new JScrollPane(table_YearlyStatistics);

		GridBagConstraints gbc_scrollPane_Statistics = new GridBagConstraints();
		gbc_scrollPane_Statistics.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_Statistics.gridwidth = 6;
		gbc_scrollPane_Statistics.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Statistics.gridx = 0;
		gbc_scrollPane_Statistics.gridy = 1;
		contentPane.add(scrollPane_Statistics, gbc_scrollPane_Statistics);

		JLabel label_Total = new JLabel("Tổng chi:");
		label_Total.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Total = new GridBagConstraints();
		gbc_label_Total.insets = new Insets(0, 0, 0, 5);
		gbc_label_Total.gridx = 0;
		gbc_label_Total.gridy = 2;
		contentPane.add(label_Total, gbc_label_Total);

		textField_Total = new JTextField();
		textField_Total.setEditable(false);

		GridBagConstraints gbc_textField_Total = new GridBagConstraints();
		gbc_textField_Total.insets = new Insets(0, 0, 0, 5);
		gbc_textField_Total.gridwidth = 3;
		gbc_textField_Total.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Total.gridx = 1;
		gbc_textField_Total.gridy = 2;
		contentPane.add(textField_Total, gbc_textField_Total);

		button_Exit = new JButton("Thoát");
		button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_button_Exit = new GridBagConstraints();
		gbc_button_Exit.gridx = 5;
		gbc_button_Exit.gridy = 2;
		contentPane.add(button_Exit, gbc_button_Exit);

		addEvents();
		loadData();
	}

	private void addEvents() {
		button_Load.addActionListener(e -> loadData());

		button_Exit.addActionListener(e -> {
			CardLayout cl = (CardLayout) mainChildForm.getLayout();
			cl.previous(mainChildForm);

			mainChildForm.revalidate();
			mainChildForm.repaint();
		});
	}

	private void loadData() {
		model_YearlyStatistics.setRowCount(0);

		String yearText = textField_Year.getText().trim();

		if (yearText.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập năm.");
			return;
		}

		int year;

		try {
			year = Integer.parseInt(yearText);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Năm không hợp lệ.");
			return;
		}

		List<Transaction> list = transactionDAO.getTransactionsByYear(userId, year);

		BigDecimal totalExpense = BigDecimal.ZERO;

		for (Transaction t : list) {
			model_YearlyStatistics.addRow(new Object[]{
					t.getTransactionId(),
					t.getWalletName(),
					t.getAmount(),
					t.getCategoryName(),
					t.getType(),
					t.getNote(),
					t.getTransactionDate()
			});

			if (t.getType() == Transaction.TransactionType.EXPENSE) {
				totalExpense = totalExpense.add(t.getAmount());
			}
		}

		textField_Total.setText(totalExpense.toString());
	}
}
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

public class StaMonth extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table_MonthlyStatistics;
	private JTextField textField_Total;
	private JPanel mainChildForm;

	private JComboBox<String> comboBox_Month;
	private JComboBox<String> comboBox_Year;
	private JButton button_Load;
	private JButton button_Exit;

	private DefaultTableModel model_MonthlyStatistics;
	private TransactionDAO transactionDAO;
	private int userId;

	public StaMonth(JPanel childform, int userId) {

		this.mainChildForm = childform;
		this.userId = userId;
		this.transactionDAO = new TransactionDAO();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 480);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{80, 120, 80, 120, 100, 100, 0};
		gbl_contentPane.rowHeights = new int[]{35, 300, 40, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel label_Month = new JLabel("Tháng:");
		label_Month.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addComponent(label_Month, 0, 0);

		comboBox_Month = new JComboBox<>();
		for (int i = 1; i <= 12; i++) {
			comboBox_Month.addItem(String.valueOf(i));
		}
		comboBox_Month.setSelectedItem(String.valueOf(LocalDate.now().getMonthValue()));
		addInput(comboBox_Month, 1, 0);

		JLabel label_Year = new JLabel("Năm:");
		label_Year.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addComponent(label_Year, 2, 0);

		comboBox_Year = new JComboBox<>();
		int currentYear = LocalDate.now().getYear();
		for (int y = currentYear - 5; y <= currentYear + 1; y++) {
			comboBox_Year.addItem(String.valueOf(y));
		}
		comboBox_Year.setSelectedItem(String.valueOf(currentYear));
		addInput(comboBox_Year, 3, 0);

		button_Load = new JButton("Xem");
		button_Load.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addComponent(button_Load, 4, 0);

		model_MonthlyStatistics = new DefaultTableModel(
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

		table_MonthlyStatistics = new JTable(model_MonthlyStatistics);

		JScrollPane scrollPane_Statistics = new JScrollPane(table_MonthlyStatistics);

		GridBagConstraints gbc_scroll = new GridBagConstraints();
		gbc_scroll.insets = new Insets(0, 0, 5, 5);
		gbc_scroll.gridwidth = 6;
		gbc_scroll.fill = GridBagConstraints.BOTH;
		gbc_scroll.gridx = 0;
		gbc_scroll.gridy = 1;
		contentPane.add(scrollPane_Statistics, gbc_scroll);

		JLabel label_Total = new JLabel("Tổng chi:");
		label_Total.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addComponent(label_Total, 0, 2);

		textField_Total = new JTextField();
		textField_Total.setEditable(false);
		addInput(textField_Total, 1, 2);

		button_Exit = new JButton("Thoát");
		button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addComponent(button_Exit, 5, 2);

		addEvents();
		loadData();
	}

	private void addComponent(Component component, int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.anchor = GridBagConstraints.WEST;
		contentPane.add(component, gbc);
	}

	private void addInput(Component component, int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(component, gbc);
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
		model_MonthlyStatistics.setRowCount(0);

		int month = Integer.parseInt((String) comboBox_Month.getSelectedItem());
		int year = Integer.parseInt((String) comboBox_Year.getSelectedItem());

		List<Transaction> list = transactionDAO.getTransactionsByMonth(userId, year, month);

		BigDecimal totalExpense = BigDecimal.ZERO;

		for (Transaction t : list) {
			model_MonthlyStatistics.addRow(new Object[]{
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
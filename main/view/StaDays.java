package main.view;

import dao.TransactionDAO;
import model.Transaction;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JTextField;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StaDays extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table_DailyStatistics;
	private JTextField textField_Total;
	private JPanel mainChildForm;

	private JSpinner spinner_Date;
	private JButton button_Load;
	private JButton button_Exit;

	private int userId;
	private TransactionDAO transactionDAO;
	private DefaultTableModel model_DailyStatistics;

	public StaDays(JPanel childform, int userId) {

		this.mainChildForm = childform;
		this.userId = userId;
		this.transactionDAO = new TransactionDAO();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 492);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{80, 120, 120, 120, 120, 0};
		gbl_contentPane.rowHeights = new int[]{40, 0, 300, 40, 0};
		gbl_contentPane.columnWeights = new double[]{
				0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE
		};
		gbl_contentPane.rowWeights = new double[]{
				0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE
		};
		contentPane.setLayout(gbl_contentPane);

		JLabel label_Day = new JLabel("Ngày:");
		label_Day.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Day = new GridBagConstraints();
		gbc_label_Day.insets = new Insets(0, 0, 5, 5);
		gbc_label_Day.gridx = 0;
		gbc_label_Day.gridy = 1;
		contentPane.add(label_Day, gbc_label_Day);

		spinner_Date = new JSpinner();
		spinner_Date.setPreferredSize(new Dimension(260, 25));
		spinner_Date.setModel(
				new SpinnerDateModel(
						new Date(),
						null,
						null,
						Calendar.DAY_OF_YEAR
				)
		);

		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner_Date, "yyyy-MM-dd");
		spinner_Date.setEditor(dateEditor);

		GridBagConstraints gbc_spinner_Date = new GridBagConstraints();
		gbc_spinner_Date.gridwidth = 2;
		gbc_spinner_Date.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_Date.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_Date.gridx = 1;
		gbc_spinner_Date.gridy = 1;
		contentPane.add(spinner_Date, gbc_spinner_Date);

		button_Load = new JButton("Xem");
		button_Load.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_button_Load = new GridBagConstraints();
		gbc_button_Load.insets = new Insets(0, 0, 5, 5);
		gbc_button_Load.gridx = 3;
		gbc_button_Load.gridy = 1;
		contentPane.add(button_Load, gbc_button_Load);

		JScrollPane scrollPane_Statistics = new JScrollPane();

		GridBagConstraints gbc_scrollPane_Statistics = new GridBagConstraints();
		gbc_scrollPane_Statistics.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_Statistics.gridwidth = 5;
		gbc_scrollPane_Statistics.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Statistics.gridx = 0;
		gbc_scrollPane_Statistics.gridy = 2;
		contentPane.add(scrollPane_Statistics, gbc_scrollPane_Statistics);

		model_DailyStatistics = new DefaultTableModel(
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

		table_DailyStatistics = new JTable(model_DailyStatistics);
		scrollPane_Statistics.setViewportView(table_DailyStatistics);

		JLabel label_Total = new JLabel("Tổng chi:");
		label_Total.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Total = new GridBagConstraints();
		gbc_label_Total.anchor = GridBagConstraints.EAST;
		gbc_label_Total.insets = new Insets(0, 0, 0, 5);
		gbc_label_Total.gridx = 0;
		gbc_label_Total.gridy = 3;
		contentPane.add(label_Total, gbc_label_Total);

		textField_Total = new JTextField();
		textField_Total.setEditable(false);
		textField_Total.setColumns(10);

		GridBagConstraints gbc_textField_Total = new GridBagConstraints();
		gbc_textField_Total.gridwidth = 2;
		gbc_textField_Total.insets = new Insets(0, 0, 0, 5);
		gbc_textField_Total.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Total.gridx = 1;
		gbc_textField_Total.gridy = 3;
		contentPane.add(textField_Total, gbc_textField_Total);

		button_Exit = new JButton("Thoát");
		button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_button_Exit = new GridBagConstraints();
		gbc_button_Exit.gridx = 4;
		gbc_button_Exit.gridy = 3;
		contentPane.add(button_Exit, gbc_button_Exit);

		addEvents();
		loadData();
	}

	private void addEvents() {
		button_Load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});

		button_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cl = (CardLayout) mainChildForm.getLayout();

				cl.previous(mainChildForm);

				mainChildForm.revalidate();
				mainChildForm.repaint();
			}
		});
	}

	private void loadData() {
		model_DailyStatistics.setRowCount(0);

		Date selectedDate = (Date) spinner_Date.getValue();

		LocalDate date = selectedDate.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();

		List<Transaction> list = transactionDAO.getTransactionsByDay(userId, date);

		BigDecimal totalExpense = BigDecimal.ZERO;

		for (Transaction t : list) {

			model_DailyStatistics.addRow(new Object[]{
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
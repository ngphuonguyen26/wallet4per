package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JSpinner;
import java.awt.Insets;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaDays extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table_DailyStatistics;
	private JTextField textField_Total;
	private JPanel mainChildForm;

	public StaDays(JPanel childform) {

		this.mainChildForm = childform;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 492);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0, 72, 42, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[] {0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[] {
				1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE
		};
		gbl_contentPane.rowWeights = new double[] {
				0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE
		};

		contentPane.setLayout(gbl_contentPane);

		// Day label
		JLabel label_Day = new JLabel("Ngày:");
		label_Day.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Day = new GridBagConstraints();
		gbc_label_Day.insets = new Insets(0, 0, 5, 5);
		gbc_label_Day.gridx = 0;
		gbc_label_Day.gridy = 1;

		contentPane.add(label_Day, gbc_label_Day);

		// Date spinner
		JSpinner spinner_Date = new JSpinner();

		spinner_Date.setPreferredSize(new Dimension(400, 20));

		spinner_Date.setModel(
				new SpinnerDateModel(
						new Date(1778518800000L),
						null,
						null,
						Calendar.DAY_OF_YEAR
				)
		);

		GridBagConstraints gbc_spinner_Date = new GridBagConstraints();
		gbc_spinner_Date.gridwidth = 3;
		gbc_spinner_Date.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_Date.gridx = 1;
		gbc_spinner_Date.gridy = 1;

		contentPane.add(spinner_Date, gbc_spinner_Date);

		// Statistics scroll pane
		JScrollPane scrollPane_Statistics = new JScrollPane();

		GridBagConstraints gbc_scrollPane_Statistics = new GridBagConstraints();
		gbc_scrollPane_Statistics.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_Statistics.gridwidth = 4;
		gbc_scrollPane_Statistics.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Statistics.gridx = 0;
		gbc_scrollPane_Statistics.gridy = 2;

		contentPane.add(scrollPane_Statistics, gbc_scrollPane_Statistics);

		// Statistics table
		table_DailyStatistics = new JTable();

		table_DailyStatistics.setModel(
				new DefaultTableModel(
						new Object[][] {},
						new String[] {
								"ID",
								"Ví",
								"Số tiền",
								"Danh mục",
								"Loại",
								"Ghi chú"
						}
				)
		);

		scrollPane_Statistics.setViewportView(table_DailyStatistics);

		// Total label
		JLabel label_Total = new JLabel("Tổng:");
		label_Total.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Total = new GridBagConstraints();
		gbc_label_Total.anchor = GridBagConstraints.EAST;
		gbc_label_Total.insets = new Insets(0, 0, 0, 5);
		gbc_label_Total.gridx = 0;
		gbc_label_Total.gridy = 3;

		contentPane.add(label_Total, gbc_label_Total);

		// Total text field
		textField_Total = new JTextField();

		textField_Total.setColumns(10);

		GridBagConstraints gbc_textField_Total = new GridBagConstraints();
		gbc_textField_Total.gridwidth = 2;
		gbc_textField_Total.insets = new Insets(0, 0, 0, 5);
		gbc_textField_Total.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Total.gridx = 1;
		gbc_textField_Total.gridy = 3;

		contentPane.add(textField_Total, gbc_textField_Total);

		// Exit button
		JButton button_Exit = new JButton("Thoát");

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

		GridBagConstraints gbc_button_Exit = new GridBagConstraints();
		gbc_button_Exit.gridx = 4;
		gbc_button_Exit.gridy = 3;

		contentPane.add(button_Exit, gbc_button_Exit);
	}
}
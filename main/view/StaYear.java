package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.CardLayout;

import javax.swing.table.DefaultTableModel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaYear extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table_YearlyStatistics;
	private JTextField textField_Year;
	private JTextField textField_Total;
	private JButton button_Exit;
	private JPanel mainChildForm;

	public StaYear(JPanel childform) {

		this.mainChildForm = childform;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 497);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {
				126, 0, 0, 0, 0,
				0, 0, 0, 0, 0
		};
		gbl_contentPane.rowHeights = new int[] {
				0, 314, 0, 0
		};
		gbl_contentPane.columnWeights = new double[] {
				1.0, 0.0, 1.0, 1.0, 0.0,
				0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE
		};
		gbl_contentPane.rowWeights = new double[] {
				0.0, 1.0, 0.0, Double.MIN_VALUE
		};

		contentPane.setLayout(gbl_contentPane);

		// Year Label
		JLabel label_Year = new JLabel("Năm:");
		label_Year.setHorizontalAlignment(SwingConstants.CENTER);
		label_Year.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Year = new GridBagConstraints();
		gbc_label_Year.insets = new Insets(0, 0, 5, 5);
		gbc_label_Year.gridx = 0;
		gbc_label_Year.gridy = 0;

		contentPane.add(label_Year, gbc_label_Year);

		// Year TextField
		textField_Year = new JTextField();

		GridBagConstraints gbc_textField_Year = new GridBagConstraints();
		gbc_textField_Year.gridwidth = 7;
		gbc_textField_Year.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Year.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Year.gridx = 1;
		gbc_textField_Year.gridy = 0;

		contentPane.add(textField_Year, gbc_textField_Year);

		textField_Year.setColumns(10);

		// Scroll Pane
		JScrollPane scrollPane_Statistics = new JScrollPane();

		GridBagConstraints gbc_scrollPane_Statistics = new GridBagConstraints();
		gbc_scrollPane_Statistics.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_Statistics.gridwidth = 8;
		gbc_scrollPane_Statistics.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Statistics.gridx = 0;
		gbc_scrollPane_Statistics.gridy = 1;

		contentPane.add(scrollPane_Statistics, gbc_scrollPane_Statistics);

		// Table
		table_YearlyStatistics = new JTable();

		table_YearlyStatistics.setModel(
				new DefaultTableModel(
						new Object[][] {},
						new String[] {
								"Số tiền",
								"Tháng"
						}
				)
		);

		table_YearlyStatistics.getColumnModel()
				.getColumn(0)
				.setResizable(false);

		scrollPane_Statistics.setViewportView(table_YearlyStatistics);

		// Total Label
		JLabel label_Total = new JLabel("Tổng:");
		label_Total.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Total = new GridBagConstraints();
		gbc_label_Total.insets = new Insets(0, 0, 0, 5);
		gbc_label_Total.gridx = 0;
		gbc_label_Total.gridy = 2;

		contentPane.add(label_Total, gbc_label_Total);

		// Total TextField
		textField_Total = new JTextField();

		GridBagConstraints gbc_textField_Total = new GridBagConstraints();
		gbc_textField_Total.insets = new Insets(0, 0, 0, 5);
		gbc_textField_Total.gridwidth = 6;
		gbc_textField_Total.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Total.gridx = 1;
		gbc_textField_Total.gridy = 2;

		contentPane.add(textField_Total, gbc_textField_Total);

		textField_Total.setColumns(10);

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

		GridBagConstraints gbc_button_Exit = new GridBagConstraints();
		gbc_button_Exit.gridx = 8;
		gbc_button_Exit.gridy = 2;

		contentPane.add(button_Exit, gbc_button_Exit);
	}
}
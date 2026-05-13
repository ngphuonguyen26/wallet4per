package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.table.DefaultTableModel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaMonth extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table_MonthlyStatistics;
	private JTextField textField_Total;
	private JPanel mainChildForm;

	public StaMonth(JPanel childform) {

		this.mainChildForm = childform;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 456);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {157, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[] {0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[] {
				1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE
		};
		gbl_contentPane.rowWeights = new double[] {
				0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE
		};

		contentPane.setLayout(gbl_contentPane);

		// Month Label
		JLabel label_Month = new JLabel("Tháng:");
		label_Month.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Month = new GridBagConstraints();
		gbc_label_Month.insets = new Insets(0, 0, 5, 5);
		gbc_label_Month.gridx = 0;
		gbc_label_Month.gridy = 0;

		contentPane.add(label_Month, gbc_label_Month);

		// Month ComboBox
		JComboBox comboBox_Month = new JComboBox();

		comboBox_Month.setPreferredSize(new Dimension(300, 22));

		comboBox_Month.setModel(
				new DefaultComboBoxModel(
						new String[] {
								"1", "2", "3", "4", "5", "6",
								"7", "8", "9", "10", "11", "12"
						}
				)
		);

		GridBagConstraints gbc_comboBox_Month = new GridBagConstraints();
		gbc_comboBox_Month.gridwidth = 4;
		gbc_comboBox_Month.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Month.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Month.gridx = 1;
		gbc_comboBox_Month.gridy = 0;

		contentPane.add(comboBox_Month, gbc_comboBox_Month);

		// Scroll Pane
		JScrollPane scrollPane_Statistics = new JScrollPane();

		GridBagConstraints gbc_scrollPane_Statistics = new GridBagConstraints();
		gbc_scrollPane_Statistics.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_Statistics.gridwidth = 5;
		gbc_scrollPane_Statistics.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Statistics.gridx = 0;
		gbc_scrollPane_Statistics.gridy = 1;

		contentPane.add(scrollPane_Statistics, gbc_scrollPane_Statistics);

		// Table
		table_MonthlyStatistics = new JTable();

		table_MonthlyStatistics.setModel(
				new DefaultTableModel(
						new Object[][] {},
						new String[] {
								"Số tiền",
								"Ngày"
						}
				)
		);

		scrollPane_Statistics.setViewportView(table_MonthlyStatistics);

		// Total Label
		JLabel label_Total = new JLabel("Tổng:");
		label_Total.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_label_Total = new GridBagConstraints();
		gbc_label_Total.insets = new Insets(0, 0, 5, 5);
		gbc_label_Total.gridx = 0;
		gbc_label_Total.gridy = 2;

		contentPane.add(label_Total, gbc_label_Total);

		// Total TextField
		textField_Total = new JTextField();

		textField_Total.setColumns(10);

		GridBagConstraints gbc_textField_Total = new GridBagConstraints();
		gbc_textField_Total.gridwidth = 3;
		gbc_textField_Total.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Total.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Total.gridx = 1;
		gbc_textField_Total.gridy = 2;

		contentPane.add(textField_Total, gbc_textField_Total);

		// Exit Button
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
		gbc_button_Exit.insets = new Insets(0, 0, 5, 0);
		gbc_button_Exit.gridx = 5;
		gbc_button_Exit.gridy = 2;

		contentPane.add(button_Exit, gbc_button_Exit);
	}
}
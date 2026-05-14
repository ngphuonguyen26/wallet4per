package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class FundGeneral extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel panel_Button;
	private JScrollPane scrollPane_Fund;
	private JTable table_Fund;
	private JButton button_AddFund;

	public FundGeneral() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 446);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));

		scrollPane_Fund = new JScrollPane();
		contentPane.add(scrollPane_Fund, BorderLayout.CENTER);

		table_Fund = new JTable();
		table_Fund.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Tên quỹ", "Số dư", "Mục tiêu", "Ghi chú"
			}
		));

		scrollPane_Fund.setViewportView(table_Fund);

		panel_Button = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_Button.getLayout();
		flowLayout.setVgap(20);

		contentPane.add(panel_Button, BorderLayout.SOUTH);

		button_AddFund = new JButton("Thêm quỹ");
		button_AddFund.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_Button.add(button_AddFund);
	}
}
package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatisticsGeneral extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel mainChildForm;

	public StatisticsGeneral(JPanel childForm) {

		this.mainChildForm = childForm;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new GridLayout(4, 1, 0, 0));

		// Top Panel
		JPanel panel_Top = new JPanel();

		contentPane.add(panel_Top);

		// Day Panel
		JPanel panel_Day = new JPanel();

		contentPane.add(panel_Day);

		JButton button_StatisticsDay = new JButton("Thống kê theo ngày");

		button_StatisticsDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StaDays day = new StaDays(childForm);

				openForm(day);
			}
		});

		button_StatisticsDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_StatisticsDay.setPreferredSize(new Dimension(200, 40));

		panel_Day.add(button_StatisticsDay);

		// Month Panel
		JPanel panel_Month = new JPanel();

		contentPane.add(panel_Month);

		JButton button_StatisticsMonth = new JButton("Thống kê theo tháng");

		button_StatisticsMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StaMonth month = new StaMonth(childForm);

				openForm(month);
			}
		});

		button_StatisticsMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_StatisticsMonth.setPreferredSize(new Dimension(200, 40));

		panel_Month.add(button_StatisticsMonth);

		// Year Panel
		JPanel panel_Year = new JPanel();

		contentPane.add(panel_Year);

		JButton button_StatisticsYear = new JButton("Thống kê theo năm");

		button_StatisticsYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StaYear year = new StaYear(childForm);

				openForm(year);
			}
		});

		button_StatisticsYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_StatisticsYear.setPreferredSize(new Dimension(200, 40));

		panel_Year.add(button_StatisticsYear);
	}

	public void openForm(JFrame form) {

		CardLayout cl = (CardLayout) mainChildForm.getLayout();

		String id = String.valueOf(form.hashCode());

		mainChildForm.add(form.getContentPane(), id);

		cl.show(mainChildForm, id);
	}
}
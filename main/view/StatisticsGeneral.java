package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;

public class StatisticsGeneral extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel mainChildForm;
	private model.User currentUser;

	public StatisticsGeneral(JPanel childForm, model.User currentUser) {

		this.mainChildForm = childForm;
		this.currentUser = currentUser;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new GridLayout(4, 1, 0, 0));

		JPanel panel_Top = new JPanel();
		contentPane.add(panel_Top);

		JPanel panel_Day = new JPanel();
		contentPane.add(panel_Day);

		JButton button_StatisticsDay = new JButton("Thống kê theo ngày");
		button_StatisticsDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_StatisticsDay.setPreferredSize(new Dimension(200, 40));
		button_StatisticsDay.addActionListener(e -> {
			StaDays day = new StaDays(mainChildForm, currentUser.getId());
			openForm(day);
		});
		panel_Day.add(button_StatisticsDay);

		JPanel panel_Month = new JPanel();
		contentPane.add(panel_Month);

		JButton button_StatisticsMonth = new JButton("Thống kê theo tháng");
		button_StatisticsMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_StatisticsMonth.setPreferredSize(new Dimension(200, 40));
		button_StatisticsMonth.addActionListener(e -> {
			StaMonth month = new StaMonth(mainChildForm, currentUser.getId());
			openForm(month);
		});
		panel_Month.add(button_StatisticsMonth);

		JPanel panel_Year = new JPanel();
		contentPane.add(panel_Year);

		JButton button_StatisticsYear = new JButton("Thống kê theo năm");
		button_StatisticsYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_StatisticsYear.setPreferredSize(new Dimension(200, 40));
		button_StatisticsYear.addActionListener(e -> {
			StaYear year = new StaYear(mainChildForm, currentUser.getId());
			openForm(year);
		});
		panel_Year.add(button_StatisticsYear);
	}

	public void openForm(JFrame form) {
		CardLayout cl = (CardLayout) mainChildForm.getLayout();

		String id = String.valueOf(form.hashCode());

		mainChildForm.add(form.getContentPane(), id);
		cl.show(mainChildForm, id);

		mainChildForm.revalidate();
		mainChildForm.repaint();
	}
}
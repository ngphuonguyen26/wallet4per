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

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public StatisticsGeneral(JPanel childForm) {
		this.mainChildForm = childForm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JButton btStaDay = new JButton("Thống kê theo ngày");
		btStaDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaDays day = new StaDays(childForm);
				openForm(day);
			}
		});
		btStaDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btStaDay.setPreferredSize(new Dimension(200, 40));
		panel_2.add(btStaDay);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		JButton btStaMonth = new JButton("Thống kê theo tháng");
		btStaMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaMonth month = new StaMonth(childForm);
				openForm(month);
			}
		});
		btStaMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btStaMonth.setPreferredSize(new Dimension(200, 40));
		panel_3.add(btStaMonth);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton btStaYear = new JButton("Thống kê theo năm");
		btStaYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaYear year = new StaYear(childForm);
				openForm(year);
			}
		});
		btStaYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btStaYear.setPreferredSize(new Dimension(200, 40));
		panel.add(btStaYear);

	}
	public void openForm(JFrame form)
	{
		 CardLayout cl = (CardLayout) mainChildForm.getLayout();
		 String id = String.valueOf(form.hashCode());
		 mainChildForm.add(form.getContentPane(), id);
		 cl.show(mainChildForm, id);
	}
}

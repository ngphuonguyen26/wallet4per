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
	private JTable table;
	private JTextField textField;
	private JPanel mainChildForm;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public StaDays(JPanel childform) {
		this.mainChildForm = childform;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 72, 42, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lbDay = new JLabel("Ngày:");
		lbDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbDay = new GridBagConstraints();
		gbc_lbDay.insets = new Insets(0, 0, 5, 5);
		gbc_lbDay.gridx = 0;
		gbc_lbDay.gridy = 1;
		contentPane.add(lbDay, gbc_lbDay);
		
		JSpinner spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(400, 20));
		spinner.setModel(new SpinnerDateModel(new Date(1778518800000L), null, null, Calendar.DAY_OF_YEAR));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.gridwidth = 3;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 1;
		contentPane.add(spinner, gbc_spinner);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "V\u00ED", "S\u1ED1 ti\u1EC1n", "Danh m\u1EE5c", "Lo\u1EA1i", "Ghi ch\u00FA"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lbSum = new JLabel("Tổng:");
		lbSum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbSum = new GridBagConstraints();
		gbc_lbSum.anchor = GridBagConstraints.EAST;
		gbc_lbSum.insets = new Insets(0, 0, 0, 5);
		gbc_lbSum.gridx = 0;
		gbc_lbSum.gridy = 3;
		contentPane.add(lbSum, gbc_lbSum);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		contentPane.add(textField, gbc_textField);
		
		JButton btExit = new JButton("Thoát");
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) mainChildForm.getLayout();
				cl.previous(mainChildForm);
				mainChildForm.remove(contentPane); 
				mainChildForm.revalidate();
			    mainChildForm.repaint();
			}
		});
		btExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btExit = new GridBagConstraints();
		gbc_btExit.gridx = 4;
		gbc_btExit.gridy = 3;
		contentPane.add(btExit, gbc_btExit);

	}

}

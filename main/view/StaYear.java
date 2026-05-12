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
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btExit;
	private JPanel mainChildForm;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public StaYear(JPanel childform) {
		this.mainChildForm = childform;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{126, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 314, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lbYear = new JLabel("Năm:");
		lbYear.setHorizontalAlignment(SwingConstants.CENTER);
		lbYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbYear = new GridBagConstraints();
		gbc_lbYear.insets = new Insets(0, 0, 5, 5);
		gbc_lbYear.gridx = 0;
		gbc_lbYear.gridy = 0;
		contentPane.add(lbYear, gbc_lbYear);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 8;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S\u1ED1 ti\u1EC1n", "Th\u00E1ng"
			}
		));
		table.getColumnModel().getColumn(0).setResizable(false);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 2;
		scrollPane.setViewportView(table);
		
		JLabel lbSum = new JLabel("Tổng:");
		lbSum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbSum = new GridBagConstraints();
		gbc_lbSum.insets = new Insets(0, 0, 0, 5);
		gbc_lbSum.gridx = 0;
		gbc_lbSum.gridy = 2;
		contentPane.add(lbSum, gbc_lbSum);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.gridwidth = 6;
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		btExit = new JButton("Thoát");
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
		gbc_btExit.gridx = 8;
		gbc_btExit.gridy = 2;
		contentPane.add(btExit, gbc_btExit);

	}

}

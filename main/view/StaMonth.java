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
	private JTable table;
	private JTextField textField;
	private JPanel mainChildForm;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public StaMonth(JPanel childform) {
		this.mainChildForm = childform;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{157, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lbMonth = new JLabel("Tháng:");
		lbMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbMonth = new GridBagConstraints();
		gbc_lbMonth.insets = new Insets(0, 0, 5, 5);
		gbc_lbMonth.gridx = 0;
		gbc_lbMonth.gridy = 0;
		contentPane.add(lbMonth, gbc_lbMonth);
		
		JComboBox cbbMonth = new JComboBox();
		cbbMonth.setPreferredSize(new Dimension(300, 22));
		cbbMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		GridBagConstraints gbc_cbbMonth = new GridBagConstraints();
		gbc_cbbMonth.gridwidth = 4;
		gbc_cbbMonth.insets = new Insets(0, 0, 5, 5);
		gbc_cbbMonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbbMonth.gridx = 1;
		gbc_cbbMonth.gridy = 0;
		contentPane.add(cbbMonth, gbc_cbbMonth);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S\u1ED1 ti\u1EC1n", "Ng\u00E0y"
			}
		));
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 2;
		scrollPane.setViewportView(table);
		
		JLabel lbSum = new JLabel("Tổng:");
		lbSum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbSum = new GridBagConstraints();
		gbc_lbSum.insets = new Insets(0, 0, 5, 5);
		gbc_lbSum.gridx = 0;
		gbc_lbSum.gridy = 2;
		contentPane.add(lbSum, gbc_lbSum);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
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
		gbc_btExit.insets = new Insets(0, 0, 5, 0);
		gbc_btExit.gridx = 5;
		gbc_btExit.gridy = 2;
		contentPane.add(btExit, gbc_btExit);

	}

}

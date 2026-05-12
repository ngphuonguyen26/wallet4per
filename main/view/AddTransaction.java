package main.view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddTransaction extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel no;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel mainChildForm;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public AddTransaction(JPanel childform) {
		this.mainChildForm = childform;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 340);
		no = new JPanel();
		no.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(no);
		GridBagLayout gbl_no = new GridBagLayout();
		gbl_no.columnWidths = new int[] {30, 113, 30, 0, 0};
		gbl_no.rowHeights = new int[]{39, 43, 43, 44, 40, 0, 0};
		gbl_no.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_no.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		no.setLayout(gbl_no);
		
		JLabel lbWallet = new JLabel("Ví:");
		lbWallet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbWallet = new GridBagConstraints();
		gbc_lbWallet.insets = new Insets(0, 0, 5, 5);
		gbc_lbWallet.gridx = 1;
		gbc_lbWallet.gridy = 1;
		no.add(lbWallet, gbc_lbWallet);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		no.add(comboBox, gbc_comboBox);
		
		JLabel lbCata = new JLabel("Danh mục:");
		lbCata.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbCata = new GridBagConstraints();
		gbc_lbCata.insets = new Insets(0, 0, 5, 5);
		gbc_lbCata.gridx = 1;
		gbc_lbCata.gridy = 2;
		no.add(lbCata, gbc_lbCata);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 2;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 2;
		no.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lbAmount = new JLabel("Số tiền");
		lbAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbAmount = new GridBagConstraints();
		gbc_lbAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lbAmount.gridx = 1;
		gbc_lbAmount.gridy = 3;
		no.add(lbAmount, gbc_lbAmount);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		no.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lbNote = new JLabel("Ghi chú:");
		lbNote.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbNote = new GridBagConstraints();
		gbc_lbNote.insets = new Insets(0, 0, 5, 5);
		gbc_lbNote.gridx = 1;
		gbc_lbNote.gridy = 4;
		no.add(lbNote, gbc_lbNote);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 4;
		no.add(textField_1, gbc_textField_1);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(100);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 5;
		no.add(panel, gbc_panel);
		
		JButton btAdd = new JButton("Thêm");
		btAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// bt add
			}
		});
		btAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btAdd);
		
		JButton btExit = new JButton("Thoát");
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) mainChildForm.getLayout();
				cl.previous(mainChildForm);
				mainChildForm.remove(no); 
				mainChildForm.revalidate();
		        mainChildForm.repaint();
			}
		});
		btExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btExit);

	}

}

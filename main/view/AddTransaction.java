package main.view;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddTransaction extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField_Wallet;
	private JTextField textField_Category;
	private JPanel mainChildForm;

	public AddTransaction(JPanel childform) {

		this.mainChildForm = childform;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 340);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {30, 113, 30, 0, 0};
		gbl_contentPane.rowHeights = new int[] {39, 43, 43, 44, 40, 0, 0};
		gbl_contentPane.columnWeights = new double[] {0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};

		contentPane.setLayout(gbl_contentPane);

		// Wallet
		JLabel lbWallet = new JLabel("Ví:");
		lbWallet.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_lbWallet = new GridBagConstraints();
		gbc_lbWallet.insets = new Insets(0, 0, 5, 5);
		gbc_lbWallet.gridx = 1;
		gbc_lbWallet.gridy = 1;

		contentPane.add(lbWallet, gbc_lbWallet);

		JComboBox comboBox = new JComboBox();

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;

		contentPane.add(comboBox, gbc_comboBox);

		// Category
		JLabel lbCate = new JLabel("Danh mục:");
		lbCate.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_lbCate = new GridBagConstraints();
		gbc_lbCate.insets = new Insets(0, 0, 5, 5);
		gbc_lbCate.gridx = 1;
		gbc_lbCate.gridy = 2;

		contentPane.add(lbCate, gbc_lbCate);

		JComboBox comboBox_1 = new JComboBox();

		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 2;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 2;

		contentPane.add(comboBox_1, gbc_comboBox_1);

		// Amount
		JLabel lbAmount = new JLabel("Số tiền");
		lbAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_lbAmount = new GridBagConstraints();
		gbc_lbAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lbAmount.gridx = 1;
		gbc_lbAmount.gridy = 3;

		contentPane.add(lbAmount, gbc_lbAmount);

		textField_Wallet = new JTextField();

		GridBagConstraints gbc_textField_Wallet = new GridBagConstraints();
		gbc_textField_Wallet.gridwidth = 2;
		gbc_textField_Wallet.insets = new Insets(0, 0, 5, 0);
		gbc_textField_Wallet.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Wallet.gridx = 2;
		gbc_textField_Wallet.gridy = 3;

		contentPane.add(textField_Wallet, gbc_textField_Wallet);
		textField_Wallet.setColumns(10);

		// Note
		JLabel lbNote = new JLabel("Ghi chú:");
		lbNote.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GridBagConstraints gbc_lbNote = new GridBagConstraints();
		gbc_lbNote.insets = new Insets(0, 0, 5, 5);
		gbc_lbNote.gridx = 1;
		gbc_lbNote.gridy = 4;

		contentPane.add(lbNote, gbc_lbNote);

		textField_Category = new JTextField();
		textField_Category.setColumns(10);

		GridBagConstraints gbc_textField_Category = new GridBagConstraints();
		gbc_textField_Category.gridwidth = 2;
		gbc_textField_Category.insets = new Insets(0, 0, 5, 0);
		gbc_textField_Category.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Category.gridx = 2;
		gbc_textField_Category.gridy = 4;

		contentPane.add(textField_Category, gbc_textField_Category);

		// Button panel
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

		contentPane.add(panel, gbc_panel);

		// Add button
		JButton button_Add = new JButton("Thêm");
		button_Add.setFont(new Font("Tahoma", Font.PLAIN, 14));

		button_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Them
			}
		});

		panel.add(button_Add);

		// Exit button
		JButton button_Exit = new JButton("Thoát");
		button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));

		button_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cl = (CardLayout) mainChildForm.getLayout();

				cl.previous(mainChildForm);

				mainChildForm.remove(contentPane);

				mainChildForm.revalidate();
				mainChildForm.repaint();
			}
		});

		panel.add(button_Exit);
	}
}
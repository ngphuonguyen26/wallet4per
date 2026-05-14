package main.view;

import util.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddTransaction extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel mainChildForm;

	private JComboBox<WalletItem> comboBox_Wallet;
	private JComboBox<CategoryItem> comboBox_Category;
	private JComboBox<String> comboBox_Type;

	private JTextField textField_Amount;
	private JTextField textField_Note;

	private JButton button_Add;
	private JButton button_Exit;

	private int userId;

	public AddTransaction(JPanel childform, int userId) {
		this.mainChildForm = childform;
		this.userId = userId;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 648, 340);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{30, 113, 30, 250, 0};
		gbl_contentPane.rowHeights = new int[]{30, 43, 43, 43, 43, 43, 50};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lbType = new JLabel("Loại:");
		lbType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addComponent(lbType, 1, 1);

		comboBox_Type = new JComboBox<>();
		comboBox_Type.addItem("Thu");
		comboBox_Type.addItem("Chi");
		addInput(comboBox_Type, 3, 1);

		JLabel lbWallet = new JLabel("Ví:");
		lbWallet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addComponent(lbWallet, 1, 2);

		comboBox_Wallet = new JComboBox<>();
		addInput(comboBox_Wallet, 3, 2);

		JLabel lbCate = new JLabel("Danh mục:");
		lbCate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addComponent(lbCate, 1, 3);

		comboBox_Category = new JComboBox<>();
		addInput(comboBox_Category, 3, 3);

		JLabel lbAmount = new JLabel("Số tiền:");
		lbAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addComponent(lbAmount, 1, 4);

		textField_Amount = new JTextField();
		addInput(textField_Amount, 3, 4);

		JLabel lbNote = new JLabel("Ghi chú:");
		lbNote.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addComponent(lbNote, 1, 5);

		textField_Note = new JTextField();
		addInput(textField_Note, 3, 5);

		JPanel panel_Button = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));

		button_Add = new JButton("Thêm");
		button_Add.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_Button.add(button_Add);

		button_Exit = new JButton("Thoát");
		button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_Button.add(button_Exit);

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 6;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(panel_Button, gbc_panel);

		loadWallets();
		loadCategories();

		addEvents();
	}

	private void addComponent(Component component, int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.anchor = GridBagConstraints.WEST;
		contentPane.add(component, gbc);
	}

	private void addInput(Component component, int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(component, gbc);
	}

	private Connection getConn() {
		return ((DBConnection) DBConnection.getInstance()).getConnection();
	}

	private void addEvents() {
		comboBox_Type.addActionListener(e -> loadCategories());

		button_Add.addActionListener(e -> addTransaction());

		button_Exit.addActionListener(e -> {
			if (mainChildForm != null) {
				mainChildForm.removeAll();
				mainChildForm.revalidate();
				mainChildForm.repaint();
			}
		});
	}

	private void loadWallets() {
		comboBox_Wallet.removeAllItems();

		String sql = "SELECT wallet_id, wallet_name, balance FROM wallets WHERE user_id = ? ORDER BY wallet_name";

		try (PreparedStatement ps = getConn().prepareStatement(sql)) {
			ps.setInt(1, userId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					WalletItem item = new WalletItem(
							rs.getInt("wallet_id"),
							rs.getString("wallet_name"),
							rs.getBigDecimal("balance")
					);
					comboBox_Wallet.addItem(item);
				}
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi load ví: " + e.getMessage());
		}
	}

	private void loadCategories() {
		comboBox_Category.removeAllItems();

		String type = (String) comboBox_Type.getSelectedItem();

		String sql = "SELECT category_id, category_name, type FROM categories WHERE type = ? ORDER BY category_name";

		try (PreparedStatement ps = getConn().prepareStatement(sql)) {
			ps.setString(1, type);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					CategoryItem item = new CategoryItem(
							rs.getInt("category_id"),
							rs.getString("category_name"),
							rs.getString("type")
					);
					comboBox_Category.addItem(item);
				}
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi load danh mục: " + e.getMessage());
		}
	}

	private void addTransaction() {
		WalletItem wallet = (WalletItem) comboBox_Wallet.getSelectedItem();
		CategoryItem category = (CategoryItem) comboBox_Category.getSelectedItem();

		String type = (String) comboBox_Type.getSelectedItem();
		String amountText = textField_Amount.getText().trim();
		String note = textField_Note.getText().trim();

		if (wallet == null) {
			JOptionPane.showMessageDialog(this, "Bạn chưa có ví nào.");
			return;
		}

		if (category == null) {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn danh mục.");
			return;
		}

		if (amountText.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền.");
			return;
		}

		BigDecimal amount;

		try {
			amount = new BigDecimal(amountText);
			if (amount.compareTo(BigDecimal.ZERO) <= 0) {
				JOptionPane.showMessageDialog(this, "Số tiền phải lớn hơn 0.");
				return;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Số tiền không hợp lệ.");
			return;
		}

		Connection conn = getConn();

		String insertSql = """
                INSERT INTO transactions
                (user_id, wallet_id, category_id, amount, type, note, transaction_date)
                VALUES (?, ?, ?, ?, ?, ?, GETDATE())
                """;

		String updateWalletSql = """
                UPDATE wallets
                SET balance = balance + ?
                WHERE wallet_id = ?
                """;

		try {
			conn.setAutoCommit(false);

			try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
				ps.setInt(1, userId);
				ps.setInt(2, wallet.walletId);
				ps.setInt(3, category.categoryId);
				ps.setBigDecimal(4, amount);
				ps.setString(5, type);
				ps.setString(6, note);
				ps.executeUpdate();
			}

			BigDecimal delta;

			if ("Thu".equals(type)) {
				delta = amount;
			} else {
				delta = amount.negate();
			}

			try (PreparedStatement ps = conn.prepareStatement(updateWalletSql)) {
				ps.setBigDecimal(1, delta);
				ps.setInt(2, wallet.walletId);
				ps.executeUpdate();
			}

			conn.commit();

			JOptionPane.showMessageDialog(this, "Thêm giao dịch thành công!");

			textField_Amount.setText("");
			textField_Note.setText("");
			loadWallets();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			JOptionPane.showMessageDialog(this, "Lỗi thêm giao dịch: " + e.getMessage());

		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static class WalletItem {
		private int walletId;
		private String walletName;
		private BigDecimal balance;

		public WalletItem(int walletId, String walletName, BigDecimal balance) {
			this.walletId = walletId;
			this.walletName = walletName;
			this.balance = balance;
		}

		@Override
		public String toString() {
			return walletName + " - " + balance;
		}
	}

	private static class CategoryItem {
		private int categoryId;
		private String categoryName;
		private String type;

		public CategoryItem(int categoryId, String categoryName, String type) {
			this.categoryId = categoryId;
			this.categoryName = categoryName;
			this.type = type;
		}

		@Override
		public String toString() {
			return categoryName;
		}
	}
}
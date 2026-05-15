package view;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import util.DBConnection;

public class AddTransaction extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel mainChildForm;

    private JComboBox<WalletItem>   comboBox_Wallet;
    private JComboBox<CategoryItem> comboBox_Category;
    private JComboBox<String>       comboBox_Type;

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
        setTitle("Thêm giao dịch");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths  = new int[]{30, 113, 30, 250, 0};
        gbl.rowHeights    = new int[]{30, 43, 43, 43, 43, 43, 50};
        gbl.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        addLabel("Loại:", 1, 1);
        comboBox_Type = new JComboBox<>(new String[]{"Thu", "Chi"});
        addInput(comboBox_Type, 3, 1);

        addLabel("Ví:", 1, 2);
        comboBox_Wallet = new JComboBox<>();
        addInput(comboBox_Wallet, 3, 2);

        addLabel("Danh mục:", 1, 3);
        comboBox_Category = new JComboBox<>();
        addInput(comboBox_Category, 3, 3);

        addLabel("Số tiền:", 1, 4);
        textField_Amount = new JTextField();
        addInput(textField_Amount, 3, 4);

        addLabel("Ghi chú:", 1, 5);
        textField_Note = new JTextField();
        addInput(textField_Note, 3, 5);

        JPanel panel_Button = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        button_Add  = new JButton("Thêm");
        button_Add.setFont(new Font("Tahoma", Font.PLAIN, 14));
        button_Exit = new JButton("Thoát");
        button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_Button.add(button_Add);
        panel_Button.add(button_Exit);

        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 3; gbc_panel.gridx = 1; gbc_panel.gridy = 6;
        gbc_panel.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(panel_Button, gbc_panel);

        loadWallets();
        loadCategories();
        addEvents();
    }

    private void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = x; gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lbl, gbc);
    }

    private void addInput(Component comp, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = x; gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(comp, gbc);
    }

    // FIX: bỏ cast sai — getInstance() đã trả về DBConnection rồi
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
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboBox_Wallet.addItem(new WalletItem(
                        rs.getInt("wallet_id"),
                        rs.getString("wallet_name"),
                        rs.getBigDecimal("balance")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi load ví: " + e.getMessage());
        }
    }

    private void loadCategories() {
    comboBox_Category.removeAllItems();
    String selectedType = (String) comboBox_Type.getSelectedItem(); // "Thu" hoặc "Chi"
    
    // Chuyển đổi "Thu"/"Chi" thành "INCOME"/"EXPENSE" để match database
    String dbType;
    if ("Thu".equals(selectedType)) {
        dbType = "INCOME";
    } else {
        dbType = "EXPENSE";
    }
    
    // Lấy cả danh mục hệ thống (user_id IS NULL) và danh mục riêng của user
    String sql = "SELECT category_id, category_name, type FROM categories "
               + "WHERE type = ? AND (user_id IS NULL OR user_id = ?) "
               + "ORDER BY category_name";
    try (PreparedStatement ps = getConn().prepareStatement(sql)) {
        ps.setString(1, dbType);  // Dùng "INCOME" hoặc "EXPENSE"
        ps.setInt(2, userId);
        ResultSet rs = ps.executeQuery();
        
        int count = 0;
        while (rs.next()) {
            comboBox_Category.addItem(new CategoryItem(
                    rs.getInt("category_id"),
                    rs.getString("category_name"),
                    rs.getString("type")
            ));
            count++;
        }
        
        if (count == 0) {
            System.out.println("No categories found for type: " + dbType);
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi load danh mục: " + e.getMessage());
        e.printStackTrace();
    }
}

    private void addTransaction() {
    WalletItem wallet     = (WalletItem) comboBox_Wallet.getSelectedItem();
    CategoryItem category = (CategoryItem) comboBox_Category.getSelectedItem();
    String type           = (String) comboBox_Type.getSelectedItem(); // "Thu" hoặc "Chi"
    String amountText     = textField_Amount.getText().trim();
    String note           = textField_Note.getText().trim();

    if (wallet == null)   { JOptionPane.showMessageDialog(this, "Bạn chưa có ví nào."); return; }
    if (category == null) { JOptionPane.showMessageDialog(this, "Bạn chưa chọn danh mục."); return; }
    if (amountText.isEmpty()) { JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền."); return; }

    BigDecimal amount;
    try {
        amount = new BigDecimal(amountText);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "Số tiền phải lớn hơn 0.");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Số tiền không hợp lệ. Nhập số, ví dụ: 50000");
        return;
    }

    // Chuyển đổi "Thu"/"Chi" thành "INCOME"/"EXPENSE" để lưu vào database
    String dbType = "Thu".equals(type) ? "INCOME" : "EXPENSE";

    Connection conn = getConn();
    String insertSql = "INSERT INTO transactions (user_id, wallet_id, category_id, amount, type, note, transaction_date) "
                     + "VALUES (?, ?, ?, ?, ?, ?, GETDATE())";
    String updateSql = "UPDATE wallets SET balance = balance + ? WHERE wallet_id = ?";

    try {
        conn.setAutoCommit(false);

        try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setInt(1, userId);
            ps.setInt(2, wallet.walletId);
            ps.setInt(3, category.categoryId);
            ps.setBigDecimal(4, amount);
            ps.setString(5, dbType);  // Lưu "INCOME" hoặc "EXPENSE"
            ps.setString(6, note);
            ps.executeUpdate();
        }

        // Cập nhật số dư ví
        BigDecimal delta = "Thu".equals(type) ? amount : amount.negate();
        try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
            ps.setBigDecimal(1, delta);
            ps.setInt(2, wallet.walletId);
            ps.executeUpdate();
        }

        conn.commit();
        JOptionPane.showMessageDialog(this, "Thêm giao dịch thành công!");
        textField_Amount.setText("");
        textField_Note.setText("");
        loadWallets(); // Refresh danh sách ví

    } catch (SQLException e) {
        try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        JOptionPane.showMessageDialog(this, "Lỗi thêm giao dịch: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try { conn.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
    }
}

    // ---- Inner classes ----
    private static class WalletItem {
        int walletId; String walletName; BigDecimal balance;
        WalletItem(int id, String name, BigDecimal bal) { walletId = id; walletName = name; balance = bal; }
        @Override public String toString() { return walletName + " - " + balance; }
    }

    private static class CategoryItem {
        int categoryId; String categoryName; String type;
        CategoryItem(int id, String name, String type) { categoryId = id; categoryName = name; this.type = type; }
        @Override public String toString() { return categoryName; }
    }
}
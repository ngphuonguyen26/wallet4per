package view;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import util.DBConnection;

/**
 * FundGeneral - Xem và thêm quỹ tiết kiệm
 * FIX: nhận userId, load dữ liệu từ DB, xử lý nút Thêm quỹ
 */
public class FundGeneral extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final NumberFormat VND = NumberFormat.getInstance(new Locale("vi", "VN"));

    private JPanel contentPane;
    private JPanel mainChildForm;
    private JTable table_Fund;
    private DefaultTableModel model_Fund;
    private JButton button_AddFund;
    private JButton button_Reload;

    private int userId;

    public FundGeneral(JPanel childform, int userId) {
        this.mainChildForm = childform;
        this.userId = userId;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 620, 460);
        setTitle("Quỹ tiết kiệm");

        contentPane = new JPanel(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Table quỹ
        model_Fund = new DefaultTableModel(
                new String[]{"ID", "Tên quỹ", "Số dư", "Mục tiêu", "% đạt", "Ghi chú"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table_Fund = new JTable(model_Fund);
        table_Fund.setRowHeight(26);
        contentPane.add(new JScrollPane(table_Fund), BorderLayout.CENTER);

        // Nút
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        button_AddFund = new JButton("➕ Thêm quỹ");
        button_AddFund.setFont(new Font("Tahoma", Font.PLAIN, 14));
        button_Reload = new JButton("🔄 Tải lại");
        button_Reload.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelBottom.add(button_AddFund);
        panelBottom.add(button_Reload);
        contentPane.add(panelBottom, BorderLayout.SOUTH);

        addEvents();
        loadFunds();
    }

    private void addEvents() {
        button_Reload.addActionListener(e -> loadFunds());

        button_AddFund.addActionListener(e -> {
            // Dialog thêm quỹ mới
            JTextField txtName   = new JTextField(16);
            JTextField txtTarget = new JTextField(16);
            JTextField txtNote   = new JTextField(16);

            JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
            form.add(new JLabel("Tên quỹ:")); form.add(txtName);
            form.add(new JLabel("Mục tiêu (đ):")); form.add(txtTarget);
            form.add(new JLabel("Ghi chú:")); form.add(txtNote);

            int result = JOptionPane.showConfirmDialog(
                    this, form, "Thêm quỹ tiết kiệm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                String name   = txtName.getText().trim();
                String target = txtTarget.getText().trim();
                String note   = txtNote.getText().trim();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tên quỹ.");
                    return;
                }

                BigDecimal targetAmt = BigDecimal.ZERO;
                if (!target.isEmpty()) {
                    try { targetAmt = new BigDecimal(target); }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Mục tiêu không hợp lệ.");
                        return;
                    }
                }

                String sql = "INSERT INTO savings_funds (user_id, fund_name, target, balance, note) VALUES (?,?,?,0,?)";
                try (PreparedStatement ps = ((DBConnection) DBConnection.getInstance()).getConnection().prepareStatement(sql)) {
                    ps.setInt(1, userId);
                    ps.setString(2, name);
                    ps.setBigDecimal(3, targetAmt);
                    ps.setString(4, note);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Tạo quỹ thành công!");
                    loadFunds();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi tạo quỹ: " + ex.getMessage());
                }
            }
        });
    }

    private void loadFunds() {
        model_Fund.setRowCount(0);
        String sql = "SELECT fund_id, fund_name, balance, target, note FROM savings_funds WHERE user_id = ? ORDER BY created_at DESC";
        try (PreparedStatement ps = ((DBConnection) DBConnection.getInstance()).getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BigDecimal balance = rs.getBigDecimal("balance");
                BigDecimal target  = rs.getBigDecimal("target");
                String progress = "-";
                if (target != null && target.compareTo(BigDecimal.ZERO) > 0) {
                    double pct = balance.divide(target, 4, java.math.RoundingMode.HALF_UP)
                                       .multiply(BigDecimal.valueOf(100)).doubleValue();
                    progress = String.format("%.1f%%", pct);
                }
                model_Fund.addRow(new Object[]{
                        rs.getInt("fund_id"),
                        rs.getString("fund_name"),
                        VND.format(balance) + " đ",
                        target != null ? VND.format(target) + " đ" : "-",
                        progress,
                        rs.getString("note") != null ? rs.getString("note") : ""
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi load quỹ: " + e.getMessage());
        }
    }
}
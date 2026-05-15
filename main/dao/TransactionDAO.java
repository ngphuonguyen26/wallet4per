package dao;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;
import util.DBConnection;


// DAO xử lý giao dịch thu/chi + thống kê theo ngày/tuần/tháng/năm.

public class TransactionDAO {

    private Connection getConn() {
        return ((DBConnection) DBConnection.getInstance()).getConnection();
    }
    // Thêm giao dịch mới + cập nhật số dư ví (trong 1 transaction)
    public boolean addTransaction(Transaction t) {
        Connection conn = getConn();
        String sqlInsert = "INSERT INTO transactions (user_id, wallet_id, category_id, amount, type, note, transaction_date) "
                         + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlBalance = "UPDATE wallets SET balance = balance + ? WHERE wallet_id = ?";

        try {
            conn.setAutoCommit(false);

            // 1. Thêm giao dịch
            try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
                ps.setInt(1, t.getUserId());
                ps.setInt(2, t.getWalletId());
                ps.setInt(3, t.getCategoryId());
                ps.setBigDecimal(4, t.getAmount());
                ps.setString(5, t.getType().name());
                ps.setString(6, t.getNote());
                ps.setTimestamp(7, t.getTransactionDate() != null
                        ? Timestamp.valueOf(t.getTransactionDate())
                        : new Timestamp(System.currentTimeMillis()));
                ps.executeUpdate();
            }

            // 2. Cập nhật số dư ví: Thu -> cộng, Chi -> trừ
            BigDecimal delta = t.getType() == Transaction.TransactionType.INCOME
                    ? t.getAmount()
                    : t.getAmount().negate();
            try (PreparedStatement ps = conn.prepareStatement(sqlBalance)) {
                ps.setBigDecimal(1, delta);
                ps.setInt(2, t.getWalletId());
                ps.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.getMessage(); }
            System.err.println("[TransactionDAO] addTransaction: " + e.getMessage());
            return false;
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException e) { e.getMessage(); }
        }
    }
    // Xóa giao dịch + hoàn lại số dư ví
    public boolean deleteTransaction(int transactionId) {
        Connection conn = getConn();
        String sqlGet    = "SELECT wallet_id, amount, type FROM transactions WHERE transaction_id = ?";
        String sqlDelete = "DELETE FROM transactions WHERE transaction_id = ?";
        String sqlBalance= "UPDATE wallets SET balance = balance + ? WHERE wallet_id = ?";

        try {
            conn.setAutoCommit(false);

            int walletId; BigDecimal amount; String type;
            try (PreparedStatement ps = conn.prepareStatement(sqlGet)) {
                ps.setInt(1, transactionId);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) { conn.rollback(); return false; }
                walletId = rs.getInt("wallet_id");
                amount   = rs.getBigDecimal("amount");
                type     = rs.getString("type");
            }

            try (PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
                ps.setInt(1, transactionId);
                ps.executeUpdate();
            }

            // Hoàn lại số dư ngược chiều
            BigDecimal delta = "INCOME".equals(type) ? amount.negate() : amount;
            try (PreparedStatement ps = conn.prepareStatement(sqlBalance)) {
                ps.setBigDecimal(1, delta);
                ps.setInt(2, walletId);
                ps.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.getMessage(); }
            System.err.println("[TransactionDAO] deleteTransaction: " + e.getMessage());
            return false;
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException e) { e.getMessage(); }
        }
    }

    // Lấy danh sách giao dịch của user (JOIN wallet & category)
    public List<Transaction> getTransactionsByUser(int userId) {
        return queryTransactions(
            "SELECT t.*, w.wallet_name, c.category_name "
          + "FROM transactions t "
          + "JOIN wallets w ON t.wallet_id = w.wallet_id "
          + "JOIN categories c ON t.category_id = c.category_id "
          + "WHERE t.user_id = ? "
          + "ORDER BY t.transaction_date DESC",
            userId
        );
    }
    // THỐNG KÊ: Chi tiêu trong NGÀY
    public List<Transaction> getTransactionsByDay(int userId, LocalDate date) {
        String sql = "SELECT t.*, w.wallet_name, c.category_name "
                   + "FROM transactions t "
                   + "JOIN wallets w ON t.wallet_id = w.wallet_id "
                   + "JOIN categories c ON t.category_id = c.category_id "
                   + "WHERE t.user_id = ? "
                   + "AND CAST(t.transaction_date AS DATE) = ? "
                   + "ORDER BY t.transaction_date DESC";
        List<Transaction> list = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setDate(2, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));
        } catch (SQLException e) {
            System.err.println("[TransactionDAO] getByDay: " + e.getMessage());
        }
        return list;
    }
    // THỐNG KÊ: Chi tiêu trong TUẦN (7 ngày tính từ date)

    public List<Transaction> getTransactionsByWeek(int userId, LocalDate startOfWeek) {
        String sql = "SELECT t.*, w.wallet_name, c.category_name "
                   + "FROM transactions t "
                   + "JOIN wallets w ON t.wallet_id = w.wallet_id "
                   + "JOIN categories c ON t.category_id = c.category_id "
                   + "WHERE t.user_id = ? "
                   + "AND CAST(t.transaction_date AS DATE) BETWEEN ? AND ? "
                   + "ORDER BY t.transaction_date DESC";
        List<Transaction> list = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setDate(2, Date.valueOf(startOfWeek));
            ps.setDate(3, Date.valueOf(startOfWeek.plusDays(6)));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));
        } catch (SQLException e) {
            System.err.println("[TransactionDAO] getByWeek: " + e.getMessage());
        }
        return list;
    }
    // THỐNG KÊ: Chi tiêu trong THÁNG

    public List<Transaction> getTransactionsByMonth(int userId, int year, int month) {
        String sql = "SELECT t.*, w.wallet_name, c.category_name "
                   + "FROM transactions t "
                   + "JOIN wallets w ON t.wallet_id = w.wallet_id "
                   + "JOIN categories c ON t.category_id = c.category_id "
                   + "WHERE t.user_id = ? "
                   + "AND YEAR(t.transaction_date) = ? AND MONTH(t.transaction_date) = ? "
                   + "ORDER BY t.transaction_date DESC";
        List<Transaction> list = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, year);
            ps.setInt(3, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));
        } catch (SQLException e) {
            System.err.println("[TransactionDAO] getByMonth: " + e.getMessage());
        }
        return list;
    }
    // THỐNG KÊ: Chi tiêu trong NĂM

    public List<Transaction> getTransactionsByYear(int userId, int year) {
        String sql = "SELECT t.*, w.wallet_name, c.category_name "
                   + "FROM transactions t "
                   + "JOIN wallets w ON t.wallet_id = w.wallet_id "
                   + "JOIN categories c ON t.category_id = c.category_id "
                   + "WHERE t.user_id = ? AND YEAR(t.transaction_date) = ? "
                   + "ORDER BY t.transaction_date DESC";
        List<Transaction> list = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));
        } catch (SQLException e) {
            System.err.println("[TransactionDAO] getByYear: " + e.getMessage());
        }
        return list;
    }

    // Tổng thu / chi trong khoảng thời gian

    public BigDecimal sumByTypeAndPeriod(int userId, Transaction.TransactionType type,
                                          LocalDateTime from, LocalDateTime to) {
        String sql = "SELECT ISNULL(SUM(amount), 0) FROM transactions "
                   + "WHERE user_id = ? AND type = ? "
                   + "AND transaction_date BETWEEN ? AND ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, type.name());
            ps.setTimestamp(3, Timestamp.valueOf(from));
            ps.setTimestamp(4, Timestamp.valueOf(to));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getBigDecimal(1);
        } catch (SQLException e) {
            System.err.println("[TransactionDAO] sumByTypeAndPeriod: " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }

    // Helper
    private List<Transaction> queryTransactions(String sql, int userId) {
        List<Transaction> list = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));
        } catch (SQLException e) {
            System.err.println("[TransactionDAO] query: " + e.getMessage());
        }
        return list;
    }

    private Transaction mapResultSet(ResultSet rs) throws SQLException {
    Transaction t = new Transaction();
    t.setTransactionId(rs.getInt("transaction_id"));
    t.setUserId(rs.getInt("user_id"));
    t.setWalletId(rs.getInt("wallet_id"));
    t.setCategoryId(rs.getInt("category_id"));
    t.setAmount(rs.getBigDecimal("amount"));
    
    // Xử lý type - chuyển đổi từ dữ liệu cũ
    String typeStr = rs.getString("type");
    Transaction.TransactionType type;
    try {
        type = Transaction.TransactionType.valueOf(typeStr);
    } catch (IllegalArgumentException e) {
        // Chuyển đổi dữ liệu cũ
        if ("Thu".equals(typeStr)) {
            type = Transaction.TransactionType.INCOME;
            System.out.println("[WARN] Converted 'Thu' to INCOME for transaction: " + rs.getInt("transaction_id"));
        } else if ("Chi".equals(typeStr)) {
            type = Transaction.TransactionType.EXPENSE;
            System.out.println("[WARN] Converted 'Chi' to EXPENSE for transaction: " + rs.getInt("transaction_id"));
        } else {
            throw new SQLException("Unknown transaction type: " + typeStr, e);
        }
    }
    t.setType(type);
    
    t.setNote(rs.getString("note"));
    Timestamp td = rs.getTimestamp("transaction_date");
    if (td != null) t.setTransactionDate(td.toLocalDateTime());
    Timestamp ca = rs.getTimestamp("created_at");
    if (ca != null) t.setCreatedAt(ca.toLocalDateTime());
    // Các field join
    try { t.setWalletName(rs.getString("wallet_name")); } catch (SQLException ignored) {}
    try { t.setCategoryName(rs.getString("category_name")); } catch (SQLException ignored) {}
    return t;
}
}

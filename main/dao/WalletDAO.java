package main.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Wallet;
import util.DBConnection;


//DAO xử lý các thao tác CRUD cho bảng wallets.

public class WalletDAO {

    private Connection getConn() {
        return ((DBConnection) DBConnection.getInstance()).getConnection();
    }
    // Thêm ví mới
    public boolean addWallet(Wallet wallet) {
        String sql = "INSERT INTO wallets (user_id, wallet_name, wallet_type, balance) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, wallet.getUserId());
            ps.setString(2, wallet.getWalletName());
            ps.setString(3, wallet.getWalletType().name());
            ps.setBigDecimal(4, wallet.getBalance() != null ? wallet.getBalance() : BigDecimal.ZERO);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[WalletDAO] addWallet: " + e.getMessage());
            return false;
        }
    }

    // Lấy danh sách ví của user
    public List<Wallet> getWalletsByUser(int userId) {
        List<Wallet> list = new ArrayList<>();
        String sql = "SELECT * FROM wallets WHERE user_id = ? ORDER BY created_at";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));
        } catch (SQLException e) {
            System.err.println("[WalletDAO] getWalletsByUser: " + e.getMessage());
        }
        return list;
    }
    // Lấy ví theo ID

    public Wallet getWalletById(int walletId) {
        String sql = "SELECT * FROM wallets WHERE wallet_id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, walletId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);
        } catch (SQLException e) {
            System.err.println("[WalletDAO] getWalletById: " + e.getMessage());
        }
        return null;
    }

    // Cập nhật tên & số dư ví
    public boolean updateWallet(Wallet wallet) {
        String sql = "UPDATE wallets SET wallet_name = ?, balance = ? WHERE wallet_id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, wallet.getWalletName());
            ps.setBigDecimal(2, wallet.getBalance());
            ps.setInt(3, wallet.getWalletId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[WalletDAO] updateWallet: " + e.getMessage());
            return false;
        }
    }
    // Cộng / trừ số dư ví (dùng khi tạo giao dịch)
    // amount > 0: cộng (thu), amount < 0: trừ (chi)
    public boolean updateBalance(int walletId, BigDecimal amount) {
        String sql = "UPDATE wallets SET balance = balance + ? WHERE wallet_id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setBigDecimal(1, amount);
            ps.setInt(2, walletId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[WalletDAO] updateBalance: " + e.getMessage());
            return false;
        }
    }
    // Xóa ví

    public boolean deleteWallet(int walletId) {
        String sql = "DELETE FROM wallets WHERE wallet_id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, walletId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[WalletDAO] deleteWallet: " + e.getMessage());
            return false;
        }
    }

    // Tổng số dư tất cả ví của user

    public BigDecimal getTotalBalance(int userId) {
        String sql = "SELECT ISNULL(SUM(balance), 0) FROM wallets WHERE user_id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getBigDecimal(1);
        } catch (SQLException e) {
            System.err.println("[WalletDAO] getTotalBalance: " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }

    // Helper: map ResultSet -> Wallet

    private Wallet mapResultSet(ResultSet rs) throws SQLException {
        Wallet w = new Wallet();
        w.setWalletId(rs.getInt("wallet_id"));
        w.setUserId(rs.getInt("user_id"));
        w.setWalletName(rs.getString("wallet_name"));
        w.setWalletType(Wallet.WalletType.valueOf(rs.getString("wallet_type")));
        w.setBalance(rs.getBigDecimal("balance"));
        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) w.setCreatedAt(ts.toLocalDateTime());
        return w;
    }
}
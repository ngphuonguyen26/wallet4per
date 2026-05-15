package dao;

import java.sql.*;
import model.User;
import util.DBConnection;

/**
 * DAO xử lý các thao tác CRUD cho bảng users.
 * Bao gồm: đăng ký, đăng nhập, lấy thông tin, cập nhật.
 */
public class UserDAO {

    private Connection getConn() {
        return ((DBConnection) DBConnection.getInstance()).getConnection();
    }

    public boolean register(User user) {
        String sql = "INSERT INTO users (username, password, fullname, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword()); // nên hash trước khi truyền vào
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[UserDAO] register: " + e.getMessage());
            return false;
        }
    }


    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password); // nên hash password trước khi so sánh
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("[UserDAO] login: " + e.getMessage());
        }
        return null;
    }


    public boolean isUsernameExist(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("[UserDAO] isUsernameExist: " + e.getMessage());
        }
        return false;
    }

    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);
        } catch (SQLException e) {
            System.err.println("[UserDAO] getUserById: " + e.getMessage());
        }
        return null;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE users SET fullname = ?, email = ? WHERE user_id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[UserDAO] updateUser: " + e.getMessage());
            return false;
        }
    }
    public boolean changePassword(int userId, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[UserDAO] changePassword: " + e.getMessage());
            return false;
        }
    }
    public boolean deleteUser(int userId) {
    Connection conn = getConn();
    
    try {
        conn.setAutoCommit(false);
        
        // 1. Xóa savings_transactions trước
        String sql1 = "DELETE FROM savings_transactions WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql1)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
        
        // 2. Xóa savings_funds
        String sql2 = "DELETE FROM savings_funds WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql2)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
        
        // 3. Xóa transactions
        String sql3 = "DELETE FROM transactions WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql3)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
        
        // 4. Xóa wallets
        String sql4 = "DELETE FROM wallets WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql4)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
        
        // 5. Cập nhật categories (set user_id = NULL)
        String sql5 = "UPDATE categories SET user_id = NULL WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql5)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
        
        // 6. Cuối cùng xóa user
        String sql6 = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql6)) {
            ps.setInt(1, userId);
            int result = ps.executeUpdate();
            
            conn.commit();
            return result > 0;
            
        } catch (SQLException e) {
            conn.rollback();
            System.err.println("[UserDAO] deleteUser: " + e.getMessage());
            return false;
        }
        
    } catch (SQLException e) {
        try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        System.err.println("[UserDAO] deleteUser error: " + e.getMessage());
        return false;
    } finally {
        try { conn.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
    }
}
    // Helper: map ResultSet -> User
    private User mapResultSet(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserId(rs.getInt("user_id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setFullName(rs.getString("fullname"));
        u.setEmail(rs.getString("email"));
        return u;
    }
}
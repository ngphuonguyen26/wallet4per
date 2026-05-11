package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import util.DBConnection;

//DAO xử lý các thao tác cho bảng categories.
public class CategoryDAO {

    private Connection getConn() {
        return ((DBConnection) DBConnection.getInstance()).getConnection();
    }

    // Lấy tất cả danh mục theo loại (thu/chi)
    public List<Category> getCategoriesByType(Category.CategoryType type) {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories WHERE type = ? ORDER BY category_name";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, type.name());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));
        } catch (SQLException e) {
            System.err.println("[CategoryDAO] getCategoriesByType: " + e.getMessage());
        }
        return list;
    }
    // Lấy tất cả danh mục
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories ORDER BY type, category_name";
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapResultSet(rs));
        } catch (SQLException e) {
            System.err.println("[CategoryDAO] getAllCategories: " + e.getMessage());
        }
        return list;
    }
    // Lấy danh mục theo ID
    public Category getCategoryById(int categoryId) {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);
        } catch (SQLException e) {
            System.err.println("[CategoryDAO] getCategoryById: " + e.getMessage());
        }
        return null;
    }

    // Helper: map ResultSet -> Category
    private Category mapResultSet(ResultSet rs) throws SQLException {
        return new Category(
            rs.getInt("category_id"),
            rs.getString("category_name"),
            Category.CategoryType.valueOf(rs.getString("type")),
            rs.getString("icon"),
            rs.getBoolean("is_default")
        );
    }
}
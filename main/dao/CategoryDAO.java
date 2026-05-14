package main.dao;

import main.model.Category;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private Connection getConn() {
        return ((DBConnection) DBConnection.getInstance()).getConnection();
    }

    // Lấy tất cả danh mục
    public List<Category> getAllCategories() {

        List<Category> list = new ArrayList<>();

        String sql = "SELECT * FROM categories ORDER BY type, category_name";

        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("[CategoryDAO] getAllCategories: " + e.getMessage());
        }

        return list;
    }

    // Lấy danh mục theo loại
    public List<Category> getCategoriesByType(Category.CategoryType type) {

        List<Category> list = new ArrayList<>();

        String sql = "SELECT * FROM categories WHERE type = ? ORDER BY category_name";

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {

            ps.setString(1, type.name());

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    list.add(mapResultSet(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("[CategoryDAO] getCategoriesByType: " + e.getMessage());
        }

        return list;
    }

    // Lấy danh mục theo ID
    public Category getCategoryById(int categoryId) {

        String sql = "SELECT * FROM categories WHERE category_id = ?";

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {

            ps.setInt(1, categoryId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("[CategoryDAO] getCategoryById: " + e.getMessage());
        }

        return null;
    }

    private Category mapResultSet(ResultSet rs) throws SQLException {

        String typeStr = rs.getString("type");

        Category.CategoryType type;

        if (typeStr.equalsIgnoreCase("Thu")) {
            type = Category.CategoryType.INCOME;
        } else if (typeStr.equalsIgnoreCase("Chi")) {
            type = Category.CategoryType.EXPENSE;
        } else {
            type = Category.CategoryType.valueOf(typeStr.toUpperCase());
        }

        return new Category(
                rs.getInt("category_id"),
                rs.getString("category_name"),
                type,
                rs.getString("icon"),
                rs.getBoolean("is_default")
        );
    }
}
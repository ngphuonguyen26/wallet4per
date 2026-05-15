package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Category.CategoryType;
import util.DBConnection;

public class CategoryDAO {
    
    private Connection getConn() {
        return ((DBConnection) DBConnection.getInstance()).getConnection();
    }
    
    // Lấy tất cả danh mục (hệ thống + của user)
    public List<Category> getAllCategoriesByUser(int userId) {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories WHERE user_id IS NULL OR user_id = ? ORDER BY type, category_name";
        
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                
                // Xử lý user_id có thể NULL
                int uid = rs.getInt("user_id");
                if (rs.wasNull()) {
                    category.setUserId(null);
                } else {
                    category.setUserId(uid);
                }
                
                category.setCategoryName(rs.getString("category_name"));
                
                // Chuyển đổi type từ database
                String dbType = rs.getString("type");
                System.out.println("Loading category: " + category.getCategoryName() + ", type=" + dbType); // Debug
                
                if ("INCOME".equals(dbType)) {
                    category.setType(CategoryType.INCOME);
                } else if ("EXPENSE".equals(dbType)) {
                    category.setType(CategoryType.EXPENSE);
                } else {
                    // Fallback cho dữ liệu cũ
                    category.setType(CategoryType.fromString(dbType));
                }
                
                category.setIcon(rs.getString("icon"));
                category.setDefault(rs.getBoolean("is_default"));
                
                list.add(category);
            }
            
            System.out.println("Loaded " + list.size() + " categories"); // Debug
            
        } catch (SQLException e) {
            System.err.println("Error in getAllCategoriesByUser: " + e.getMessage());
            e.printStackTrace();
        }
        
        return list;
    }
    
    // Lấy danh mục theo type (INCOME hoặc EXPENSE)
    public List<Category> getCategoriesByType(int userId, CategoryType type) {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories WHERE (user_id IS NULL OR user_id = ?) AND type = ? ORDER BY category_name";
        
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, type.name()); // "INCOME" hoặc "EXPENSE"
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setUserId(rs.getInt("user_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setType(type);
                category.setIcon(rs.getString("icon"));
                category.setDefault(rs.getBoolean("is_default"));
                list.add(category);
            }
        } catch (SQLException e) {
            System.err.println("Error in getCategoriesByType: " + e.getMessage());
        }
        return list;
    }
}
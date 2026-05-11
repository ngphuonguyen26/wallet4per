package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyChiTieu;encrypt=true;trustServerCertificate=true";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456"; 

    private static DBConnection instance;
    private Connection connection;

    private DBConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("[DB] Kết nối SQL Server thành công!");
        } catch (ClassNotFoundException e) {
            System.err.println("[DB] Không tìm thấy driver SQL Server. Hãy thêm sqljdbc.jar vào classpath.");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.err.println("[DB] Lỗi kết nối: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
 
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("[DB] Reconnecting...");
                instance = new DBConnection();
            }
        } catch (SQLException e) {
            System.err.println("[DB] Lỗi kiểm tra kết nối: " + e.getMessage());
        }
        return connection;
    }

    public static Object getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
}
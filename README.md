# 🪙 Quản lý Chi tiêu Cá nhân
### Java Swing + SQL Server


---


## 💡 Giới thiệu
Ứng dụng **Quản lý Chi tiêu Cá nhân** giúp người dùng:
- Theo dõi thu nhập và chi tiêu hằng ngày
- Quản lý ví điện tử
- Thống kê tài chính theo thời gian
- Tạo và quản lý quỹ tiết kiệm

---

## 🧰 Công nghệ sử dụng
- Java 8+ (Swing)
- SQL Server 2017+
- JDBC

---

## ⚙️ Chức năng chính
### Người dùng
- Đăng ký/Đăng nhập
- Quản lý ví (Tiền mặt, Vietcombank, MoMo)
- Thêm/Xóa giao dịch thu/chi
- Thống kê theo ngày/tuần/tháng/năm
- Quản lý quỹ tiết kiệm

---

## 🖥️ Cài đặt & Cấu hình
1. Cài đặt **JDK 8+** và **SQL Server 2017+**
2. Chạy file `BT-Java.sql` để tạo database `QuanLyChiTieu`
3. Cập nhật kết nối trong `src/util/DBConnection.java`:
   ```java
   private final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyChiTieu";
   private final String USER = "sa";
   private final String PASSWORD = "your_password";

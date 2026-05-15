# QUẢN LÝ CHI TIÊU CÁ NHÂN - JAVA SWING + SQL SERVER

## 1. GIỚI THIỆU DỰ ÁN

Ứng dụng **Quản lý Chi tiêu Cá nhân** giúp người dùng theo dõi thu nhập và chi tiêu hàng ngày, quản lý ví điện tử, thống kê tài chính theo ngày/tuần/tháng/năm, và tạo quỹ tiết kiệm.

### Công nghệ sử dụng:
- Java 8+ (Swing)
- SQL Server
- JDBC

## 2. CHỨC NĂNG CHÍNH

### 2.1. Chức năng chung (Đăng nhập/Đăng ký)
- Đăng ký tài khoản mới
- Đăng nhập vào hệ thống
- Tự động tạo ví mặc định khi đăng ký (Tiền mặt, Vietcombank, MoMo)

### 2.2. Người dùng (User)
- Xem danh sách giao dịch theo thời gian
- Thêm giao dịch thu/chi với danh mục và ví tương ứng
- Xóa giao dịch
- Xem thống kê chi tiêu theo ngày/tuần/tháng/năm
- Quản lý quỹ tiết kiệm (tạo quỹ, gửi tiền, rút tiền)

### 2.3. Quản trị viên (Admin - nếu có)
- Quản lý danh mục (thêm, sửa, xóa)
- Quản lý người dùng
- Xem thống kê tổng quan

## 3. CÀI ĐẶT VÀ CẤU HÌNH

### 3.1. Yêu cầu hệ thống
- Java JDK 8 hoặc cao hơn
- SQL Server 2017 hoặc cao hơn
- IntelliJ IDEA / Eclipse / NetBeans

### 3.2. Cài đặt Database
1. Mở SQL Server Management Studio (SSMS)
2. Chạy file `BT-Java.sql` để tạo database `QuanLyChiTieu`
3. Kiểm tra kết nối với tài khoản sa (hoặc tài khoản Windows)

### 3.3. Cấu hình kết nối

Mở file `src/util/DBConnection.java` và sửa thông tin kết nối:

```java
private final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyChiTieu;useUnicode=true;characterEncoding=UTF-8";
private final String USER = "sa";
private final String PASSWORD = "your_password";
### 3.4. Thêm JDBC Driver
- Tải file `mssql-jdbc-12.4.2.jre8.jar` từ Microsoft
- Thêm vào project classpath

### 3.5. Chạy chương trình
- Chạy file `Main.java`
- Đăng nhập với tài khoản mặc định:
    - Username: `anntv`
    - Password: `123456`

## 4. TÀI KHOẢN MẶC ĐỊNH

| Vai trò | Username | Password | Mô tả |
|---------|----------|----------|-------|
| User | anntv | 123456 | Nguyễn Văn An - có dữ liệu mẫu |
| User | (tạo mới) | (tự đặt) | Đăng ký để có tài khoản mới |

## 5. CẤU TRÚC DATABASE

### Bảng chính:
- **users**: Thông tin người dùng (user_id, username, password, fullname, email)
- **wallets**: Ví tiền (wallet_id, user_id, wallet_name, balance)
- **categories**: Danh mục thu/chi (category_id, category_name, type)
- **transactions**: Giao dịch (transaction_id, user_id, wallet_id, category_id, amount, type)
- **savings_funds**: Quỹ tiết kiệm (fund_id, user_id, fund_name, target, balance)
- **savings_transactions**: Lịch sử gửi/rút quỹ

### Ràng buộc khóa ngoại và trigger:
- Tự động tạo ví mặc định khi đăng ký user mới

## 6. CÁCH SỬ DỤNG

### 6.1. Đăng nhập/Đăng ký
- Chạy chương trình -> Form đăng nhập hiển thị
- Chọn "Đăng ký" để tạo tài khoản mới
- Đăng nhập với tài khoản vừa tạo

### 6.2. Màn hình chính
- Hiển thị danh sách giao dịch gần đây
- Các nút chức năng: Thêm GD, Thống kê, Quỹ TK, Danh mục

### 6.3. Thêm giao dịch
- Chọn loại (Thu/Chi)
- Chọn ví và danh mục
- Nhập số tiền và ghi chú
- Nhấn "Thêm" để lưu

### 6.4. Xem thống kê
- Chọn "Thống kê" -> Chọn theo Ngày/Tuần/Tháng/Năm
- Xem biểu đồ và bảng chi tiết

### 6.5. Quỹ tiết kiệm
- Tạo quỹ mới với mục tiêu
- Gửi tiền vào quỹ từ ví
- Rút tiền khi cần

## 7. SINH VIÊN THỰC HIỆN

| STT | Họ và tên            | Nhiệm vụ |
|-----|----------------------|----------|
| 1   | Phạm Thị Ngọc Khuê   | Đảm nhiệm UI, thiết kế |
| 2   | Lê Xuân Nam          | Đảm nhiệm controller, qua lại của form, chức năng nút bấm |
| 3   | Nguyễn Phương Uyên   | Đảm nhiệm Database, truy vấn dữ liệu |

> **Ghi chú:**  
> - Tất cả các thành viên đều tham gia trao đổi, góp ý nội dung và hỗ trợ lẫn nhau trong quá trình thực hiện.  
> - Công việc có thể linh động điều chỉnh tùy vào tiến độ và năng lực từng thành viên.

- **Môn học:** Lập trình Java
- **Năm:** 2026

## 8. TÀI LIỆU THAM KHẢO

- [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Microsoft JDBC Driver for SQL Server](https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server)
- [SQL Server Documentation](https://docs.microsoft.com/en-us/sql/sql-server/)

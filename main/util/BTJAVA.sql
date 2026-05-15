-- =============================================
-- QUẢN LÝ CHI TIÊU CÁ NHÂN - SQL Server
-- Phiên bản cập nhật: Sử dụng ENUM INCOME/EXPENSE
-- =============================================
USE master;
GO

-- Nếu DB tồn tại thì mới đưa về SINGLE_USER và DROP
IF DB_ID('QuanLyChiTieu') IS NOT NULL
BEGIN
    ALTER DATABASE QuanLyChiTieu SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE QuanLyChiTieu;
END
GO

CREATE DATABASE QuanLyChiTieu;
GO

USE QuanLyChiTieu;
GO

-- Drop existing tables if they exist
IF OBJECT_ID('dbo.savings_transactions', 'U') IS NOT NULL DROP TABLE dbo.savings_transactions;
IF OBJECT_ID('dbo.savings_funds', 'U') IS NOT NULL DROP TABLE dbo.savings_funds;
IF OBJECT_ID('dbo.transactions', 'U') IS NOT NULL DROP TABLE dbo.transactions;
IF OBJECT_ID('dbo.categories', 'U') IS NOT NULL DROP TABLE dbo.categories;
IF OBJECT_ID('dbo.wallets', 'U') IS NOT NULL DROP TABLE dbo.wallets;
IF OBJECT_ID('dbo.users', 'U') IS NOT NULL DROP TABLE dbo.users;

-- =============================================
-- BẢNG USERS
-- =============================================
CREATE TABLE users (
    user_id    INT IDENTITY(1,1) PRIMARY KEY,
    username   NVARCHAR(24)  NOT NULL UNIQUE,
    password   NVARCHAR(255) NOT NULL,  -- Tăng độ dài cho mã hóa
    fullname   NVARCHAR(24)  NOT NULL,
    email      NVARCHAR(50),
    created_at DATETIME DEFAULT GETDATE()
);

-- =============================================
-- BẢNG WALLETS
-- =============================================
CREATE TABLE wallets (
    wallet_id   INT IDENTITY(1,1) PRIMARY KEY,
    user_id     INT NOT NULL,
    wallet_name NVARCHAR(20) NOT NULL,
    wallet_type NVARCHAR(20) NOT NULL,
    balance     DECIMAL(18,2) DEFAULT 0,
    created_at  DATETIME DEFAULT GETDATE(),
    CONSTRAINT fk_wallets_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- =============================================
-- BẢNG CATEGORIES
-- Sử dụng INCOME/EXPENSE thay vì Thu/Chi
-- =============================================
CREATE TABLE categories (
    category_id   INT IDENTITY(1,1) PRIMARY KEY,
    user_id       INT          NULL,
    category_name NVARCHAR(20) NOT NULL,
    type          NVARCHAR(10) NOT NULL CHECK (type IN ('INCOME', 'EXPENSE')),
    icon          NVARCHAR(20),
    is_default    BIT DEFAULT 1,
    CONSTRAINT fk_categories_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
);

-- =============================================
-- DANH MỤC HỆ THỐNG (user_id = NULL)
-- =============================================
INSERT INTO categories (user_id, category_name, type, icon, is_default) VALUES
(NULL, N'Lương',           'INCOME', 'salary',        1),
(NULL, N'Thưởng',          'INCOME', 'bonus',          1),
(NULL, N'Ăn uống',         'EXPENSE', 'food',           1),
(NULL, N'Sức khỏe, Y tế',  'EXPENSE', 'health',         1),
(NULL, N'Giáo dục',        'EXPENSE', 'education',      1),
(NULL, N'Gia dụng',        'EXPENSE', 'household',      1),
(NULL, N'Giải trí',        'EXPENSE', 'entertainment',  1),
(NULL, N'Tình nguyện',     'EXPENSE', 'volunteer',      1),
(NULL, N'Thời trang',      'EXPENSE', 'fashion',        1);

-- =============================================
-- BẢNG TRANSACTIONS
-- Sử dụng INCOME/EXPENSE thay vì Thu/Chi
-- =============================================
CREATE TABLE transactions (
    transaction_id   INT IDENTITY(1,1) PRIMARY KEY,
    user_id          INT NOT NULL,
    wallet_id        INT NOT NULL,
    category_id      INT NOT NULL,
    amount           DECIMAL(18,2) NOT NULL CHECK (amount > 0),
    type             NVARCHAR(10)  NOT NULL CHECK (type IN ('INCOME', 'EXPENSE')),
    note             NVARCHAR(255),
    transaction_date DATETIME DEFAULT GETDATE(),
    created_at       DATETIME DEFAULT GETDATE(),
    CONSTRAINT fk_trans_user     FOREIGN KEY (user_id)     REFERENCES users(user_id),
    CONSTRAINT fk_trans_wallet   FOREIGN KEY (wallet_id)   REFERENCES wallets(wallet_id),
    CONSTRAINT fk_trans_category FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- =============================================
-- BẢNG SAVINGS_FUNDS
-- =============================================
CREATE TABLE savings_funds (
    fund_id    INT IDENTITY(1,1) PRIMARY KEY,
    user_id    INT NOT NULL,
    fund_name  NVARCHAR(100) NOT NULL,
    target     DECIMAL(18,2) DEFAULT 0,
    balance    DECIMAL(18,2) DEFAULT 0,
    note       NVARCHAR(255),
    created_at DATETIME DEFAULT GETDATE(),
    CONSTRAINT fk_fund_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- =============================================
-- BẢNG SAVINGS_TRANSACTIONS
-- =============================================
CREATE TABLE savings_transactions (
    savings_trans_id INT IDENTITY(1,1) PRIMARY KEY,
    fund_id          INT NOT NULL,
    user_id          INT NOT NULL,
    wallet_id        INT NOT NULL,
    amount           DECIMAL(18,2) NOT NULL CHECK (amount > 0),
    type             NVARCHAR(10)  NOT NULL CHECK (type IN ('DEPOSIT', 'WITHDRAW')),
    note             NVARCHAR(255),
    trans_date       DATETIME DEFAULT GETDATE(),
    CONSTRAINT fk_strans_fund   FOREIGN KEY (fund_id)   REFERENCES savings_funds(fund_id),
    CONSTRAINT fk_strans_user   FOREIGN KEY (user_id)   REFERENCES users(user_id),
    CONSTRAINT fk_strans_wallet FOREIGN KEY (wallet_id) REFERENCES wallets(wallet_id)
);

-- =============================================
-- INDEX
-- =============================================
CREATE INDEX idx_trans_user_date  ON transactions(user_id, transaction_date);
CREATE INDEX idx_trans_wallet     ON transactions(wallet_id);
CREATE INDEX idx_strans_fund      ON savings_transactions(fund_id);
CREATE INDEX idx_strans_user_date ON savings_transactions(user_id, trans_date);
GO

-- ============================================================
-- DỮ LIỆU MẪU: 1 người dùng với lịch sử chi tiêu thực tế
-- Tên: Nguyễn Văn An | username: anntv | password: 123456
-- ============================================================

-- 1. User
INSERT INTO users (username, password, fullname, email) VALUES
(N'anntv', N'123456', N'Nguyễn Văn An', N'an.nguyen@email.com');
-- user_id = 1

-- 2. Ví của An (4 loại)
INSERT INTO wallets (user_id, wallet_name, wallet_type, balance) VALUES
(1, N'Tiền mặt',    'CASH', 3250000),   -- wallet_id = 1
(1, N'Vietcombank', 'VCB',  19845000),  -- wallet_id = 2
(1, N'MoMo',        'MOMO', 1256000),   -- wallet_id = 3
(1, N'Vietinbank',  'VTB',  0);         -- wallet_id = 4

-- 3. Danh mục riêng của An (thêm 1 danh mục tự tạo)
INSERT INTO categories (user_id, category_name, type, icon, is_default) VALUES
(1, N'Đi lại',  'EXPENSE', 'transport', 0);
-- category_id = 10

-- -------------------------------------------------------
-- 4. Giao dịch tháng 3/2026 (Sử dụng INCOME/EXPENSE)
-- -------------------------------------------------------
INSERT INTO transactions (user_id, wallet_id, category_id, amount, type, note, transaction_date) VALUES
-- Nhận lương tháng 3 vào VCB
(1, 2, 1, 12000000, 'INCOME', N'Lương tháng 3',         '2026-03-05 08:00:00'),
-- Chi sinh hoạt
(1, 1, 3, 150000,   'EXPENSE', N'Ăn sáng cả tuần',       '2026-03-06 07:30:00'),
(1, 3, 3, 85000,    'EXPENSE', N'Trà sữa + bánh mì',     '2026-03-07 15:00:00'),
(1, 1, 3, 200000,   'EXPENSE', N'Đi ăn nhà hàng',        '2026-03-09 12:00:00'),
(1, 3, 10, 50000,   'EXPENSE', N'Grab đi làm',            '2026-03-10 07:45:00'),
(1, 2, 5, 300000,   'EXPENSE', N'Mua sách lập trình',     '2026-03-11 10:00:00'),
(1, 1, 4, 120000,   'EXPENSE', N'Mua thuốc cảm',          '2026-03-12 18:00:00'),
(1, 3, 7, 200000,   'EXPENSE', N'Xem phim + bắp',         '2026-03-15 19:30:00'),
(1, 2, 6, 450000,   'EXPENSE', N'Mua đồ gia dụng',        '2026-03-17 10:00:00'),
(1, 1, 3, 95000,    'EXPENSE', N'Cơm trưa văn phòng',     '2026-03-18 12:00:00'),
(1, 3, 9, 350000,   'EXPENSE', N'Mua áo mới',             '2026-03-20 14:00:00'),
(1, 2, 8, 200000,   'EXPENSE', N'Ủng hộ từ thiện',        '2026-03-22 09:00:00'),
(1, 1, 3, 130000,   'EXPENSE', N'Ăn tối cùng bạn bè',    '2026-03-25 19:00:00'),
(1, 3, 10, 75000,   'EXPENSE', N'Xe ôm đi đám cưới',     '2026-03-28 17:00:00');

-- -------------------------------------------------------
-- 5. Giao dịch tháng 4/2026
-- -------------------------------------------------------
INSERT INTO transactions (user_id, wallet_id, category_id, amount, type, note, transaction_date) VALUES
(1, 2, 1, 12000000, 'INCOME', N'Lương tháng 4',          '2026-04-05 08:00:00'),
(1, 2, 2, 2000000,  'INCOME', N'Thưởng KPI quý 1',       '2026-04-05 08:05:00'),
(1, 1, 3, 180000,   'EXPENSE', N'Ăn sáng + cà phê',       '2026-04-07 07:30:00'),
(1, 3, 3, 250000,   'EXPENSE', N'Đặt đồ ăn Shopee Food',  '2026-04-09 12:00:00'),
(1, 2, 5, 500000,   'EXPENSE', N'Khóa học online',         '2026-04-10 20:00:00'),
(1, 1, 4, 500000,   'EXPENSE', N'Khám sức khỏe định kỳ',  '2026-04-12 09:00:00'),
(1, 3, 7, 150000,   'EXPENSE', N'Netflix tháng 4',         '2026-04-13 08:00:00'),
(1, 1, 10, 120000,  'EXPENSE', N'Xe buýt + grab tuần này','2026-04-14 08:00:00'),
(1, 2, 6, 280000,   'EXPENSE', N'Mua bóng đèn, chổi',     '2026-04-16 11:00:00'),
(1, 3, 3, 95000,    'EXPENSE', N'Cơm gà + nước',           '2026-04-18 12:30:00'),
(1, 1, 9, 420000,   'EXPENSE', N'Mua quần jeans',          '2026-04-20 15:00:00'),
(1, 2, 7, 300000,   'EXPENSE', N'Karaoke với bạn bè',      '2026-04-26 20:00:00'),
(1, 1, 3, 160000,   'EXPENSE', N'Ăn lẩu cuối tháng',      '2026-04-30 18:30:00');

-- -------------------------------------------------------
-- 6. Giao dịch tháng 5/2026 (tháng hiện tại)
-- -------------------------------------------------------
INSERT INTO transactions (user_id, wallet_id, category_id, amount, type, note, transaction_date) VALUES
(1, 2, 1, 12000000, 'INCOME', N'Lương tháng 5',          '2026-05-05 08:00:00'),
(1, 1, 3, 90000,    'EXPENSE', N'Ăn sáng đầu tuần',       '2026-05-06 07:30:00'),
(1, 3, 3, 130000,   'EXPENSE', N'Trà sữa + ăn vặt',       '2026-05-08 15:30:00'),
(1, 2, 5, 200000,   'EXPENSE', N'Sách giáo trình mới',    '2026-05-09 10:00:00'),
(1, 3, 10, 60000,   'EXPENSE', N'Grab đi học',             '2026-05-10 07:45:00'),
(1, 1, 4, 80000,    'EXPENSE', N'Mua vitamin C',           '2026-05-11 18:00:00'),
(1, 3, 7, 89000,    'EXPENSE', N'Mua game Steam sale',    '2026-05-13 21:00:00');

-- -------------------------------------------------------
-- 8. Quỹ tiết kiệm
-- -------------------------------------------------------
INSERT INTO savings_funds (user_id, fund_name, target, balance, note) VALUES
(1, N'Mua laptop mới',  15000000, 4500000, N'Dành dụm mua MacBook Air M3'),
(1, N'Du lịch Đà Nẵng', 8000000, 1500000, N'Kế hoạch hè năm nay'),  -- Đã rút 500K
(1, N'Quỹ khẩn cấp',    10000000, 3000000, N'3 tháng chi phí sinh hoạt');
-- fund_id: 1, 2, 3

-- -------------------------------------------------------
-- 9. Lịch sử gửi quỹ tiết kiệm
-- -------------------------------------------------------
INSERT INTO savings_transactions (fund_id, user_id, wallet_id, amount, type, note, trans_date) VALUES
(1, 1, 2, 1500000, 'DEPOSIT',  N'Gửi đầu tháng 3',     '2026-03-05 09:00:00'),
(1, 1, 2, 1500000, 'DEPOSIT',  N'Gửi đầu tháng 4',     '2026-04-05 09:00:00'),
(1, 1, 2, 1500000, 'DEPOSIT',  N'Gửi đầu tháng 5',     '2026-05-05 09:00:00'),
(2, 1, 2, 1000000, 'DEPOSIT',  N'Bắt đầu dành dụm',    '2026-03-10 10:00:00'),
(2, 1, 2, 1000000, 'DEPOSIT',  N'Tháng 4 gửi thêm',    '2026-04-10 10:00:00'),
(3, 1, 2, 1000000, 'DEPOSIT',  N'Lập quỹ khẩn cấp',    '2026-03-15 10:00:00'),
(3, 1, 2, 1000000, 'DEPOSIT',  N'Bổ sung tháng 4',     '2026-04-15 10:00:00'),
(3, 1, 2, 1000000, 'DEPOSIT',  N'Bổ sung tháng 5',     '2026-05-15 10:00:00'),
(2, 1, 1, 500000,  'WITHDRAW', N'Rút đặt cọc khách sạn','2026-05-01 08:00:00');

-- ============================================================
-- KIỂM TRA DỮ LIỆU
-- ============================================================
SELECT 'Categories' as TableName, type, COUNT(*) as Count 
FROM categories GROUP BY type
UNION ALL
SELECT 'Transactions', type, COUNT(*) 
FROM transactions GROUP BY type;

-- Hiển thị tổng thu chi theo tháng
SELECT 
    YEAR(transaction_date) as Nam,
    MONTH(transaction_date) as Thang,
    SUM(CASE WHEN type = 'INCOME' THEN amount ELSE 0 END) as TongThu,
    SUM(CASE WHEN type = 'EXPENSE' THEN amount ELSE 0 END) as TongChi
FROM transactions
WHERE user_id = 1
GROUP BY YEAR(transaction_date), MONTH(transaction_date)
ORDER BY Nam DESC, Thang DESC;

GO


USE QuanLyChiTieu;
GO

-- Kiểm tra categories
SELECT category_id, user_id, category_name, type, icon, is_default 
FROM categories 
ORDER BY type, category_name;

-- Kiểm tra transactions (đã cập nhật chưa)
SELECT TOP 5 transaction_id, type FROM transactions;

USE QuanLyChiTieu;
GO

-- Tạo trigger tự động tạo ví khi có user mới
CREATE TRIGGER trg_create_default_wallets
ON users
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;
    
    DECLARE @userId INT;
    
    -- Lấy user_id vừa insert
    SELECT @userId = user_id FROM inserted;
    
    -- Tạo 3 ví mặc định
    INSERT INTO wallets (user_id, wallet_name, wallet_type, balance)
    VALUES 
        (@userId, N'Tiền mặt', 'CASH', 0),
        (@userId, N'Vietcombank', 'VCB', 0),
        (@userId, N'MoMo', 'MOMO', 0);
END
GO

-- Kiểm tra trigger đã được tạo
SELECT 
    name AS trigger_name,
    is_disabled,
    CASE WHEN is_disabled = 0 THEN 'Active' ELSE 'Disabled' END AS status
FROM sys.triggers
WHERE name = 'trg_create_default_wallets';

USE QuanLyChiTieu;
GO

-- Thêm cột đánh dấu xóa vào bảng users
ALTER TABLE users ADD is_deleted BIT DEFAULT 0;
ALTER TABLE users ADD deleted_at DATETIME NULL;


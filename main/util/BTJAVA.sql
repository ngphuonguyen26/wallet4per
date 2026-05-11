create database QuanLyChiTieu;
go

use QuanLyChiTieu;
go

create table users (
	user_id int identity(1,1) primary key,
	username nvarchar(24) not null unique,
	password nvarchar(20) not null,
	fullname nvarchar(24) not null,
	email nvarchar(20),
	created_at datetime default getdate()
);

create table wallets (
	wallet_id int identity(1,1) primary key,
	user_id int not null,
	wallet_name nvarchar(20) not null,
	wallet_type nvarchar(20) not null,
	balance decimal(18,2) default 0,
	created_at datetime default getdate()
);

create table categories(
	category_id int identity(1,1) primary key,
	category_name nvarchar(20) not null,
	type nvarchar(10) not null check (type in ('Thu','Chi')),
	icon nvarchar(20),
	is_default bit default 1
);

insert into categories(category_name,type,icon) values
-- thu
(N'Lương',          'Thu',  'salary'),
(N'Thưởng',         'Thu',  'bonus'),
-- Chi
(N'Ăn uống',        'Chi', 'food'),
(N'Sức khỏe, Y tế', 'Chi', 'health'),
(N'Giáo dục',       'Chi', 'education'),
(N'Gia dụng',       'Chi', 'household'),
(N'Giải trí',       'Chi', 'entertainment'),
(N'Tình nguyện',    'Chi', 'volunteer'),
(N'Thời trang',     'Chi', 'fashion');

create table transactions (
	transaction_id int identity(1,1) primary key,
	user_id int not null,
	wallet_id int not null,
	category_id int not null,
	amount decimal(18,2) not null check (amount > 0),
	type nvarchar(10) not null check (type in ('Thu','Chi')),
	note nvarchar(40),
	transaction_date datetime default getdate(),
	created_at datetime default getdate(),
	CONSTRAINT fk_trans_user     FOREIGN KEY (user_id)     REFERENCES users(user_id),
    CONSTRAINT fk_trans_wallet   FOREIGN KEY (wallet_id)   REFERENCES wallets(wallet_id),
    CONSTRAINT fk_trans_category FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

create table savings_funds (
	fund_id int identity(1,1) primary key,
	user_id int not null,
	fund_name nvarchar(100) not null,
	target decimal(18,2) default 0,
	balance decimal(18,2) default 0,
	note nvarchar(50),
	created_at datetime default getdate(),
	CONSTRAINT fk_fund_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE savings_transactions (
    savings_trans_id INT IDENTITY(1,1) PRIMARY KEY,
    fund_id          INT NOT NULL,
    user_id          INT NOT NULL,
    wallet_id        INT NOT NULL,             -- rút/gửi từ ví nào
    amount           DECIMAL(18,2) NOT NULL CHECK (amount > 0),
    type             NVARCHAR(10)  NOT NULL CHECK (type IN ('DEPOSIT', 'WITHDRAW')),
    note             NVARCHAR(255),
    trans_date       DATETIME DEFAULT GETDATE(),
    CONSTRAINT fk_strans_fund   FOREIGN KEY (fund_id)   REFERENCES savings_funds(fund_id),
    CONSTRAINT fk_strans_user   FOREIGN KEY (user_id)   REFERENCES users(user_id),
    CONSTRAINT fk_strans_wallet FOREIGN KEY (wallet_id) REFERENCES wallets(wallet_id)
);

CREATE INDEX idx_trans_user_date     ON transactions(user_id, transaction_date);
CREATE INDEX idx_trans_wallet        ON transactions(wallet_id);
CREATE INDEX idx_strans_fund         ON savings_transactions(fund_id);
CREATE INDEX idx_strans_user_date    ON savings_transactions(user_id, trans_date);
 
GO

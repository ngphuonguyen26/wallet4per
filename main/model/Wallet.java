package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wallet {

    public enum WalletType {
        MOMO, VCB, VTB, CASH;

        public String getDisplayName() {
            return switch (this) {
                case MOMO -> "MoMo";
                case VCB -> "Vietcombank";
                case VTB -> "Vietinbank";
                case CASH -> "Tiền mặt";
                default -> this.name();
            };
        }
    }

    private int walletId;
    private int userId;
    private String walletName;
    private WalletType walletType;
    private BigDecimal balance;
    private LocalDateTime createdAt;

    public Wallet() {}

    public Wallet(int userId, String walletName, WalletType walletType) {
        this.userId     = userId;
        this.walletName = walletName;
        this.walletType = walletType;
        this.balance    = BigDecimal.ZERO;
    }

    public Wallet(int walletId, int userId, String walletName,
                  WalletType walletType, BigDecimal balance, LocalDateTime createdAt) {
        this.walletId   = walletId;
        this.userId     = userId;
        this.walletName = walletName;
        this.walletType = walletType;
        this.balance    = balance;
        this.createdAt  = createdAt;
    }

    // Getters & Setters
    public int getWalletId()                     { return walletId; }
    public void setWalletId(int walletId)        { this.walletId = walletId; }

    public int getUserId()                       { return userId; }
    public void setUserId(int userId)            { this.userId = userId; }

    public String getWalletName()                        { return walletName; }
    public void setWalletName(String walletName)         { this.walletName = walletName; }

    public WalletType getWalletType()                    { return walletType; }
    public void setWalletType(WalletType walletType)     { this.walletType = walletType; }

    public BigDecimal getBalance()                       { return balance; }
    public void setBalance(BigDecimal balance)           { this.balance = balance; }

    public LocalDateTime getCreatedAt()                  { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt)    { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return walletName + " (" + walletType.getDisplayName() + ") - " + balance;
    }
}
package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SavingsTransaction {

    public enum SavingsType {
        DEPOSIT, WITHDRAW;

        public String getDisplayName() {
            return this == DEPOSIT ? "Gửi" : "Rút";
        }
    }

    private int savingsTransId;
    private int fundId;
    private int userId;
    private int walletId;
    private BigDecimal amount;
    private SavingsType type;
    private String note;
    private LocalDateTime transDate;

    // Field join để hiển thị
    private String fundName;
    private String walletName;

    public SavingsTransaction() {}

    public SavingsTransaction(int fundId, int userId, int walletId,
                               BigDecimal amount, SavingsType type, String note) {
        this.fundId   = fundId;
        this.userId   = userId;
        this.walletId = walletId;
        this.amount   = amount;
        this.type     = type;
        this.note     = note;
    }

    // Getters & Setters
    public int getSavingsTransId()                           { return savingsTransId; }
    public void setSavingsTransId(int savingsTransId)        { this.savingsTransId = savingsTransId; }

    public int getFundId()                                   { return fundId; }
    public void setFundId(int fundId)                        { this.fundId = fundId; }

    public int getUserId()                                   { return userId; }
    public void setUserId(int userId)                        { this.userId = userId; }

    public int getWalletId()                                 { return walletId; }
    public void setWalletId(int walletId)                    { this.walletId = walletId; }

    public BigDecimal getAmount()                            { return amount; }
    public void setAmount(BigDecimal amount)                 { this.amount = amount; }

    public SavingsType getType()                             { return type; }
    public void setType(SavingsType type)                    { this.type = type; }

    public String getNote()                                  { return note; }
    public void setNote(String note)                         { this.note = note; }

    public LocalDateTime getTransDate()                      { return transDate; }
    public void setTransDate(LocalDateTime transDate)        { this.transDate = transDate; }

    public String getFundName()                              { return fundName; }
    public void setFundName(String fundName)                 { this.fundName = fundName; }

    public String getWalletName()                            { return walletName; }
    public void setWalletName(String walletName)             { this.walletName = walletName; }

    @Override
    public String toString() {
        return type.getDisplayName() + " | " + amount + " | " + fundName + " | " + transDate;
    }
}
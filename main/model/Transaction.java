package model;
 
import java.math.BigDecimal;
import java.time.LocalDateTime;
 
public class Transaction {
 
    public enum TransactionType {
        INCOME, EXPENSE;
 
        public String getDisplayName() {
            return this == INCOME ? "Thu" : "Chi";
        }
    }
 
    private int transactionId;
    private int userId;
    private int walletId;
    private int categoryId;
    private BigDecimal amount;
    private TransactionType type;
    private String note;
    private LocalDateTime transactionDate;
    private LocalDateTime createdAt;
 
    // Thêm các field join để hiển thị trên UI (không map DB trực tiếp)
    private String walletName;
    private String categoryName;
 
    public Transaction() {}
 
    public Transaction(int userId, int walletId, int categoryId,
                       BigDecimal amount, TransactionType type,
                       String note, LocalDateTime transactionDate) {
        this.userId          = userId;
        this.walletId        = walletId;
        this.categoryId      = categoryId;
        this.amount          = amount;
        this.type            = type;
        this.note            = note;
        this.transactionDate = transactionDate;
    }
 
    // Getters & Setters
    public int getTransactionId()                        { return transactionId; }
    public void setTransactionId(int transactionId)      { this.transactionId = transactionId; }
 
    public int getUserId()                               { return userId; }
    public void setUserId(int userId)                    { this.userId = userId; }
 
    public int getWalletId()                             { return walletId; }
    public void setWalletId(int walletId)                { this.walletId = walletId; }
 
    public int getCategoryId()                           { return categoryId; }
    public void setCategoryId(int categoryId)            { this.categoryId = categoryId; }
 
    public BigDecimal getAmount()                        { return amount; }
    public void setAmount(BigDecimal amount)             { this.amount = amount; }
 
    public TransactionType getType()                     { return type; }
    public void setType(TransactionType type)            { this.type = type; }
 
    public String getNote()                              { return note; }
    public void setNote(String note)                     { this.note = note; }
 
    public LocalDateTime getTransactionDate()                        { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate)    { this.transactionDate = transactionDate; }
 
    public LocalDateTime getCreatedAt()                              { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt)                { this.createdAt = createdAt; }
 
    public String getWalletName()                        { return walletName; }
    public void setWalletName(String walletName)         { this.walletName = walletName; }
 
    public String getCategoryName()                      { return categoryName; }
    public void setCategoryName(String categoryName)     { this.categoryName = categoryName; }
 
    @Override
    public String toString() {
        return type.getDisplayName() + " | " + amount + " | " + categoryName + " | " + transactionDate;
    }
}

package main.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class SavingsFund {

    private int fundId;
    private int userId;
    private String fundName;
    private BigDecimal target;
    private BigDecimal balance;
    private String note;
    private LocalDateTime createdAt;

    public SavingsFund() {}

    public SavingsFund(int userId, String fundName, BigDecimal target, String note) {
        this.userId   = userId;
        this.fundName = fundName;
        this.target   = target;
        this.balance  = BigDecimal.ZERO;
        this.note     = note;
    }

    public SavingsFund(int fundId, int userId, String fundName,
                       BigDecimal target, BigDecimal balance,
                       String note, LocalDateTime createdAt) {
        this.fundId    = fundId;
        this.userId    = userId;
        this.fundName  = fundName;
        this.target    = target;
        this.balance   = balance;
        this.note      = note;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public int getFundId()                       { return fundId; }
    public void setFundId(int fundId)            { this.fundId = fundId; }

    public int getUserId()                       { return userId; }
    public void setUserId(int userId)            { this.userId = userId; }

    public String getFundName()                      { return fundName; }
    public void setFundName(String fundName)         { this.fundName = fundName; }

    public BigDecimal getTarget()                    { return target; }
    public void setTarget(BigDecimal target)         { this.target = target; }

    public BigDecimal getBalance()                   { return balance; }
    public void setBalance(BigDecimal balance)       { this.balance = balance; }

    public String getNote()                          { return note; }
    public void setNote(String note)                 { this.note = note; }

    public LocalDateTime getCreatedAt()                  { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt)    { this.createdAt = createdAt; }

    /** Tính % đạt được so với mục tiêu */
    public double getProgressPercent() {
        if (target == null || target.compareTo(BigDecimal.ZERO) <= 0) return 0;
        return balance.divide(target, 4, java.math.RoundingMode.HALF_UP)
                      .multiply(BigDecimal.valueOf(100))
                      .doubleValue();
    }

    @Override
    public String toString() {
        return "Quỹ: " + fundName + " | Số dư: " + balance + " / " + target;
    }
}
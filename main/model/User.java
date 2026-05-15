package model;

import java.time.LocalDateTime;

public class User {
    private int userId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private LocalDateTime createdAt;
    

    public User() {}

    public User(String username, String password, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email    = email;
        
    }

    public User(int userId, String username, String password, String fullName,
                String email, LocalDateTime createdAt) {
        this.userId    = userId;
        this.username  = username;
        this.password  = password;
        this.fullName  = fullName;
        this.email     = email;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public int getUserId()               { return userId; }
    public void setUserId(int userId)    { this.userId = userId; }

    /** Alias getId() để tương thích controller cũ */
    public int getId()                   { return userId; }

    public String getUsername()                  { return username; }
    public void setUsername(String username)     { this.username = username; }

    public String getPassword()                  { return password; }
    public void setPassword(String password)     { this.password = password; }

    public String getFullName()                  { return fullName; }
    public void setFullName(String fullName)     { this.fullName = fullName; }

    /** Alias getFullname() khớp tên cột DB */
    public String getFullname()                  { return fullName; }
    public void setFullname(String fullname)     { this.fullName = fullname; }

    public String getEmail()                     { return email; }
    public void setEmail(String email)           { this.email = email; }

    public LocalDateTime getCreatedAt()                      { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt)        { this.createdAt = createdAt; }
    
    
    @Override
    public String toString() {
        return "User{userId=" + userId + ", username='" + username + "', fullName='" + fullName + "'}";
    }
}
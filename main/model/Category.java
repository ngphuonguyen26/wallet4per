package model;

public class Category {

    // Chỉ giữ ONE enum duy nhất
    public enum CategoryType {
        INCOME("Thu nhập"),
        EXPENSE("Chi tiêu");
        
        private String displayName;
        
        CategoryType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
        
        // Chuyển đổi từ String (hỗ trợ cả dữ liệu cũ và mới)
        public static CategoryType fromString(String text) {
            if (text == null) return null;
            
            // Xử lý dữ liệu cũ (Thu/Chi)
            if ("Thu".equals(text) || "INCOME".equals(text)) {
                return INCOME;
            }
            if ("Chi".equals(text) || "EXPENSE".equals(text)) {
                return EXPENSE;
            }
            
            // Trường hợp khác
            try {
                return CategoryType.valueOf(text);
            } catch (IllegalArgumentException e) {
                System.err.println("Unknown category type: " + text);
                return EXPENSE; // default
            }
        }
    }

    private int categoryId;
    private Integer userId;  // Dùng Integer để có thể null cho danh mục hệ thống
    private String categoryName;
    private CategoryType type;
    private String icon;
    private boolean isDefault;

    public Category() {
    }

    public Category(int categoryId, String categoryName, CategoryType type, String icon, boolean isDefault) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.type = type;
        this.icon = icon;
        this.isDefault = isDefault;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public String toString() {
        return categoryName;  // Đơn giản hơn, chỉ hiển thị tên danh mục
        // Hoặc: return categoryName + " [" + type.getDisplayName() + "]";
    }
}
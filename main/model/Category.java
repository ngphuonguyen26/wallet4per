package model;

public class Category {

    public enum CategoryType {
        INCOME, EXPENSE;

        public String getDisplayName() {
            return this == INCOME ? "Thu" : "Chi";
        }
    }

    private int categoryId;
    private String categoryName;
    private CategoryType type;
    private String icon;
    private boolean isDefault;

    public Category() {}

    public Category(int categoryId, String categoryName, CategoryType type,
                    String icon, boolean isDefault) {
        this.categoryId   = categoryId;
        this.categoryName = categoryName;
        this.type         = type;
        this.icon         = icon;
        this.isDefault    = isDefault;
    }

    // Getters & Setters
    public int getCategoryId()                       { return categoryId; }
    public void setCategoryId(int categoryId)        { this.categoryId = categoryId; }

    public String getCategoryName()                          { return categoryName; }
    public void setCategoryName(String categoryName)         { this.categoryName = categoryName; }

    public CategoryType getType()                    { return type; }
    public void setType(CategoryType type)           { this.type = type; }

    public String getIcon()                          { return icon; }
    public void setIcon(String icon)                 { this.icon = icon; }

    public boolean isDefault()                       { return isDefault; }
    public void setDefault(boolean isDefault)        { this.isDefault = isDefault; }

    @Override
    public String toString() {
        return categoryName + " [" + type.getDisplayName() + "]";
    }
}

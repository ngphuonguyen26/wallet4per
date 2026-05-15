package control;

import dao.CategoryDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import view.Category;

public class CategoryController {

    private final Category view;
    private final CategoryDAO categoryDAO;
    private int userId;

    public CategoryController(Category view, int userId) {
        this.view = view;
        this.userId = userId;
        this.categoryDAO = new CategoryDAO();

        loadCategories();
    }
    

    public void loadCategories() {
        DefaultTableModel tableModel = view.getModel_Category();
        tableModel.setRowCount(0);

        List<model.Category> categoryList = categoryDAO.getAllCategoriesByUser(userId);

        for (model.Category category : categoryList) {
            tableModel.addRow(new Object[]{
                    category.getCategoryId(),
                    category.getCategoryName(),
                    category.getType().getDisplayName(),
                    category.getIcon(),
                    category.isDefault() ? "Có" : "Không"
            });
        }
    }
}
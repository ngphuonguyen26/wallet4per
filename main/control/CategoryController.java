package main.control;

import main.dao.CategoryDAO;
import main.view.Category;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CategoryController {

    private Category view;
    private CategoryDAO categoryDAO;

    public CategoryController(Category view) {
        this.view = view;
        this.categoryDAO = new CategoryDAO();

        loadCategories();
    }

    public void loadCategories() {
        DefaultTableModel tableModel = view.getModel_Category();
        tableModel.setRowCount(0);

        List<main.model.Category> categoryList = categoryDAO.getAllCategories();

        for (main.model.Category category : categoryList) {
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
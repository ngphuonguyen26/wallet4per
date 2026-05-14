package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class Category extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table_Category;
	private DefaultTableModel model_Category;

	public Category() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		model_Category = new DefaultTableModel(
				new Object[][]{},
				new String[]{
						"ID", "Tên danh mục", "Loại", "Icon", "Mặc định"
				}
		);

		table_Category = new JTable(model_Category);

		JScrollPane scrollPane = new JScrollPane(table_Category);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

	public DefaultTableModel getModel_Category() {
		return model_Category;
	}
}
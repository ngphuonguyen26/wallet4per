package view;

import dao.TransactionDAO;
import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.Transaction;

/**
 * Thống kê chi tiêu theo NĂM
 */
public class StaYear extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final NumberFormat VND = NumberFormat.getInstance(new Locale("vi", "VN"));
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private JPanel contentPane, mainChildForm;
    private JTable table;
    private JTextField textField_Year, textField_TotalIncome, textField_TotalExpense;
    private JButton button_Load, button_Exit;
    private DefaultTableModel model;
    private TransactionDAO transactionDAO;
    private int userId;

    public StaYear(JPanel childform, int userId) {
        this.mainChildForm = childform;
        this.userId = userId;
        this.transactionDAO = new TransactionDAO();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 760, 510);
        setTitle("Thống kê theo năm");

        contentPane = new JPanel(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Top
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelTop.add(new JLabel("Năm:"));
        textField_Year = new JTextField(6);
        textField_Year.setText(String.valueOf(LocalDate.now().getYear()));
        panelTop.add(textField_Year);
        button_Load = new JButton("Xem");
        button_Exit = new JButton("Thoát");
        panelTop.add(button_Load);
        panelTop.add(button_Exit);
        contentPane.add(panelTop, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(
                new String[]{"ID", "Ví", "Danh mục", "Loại", "Số tiền", "Ghi chú", "Ngày"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        table.setRowHeight(24);
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
        panelBottom.add(new JLabel("Tổng thu:"));
        textField_TotalIncome = new JTextField(12); textField_TotalIncome.setEditable(false);
        panelBottom.add(textField_TotalIncome);
        panelBottom.add(new JLabel("Tổng chi:"));
        textField_TotalExpense = new JTextField(12); textField_TotalExpense.setEditable(false);
        panelBottom.add(textField_TotalExpense);
        contentPane.add(panelBottom, BorderLayout.SOUTH);

        addEvents();
        loadData();
    }

    private void addEvents() {
        button_Load.addActionListener(e -> loadData());
        button_Exit.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainChildForm.getLayout();
            cl.previous(mainChildForm);
            mainChildForm.revalidate(); mainChildForm.repaint();
        });
    }

    private void loadData() {
        model.setRowCount(0);
        String yearText = textField_Year.getText().trim();
        if (yearText.isEmpty()) { JOptionPane.showMessageDialog(this, "Vui lòng nhập năm."); return; }

        int year;
        try { year = Integer.parseInt(yearText); }
        catch (NumberFormatException e) { JOptionPane.showMessageDialog(this, "Năm không hợp lệ."); return; }

        List<Transaction> list = transactionDAO.getTransactionsByYear(userId, year);
        BigDecimal totalIncome = BigDecimal.ZERO, totalExpense = BigDecimal.ZERO;

        for (Transaction t : list) {
            // FIX: getDisplayName() → "Thu" / "Chi"
            String typeStr = t.getType().getDisplayName();
            model.addRow(new Object[]{
                    t.getTransactionId(),
                    t.getWalletName(),
                    t.getCategoryName(),
                    typeStr,
                    VND.format(t.getAmount()) + " đ",
                    t.getNote() != null ? t.getNote() : "",
                    t.getTransactionDate() != null ? t.getTransactionDate().format(FMT) : ""
            });
            if (t.getType() == Transaction.TransactionType.INCOME)
                totalIncome = totalIncome.add(t.getAmount());
            else
                totalExpense = totalExpense.add(t.getAmount());
        }

        textField_TotalIncome.setText(VND.format(totalIncome) + " đ");
        textField_TotalExpense.setText(VND.format(totalExpense) + " đ");
    }
}
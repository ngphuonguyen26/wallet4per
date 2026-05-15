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
 * Thống kê chi tiêu theo THÁNG
 */
public class StaMonth extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final NumberFormat VND = NumberFormat.getInstance(new Locale("vi", "VN"));
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private JPanel contentPane, mainChildForm;
    private JTable table;
    private JTextField textField_TotalIncome, textField_TotalExpense;
    private JComboBox<String> comboBox_Month, comboBox_Year;
    private JButton button_Load, button_Exit;
    private DefaultTableModel model;
    private TransactionDAO transactionDAO;
    private int userId;

    public StaMonth(JPanel childform, int userId) {
        this.mainChildForm = childform;
        this.userId = userId;
        this.transactionDAO = new TransactionDAO();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 760, 490);
        setTitle("Thống kê theo tháng");

        contentPane = new JPanel(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Top
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelTop.add(new JLabel("Tháng:"));
        comboBox_Month = new JComboBox<>();
        for (int i = 1; i <= 12; i++) comboBox_Month.addItem(String.valueOf(i));
        comboBox_Month.setSelectedItem(String.valueOf(LocalDate.now().getMonthValue()));
        panelTop.add(comboBox_Month);

        panelTop.add(new JLabel("Năm:"));
        comboBox_Year = new JComboBox<>();
        int y = LocalDate.now().getYear();
        for (int i = y - 5; i <= y + 1; i++) comboBox_Year.addItem(String.valueOf(i));
        comboBox_Year.setSelectedItem(String.valueOf(y));
        panelTop.add(comboBox_Year);

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
        int month = Integer.parseInt((String) comboBox_Month.getSelectedItem());
        int year  = Integer.parseInt((String) comboBox_Year.getSelectedItem());

        List<Transaction> list = transactionDAO.getTransactionsByMonth(userId, year, month);
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
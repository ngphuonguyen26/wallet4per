package view;

import dao.TransactionDAO;
import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.Transaction;

/**
 * Thống kê chi tiêu theo NGÀY
 */
public class StaDays extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final NumberFormat VND = NumberFormat.getInstance(new Locale("vi", "VN"));
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private JPanel contentPane;
    private JPanel mainChildForm;
    private JTable table_DailyStatistics;
    private JTextField textField_TotalExpense;
    private JTextField textField_TotalIncome;
    private JSpinner spinner_Date;
    private JButton button_Load;
    private JButton button_Exit;

    private int userId;
    private TransactionDAO transactionDAO;
    private DefaultTableModel model_DailyStatistics;

    public StaDays(JPanel childform, int userId) {
        this.mainChildForm = childform;
        this.userId = userId;
        this.transactionDAO = new TransactionDAO();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 720, 500);
        setTitle("Thống kê theo ngày");

        contentPane = new JPanel(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // --- Top panel ---
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelTop.add(new JLabel("Ngày:"));

        spinner_Date = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
        spinner_Date.setEditor(new JSpinner.DateEditor(spinner_Date, "yyyy-MM-dd"));
        spinner_Date.setPreferredSize(new Dimension(160, 26));
        panelTop.add(spinner_Date);

        button_Load = new JButton("Xem");
        panelTop.add(button_Load);
        button_Exit = new JButton("Thoát");
        panelTop.add(button_Exit);
        contentPane.add(panelTop, BorderLayout.NORTH);

        // --- Table ---
        model_DailyStatistics = new DefaultTableModel(
                new String[]{"ID", "Ví", "Danh mục", "Loại", "Số tiền", "Ghi chú", "Ngày"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table_DailyStatistics = new JTable(model_DailyStatistics);
        table_DailyStatistics.setRowHeight(24);
        contentPane.add(new JScrollPane(table_DailyStatistics), BorderLayout.CENTER);

        // --- Bottom panel ---
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
        panelBottom.add(new JLabel("Tổng thu:"));
        textField_TotalIncome = new JTextField(12);
        textField_TotalIncome.setEditable(false);
        panelBottom.add(textField_TotalIncome);
        panelBottom.add(new JLabel("Tổng chi:"));
        textField_TotalExpense = new JTextField(12);
        textField_TotalExpense.setEditable(false);
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
        model_DailyStatistics.setRowCount(0);
        Date selected = (Date) spinner_Date.getValue();
        LocalDate date = selected.toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        List<Transaction> list = transactionDAO.getTransactionsByDay(userId, date);
        BigDecimal totalIncome = BigDecimal.ZERO, totalExpense = BigDecimal.ZERO;

        for (Transaction t : list) {
            // FIX: dùng getDisplayName() để hiển thị "Thu"/"Chi" thay vì tên enum
            String typeStr = t.getType().getDisplayName();
            model_DailyStatistics.addRow(new Object[]{
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
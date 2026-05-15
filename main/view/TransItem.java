package view;

import dao.TransactionDAO;
import java.awt.*;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.Transaction;

/**
 * Xem toàn bộ giao dịch của user
 */
public class TransItem extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final NumberFormat VND = NumberFormat.getInstance(new Locale("vi", "VN"));
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private JPanel contentPane, mainChildForm;
    private JTable table_Transactions;
    private JButton button_Exit, button_Reload;
    private DefaultTableModel model_Transactions;
    private TransactionDAO transactionDAO;
    private int userId;

    public TransItem(JPanel childform, int userId) {
        this.mainChildForm = childform;
        this.userId = userId;
        this.transactionDAO = new TransactionDAO();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 780, 490);
        setTitle("Tất cả giao dịch");

        contentPane = new JPanel(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        model_Transactions = new DefaultTableModel(
                new String[]{"ID", "Ví", "Danh mục", "Loại", "Số tiền", "Ghi chú", "Ngày"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table_Transactions = new JTable(model_Transactions);
        table_Transactions.setRowHeight(24);
        contentPane.add(new JScrollPane(table_Transactions), BorderLayout.CENTER);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        button_Reload = new JButton("Tải lại");
        button_Exit   = new JButton("Thoát");
        button_Reload.setFont(new Font("Tahoma", Font.PLAIN, 14));
        button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelBottom.add(button_Reload);
        panelBottom.add(button_Exit);
        contentPane.add(panelBottom, BorderLayout.SOUTH);

        addEvents();
        loadTransactions();
    }

    private void addEvents() {
        button_Reload.addActionListener(e -> loadTransactions());
        button_Exit.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainChildForm.getLayout();
            cl.previous(mainChildForm);
            mainChildForm.revalidate(); mainChildForm.repaint();
        });
    }

    private void loadTransactions() {
        model_Transactions.setRowCount(0);
        List<Transaction> list = transactionDAO.getTransactionsByUser(userId);

        for (Transaction t : list) {
            // FIX: getDisplayName() → "Thu" / "Chi"
            model_Transactions.addRow(new Object[]{
                    t.getTransactionId(),
                    t.getWalletName(),
                    t.getCategoryName(),
                    t.getType().getDisplayName(),
                    VND.format(t.getAmount()) + " đ",
                    t.getNote() != null ? t.getNote() : "",
                    t.getTransactionDate() != null ? t.getTransactionDate().format(FMT) : ""
            });
        }

        if (list.isEmpty())
            JOptionPane.showMessageDialog(this, "Chưa có giao dịch nào.");
    }
}
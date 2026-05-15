package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.User;

public class TransactionGeneral extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel mainChildForm;
    // FIX: đổi tên biến nhất quán thành currentUser (trước là currentuser)
    private User currentUser;

    public TransactionGeneral(JPanel childform, User currentUser) {
        this.mainChildForm = childform;
        this.currentUser = currentUser;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 452, 304);
        setTitle("Giao dịch");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(4, 1, 0, 0));

        contentPane.add(new JPanel()); // spacer

        addMenuButton("➕ Thêm giao dịch", e -> {
            // FIX: dùng getUserId() thay vì getId()
            AddTransaction add = new AddTransaction(mainChildForm, currentUser.getUserId());
            openForm(add);
        });

        addMenuButton("📋 Giao dịch theo ngày", e -> {
            TransDay day = new TransDay(mainChildForm, currentUser.getUserId());
            openForm(day);
        });

        addMenuButton("📄 Tất cả giao dịch", e -> {
            TransItem item = new TransItem(mainChildForm, currentUser.getUserId());
            openForm(item);
        });
    }

    private void addMenuButton(String label, java.awt.event.ActionListener listener) {
        JPanel panel = new JPanel();
        JButton btn = new JButton(label);
        btn.setPreferredSize(new Dimension(200, 40));
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.addActionListener(listener);
        panel.add(btn);
        contentPane.add(panel);
    }

    public void openForm(JFrame form) {
        CardLayout cl = (CardLayout) mainChildForm.getLayout();
        String id = String.valueOf(form.hashCode());
        mainChildForm.add(form.getContentPane(), id);
        cl.show(mainChildForm, id);
        mainChildForm.revalidate();
        mainChildForm.repaint();
    }
    
}
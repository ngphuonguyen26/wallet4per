package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.User;

public class StatisticsGeneral extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel mainChildForm;
    private User currentUser;

    public StatisticsGeneral(JPanel childForm, User currentUser) {
        this.mainChildForm = childForm;
        this.currentUser = currentUser;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Thống kê chi tiêu");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(4, 1, 0, 0));

        contentPane.add(new JPanel()); // spacer top

        addMenuButton("📅 Thống kê theo ngày", e -> {
            // FIX: dùng getUserId() thay vì getId()
            StaDays day = new StaDays(mainChildForm, currentUser.getUserId());
            openForm(day);
        });

        addMenuButton("📆 Thống kê theo tháng", e -> {
            StaMonth month = new StaMonth(mainChildForm, currentUser.getUserId());
            openForm(month);
        });

        addMenuButton("🗓 Thống kê theo năm", e -> {
            StaYear year = new StaYear(mainChildForm, currentUser.getUserId());
            openForm(year);
        });
    }

    private void addMenuButton(String label, java.awt.event.ActionListener listener) {
        JPanel panel = new JPanel();
        JButton btn = new JButton(label);
        btn.setPreferredSize(new Dimension(220, 40));
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.addActionListener(listener);
        panel.add(btn);
        contentPane.add(panel);
    }

    public void openForm(JFrame form) {
        String id = String.valueOf(form.hashCode());
        mainChildForm.add(form.getContentPane(), id);

        CardLayout cl = (CardLayout) mainChildForm.getLayout();
        cl.show(mainChildForm, id);

        mainChildForm.revalidate();
        mainChildForm.repaint();
    }
}
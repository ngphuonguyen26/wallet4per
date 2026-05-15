package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.User;

public class UserInfo extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField textField_ID;
    private JTextField textField_Username;
    private JTextField textField_FullName;
    private JTextField textField_Email;

    private User currentUser;
    private JButton button_Exit;
    private JPanel mainChildForm;

    public UserInfo(JPanel childform, User user) {
        this.currentUser = user;
        this.mainChildForm = childform;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 596, 280);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths  = new int[]{0, 95, 0, 0, 0};
        gbl.rowHeights    = new int[]{0, 39, 40, 38, 37, 20, 51, 0, 0};
        gbl.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        String[] labels = {"ID:", "Username:", "Họ và tên:", "Email:"};
        int[] rows = {1, 2, 3, 4};

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0, 0, 5, 5);
            gbc.gridx = 1; gbc.gridy = rows[i];
            contentPane.add(lbl, gbc);
        }

        textField_ID       = addField(contentPane, 3, 1);
        textField_Username = addField(contentPane, 3, 2);
        textField_FullName = addField(contentPane, 3, 3);
        textField_Email    = addField(contentPane, 3, 4);

        button_Exit = new JButton("Thoát");
        button_Exit.setFont(new Font("Tahoma", Font.PLAIN, 17));
        GridBagConstraints gbc_btn = new GridBagConstraints();
        gbc_btn.insets = new Insets(0, 0, 5, 0);
        gbc_btn.gridx = 3; gbc_btn.gridy = 6;
        contentPane.add(button_Exit, gbc_btn);

        showUserInfo();
    }

    private JTextField addField(JPanel panel, int x, int y) {
        JTextField tf = new JTextField(10);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = x; gbc.gridy = y;
        panel.add(tf, gbc);
        return tf;
    }

    private void showUserInfo() {
        // FIX: dùng getUserId() và getFullName() — khớp với User model đã sửa
        textField_ID.setText(String.valueOf(currentUser.getUserId()));
        textField_Username.setText(currentUser.getUsername());
        textField_FullName.setText(currentUser.getFullName());
        textField_Email.setText(currentUser.getEmail());

        textField_ID.setEditable(false);
        textField_Username.setEditable(false);
        textField_FullName.setEditable(false);
        textField_Email.setEditable(false);
    }

    public JPanel getMainChildForm()    { return mainChildForm; }
    public JPanel getContentPanePanel() { return contentPane; }
    public JButton getButton_Exit()     { return button_Exit; }
}
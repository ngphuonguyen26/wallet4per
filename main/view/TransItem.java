package main.view;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransItem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btExit;
	private JPanel mainChildForm;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public TransItem(JPanel childform) {
		this.mainChildForm = childform;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "V\u00ED", "S\u1ED1 ti\u1EC1n", "Danh m\u1EE5c", "Lo\u1EA1i", "Ghi ch\u00FA", "Ng\u00E0y"
			}
		));
		scrollPane.setViewportView(table);
		
		btExit = new JButton("Thoát");
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) mainChildForm.getLayout();
				cl.previous(mainChildForm);
				mainChildForm.remove(contentPane); 
				mainChildForm.revalidate();
			    mainChildForm.repaint();
			}
		});
		btExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btExit);
	}

}

package spms;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class WarningBox extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarningBox dialog = new WarningBox("<html>This is a test Warning message.<br>It is working.</html>");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	
	public WarningBox(String msg)
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\files\\warning.jpg"));
		setForeground(Color.BLACK);
		setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		setTitle("SPMS Warning!");
		setResizable(false);
		setBounds(450, 200, 583, 180);
		getContentPane().setLayout(null);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setBounds(237, 117, 89, 23);
		getContentPane().add(btnOk);
		
		JLabel lblNewLabel = new JLabel(msg);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(10, 11, 557, 84);
		getContentPane().add(lblNewLabel);
	}
}

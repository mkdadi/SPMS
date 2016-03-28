package spms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomePage {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage window = new WelcomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel welcomePage = new JPanel();
		frame.getContentPane().add(welcomePage, "name_54045679379261");
		welcomePage.setLayout(null);
		
		JScrollPane noticeScrollPane = new JScrollPane();
		noticeScrollPane.setBounds(32, 101, 301, 188);
		welcomePage.add(noticeScrollPane);
		
		JTextPane noticeTextPane = new JTextPane();
		noticeTextPane.setEditable(false);
		noticeScrollPane.setViewportView(noticeTextPane);
		
		JLabel lblwelcomeText = new JLabel("Welcome to Swimming Pool Management Application");
		lblwelcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		lblwelcomeText.setBounds(32, 21, 728, 26);
		welcomePage.add(lblwelcomeText);
		
		JLabel lblNoticeBoard = new JLabel("Notice Board:");
		lblNoticeBoard.setBounds(32, 74, 224, 26);
		welcomePage.add(lblNoticeBoard);
		
		JLabel lblEvents = new JLabel("Events");
		lblEvents.setBounds(32, 318, 184, 21);
		welcomePage.add(lblEvents);
		
		JScrollPane eventScrollPane = new JScrollPane();
		eventScrollPane.setBounds(32, 338, 419, 122);
		welcomePage.add(eventScrollPane);
		
		JTextPane eventTextPane = new JTextPane();
		eventTextPane.setEditable(false);
		eventScrollPane.setViewportView(eventTextPane);
		
		textField = new JTextField();
		textField.setBounds(562, 101, 166, 20);
		welcomePage.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(562, 166, 166, 20);
		welcomePage.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(473, 104, 79, 14);
		welcomePage.add(lblId);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(473, 169, 79, 14);
		welcomePage.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals(null)&&!textField_1.getText().equals(null))
				{
					
				}
			}
		});
		btnLogin.setBounds(639, 224, 89, 23);
		welcomePage.add(btnLogin);
		
		JButton btnApplyForMembership = new JButton("Apply For Membership");
		btnApplyForMembership.setBounds(514, 338, 246, 23);
		welcomePage.add(btnApplyForMembership);
		
		JButton btnApplyForA = new JButton("Apply for a Course");
		btnApplyForA.setBounds(514, 386, 246, 23);
		welcomePage.add(btnApplyForA);
		
		JButton btnParticipateInEvent = new JButton("Participate in Event");
		btnParticipateInEvent.setBounds(514, 437, 246, 23);
		welcomePage.add(btnParticipateInEvent);
	}
}

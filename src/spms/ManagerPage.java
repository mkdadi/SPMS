package spms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.CardLayout;

public class ManagerPage {

	private JFrame frmSpms;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerPage window = new ManagerPage();
					window.frmSpms.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManagerPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpms = new JFrame();
		frmSpms.setTitle("SPMS");
		frmSpms.setResizable(false);
		frmSpms.setBounds(100, 100, 800, 500);
		frmSpms.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSpms.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 794, 471);
		frmSpms.getContentPane().add(tabbedPane);
		
		JPanel homeTab = new JPanel();
		tabbedPane.addTab("HOME", null, homeTab, null);
		homeTab.setLayout(null);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(10, 11, 89, 23);
		homeTab.add(btnRefresh);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(690, 11, 89, 23);
		homeTab.add(btnLogOut);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 769, 387);
		homeTab.add(scrollPane);
		
		JTextPane postsPane = new JTextPane();
		scrollPane.setViewportView(postsPane);
		
		JPanel notifsTab = new JPanel();
		tabbedPane.addTab("Notifications", null, notifsTab, null);
		notifsTab.setLayout(new CardLayout(0, 0));
		
		JPanel notifsList = new JPanel();
		notifsTab.add(notifsList, "name_12459527293883");
		notifsList.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 789, 443);
		notifsList.add(scrollPane_1);
		
		JPanel notifButtons = new JPanel();
		scrollPane_1.setViewportView(notifButtons);
		notifButtons.setLayout(null);
		
		JPanel workTab = new JPanel();
		tabbedPane.addTab("WORK", null, workTab, null);
		workTab.setLayout(null);
		
		JTabbedPane workTabPane = new JTabbedPane(JTabbedPane.TOP);
		workTabPane.setBounds(0, 0, 789, 443);
		workTab.add(workTabPane);
		
		JPanel addMember = new JPanel();
		workTabPane.addTab("Add Member", null, addMember, null);
		
		JPanel addCourse = new JPanel();
		workTabPane.addTab("Add Course", null, addCourse, null);
		
		JPanel addEvent = new JPanel();
		workTabPane.addTab("Add Event", null, addEvent, null);
		
		JPanel poolValues = new JPanel();
		workTabPane.addTab("Timings and Fees", null, poolValues, null);
		
		JPanel changeForm = new JPanel();
		workTabPane.addTab("Change Form", null, changeForm, null);
		
		JPanel socialTab = new JPanel();
		tabbedPane.addTab("SOCIAL", null, socialTab, null);
		socialTab.setLayout(null);
		
		JTabbedPane socialTabPane = new JTabbedPane(JTabbedPane.LEFT);
		socialTabPane.setBounds(0, 0, 789, 443);
		socialTab.add(socialTabPane);
		
		JPanel Discussions = new JPanel();
		socialTabPane.addTab("Discussions", null, Discussions, null);
		Discussions.setLayout(new CardLayout(0, 0));
		
		JPanel notice = new JPanel();
		socialTabPane.addTab("Notice", null, notice, null);
		
		JPanel Post = new JPanel();
		socialTabPane.addTab("POST", null, Post, null);
		
		JPanel complaints = new JPanel();
		socialTabPane.addTab("Complaints", null, complaints, null);
	}
}

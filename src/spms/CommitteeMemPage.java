package spms;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import user.CommitteeMember;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class CommitteeMemPage {

	public static int loggedIn=0;
	public static CommitteeMember cMember;
	
	private JFrame frmSpms;
	private JPanel homeTab;
	private JPanel notifsTab;
	private JPanel socialTab;
	private JTextPane postsPane;
	private JPanel Discussions;
	private JPanel notice;
	private JPanel Post;
	private JPanel complaints;
	private JScrollPane scrollPane_2;
	private JTextPane textNotif;
	private JPanel textPane;
	private JButton btnDel;
	private JButton btnAdd;
	private JPanel buttonsPane;
	private JScrollPane scrollPane_1;
	private JPanel notifButtons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(loggedIn==0)return;
					CommitteeMemPage window = new CommitteeMemPage();
					window.insertPosts();
					window.notifs();
					window.frmSpms.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean insertPosts()
	{
		Database database=new Database();
		ResultSet rSet=database.Query("SELECT * FROM posts ORDER BY `id` DESC");
		postsPane.setText("");
		try {
			while(rSet.next())
			{
				postsPane.setText(postsPane.getText()+rSet.getString("poster")
					+":\n    "+rSet.getString("post")+"\n\n----------------------------------\n\n");
			}
			postsPane.setCaretPosition(0);
		} catch (SQLException e) {
			return false;
		}
		database.disconnect();
		return true;
	}
	
	public boolean notifs()
	{
		Database db=new Database();
		ResultSet rSet=db.Query("SELECT notifics FROM cmembers WHERE id = "+cMember.id);
		String [] notifs=null;
		try {
			if(rSet.next())
			{
				notifs=rSet.getString("notifics").split(",");
			}
			int i=0;
			while(i<notifs.length)
			{
				ResultSet rrSet=db.Query("SELECT * FROM notifs WHERE id = "+notifs[i]);
				if(rrSet.next())
				{
					JButton button=new JButton("Text Notification");
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JButton button=(JButton)e.getSource();
							int notifId=(int)button.getClientProperty("notifId");
							Database database=new Database();
							ResultSet rrSet=db.Query("SELECT * FROM notifs WHERE id = "+notifId);
							try {
								if (rrSet.next()) {
									textNotif.setText(rrSet.getString("text"));
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							btnAdd.putClientProperty("type", 0);
							btnAdd.putClientProperty("notifId", notifId);
							btnDel.putClientProperty("type", 0);
							btnDel.putClientProperty("notifId", notifId);
							btnAdd.setText("OK");
							btnDel.setText("Back");
							buttonsPane.setVisible(false);
							textPane.setVisible(true);
							database.disconnect();
						}
					});
					button.putClientProperty("notifId", Integer.parseInt(notifs[i]));
					notifButtons.add(button);						
				}
				i++;
			}
			db.disconnect();
		} catch (Exception e) {
		}
		return true;
	}
	
	/**
	 * Create the application.
	 */
	public CommitteeMemPage() {
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
		
		homeTab = new JPanel();
		tabbedPane.addTab("HOME", null, homeTab, null);
		homeTab.setLayout(null);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertPosts();
			}
		});
		btnRefresh.setBounds(10, 11, 89, 23);
		homeTab.add(btnRefresh);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSpms.dispose();
				loggedIn=0;
				Spms.window.noticeTextPane.setText("");
				Spms.window.notices();
				Spms.window.frmSpms.setVisible(true);
			}
		});
		btnLogOut.setBounds(690, 11, 89, 23);
		homeTab.add(btnLogOut);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 769, 387);
		homeTab.add(scrollPane);
		
		postsPane = new JTextPane();
		postsPane.setEditable(false);
		scrollPane.setViewportView(postsPane);
		
		notifsTab = new JPanel();
		tabbedPane.addTab("Notifications", null, notifsTab, null);
		notifsTab.setLayout(new CardLayout(0, 0));
		
		JPanel notifsList = new JPanel();
		notifsTab.add(notifsList, "name_12459527293883");
		notifsList.setLayout(new CardLayout(0, 0));
		
		buttonsPane = new JPanel();
		notifsList.add(buttonsPane, "name_26076065599744");
		buttonsPane.setLayout(null);
		
		notifButtons = new JPanel();
		notifButtons.setBounds(0,0,789,443);
		
		scrollPane_1 = new JScrollPane(notifButtons);
		notifButtons.setLayout(new BoxLayout(notifButtons, BoxLayout.Y_AXIS));
		scrollPane_1.setBounds(0, 0, 789, 443);
		buttonsPane.add(scrollPane_1);
		
		textPane = new JPanel();
		notifsList.add(textPane, "name_23531619524392");
		textPane.setLayout(null);
		
		textNotif = new JTextPane();
		textNotif.setEditable(false);
		textNotif.setBounds(0, 0, 789, 403);
		
		btnDel = new JButton("Discard");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button= (JButton)e.getSource();
				int notifI=(int)button.getClientProperty("notifId");
				String notifId=""+notifI;
				Database database=new Database();
				ResultSet rSet=database.Query("SELECT notifics FROM cmembers WHERE id = "+cMember.id);
				String [] notifs;
				int check=0; 
				try {
					if(rSet.next())
					{
						notifs=rSet.getString("notifics").split(",");
						for(int i=0;i<notifs.length;i++)
						{
							if(notifs[i].equals(notifId))
							{
								notifs[i]=null;
								if(i==notifs.length-1)check=1;
							}
						}
						String tempNotif="NULL";
						for(int i=0;i<notifs.length;i++)
						{
							if(!(notifs[i]==null))
							{
								if(tempNotif.equals("NULL")) tempNotif="";
								tempNotif+=notifs[i];
								if(i!=notifs.length-1)
								{
									if(!(check==1&&i==notifs.length-2))
										tempNotif+=",";	
								}
							}
						}
						database.Update("UPDATE `cmembers` SET notifics = '"+tempNotif+"' WHERE id = '"+cMember.id+"'");
					}
					database.disconnect();
					textPane.setVisible(false);
					notifButtons.removeAll();
					buttonsPane.updateUI();
					notifs();
					buttonsPane.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDel.setBounds(10, 407, 89, 23);
		textPane.add(btnDel);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button= (JButton)e.getSource();
				int notifI=(int)button.getClientProperty("notifId");
				String notifId=""+notifI;
				Database database=new Database();
				ResultSet rSet=database.Query("SELECT notifics FROM cmembers WHERE id = "+cMember.id);
				String [] notifs;
				int check=0; 
				try {
					if(rSet.next())
					{
						notifs=rSet.getString("notifics").split(",");
						for(int i=0;i<notifs.length;i++)
						{
							if(notifs[i].equals(notifId))
							{
								notifs[i]=null;
								if(i==notifs.length-1)check=1;
							}
						}
						String tempNotif="NULL";
						for(int i=0;i<notifs.length;i++)
						{
							if(!(notifs[i]==null))
							{
								if(tempNotif.equals("NULL")) tempNotif="";
								tempNotif+=notifs[i];
								if(i!=notifs.length-1)
								{
									if(!(check==1&&i==notifs.length-2))
										tempNotif+=",";	
								}
							}
						}
						database.Update("UPDATE `cmembers` SET notifics = '"+tempNotif+"' WHERE id = '"+cMember.id+"'");
					}
					database.disconnect();
					textPane.setVisible(false);
					notifButtons.removeAll();
					notifs();
					buttonsPane.updateUI();
					buttonsPane.setVisible(true);
			}
			catch(Exception e2)
			{}
			}
		});
		btnAdd.setBounds(688, 407, 89, 23);
		textPane.add(btnAdd);
		
		scrollPane_2 = new JScrollPane(textNotif);
		scrollPane_2.setBounds(10, 11, 769, 366);
		textPane.add(scrollPane_2);
		
		socialTab = new JPanel();
		tabbedPane.addTab("SOCIAL", null, socialTab, null);
		socialTab.setLayout(null);
		
		JTabbedPane socialTabPane = new JTabbedPane(JTabbedPane.LEFT);
		socialTabPane.setBounds(0, 0, 789, 443);
		socialTab.add(socialTabPane);
		
		Discussions = new JPanel();
		socialTabPane.addTab("Discussions", null, Discussions, null);
		Discussions.setLayout(new CardLayout(0, 0));
		
		notice = new JPanel();
		socialTabPane.addTab("Notice", null, notice, null);
		
		Post = new JPanel();
		socialTabPane.addTab("POST", null, Post, null);
		
		complaints = new JPanel();
		socialTabPane.addTab("Complaints", null, complaints, null);
	}
}

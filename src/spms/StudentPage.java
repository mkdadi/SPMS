package spms;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import user.Student;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class StudentPage {

	public static int loggedIn=0;
	public static Student student;
	
	private JFrame frmSpms;
	private JPanel homeTab;
	private JPanel notifsTab;
	private JPanel socialTab;
	private JTextPane postsPane;
	private JPanel Discussions;
	private JPanel complaint;
	private JPanel Post;
	private JScrollPane scrollPane_2;
	private JTextPane textNotif;
	private JPanel textPane;
	private JButton btnDel;
	private JButton btnAdd;
	private JPanel buttonsPane;
	private JScrollPane scrollPane_1;
	private JPanel notifButtons;
	private JPanel optDis;
	private JButton btnViewDiscussions;
	private JButton btnStartDiscussion;
	protected JTextField newDisName;
	private JPanel disButtons;
	private JPanel discussion;
	private JTextPane disTitlePane;
	private JTextPane disPane;
	private JTextPane disMessPane;
	private JButton btnSend;
	private JButton btnBack;
	private JScrollPane scrollPane_4;
	private JTextPane complainPane;
	private JButton btnPostComplaint;
	private JTextPane postPane;
	private JButton btnPost;
	private JPanel disButtonsPane;
	private JScrollPane scrollPane_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(loggedIn==0)return;
					StudentPage window = new StudentPage();
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
		ResultSet rSet=database.Query("SELECT * FROM posts WHERE `type` = 0 ORDER BY `id` DESC");
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
		ResultSet rSet=db.Query("SELECT notifics FROM students WHERE id = "+student.id);
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
	public StudentPage() {
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
				ResultSet rSet=database.Query("SELECT notifics FROM students WHERE id = "+student.id);
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
						database.Update("UPDATE `students` SET notifics = '"+tempNotif+"' WHERE id = '"+student.id+"'");
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
				ResultSet rSet=database.Query("SELECT notifics FROM students WHERE id = "+student.id);
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
						database.Update("UPDATE `students` SET notifics = '"+tempNotif+"' WHERE id = '"+student.id+"'");
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
		
		optDis = new JPanel();
		Discussions.add(optDis, "name_25271656067761");
		optDis.setLayout(null);
		
		btnViewDiscussions = new JButton("View Discussions");
		btnViewDiscussions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				ResultSet rSet=db.Query("SELECT * FROM `discussions`");
				String discName=null;
				int ID=0;
				try {
					while(rSet.next())
					{
						discName=rSet.getString("title");
						ID=Integer.parseInt(rSet.getString("id"));
						JButton button=new JButton(discName);
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JButton button=(JButton)e.getSource();
								int id=(int)button.getClientProperty("id");
								Database database=new Database();
								ResultSet rrrSet = database.Query("SELECT * FROM discussions WHERE id = "+id);
								String[] discs=null;
								String[] divdisc=null;
								disPane.setText("");
								try {
									if(rrrSet.next())
									{
										disTitlePane.setText(rrrSet.getString("title"));
										discs=rrrSet.getString("discussion").split("\\$\\$\\$");
										for(int i=0;i<discs.length;i++)
										{
											divdisc=discs[i].split("\\$\\$");
											disPane.setText(disPane.getText()+"\n"+divdisc[0]
													+"\n	"+divdisc[1]+"\n\n---\n");
										}
									}
								} 
								catch (SQLException e1) {
								}
								btnSend.putClientProperty("id", id);
								disButtonsPane.setVisible(false);
								discussion.setVisible(true);
								database.disconnect();
							}
						});
						button.putClientProperty("id", ID);
						disButtons.add(button);
					}
				}
				catch(Exception e3)
				{}
				disButtonsPane.setVisible(true);
				optDis.setVisible(false);
				db.disconnect();
			}
		});
		btnViewDiscussions.setBounds(218, 104, 242, 23);
		optDis.add(btnViewDiscussions);
		
		btnStartDiscussion = new JButton("Start Discussion");
		btnStartDiscussion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newDisName.getText().equals("")) return;
				Database database=new Database();
				ResultSet rSet=database.Query("SELECT * FROM `discussions` WHERE `title` = '"+newDisName.getText()+"'");
				try {
					if (rSet.next()) {
						newDisName.setText("");
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.Update("INSERT INTO `discussions` VALUES (NULL,'"+newDisName.getText()+"',NULL)");
				rSet=database.Query("SELECT `id` FROM `discussions` WHERE `title` = '"+newDisName.getText()+"'");
				int id=0;
				try {
					if(rSet.next())
					{
						id=Integer.parseInt(rSet.getString("id"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				btnSend.putClientProperty("id", id);
				disTitlePane.setText(newDisName.getText());
				newDisName.setText("");
				optDis.setVisible(false);
				discussion.setVisible(true);
			}
		});
		btnStartDiscussion.setBounds(218, 281, 242, 23);
		optDis.add(btnStartDiscussion);
		
		newDisName = new JTextField();
		newDisName.setBounds(218, 250, 242, 20);
		optDis.add(newDisName);
		newDisName.setColumns(10);
		
		JLabel lblNewDiscussion = new JLabel("New Discussion:");
		lblNewDiscussion.setBounds(218, 225, 148, 14);
		optDis.add(lblNewDiscussion);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(143, 253, 75, 14);
		optDis.add(lblTitle);
		
		disButtonsPane = new JPanel();
		Discussions.add(disButtonsPane, "name_25210239375329");
		disButtonsPane.setLayout(new BoxLayout(disButtonsPane, BoxLayout.Y_AXIS));
		
		disButtons = new JPanel();
		
		scrollPane_5 = new JScrollPane(disButtons);
		disButtons.setLayout(new BoxLayout(disButtons, BoxLayout.Y_AXIS));
		disButtonsPane.add(scrollPane_5);
		
		discussion = new JPanel();
		Discussions.add(discussion, "name_25394556426930");
		discussion.setLayout(null);
		
		disTitlePane = new JTextPane();
		disTitlePane.setEditable(false);
		disTitlePane.setBounds(10, 11, 694, 29);
		discussion.add(disTitlePane);
		
		disPane = new JTextPane();
		disPane.setEditable(false);
		disPane.setBounds(10, 44, 694, 304);
		
		JScrollPane scrollPane_3 = new JScrollPane(disPane);
		scrollPane_3.setBounds(10, 44, 694, 304);
		discussion.add(scrollPane_3);
		
		disMessPane = new JTextPane();
		disMessPane.setBounds(10, 359, 594, 68);
		discussion.add(disMessPane);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(disMessPane.getText()==null) return;
				JButton button=(JButton)e.getSource();
				int id=(int)button.getClientProperty("id");
				Database database=new Database();
				ResultSet rSet=database.Query("SELECT discussion FROM discussions WHERE `id` = "+id);
				String disc=null;
				try {
					if(rSet.next())
					{
						disc=rSet.getString("discussion");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(disc==null) disc="";
				else disc+="$$$";
				rSet=database.Query("SELECT `name` FROM `students` WHERE `id` = "+student.id);
				String Name="";
				try {
					if(rSet.next())
					{
						Name+=rSet.getString("name");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				disc+=Name+":"+"$$"+disMessPane.getText();
				database.Update("UPDATE `discussions` SET `discussion` = '"+disc+"' WHERE `id` = "+id);
				ResultSet rrrSet = database.Query("SELECT * FROM discussions WHERE id = "+id);
				String[] discs=null;
				String[] divdisc=null;
				disPane.setText("");
				try {
					if(rrrSet.next())
					{
						discs=rrrSet.getString("discussion").split("\\$\\$\\$");
						for(int i=0;i<discs.length;i++)
						{
							divdisc=discs[i].split("\\$\\$");
							disPane.setText(disPane.getText()+"\n"+divdisc[0]
									+"\n	"+divdisc[1]+"\n\n---\n");
						}
					}
				} 
				catch (SQLException e1) {
				}
				disMessPane.setText("");
				database.disconnect();
			}
		});
		btnSend.setBounds(614, 359, 89, 23);
		discussion.add(btnSend);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disMessPane.setText("");
				disPane.setText("");
				disButtons.removeAll();
				disButtons.updateUI();
				disTitlePane.setText("");
				discussion.setVisible(false);
				optDis.setVisible(true);
			}
		});
		btnBack.setBounds(614, 393, 89, 23);
		discussion.add(btnBack);
		
		complaint = new JPanel();
		socialTabPane.addTab("Complaint", null, complaint, null);
		complaint.setLayout(null);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 11, 683, 340);
		complaint.add(scrollPane_4);
		
		complainPane = new JTextPane();
		scrollPane_4.setViewportView(complainPane);
		
		btnPostComplaint = new JButton("Complain");
		btnPostComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				Database database=new Database();
				database.Update("INSERT INTO notices VALUES('"+complainPane.getText()+"','"
				+java.sql.Date.valueOf(LocalDate.now().plusDays(7))+"')");
				database.disconnect();
				complainPane.setText("");
			}
		});
		btnPostComplaint.setBounds(573, 390, 120, 23);
		complaint.add(btnPostComplaint);
		
		Post = new JPanel();
		socialTabPane.addTab("POST", null, Post, null);
		Post.setLayout(null);
		
		postPane = new JTextPane();
		postPane.setBounds(10, 11, 694, 301);
		Post.add(postPane);
		
		btnPost = new JButton("Post");
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(postPane.getText().equals(""))
					return;
				Database database=new Database();
				ResultSet rSet=database.Query("SELECT `name` FROM `students` WHERE `id` = "+student.id);
				String Name="";
				try {
					if(rSet.next())
					{
						Name+=rSet.getString("name");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.Update("INSERT INTO `posts` VALUES (NULL,0,'"+Name+"','"+postPane.getText()+"',NULL)");
				postPane.setText("");
				database.disconnect();
			}
		});
		btnPost.setBounds(615, 364, 89, 23);
		Post.add(btnPost);
	}
}

package spms;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import user.CommitteeMember;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import java.awt.Font;
import java.awt.Toolkit;

public class CommitteeMemPage {

	public static int loggedIn=0;
	public static CommitteeMember cMember;
	public static int Type;
	public static String eventID;
	public static String courseID;

	private JFrame frmSpms;
	private JPanel homeTab;
	private JPanel notifsTab;
	private JPanel socialTab;
	private JTextPane postsPane;
	private JPanel Discussions;
	private JPanel notice;
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
	private JTextPane noticePane;
	private JButton btnPostNotice;
	private JTextPane postPane;
	private JButton btnPost;
	private JRadioButton rdbtnPublic;
	private JRadioButton rdbtnPrivate;
	private JPanel workTab;
	private JPanel cMemWork;
	private JPanel cCoordWork;
	private JPanel eManWork;
	private JPanel disButtonsPane;
	private JScrollPane scrollPane_5;
	private JLabel lblNotify;
	private JTextPane cCoordPane;
	private JButton btnNotify;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnBackStroke;
	private JRadioButton rdbtnBreastStroke;
	private JRadioButton rdbtnButterfly;
	private JRadioButton rdbtnm;
	private JRadioButton rdbtnm_1;
	private JRadioButton rdbtnm_2;
	private JRadioButton rdbtnm_3;
	private JRadioButton rdbtnMen;
	private JRadioButton rdbtnWomen;
	private ButtonGroup group;
	private ButtonGroup b2ButtonGroup;
	private ButtonGroup b3;
	private ButtonGroup b1;

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
		String notificList;
		try {
			if(rSet.next())
			{
				notificList=rSet.getString("notifics");
				if(notificList!=null) notifs=notificList.split(",");
				else notifs=null;
			}
			int i=0;
			while(notifs!=null&&i<notifs.length)
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
							ResultSet rrSet=database.Query("SELECT * FROM notifs WHERE id = "+notifId);
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
		switch (Type) {
		case 0:
			break;
		case 1:
		{
			cMemWork.setVisible(false);
			cCoordWork.setVisible(true);
			break;
		}
		case 2:
		{
			cMemWork.setVisible(false);
			Database database=new Database();
			ResultSet rSet=database.Query("SELECT `type` FROM `events` WHERE `id` = '"+eventID+"'");
			int type=131;
			try {
				if (rSet.next()) {
					type=Integer.parseInt(rSet.getString("type"));
					if(type%10==1)
					{
						b2ButtonGroup.clearSelection();
						rdbtnMen.setSelected(true);
					}
					else {
						b2ButtonGroup.clearSelection();
						rdbtnWomen.setSelected(true);
					}
					type/=10;
					if (type%10==1) {
						b3.clearSelection();
						rdbtnm.setSelected(true);
					}
					else if (type%10==2) {
						b3.clearSelection();
						rdbtnm_1.setSelected(true);
					}
					else if (type%10==3) {
						b3.clearSelection();
						rdbtnm_2.setSelected(true);
					}
					else {
						b3.clearSelection();
						rdbtnm_3.setSelected(true);
					}
					type/=10;
					if (type%10==1) {
						b1.clearSelection();
						rdbtnNewRadioButton.setSelected(true);
					}
					else if (type%10==2) {
						b1.clearSelection();
						rdbtnBackStroke.setSelected(true);
					}
					else if (type%10==3) {
						b1.clearSelection();
						rdbtnBreastStroke.setSelected(true);
					}
					else {
						b1.clearSelection();
						rdbtnButterfly.setSelected(true);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			eManWork.setVisible(true);
			break;
		}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpms = new JFrame();
		frmSpms.setIconImage(Toolkit.getDefaultToolkit().getImage(".\\files\\spms1.jpg"));
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
				String notificList;
				int check=0; 
				try {
					if(rSet.next())
					{
						notificList=rSet.getString("notifics");
						if(notificList!=null)notifs=notificList.split(",");
						else notifs=null;
						for(int i=0;i<notifs.length;i++)
						{
							if(notifs[i].equals(notifId))
							{
								notifs[i]=null;
								if(i==notifs.length-1)check=1;
							}
						}
						String tempNotif="NULL";
						for(int i=0;notifs!=null&&i<notifs.length;i++)
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
				String notificList;
				int check=0; 
				try {
					if(rSet.next())
					{
						notificList=rSet.getString("notifics");
						if(notificList!=null)notifs=notificList.split(",");
						else notifs=null;
						for(int i=0;notifs!=null&&i<notifs.length;i++)
						{
							if(notifs[i].equals(notifId))
							{
								notifs[i]=null;
								if(i==notifs.length-1)check=1;
							}
						}
						String tempNotif="NULL";
						for(int i=0;notifs!=null&&i<notifs.length;i++)
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
		
		workTab = new JPanel();
		tabbedPane.addTab("Work", null, workTab, null);
		workTab.setLayout(new CardLayout(0, 0));
		
		cMemWork = new JPanel();
		workTab.add(cMemWork, "name_1684213478490");
		cMemWork.setLayout(null);
		
		JTextPane txtpnYouHaveNo = new JTextPane();
		txtpnYouHaveNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnYouHaveNo.setText("You have no work allotted right now.");
		txtpnYouHaveNo.setBounds(232, 191, 313, 29);
		cMemWork.add(txtpnYouHaveNo);
		
		cCoordWork = new JPanel();
		workTab.add(cCoordWork, "name_1687568462609");
		cCoordWork.setLayout(null);
		
		lblNotify = new JLabel("Notify Students:");
		lblNotify.setBounds(26, 24, 107, 21);
		cCoordWork.add(lblNotify);
		
		cCoordPane = new JTextPane();
		cCoordPane.setBounds(25, 56, 722, 295);
		cCoordWork.add(cCoordPane);
		
		btnNotify = new JButton("Notify");
		btnNotify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cCoordPane.getText()==null||cCoordPane.getText().equals(""))
				{
					WarningBox warningBox=new WarningBox("Enter something to notify students!");
					warningBox.setVisible(true);
					return;
				}
				Database database=new Database();
				database.Update("INSERT INTO `notifs` VALUES (NULL,'"+cCoordPane.getText().replace("'", "")+"',NULL,0)");
				ResultSet rSet=database.Query("SELECT `students` FROM `courses` WHERE `ID` = '"+courseID+"'");
				String[] students;
				String notifics;
				String studentList;
				String id="";
				try {
					if (rSet.next()) {
						studentList=rSet.getString("students");
						if(studentList!=null)students=studentList.split(",");
						else students=null;
						rSet=database.Query("SELECT `id` FROM `notifs` WHERE `text` = '"+cCoordPane.getText().replace("'", "")+"'");
						if(rSet.next())
						{
							id=rSet.getString("id");
							for(int i=0;students!=null&&i<students.length;i++)
							{
								rSet=database.Query("SELECT `notifics` FROM `students` WHERE id = "+students[i]);
								if (rSet.next()) {
									notifics=rSet.getString("notifics");
									if(notifics!=null&&!(notifics.equals(""))&&!(notifics.equals("NULL"))) notifics+=",";
									else notifics="";
									notifics+=id;
									database.Update("UPDATE `students` SET `notifics` = '"+notifics+"' WHERE `id` = '"+students[i]+"'");
								}
							}
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				cCoordPane.setText("");
			}
		});
		btnNotify.setBounds(658, 387, 89, 23);
		cCoordWork.add(btnNotify);
		
		eManWork = new JPanel();
		workTab.add(eManWork, "name_1726427535571");
		eManWork.setLayout(null);
		
		rdbtnNewRadioButton = new JRadioButton("Free Style");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(222, 44, 109, 23);
		eManWork.add(rdbtnNewRadioButton);
		
		rdbtnBackStroke = new JRadioButton("Back Stroke");
		rdbtnBackStroke.setBounds(222, 96, 109, 23);
		eManWork.add(rdbtnBackStroke);
		
		rdbtnBreastStroke = new JRadioButton("Breast Stroke");
		rdbtnBreastStroke.setBounds(222, 149, 109, 23);
		eManWork.add(rdbtnBreastStroke);
		
		rdbtnButterfly = new JRadioButton("Butterfly");
		rdbtnButterfly.setBounds(222, 203, 109, 23);
		eManWork.add(rdbtnButterfly);
		
		b1=new ButtonGroup();
		b1.add(rdbtnBackStroke);
		b1.add(rdbtnBreastStroke);
		b1.add(rdbtnButterfly);
		b1.add(rdbtnNewRadioButton);
		
		rdbtnm = new JRadioButton("20m");
		rdbtnm.setBounds(473, 44, 109, 23);
		eManWork.add(rdbtnm);
		
		rdbtnm_1 = new JRadioButton("50m");
		rdbtnm_1.setBounds(473, 96, 109, 23);
		eManWork.add(rdbtnm_1);
		
		rdbtnm_2 = new JRadioButton("100m");
		rdbtnm_2.setSelected(true);
		rdbtnm_2.setBounds(473, 149, 109, 23);
		eManWork.add(rdbtnm_2);
		
		rdbtnm_3 = new JRadioButton("200m");
		rdbtnm_3.setBounds(473, 203, 109, 23);
		eManWork.add(rdbtnm_3);
		
		b3=new ButtonGroup();
		b3.add(rdbtnm);
		b3.add(rdbtnm_1);
		b3.add(rdbtnm_2);
		b3.add(rdbtnm_3);
		
		rdbtnMen = new JRadioButton("Men");
		rdbtnMen.setSelected(true);
		rdbtnMen.setBounds(265, 286, 109, 23);
		eManWork.add(rdbtnMen);
		
		rdbtnWomen = new JRadioButton("Women");
		rdbtnWomen.setBounds(376, 286, 109, 23);
		eManWork.add(rdbtnWomen);
		
		b2ButtonGroup=new ButtonGroup();
		b2ButtonGroup.add(rdbtnMen);
		b2ButtonGroup.add(rdbtnWomen);
		
		JButton btnSetFormat = new JButton("Set Format");
		btnSetFormat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a1;
				String first="";
				String second="";
				String third="";
				if(rdbtnNewRadioButton.isSelected())
				{
					a1=1;
					third="Free Style";
				}
				else if (rdbtnBackStroke.isSelected()) {
					a1=2;
					third="Back Stroke";
				}
				else if (rdbtnBreastStroke.isSelected()) {
					a1=3;
					third="Breast Stroke";
				}
				else {
					a1=4;
					third="Butterfly";
				}
				int a2;
				if(rdbtnm.isSelected())
				{
					a2=1;
					second="20m";
				}
				else if (rdbtnm_1.isSelected()) {
					a2=2;
					second="50m";
				}
				else if (rdbtnm_2.isSelected()) {
					a2=3;
					second="100m";
				}
				else {
					a2=4;
					second="200m";
				}
				int a3;
				if (rdbtnMen.isSelected()) {
					a3=1;
					first="Men";
				}
				else {
					a3=2;
					first="Women";
				}
				Timestamp timestamp=null;
				Database database=new Database();
				database.Update("UPDATE `events` SET `type` = "+a1+a2+a3+" WHERE `ID` = '"+eventID+"'");
				ResultSet rSet=database.Query("SELECT `timestamp` FROM `events` WHERE `ID` = '"+eventID+"'");
				try {
					if (rSet.next()) {
						timestamp=Timestamp.valueOf((rSet.getString("timestamp")));
						java.sql.Date date=new java.sql.Date(timestamp.getTime());
						database.Update("INSERT INTO `notices` VALUES ('Event format change:\n	The format of the event, "+eventID
								+", has been changed to "+first+" "+second+" "+third+"','"+date+"')");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.disconnect();
			}
		});
		btnSetFormat.setBounds(305, 361, 129, 23);
		eManWork.add(btnSetFormat);
		
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
				if(newDisName.getText().equals("")) 
				{
					WarningBox warningBox=new WarningBox("Enter name to start discussion!");
					warningBox.setVisible(true);
					return;
				}
				Database database=new Database();
				ResultSet rSet=database.Query("SELECT * FROM `discussions` WHERE `title` = '"+newDisName.getText().replace("'", "")+"'");
				try {
					if (rSet.next()) {
						WarningBox warningBox=new WarningBox("Discussion with this name already exists!");
						warningBox.setVisible(true);
						newDisName.setText("");
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.Update("INSERT INTO `discussions` VALUES (NULL,'"+newDisName.getText().replace("'", "")+"',NULL)");
				rSet=database.Query("SELECT `id` FROM `discussions` WHERE `title` = '"+newDisName.getText().replace("'", "")+"'");
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
				if(disMessPane.getText()==null) 
				{
					WarningBox warningBox=new WarningBox("Enter something to post to discussion!");
					warningBox.setVisible(true);
					return;
				}
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
				rSet=database.Query("SELECT `name` FROM `cmembers` WHERE `id` = "+cMember.id);
				String Name="(Com. Member) ";
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
		
		notice = new JPanel();
		socialTabPane.addTab("Notice", null, notice, null);
		notice.setLayout(null);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 11, 683, 340);
		notice.add(scrollPane_4);
		
		noticePane = new JTextPane();
		scrollPane_4.setViewportView(noticePane);
		
		btnPostNotice = new JButton("Post Notice");
		btnPostNotice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (noticePane.getText().equals("")) {
					WarningBox warningBox=new WarningBox("Enter something to make Notice!");
					warningBox.setVisible(true);
				}
				Database database=new Database();
				database.Update("INSERT INTO notices VALUES('"+noticePane.getText().replace("'", "")+"','"
				+java.sql.Date.valueOf(LocalDate.now().plusDays(7))+"')");
				database.disconnect();
				noticePane.setText("");
			}
		});
		btnPostNotice.setBounds(573, 390, 120, 23);
		notice.add(btnPostNotice);
		
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
				{
					WarningBox warningBox=new WarningBox("Enter something to post!");
					warningBox.setVisible(true);
					return;
				}
				Database database=new Database();
				ResultSet rSet=database.Query("SELECT `name` FROM `cmembers` WHERE `id` = "+cMember.id);
				String Name="(Com. Member) ";
				try {
					if(rSet.next())
					{
						Name+=rSet.getString("name");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				boolean type=rdbtnPrivate.isSelected();
				int postType=0;
				if(type)
				{
					postType=1;
				}
				database.Update("INSERT INTO `posts` VALUES (NULL,"+postType+",'"+Name+"','"+postPane.getText().replace("'", "")+"',NULL)");
				postPane.setText("");
				database.disconnect();
			}
		});
		btnPost.setBounds(615, 404, 89, 23);
		Post.add(btnPost);
		
		rdbtnPublic = new JRadioButton("Public");
		rdbtnPublic.setSelected(true);
		rdbtnPublic.setBounds(350, 350, 109, 23);
		Post.add(rdbtnPublic);
		
		rdbtnPrivate = new JRadioButton("Private");
		rdbtnPrivate.setBounds(471, 350, 109, 23);
		Post.add(rdbtnPrivate);
		
		group = new ButtonGroup();
		group.add(rdbtnPublic);
		group.add(rdbtnPrivate);
		 
		Calendar calendar=new Calendar();
		tabbedPane.addTab("CALENDAR", null, calendar.frame.getContentPane(), null);
		socialTab.setLayout(null);
	}
}

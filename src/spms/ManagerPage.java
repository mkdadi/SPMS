package spms;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import user.Manager;
import user.Member;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class ManagerPage {

	public static int loggedIn=0;
	public static Manager manager;
	
	private JFrame frmSpms;
	private JPanel homeTab;
	private JPanel notifsTab;
	private JPanel workTab;
	private JPanel socialTab;
	private JTextPane postsPane;
	private JPanel addCourse;
	private JPanel addEvent;
	private JPanel poolValues;
	private JPanel changeForm;
	private JPanel Discussions;
	private JPanel notice;
	private JPanel Post;
	private JPanel complaints;
	private JTextField aCName;
	private JTextField aCID;
	private JLabel lblExistingCourseMax;
	private JLabel lblStartDate;
	private JTextField aCSD;
	private JLabel lblDdMmYyyy;
	private JLabel lblDuration;
	private JTextField aCDuration;
	private JLabel lblInMonthseither;
	private JLabel lblFee;
	private JTextField aCFee;
	private JLabel lblInRupees;
	private JTextField aCCoordinator;
	private JTextField aEName;
	private JTextField aEID;
	private JTextField aEManager;
	private JTextField aEST;
	private JTextField aEDuration;
	private JTextField aEFee;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_10;
	private JLabel lblRandomData;
	private JLabel lblFiles;
	private JLabel lblString;
	private JLabel lblLocaldate;
	private JLabel lblString_1;
	private JLabel lblString_2;
	private JLabel lblString_3;
	private JTextField cF1;
	private JTextField cF2;
	private JTextField cF3;
	private JTextField cF4;
	private JTextField cF5;
	private JTextField cF6;
	private JTextField cF7;
	private JTextField cF8;
	private JTextField cF9;
	private JButton btnChange;
	private JLabel lblStartTimeOf;
	private JTextField startTimeSlot;
	private JLabel lblAnyIntegerFrom;
	private JLabel lblEndTimeOf;
	private JTextField endTimeSlot;
	private JLabel lblAnyIntegerFrom_1;
	private JLabel memberfeelbl;
	private JTextField memberFee;
	private JLabel lblInRupees_1;
	private JButton btnChange_1;
	private JTextField maxBookings;
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
					ManagerPage window = new ManagerPage();
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
		ResultSet rSet=db.Query("SELECT notifics FROM manager WHERE id = "+manager.id);
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
				String type,userID;
				if(rrSet.next())
				{
					type=rrSet.getString("type");
					userID=rrSet.getString("userID");
					switch(Integer.parseInt(type))
					{
						case 0:
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
						break;
						case 1:
						{
							JButton button=new JButton("Membership Application");
							button.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									JButton button=(JButton)e.getSource();
									int id=(int)button.getClientProperty("id");
									int notifId=(int)button.getClientProperty("notifId");
									Database database=new Database();
									ResultSet rrrSet = database.Query("SELECT * FROM mapplics WHERE id = "+id);
									try {
										if(rrrSet.next())
										{
											textNotif.setText("Name: "+rrrSet.getString("name")+
													"\nEmail ID: "+rrrSet.getString("emailID")+"\nPhone No: "+rrrSet.getString("phoneNo")+"\n"
												+"Birth Day: "+rrrSet.getString("dob")+"\nAddress: "+rrrSet.getString("address"));
										btnAdd.putClientProperty("type", 1);
										btnAdd.putClientProperty("id", id);
										btnAdd.putClientProperty("notifId", notifId);
										btnDel.putClientProperty("type", 1);
										btnDel.putClientProperty("id", id);
										btnDel.putClientProperty("notifId", notifId);
										btnAdd.setText("Add");
										btnDel.setText("Discard");
										}
									} catch (SQLException e1) {
									}
									buttonsPane.setVisible(false);
									textPane.setVisible(true);
									database.disconnect();
								}
							});
							button.putClientProperty("id", Integer.parseInt(userID));
							button.putClientProperty("notifId", Integer.parseInt(notifs[i]));
							notifButtons.add(button);
						}
							break;
						case 2:
						{
							JButton button=new JButton("Join Course Application");
							button.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									JButton button=(JButton)e.getSource();
									int id=(int)button.getClientProperty("id");
									int notifId=(int)button.getClientProperty("notifId");
									Database database=new Database();
									ResultSet rrrSet = database.Query("SELECT * FROM capplics WHERE id = "+id);
									try {
										if(rrrSet.next())
										{
											textNotif.setText("Name: "+rrrSet.getString("name")+
													"\nEmail ID: "+rrrSet.getString("emailID")+"\nPhone No: "+rrrSet.getString("phoneNo")+"\n"
												+"Birth Day: "+rrrSet.getString("dob")+"\nAddress: "+rrrSet.getString("address")
												+"\nApplied to Course: "+rrrSet.getString("courseID"));
										btnAdd.putClientProperty("type", 2);
										btnAdd.putClientProperty("id", id);
										btnAdd.putClientProperty("notifId", notifId);
										btnDel.putClientProperty("type", 2);
										btnDel.putClientProperty("id", id);
										btnDel.putClientProperty("notifId", notifId);
										btnAdd.setText("Add");
										btnDel.setText("Discard");
										}
									} catch (SQLException e1) {
									}
									buttonsPane.setVisible(false);
									textPane.setVisible(true);
									database.disconnect();
								}
							});
							button.putClientProperty("id", Integer.parseInt(userID));
							button.putClientProperty("notifId", Integer.parseInt(notifs[i]));
							notifButtons.add(button);
						}
							break;
						case 3:
						{
							JButton button=new JButton("Participation Application");
							button.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									JButton button=(JButton)e.getSource();
									int id=(int)button.getClientProperty("id");
									int notifId=(int)button.getClientProperty("notifId");
									Database database=new Database();
									ResultSet rrrSet = database.Query("SELECT * FROM papplics WHERE id = "+id);
									try {
										if(rrrSet.next())
										{
											textNotif.setText("Name: "+rrrSet.getString("name")+
													"\nEmail ID: "+rrrSet.getString("emailID")+"\nPhone No: "+rrrSet.getString("phoneNo")+"\n"
												+"Birth Day: "+rrrSet.getString("dob")+"\nAddress: "+rrrSet.getString("address")
												+"\nApplied for event: "+rrrSet.getString("eventID"));
										btnAdd.putClientProperty("type", 3);
										btnAdd.putClientProperty("id", id);
										btnAdd.putClientProperty("notifId", notifId);
										btnDel.putClientProperty("type", 3);
										btnDel.putClientProperty("id", id);
										btnDel.putClientProperty("notifId", notifId);
										btnAdd.setText("Add");
										btnDel.setText("Discard");
										}
									} catch (SQLException e1) {
									}
									buttonsPane.setVisible(false);
									textPane.setVisible(true);
									database.disconnect();
								}
							});
							button.putClientProperty("id", Integer.parseInt(userID));
							button.putClientProperty("notifId", Integer.parseInt(notifs[i]));
							notifButtons.add(button);
						}
							break;
						case 4:
						{
							JButton button=new JButton("Viewer Application");
							button.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									JButton button=(JButton)e.getSource();
									int id=(int)button.getClientProperty("id");
									int notifId=(int)button.getClientProperty("notifId");
									Database database=new Database();
									ResultSet rrrSet = database.Query("SELECT * FROM vapplics WHERE id = "+id);
									try {
										if(rrrSet.next())
										{
											textNotif.setText("Name: "+rrrSet.getString("name")+
													"\nEmail ID: "+rrrSet.getString("emailID")+"\nPhone No: "+rrrSet.getString("phoneNo")+"\n"
												+"Birth Day: "+rrrSet.getString("dob")+"\nAddress: "+rrrSet.getString("address"));
										btnAdd.putClientProperty("type", 4);
										btnAdd.putClientProperty("id", id);
										btnAdd.putClientProperty("notifId", notifId);
										btnDel.putClientProperty("type", 4);
										btnDel.putClientProperty("id", id);
										btnDel.putClientProperty("notifId", notifId);
										btnAdd.setText("Add");
										btnDel.setText("Discard");
										}
									} catch (SQLException e1) {
									}
									buttonsPane.setVisible(false);
									textPane.setVisible(true);
									database.disconnect();
								}
							});
							button.putClientProperty("id", Integer.parseInt(userID));
							button.putClientProperty("notifId", Integer.parseInt(notifs[i]));
							notifButtons.add(button);
						}
							break;
					}
						
				}
				i++;
			}
			db.disconnect();
		} catch (Exception e) {
			db.disconnect();
		}
		return true;
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
				int id=(int)button.getClientProperty("id");
				int type=(int)button.getClientProperty("type");
				int notifI=(int)button.getClientProperty("notifId");
				String notifId=""+notifI;
				Database database=new Database();
				switch (type) {
				case 0:
					break;
				case 1:
					database.Update("DELETE FROM mapplics WHERE id = "+id);
					break;
				case 3:
					database.Update("DELETE FROM capplics WHERE id = "+id);
					break;
				case 4:
					database.Update("DELETE FROM vapplics WHERE id = "+id);
					break;
				}
				database.Update("DELETE FROM notifs WHERE id = "+notifId);
				ResultSet rSet=database.Query("SELECT notifics FROM manager WHERE id = "+manager.id);
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
						database.Update("UPDATE `manager` SET notifics = '"+tempNotif+"' WHERE id = '"+manager.id+"'");
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
				int id=(int)button.getClientProperty("id");
				int type=(int)button.getClientProperty("type");
				int notifI=(int)button.getClientProperty("notifId");
				String notifId=""+notifI;
				Database database=new Database();
				switch (type) {
				case 0:
					break;
				case 1:
					ResultSet rSet=database.Query("SELECT * FROM mapplics WHERE id = '"+id+"'");
					try {
						if (rSet.next()) {
							Member member=new Member();
							member.Name=rSet.getString("name");
							member.emailID=rSet.getString("emailID");
							database.Update("INSERT INTO member VALUES(NULL,'"+member.Name+"','"+member.emailID+"',NULL)");
							rSet=database.Query("SELECT id FROM member WHERE `name` = '"+member.Name+"' AND `emailID` = '"+member.emailID+"'");
							if (rSet.next()) {
								member.id=Integer.parseInt(rSet.getString("id"));
							}
							Mail mail=new Mail();
							mail.to.add(member.emailID);
							mail.subject="SPMS Member Approval";
							mail.message="Dear "+member.Name+",\n    Your request for membership in SPMS has been accepted. "
									+ "You will receive credentials for your account in a short while.\n\nRegards,\nManager,\nSPMS.";
							try {
								mail.send();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							member.setPassword();
							String pass="";
							rSet=database.Query("SELECT password FROM member WHERE `id` = '"+member.id+"'");
							if (rSet.next()) {
								pass=rSet.getString("password");
							}
							database.Update("INSERT INTO login VALUES ('"+member.id+"','"+pass+"','1')");
							database.Update("DELETE FROM mapplics WHERE id = "+id);
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					break;
				case 3:
					//database.Update("DELETE FROM capplics WHERE id = "+id);
					break;
				case 4:
					//database.Update("DELETE FROM vapplics WHERE id = "+id);
					break;
				}
				database.Update("DELETE FROM notifs WHERE id = "+notifId);
				ResultSet rSet=database.Query("SELECT notifics FROM manager WHERE id = "+manager.id);
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
						database.Update("UPDATE `manager` SET notifics = '"+tempNotif+"' WHERE id = '"+manager.id+"'");
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
		tabbedPane.addTab("WORK", null, workTab, null);
		workTab.setLayout(null);
		
		JTabbedPane workTabPane = new JTabbedPane(JTabbedPane.TOP);
		workTabPane.setBounds(0, 0, 789, 443);
		workTab.add(workTabPane);
		
		addCourse = new JPanel();
		workTabPane.addTab("Add Course", null, addCourse, null);
		addCourse.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Course Name:");
		lblCourseName.setBounds(45, 34, 107, 14);
		addCourse.add(lblCourseName);
		
		aCName = new JTextField();
		aCName.setBounds(176, 31, 202, 20);
		addCourse.add(aCName);
		aCName.setColumns(10);
		
		JLabel lblCourseId = new JLabel("Course ID:");
		lblCourseId.setBounds(45, 89, 107, 14);
		addCourse.add(lblCourseId);
		
		aCID = new JTextField();
		aCID.setBounds(176, 86, 202, 20);
		addCourse.add(aCID);
		aCID.setColumns(10);
		
		JLabel lblUseYearOr = new JLabel("Use year or start date of the course in ID so that it won't collide with");
		lblUseYearOr.setBounds(388, 89, 386, 14);
		addCourse.add(lblUseYearOr);
		
		lblExistingCourseMax = new JLabel(" existing course. Max 10 Characters.");
		lblExistingCourseMax.setBounds(388, 106, 220, 14);
		addCourse.add(lblExistingCourseMax);
		
		lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(45, 220, 107, 14);
		addCourse.add(lblStartDate);
		
		aCSD = new JTextField();
		aCSD.setBounds(176, 217, 202, 20);
		addCourse.add(aCSD);
		aCSD.setColumns(10);
		
		lblDdMmYyyy = new JLabel("dd mm yyyy format");
		lblDdMmYyyy.setBounds(386, 220, 254, 14);
		addCourse.add(lblDdMmYyyy);
		
		lblDuration = new JLabel("Duration:");
		lblDuration.setBounds(45, 285, 107, 14);
		addCourse.add(lblDuration);
		
		aCDuration = new JTextField();
		aCDuration.setBounds(176, 282, 202, 20);
		addCourse.add(aCDuration);
		aCDuration.setColumns(10);
		
		lblInMonthseither = new JLabel("in months (either 3 or 6 or 12 months)");
		lblInMonthseither.setBounds(388, 285, 355, 14);
		addCourse.add(lblInMonthseither);
		
		lblFee = new JLabel("Fee:");
		lblFee.setBounds(45, 341, 107, 14);
		addCourse.add(lblFee);
		
		aCFee = new JTextField();
		aCFee.setBounds(176, 338, 202, 20);
		addCourse.add(aCFee);
		aCFee.setColumns(10);
		
		lblInRupees = new JLabel("in rupees");
		lblInRupees.setBounds(388, 341, 124, 14);
		addCourse.add(lblInRupees);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Course course=new Course();
				course.Name=aCName.getText();
				course.coordinatorID=Integer.parseInt(aCCoordinator.getText());
				String[] date=aCSD.getText().split(" ");
				course.start=LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
				course.duration=Integer.parseInt(aCDuration.getText());
				course.ID=aCID.getText();
				course.fee=Integer.parseInt(aCFee.getText());
				course.add();
				aCName.setText("");
				aCCoordinator.setText("");
				aCDuration.setText("");
				aCFee.setText("");
				aCID.setText("");
				aCSD.setText("");
				String notice=course.Name+":\n	This course is starting on "+course.start+" for a duration of "+course.duration
						+" months and Fee is "+course.fee+" rupees. Applications are welcome for this course now,"
						+" those who want to apply please enter the following detail:\n"
						+"Course ID: "+course.ID+"\nFee receipt must be submitted during application. If not selected for the course"
						+" money will be refunded.";
				Database database=new Database();
				database.Update("INSERT INTO notices VALUES('"+notice+"','"+java.sql.Date.valueOf(course.start)+"')");
				database.disconnect();
			}
		});
		btnSubmit.setBounds(289, 381, 89, 23);
		addCourse.add(btnSubmit);
		
		JLabel lblCourseCoordinator = new JLabel("Course Coordinator:");
		lblCourseCoordinator.setBounds(45, 157, 124, 14);
		addCourse.add(lblCourseCoordinator);
		
		aCCoordinator = new JTextField();
		aCCoordinator.setBounds(176, 154, 202, 20);
		addCourse.add(aCCoordinator);
		aCCoordinator.setColumns(10);
		
		JLabel lblGiveAnExisting = new JLabel("give an existing Committee member ID");
		lblGiveAnExisting.setBounds(388, 157, 355, 14);
		addCourse.add(lblGiveAnExisting);
		
		addEvent = new JPanel();
		workTabPane.addTab("Add Event", null, addEvent, null);
		addEvent.setLayout(null);
		
		JLabel lblEventName = new JLabel("Event Name:");
		lblEventName.setBounds(45, 34, 107, 14);
		addEvent.add(lblEventName);
		
		aEName = new JTextField();
		aEName.setColumns(10);
		aEName.setBounds(176, 31, 202, 20);
		addEvent.add(aEName);
		
		JLabel lblEventId = new JLabel("Event ID:");
		lblEventId.setBounds(45, 89, 107, 14);
		addEvent.add(lblEventId);
		
		aEID = new JTextField();
		aEID.setColumns(10);
		aEID.setBounds(176, 86, 202, 20);
		addEvent.add(aEID);
		
		JLabel lblUseYearOr_1 = new JLabel("Use year or start date of the event in ID so that it won't collide with");
		lblUseYearOr_1.setBounds(388, 89, 386, 14);
		addEvent.add(lblUseYearOr_1);
		
		JLabel lblExistingCoursesMax = new JLabel(" existing courses. Max 10 Characters.");
		lblExistingCoursesMax.setBounds(388, 106, 220, 14);
		addEvent.add(lblExistingCoursesMax);
		
		JLabel label_4 = new JLabel("give an existing Committee member ID");
		label_4.setBounds(388, 157, 355, 14);
		addEvent.add(label_4);
		
		aEManager = new JTextField();
		aEManager.setColumns(10);
		aEManager.setBounds(176, 154, 202, 20);
		addEvent.add(aEManager);
		
		JLabel lblEventManager = new JLabel("Event Manager:");
		lblEventManager.setBounds(45, 157, 124, 14);
		addEvent.add(lblEventManager);
		
		JLabel lblStartTime = new JLabel("Start Time:");
		lblStartTime.setBounds(45, 220, 107, 14);
		addEvent.add(lblStartTime);
		
		aEST = new JTextField();
		aEST.setColumns(10);
		aEST.setBounds(176, 217, 202, 20);
		addEvent.add(aEST);
		
		JLabel lblDdMmYyyy_1 = new JLabel("dd mm yyyy hh format");
		lblDdMmYyyy_1.setBounds(386, 220, 254, 14);
		addEvent.add(lblDdMmYyyy_1);
		
		JLabel lblInNoOf = new JLabel("in no of slots");
		lblInNoOf.setBounds(388, 285, 355, 14);
		addEvent.add(lblInNoOf);
		
		aEDuration = new JTextField();
		aEDuration.setColumns(10);
		aEDuration.setBounds(176, 282, 202, 20);
		addEvent.add(aEDuration);
		
		JLabel label_9 = new JLabel("Duration:");
		label_9.setBounds(45, 285, 107, 14);
		addEvent.add(label_9);
		
		JLabel lblParticipationFee = new JLabel("Participation Fee:");
		lblParticipationFee.setBounds(45, 341, 107, 14);
		addEvent.add(lblParticipationFee);
		
		aEFee = new JTextField();
		aEFee.setColumns(10);
		aEFee.setBounds(176, 338, 202, 20);
		addEvent.add(aEFee);
		
		JLabel label_11 = new JLabel("in rupees");
		label_11.setBounds(388, 341, 124, 14);
		addEvent.add(label_11);
		
		JButton button = new JButton("Submit");
		button.setBounds(289, 381, 89, 23);
		addEvent.add(button);
		
		poolValues = new JPanel();
		workTabPane.addTab("Timings and Fees", null, poolValues, null);
		poolValues.setLayout(null);
		
		lblStartTimeOf = new JLabel("Start time of first Slot:");
		lblStartTimeOf.setBounds(57, 50, 194, 14);
		poolValues.add(lblStartTimeOf);
		
		startTimeSlot = new JTextField();
		startTimeSlot.setBounds(225, 47, 209, 20);
		poolValues.add(startTimeSlot);
		startTimeSlot.setColumns(10);
		
		lblAnyIntegerFrom = new JLabel("any integer from 0 to 23");
		lblAnyIntegerFrom.setBounds(478, 50, 194, 14);
		poolValues.add(lblAnyIntegerFrom);
		
		lblEndTimeOf = new JLabel("End time of Last Slot:");
		lblEndTimeOf.setBounds(57, 119, 194, 14);
		poolValues.add(lblEndTimeOf);
		
		endTimeSlot = new JTextField();
		endTimeSlot.setBounds(225, 116, 209, 20);
		poolValues.add(endTimeSlot);
		endTimeSlot.setColumns(10);
		
		lblAnyIntegerFrom_1 = new JLabel("any integer from 0 to 23");
		lblAnyIntegerFrom_1.setBounds(478, 119, 194, 14);
		poolValues.add(lblAnyIntegerFrom_1);
		
		memberfeelbl = new JLabel("MemberShip Fee:");
		memberfeelbl.setBounds(57, 191, 194, 14);
		poolValues.add(memberfeelbl);
		
		memberFee = new JTextField();
		memberFee.setBounds(225, 188, 209, 20);
		poolValues.add(memberFee);
		memberFee.setColumns(10);
		
		lblInRupees_1 = new JLabel("in rupees");
		lblInRupees_1.setBounds(478, 191, 194, 14);
		poolValues.add(lblInRupees_1);
		
		btnChange_1 = new JButton("Change");
		btnChange_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.Update("UPDATE `spmsvalues` SET `start` = '"+startTimeSlot.getText()+"', `end` = '"+endTimeSlot.getText()
				+"', `memberFee` = '"+memberFee.getText()+"', `maxBookings` = '"+maxBookings.getText()+"' WHERE `id` = '1')");
				ResultSet resultSet=db.Query("SELECT memberList FROM slots WHERE `hour` < '"+startTimeSlot.getText()
					+"' OR `hour` > '"+endTimeSlot.getText()+"'");
				
				//TODO
			}
		});
		btnChange_1.setBounds(225, 313, 89, 23);
		poolValues.add(btnChange_1);
		
		JLabel lblMaximumNoBookings = new JLabel("Maximum No. Bookings Per Slot:");
		lblMaximumNoBookings.setBounds(57, 265, 329, 14);
		poolValues.add(lblMaximumNoBookings);
		
		maxBookings = new JTextField();
		maxBookings.setBounds(225, 262, 209, 20);
		poolValues.add(maxBookings);
		maxBookings.setColumns(10);
		
		changeForm = new JPanel();
		workTabPane.addTab("Change Form", null, changeForm, null);
		changeForm.setLayout(null);
		
		label = new JLabel("1.");
		label.setBounds(162, 85, 46, 14);
		changeForm.add(label);
		
		label_1 = new JLabel("2.");
		label_1.setBounds(162, 140, 46, 14);
		changeForm.add(label_1);
		
		label_2 = new JLabel("3.");
		label_2.setBounds(162, 197, 46, 14);
		changeForm.add(label_2);
		
		label_3 = new JLabel("4.");
		label_3.setBounds(162, 251, 46, 14);
		changeForm.add(label_3);
		
		label_5 = new JLabel("5.");
		label_5.setBounds(162, 309, 46, 14);
		changeForm.add(label_5);
		
		label_6 = new JLabel("6.");
		label_6.setBounds(435, 85, 46, 14);
		changeForm.add(label_6);
		
		label_7 = new JLabel("7.");
		label_7.setBounds(435, 140, 46, 14);
		changeForm.add(label_7);
		
		label_8 = new JLabel("8.");
		label_8.setBounds(435, 197, 46, 14);
		changeForm.add(label_8);
		
		label_10 = new JLabel("9.");
		label_10.setBounds(435, 251, 46, 14);
		changeForm.add(label_10);
		
		lblRandomData = new JLabel("Random Data");
		lblRandomData.setBounds(58, 33, 141, 14);
		changeForm.add(lblRandomData);
		
		lblFiles = new JLabel("Files required");
		lblFiles.setBounds(435, 33, 217, 14);
		changeForm.add(lblFiles);
		
		lblString = new JLabel("String");
		lblString.setBounds(58, 85, 46, 14);
		changeForm.add(lblString);
		
		lblLocaldate = new JLabel("LocalDate");
		lblLocaldate.setBounds(58, 140, 94, 14);
		changeForm.add(lblLocaldate);
		
		lblString_1 = new JLabel("String");
		lblString_1.setBounds(58, 197, 46, 14);
		changeForm.add(lblString_1);
		
		lblString_2 = new JLabel("String");
		lblString_2.setBounds(58, 251, 46, 14);
		changeForm.add(lblString_2);
		
		lblString_3 = new JLabel("String");
		lblString_3.setBounds(58, 309, 46, 14);
		changeForm.add(lblString_3);
		
		cF1 = new JTextField();
		cF1.setBounds(218, 82, 158, 20);
		changeForm.add(cF1);
		cF1.setColumns(10);
		
		cF2 = new JTextField();
		cF2.setBounds(218, 137, 158, 20);
		changeForm.add(cF2);
		cF2.setColumns(10);
		
		cF3 = new JTextField();
		cF3.setBounds(218, 194, 158, 20);
		changeForm.add(cF3);
		cF3.setColumns(10);
		
		cF4 = new JTextField();
		cF4.setBounds(218, 248, 158, 20);
		changeForm.add(cF4);
		cF4.setColumns(10);
		
		cF5 = new JTextField();
		cF5.setBounds(218, 306, 158, 20);
		changeForm.add(cF5);
		cF5.setColumns(10);
		
		cF6 = new JTextField();
		cF6.setBounds(491, 82, 229, 20);
		changeForm.add(cF6);
		cF6.setColumns(10);
		
		cF7 = new JTextField();
		cF7.setBounds(491, 137, 229, 20);
		changeForm.add(cF7);
		cF7.setColumns(10);
		
		cF8 = new JTextField();
		cF8.setBounds(491, 194, 229, 20);
		changeForm.add(cF8);
		cF8.setColumns(10);
		
		cF9 = new JTextField();
		cF9.setBounds(491, 248, 229, 20);
		changeForm.add(cF9);
		cF9.setColumns(10);
		
		btnChange = new JButton("Change");
		btnChange.setBounds(435, 305, 89, 23);
		changeForm.add(btnChange);
		
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

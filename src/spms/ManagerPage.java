package spms;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import user.EventManager;
import user.Manager;
import user.Member;
import user.Participant;
import user.Student;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

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
	private JPanel addCM;
	private JTextField CMName;
	private JTextField CMEmail;
	private JPanel disButtonsPane;
	private JPanel optDis;
	private JButton btnViewDiscussions;
	private JButton btnStartDiscussion;
	private JPanel discussion;
	private JTextPane disPane;
	private JTextPane disTitlePane;
	private JTextPane disMessPane;
	private JButton btnBack;
	private JScrollPane scrollPane_4;
	private JTextPane noticePane;
	private JButton btnPostNotice;
	private JTextPane postPane;
	private JButton btnPost;
	private JTextField newDisName;
	private JButton btnSend;
	private JRadioButton rdbtnPrivate;
	private JRadioButton rdbtnPublic;
	private JScrollPane scrollPane_5;
	private JPanel disButtons;
	private JPanel addBooking;
	private JTextField bookedByID;
	private JTextField bookingDate;
	private JTextField startingSlotBooking;
	private JTextField noOfSlotsBooking;

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
					Database database=new Database();
					ResultSet rSet=database.Query("SELECT * FROM `form` WHERE `id` = 1");
					if (rSet.next()) {
						window.cF1.setText(rSet.getString("cF1"));
						window.cF2.setText(rSet.getString("cF2"));
						window.cF3.setText(rSet.getString("cF3"));
						window.cF4.setText(rSet.getString("cF4"));
						window.cF5.setText(rSet.getString("cF5"));
						window.cF6.setText(rSet.getString("cF6"));
						window.cF7.setText(rSet.getString("cF7"));
						window.cF8.setText(rSet.getString("cF8"));
						window.cF9.setText(rSet.getString("cF9"));
					}
					rSet.close();
					database.disconnect();
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
		String notificList;
		try {
			if(rSet.next())
			{
				notificList=rSet.getString("notifics");
				if (notificList!=null) {
					notifs=notificList.split(",");
				}
				else {
					notifs=null;
				}
			}
			int i=0;
			while(notifs!=null&&i<notifs.length)
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
									int id=(int)button.getClientProperty("id");
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
									btnAdd.putClientProperty("id", id);
									btnDel.putClientProperty("type", 0);
									btnDel.putClientProperty("notifId", notifId);
									btnDel.putClientProperty("id", id);
									btnAdd.setText("OK");
									btnDel.setText("Back");
									buttonsPane.setVisible(false);
									textPane.setVisible(true);
									database.disconnect();
								}
							});
							button.putClientProperty("id", 0);
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
											textNotif.setText(cF1.getText()+": "+rrrSet.getString("name")+
													"\n"+cF2.getText()+": "+rrrSet.getString("emailID")
													+"\n"+cF3.getText()+": "+rrrSet.getString("phoneNo")+"\n"
													+cF4.getText()+": "+rrrSet.getString("dob")
													+"\n"+cF5.getText()+": "+rrrSet.getString("address")
													+"\n"+cF6.getText()+": "+rrrSet.getString("photo").replace("/", "")+"(Local Folder)"
													+"\n"+cF7.getText()+": "+rrrSet.getString("birthCert").replace("/", "")+"(Local Folder)"
													+"\n"+cF8.getText()+": "+rrrSet.getString("medicalCert").replace("/", "")+"(Local Folder)"
													+"\n"+cF9.getText()+": "+rrrSet.getString("feeReceipt").replace("/", "")+"(Local Folder)");
											FTPTransfer ftpTransfer;
											try {
												ftpTransfer = new FTPTransfer();
												ftpTransfer.downloadFrom=rrrSet.getString("photo");
												ftpTransfer.downloadTo=rrrSet.getString("photo").replace("/", "");
												ftpTransfer.download();
												ftpTransfer.downloadFrom=rrrSet.getString("birthCert");
												ftpTransfer.downloadTo=rrrSet.getString("birthCert").replace("/", "");
												ftpTransfer.download();
												ftpTransfer.downloadFrom=rrrSet.getString("medicalCert");
												ftpTransfer.downloadTo=rrrSet.getString("medicalCert").replace("/", "");
												ftpTransfer.download();
												ftpTransfer.downloadFrom=rrrSet.getString("feeReceipt");
												ftpTransfer.downloadTo=rrrSet.getString("feeReceipt").replace("/", "");
												ftpTransfer.download();
											} catch (Exception e1) {
												e1.printStackTrace();
											}
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
												+"\nApplied to Course: "+rrrSet.getString("courseID")
												+"\nPhoto: "+rrrSet.getString("photo").replace("/", "")+"(Local Folder)"
												+"\nFee Receipt: "+rrrSet.getString("feeReceipt").replace("/", "")+"(Local Folder)"
												+"\nMedical Certificate: "+rrrSet.getString("medicalCert").replace("/", "")+"(Local Folder)"
												);
											try {
												FTPTransfer ftpTransfer=new FTPTransfer();
												ftpTransfer.downloadFrom=rrrSet.getString("medicalCert");
												ftpTransfer.downloadTo=rrrSet.getString("medicalCert").replace("/", "");
												ftpTransfer.download();
												ftpTransfer.downloadFrom=rrrSet.getString("photo");
												ftpTransfer.downloadTo=rrrSet.getString("photo").replace("/", "");
												ftpTransfer.download();
												ftpTransfer.downloadFrom=rrrSet.getString("feeReceipt");
												ftpTransfer.downloadTo=rrrSet.getString("feeReceipt").replace("/", "");
												ftpTransfer.download();
											} catch (Exception e1) {
												e1.printStackTrace();
												}
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
												+"\nApplied for event: "+rrrSet.getString("eventID")
												+"\nPhoto: "+rrrSet.getString("photo").replace("/", "")+"(Local Folder)"
												+"\nFee Receipt: "+rrrSet.getString("feeReceipt").replace("/", "")+"(Local Folder)"
												+"\nMedical Certificate: "+rrrSet.getString("medicalCert").replace("/", "")+"(Local Folder)"
												);
											try {
												FTPTransfer ftpTransfer=new FTPTransfer();
												ftpTransfer.downloadFrom=rrrSet.getString("medicalCert");
												ftpTransfer.downloadTo=rrrSet.getString("medicalCert").replace("/", "");
												ftpTransfer.download();
												ftpTransfer.downloadFrom=rrrSet.getString("photo");
												ftpTransfer.downloadTo=rrrSet.getString("photo").replace("/", "");
												ftpTransfer.download();
												ftpTransfer.downloadFrom=rrrSet.getString("feeReceipt");
												ftpTransfer.downloadTo=rrrSet.getString("feeReceipt").replace("/", "");
												ftpTransfer.download();
											} catch (Exception e1) {
												e1.printStackTrace();
											}
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
											textNotif.setText("Name: "+rrrSet.getString("name")+"\nEmail ID: "+rrrSet.getString("emailID")
													+"\nPhone No: "+rrrSet.getString("phoneNo")+"\n"+"Address: "+rrrSet.getString("address")
													+"\nFee Receipt: "+rrrSet.getString("ticketReceipt").replace("/", "")+"(Local Folder)"
													);
											try {
												FTPTransfer ftpTransfer=new FTPTransfer();
												ftpTransfer.downloadFrom=rrrSet.getString("ticketReceipt");
												ftpTransfer.downloadTo=rrrSet.getString("ticketReceipt").replace("/", "");
												ftpTransfer.download();
											} catch (Exception e1) {
												e1.printStackTrace();
											}
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
										e1.printStackTrace();
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
					database.Update("DELETE FROM `notifs` WHERE `id` = "+notifI);
					break;
				case 3:
					database.Update("DELETE FROM capplics WHERE id = "+id);
					database.Update("DELETE FROM `notifs` WHERE `id` = "+notifI);
					break;
				case 4:
					database.Update("DELETE FROM vapplics WHERE id = "+id);
					database.Update("DELETE FROM `notifs` WHERE `id` = "+notifI);
					break;
				}
				ResultSet rSet=database.Query("SELECT notifics FROM manager WHERE id = "+manager.id);
				String [] notifs;
				String notificList;
				int check=0; 
				try {
					if(rSet.next())
					{
						notificList=rSet.getString("notifics");
						if(notificList!=null)notifs=notificList.split(",");
						else {
							notifs=null;
						}
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
				{
					ResultSet rSet=database.Query("SELECT * FROM mapplics WHERE id = '"+id+"'");
					try {
						if (rSet.next()) {
							Member member=new Member();
							member.Name=rSet.getString("name");
							member.emailID=rSet.getString("emailID");
							database.Update("INSERT INTO member VALUES(NULL,'"+member.Name+"','"+member.emailID+"',NULL,NULL)");
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
							database.Update("DELETE FROM `notifs` WHERE `id` = "+notifI);
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
					break;
				case 2:
				{
					ResultSet rSet=database.Query("SELECT * FROM capplics WHERE id = '"+id+"'");
					try {
						if (rSet.next()) {
							Student student=new Student();
							student.Name=rSet.getString("name");
							student.emailID=rSet.getString("emailID");
							student.course=new Course();
							student.course.ID=rSet.getString("courseID");
							database.Update("INSERT INTO students VALUES(NULL,'"+student.Name+"','"+student.emailID+"',NULL,'"+student.course.ID
									+"',NULL)");
							rSet=database.Query("SELECT id FROM students WHERE `name` = '"+student.Name+"' AND `courseID` = '"
									+student.course.ID+"'");
							if (rSet.next()) {
								student.id=Integer.parseInt(rSet.getString("id"));
							}
							String particiList="";
							rSet=database.Query("SELECT `students` FROM `courses` WHERE `ID` = "+student.id);
							if (rSet.next()) {
								particiList=rSet.getString("students");
							}
							if(particiList==null||particiList.equals("NULL")||particiList.equals("")) particiList=student.id+"";
							else particiList+=","+student.id;
							database.Update("UPDATE `courses` SET `students` = '"+particiList+"' WHERE `ID` = '"+student.course.ID+"'");
							Mail mail=new Mail();
							mail.to.add(student.emailID);
							mail.subject="SPMS Course Approval";
							mail.message="Dear "+student.Name+",\n    Your request for participation in SPMS Course has been accepted. "
									+ "You will receive credentials for your account in a short while.\n\nRegards,\nManager,\nSPMS.";
							try {
								mail.send();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							student.setPassword();
							String pass="";
							rSet=database.Query("SELECT `password` FROM `students` WHERE `id` = "+student.id);
							if (rSet.next()) {
								pass=rSet.getString("password");
							}
							database.Update("INSERT INTO login VALUES ("+student.id+",'"+pass+"','3')");
							database.Update("DELETE FROM capplics WHERE id = "+id);
							database.Update("DELETE FROM `notifs` WHERE `id` = "+notifI);
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
				break;
				case 3:
				{
					ResultSet rSet=database.Query("SELECT * FROM papplics WHERE id = '"+id+"'");
					try {
						if (rSet.next()) {
							Participant participant=new Participant();
							participant.Name=rSet.getString("name");
							participant.emailID=rSet.getString("emailID");
							participant.eventID=rSet.getString("eventID");
							database.Update("INSERT INTO participants VALUES(NULL,'"+participant.Name+"','"
									+participant.emailID+"','"+participant.eventID+"')");
							rSet=database.Query("SELECT `id` FROM participants WHERE `name` = '"+participant.Name
									+"' AND `eventID` = '"+participant.eventID+"'");
							if (rSet.next()) {
								participant.id=Integer.parseInt(rSet.getString("id"));
							}
							String particiList="";
							rSet=database.Query("SELECT `particiList` FROM `events` WHERE `ID` = '"+participant.eventID+"'");
							if (rSet.next()) {
								particiList=rSet.getString("particiList");
							}
							if(particiList==null||particiList.equals("NULL")||particiList.equals("")) particiList=participant.id+"";
							else particiList+=","+participant.id;
							database.Update("UPDATE `events` SET `particiList` = '"+particiList+"' WHERE `ID` = '"+participant.eventID+"'");
							Mail mail=new Mail();
							mail.to.add(participant.emailID);
							mail.subject="SPMS participant Approval";
							mail.message="Dear "+participant.Name+",\n    Your request for participation in SPMS event has been accepted. "
									+ "The event you registered for is "+participant.eventID
									+"\nYour ID for event is "+participant.id+"\nPlease do bring your certificates for the event"
											+ "\n\nRegards,\nManager,\nSPMS.";
							try {
								mail.send();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							database.Update("DELETE FROM papplics WHERE id = "+id);
							database.Update("DELETE FROM `notifs` WHERE `id` = "+notifI);
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
					break;
				case 4:
				{
					ResultSet rSet=database.Query("SELECT * FROM vapplics WHERE id = '"+id+"'");
					try {
						if (rSet.next()) {
							Participant participant=new Participant();
							participant.Name=rSet.getString("name");
							participant.emailID=rSet.getString("emailID");
							ResultSet resultSet=database.Query("SELECT * FROM `events`");
							String event="";
							java.sql.Timestamp dateTime=Timestamp.valueOf(LocalDateTime.now());
							if(resultSet.next())
							{
								event=resultSet.getString("ID");
								dateTime=Timestamp.valueOf(resultSet.getString("timestamp"));
							}
							participant.eventID=event;
							java.sql.Date date=new java.sql.Date(dateTime.getTime());
							database.Update("INSERT INTO viewers VALUES(NULL,'"+participant.Name+"','"
									+participant.emailID+"','"+event+"','"
									+date+"')");
							rSet=database.Query("SELECT id FROM viewers WHERE `name` = '"+participant.Name
									+"' AND `eventID` = '"+participant.eventID+"'");
							if (rSet.next()) {
								participant.id=Integer.parseInt(rSet.getString("id"));
							}
							Mail mail=new Mail();
							mail.to.add(participant.emailID);
							mail.subject="SPMS Event Spectation Approval";
							mail.message="Dear "+participant.Name+",\n    Your request for viewing the SPMS event has been accepted. "
									+"\nYour ID for event with ID: "+event+", is "+participant.id+"\nPlease do bring your certificates for the event"
											+ "\n\nRegards,\nManager,\nSPMS.";
							try {
								mail.send();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							database.Update("DELETE FROM vapplics WHERE id = "+id);
							database.Update("DELETE FROM `notifs` WHERE `id` = "+notifI);
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
					break;
				}
				ResultSet rSet=database.Query("SELECT notifics FROM manager WHERE id = "+manager.id);
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
						for(int i=0;notifs!=null && i<notifs.length;i++)
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
				try{
					course.coordinatorID=Integer.parseInt(aCCoordinator.getText());
				}
				catch(Exception e2)
				{
					WarningBox warningBox=new WarningBox("Not a valid input for Coordinator ID!");
					warningBox.setVisible(true);
					aCCoordinator.setText("");
					return;
				}
				Database db=new Database();
				ResultSet test=db.Query("SELECT * FROM `cmembers` WHERE `id`="+course.coordinatorID);
				try {
					if (!test.next()) {
						WarningBox warningBox=new WarningBox("No committee Member with such ID");
						warningBox.setVisible(true);
						aCCoordinator.setText("");
						db.disconnect();
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				db.disconnect();
				String[] date=aCSD.getText().split(" ");
				try{
					course.start=LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
				}
				catch(Exception e2)
				{
					WarningBox warningBox=new WarningBox("Incorrect Date");
					warningBox.setVisible(true);
					aCSD.setText("");
					return;
				}
				try{
					course.duration=Integer.parseInt(aCDuration.getText());
				}
				catch(Exception e2)
				{
					WarningBox warningBox=new WarningBox("Invalid input for duration");
					warningBox.setVisible(true);
					aCDuration.setText("");
					return;
				}
				course.ID=aCID.getText();
				db=new Database();
				test=db.Query("SELECT * FROM `courses` WHERE `ID`="+course.ID);
				try {
					if (test.next()) {
						WarningBox warningBox=new WarningBox("Course already exists!");
						warningBox.setVisible(true);
						aCID.setText("");
						db.disconnect();
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				db.disconnect();
				try{
					course.fee=Integer.parseInt(aCFee.getText());
				}
				catch(Exception e2)
				{
					WarningBox warningBox=new WarningBox("Invalid input for Fee!");
					warningBox.setVisible(true);
					aCFee.setText("");
					return;
				}
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
				database.Update("INSERT INTO `courses` VALUES ('"+course.ID+"','"+course.Name+"',"+course.coordinatorID+",'"+
						java.sql.Date.valueOf(course.start)+"',"+course.duration+","+course.fee+",NULL)");
				database.Update("UPDATE `cmembers` SET `type` = 1 WHERE `id` = "+course.coordinatorID);
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
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Event event=new Event();
				event.name=aEName.getText();
				event.manager=new EventManager();
				try{
					event.manager.id=Integer.parseInt(aEManager.getText());
				}
				catch(Exception e2)
				{
					WarningBox warningBox=new WarningBox("Invalid input for Committee Member ID!");
					aEManager.setText("");
					warningBox.setVisible(true);
					return;
				}
				Database db=new Database();
				ResultSet test=db.Query("SELECT * FROM `cmembers` WHERE `id`="+event.manager.id);
				try {
					if (!test.next()) {
						WarningBox warningBox=new WarningBox("No committee Member with such ID");
						warningBox.setVisible(true);
						aEManager.setText("");
						db.disconnect();
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				db.disconnect();
				String[] date=aEST.getText().split(" ");
				try{
					event.start=LocalDateTime.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), 
						Integer.parseInt(date[0]), Integer.parseInt(date[3]), 0);
				}
				catch(Exception e2)
				{
					WarningBox warningBox=new WarningBox("Invalid Date!");
					warningBox.setVisible(true);
					aEST.setText("");
					return;
				}
				try{
					event.duration=Integer.parseInt(aEDuration.getText());
				}
				catch(Exception e2)
				{
					WarningBox warningBox=new WarningBox("Invalid !");
					warningBox.setVisible(true);
					aEDuration.setText("");
					return;
				}
				event.ID=aEID.getText();
				db=new Database();
				test=db.Query("SELECT * FROM `events` WHERE `ID`="+event.ID);
				try {
					if (test.next()) {
						WarningBox warningBox=new WarningBox("Event ID already exists");
						warningBox.setVisible(true);
						aEID.setText("");
						db.disconnect();
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				db.disconnect();
				try{
					event.fee=Integer.parseInt(aEFee.getText());
				}
				catch(Exception e2)
				{
					WarningBox warningBox=new WarningBox("Invalid input for Fee!");
					warningBox.setVisible(true);
					aEFee.setText("");
					return;
				}
				String notice=event.name+":\n	This event will take place on "+event.start+" for a duration of "+event.duration
						+" hours and Participation fee is "+event.fee+" rupees. Applications are welcome for this event now,"
						+" those who want to apply Click apply for event and please enter the following detail:\n"
						+"Event ID: "+event.ID+".\nFee receipt must be submitted during application. If not selected for the event"
						+" money will be refunded.";
				Database database=new Database();
				database.Update("INSERT INTO notices VALUES('"+notice+"','"+java.sql.Date.valueOf(event.start.toLocalDate())+"')");
				int slot;
				String memberList;
				String[] members;
				String notifi;
				String notifics="";
				int time=Integer.parseInt(date[3]);
				for(int i=0;i<event.duration;i++)
				{
					slot=time+i;
					database.Update("UPDATE `slots` SET `eventID` = '"+event.ID.replace("'", "")+"' WHERE `date` = '"
					+java.sql.Date.valueOf(event.start.toLocalDate())+"' AND `hour` = "+slot);
					ResultSet rSet=database.Query("SELECT * FROM `slots` WHERE `date` = '"
					+java.sql.Date.valueOf(event.start.toLocalDate())+"' AND `hour` = "+slot);
					try {
						if (rSet.next()) {
							memberList=rSet.getString("memberList");
							if (memberList==null||memberList.equals("")||memberList.equals("NULL")) {
								members=null;
							}
							else {
								members=memberList.split(",");
							}
							notifi="The slot on "+date.toString()+" for the slot "+slot+" is booked by for an event."
									+" So, the slots have been cancelled please change your slots.\nManager.";
							database.Update("INSERT INTO `notifs` VALUES (NULL,'"+notifi+"',NULL,0)");
							ResultSet rrSet=database.Query("SELECT `id` FROM `notifs` WHERE `text` = '"+notifi+"'");
							int id=0;
							if (rrSet.next()) {
								id=Integer.parseInt(rrSet.getString("id"));
							}
							if (members==null) {
								continue;
							}
							for(int j=0;j<members.length;j++)
							{
								ResultSet rrResultSet=database.Query("SELECT `notifics` FROM `member` WHERE `id` = "+members[j]);
								if (rrResultSet.next()) {
									notifics=rrResultSet.getString("notifics");
								}
								if(notifics!=null&&notifics!=""&&!(notifics.equals("NULL")))
									notifics+=","+id;
								else notifics=id+"";
								database.Update("UPDATE `member` SET `notifics` = '"+notifics+"' WHERE `id` = "+members[j]);
							}
							database.Update("UPDATE `slots` SET `memberList` = 'NULL' WHERE `date` = '"
									+java.sql.Date.valueOf(event.start.toLocalDate())+"' , "+ "`hour` = "+slot);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				aEName.setText("");
				aEManager.setText("");
				aEDuration.setText("");
				aEFee.setText("");
				aEID.setText("");
				aEST.setText("");
				database.Update("INSERT INTO `events` VALUES ('"+event.ID+"','"+event.name+"',"+event.manager.id+",NULL,"
						+event.duration+","+event.fee+",NULL,'131')");
				database.Update("UPDATE `cmembers` SET `type` = 2 WHERE `id` = "+event.manager.id);
				database.disconnect();
			}
		});
		button.setBounds(289, 381, 89, 23);
		addEvent.add(button);
		
		addCM = new JPanel();
		workTabPane.addTab("Add Committee Member", null, addCM, null);
		addCM.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(87, 47, 80, 14);
		addCM.add(lblName);
		
		JLabel lblEmailId = new JLabel("Email ID:");
		lblEmailId.setBounds(87, 97, 80, 14);
		addCM.add(lblEmailId);
		
		CMName = new JTextField();
		CMName.setBounds(204, 44, 252, 20);
		addCM.add(CMName);
		CMName.setColumns(10);
		
		CMEmail = new JTextField();
		CMEmail.setBounds(204, 94, 252, 20);
		addCM.add(CMEmail);
		CMEmail.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database database=new Database();
				if(!Spms.checkMail(CMEmail.getText()))
				{
					CMEmail.setText("");
					return;
				}
				ResultSet teSet=database.Query("SELECT * FROM `cmembers` WHERE `name` = '"+CMName.getText().replace("'","")+"'");
				try {
					if (teSet.next()) {
						WarningBox warningBox=new WarningBox("Member with this name already exists");
						warningBox.setVisible(true);
						CMName.setText("");
						return;
					}
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
				database.Update("INSERT INTO cmembers VALUES(NULL,'"
				+CMName.getText().replace("'", "")+"',NULL,0)");
				String pass="";
				int i;
				Random random=new Random();
				for(int j=0;j<9;j++)
				{
					i= random.nextInt(26)+'A';
					pass+=(char)i;
				}
				ResultSet resultSet=database.Query("SELECT id FROM cmembers WHERE `name` = '"+CMName.getText().replace("'", "")+"'"
						+ " AND `type` = 0");
				int id=0;
				try {
					if (resultSet.next()) {
						id=Integer.parseInt(resultSet.getString("id"));
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					return;
				}
				database.Update("INSERT INTO login VALUES ("+id+",'"+pass+"',2)");
				database.disconnect();
				Mail mail=new Mail();
				mail.to.add(CMEmail.getText());
				mail.subject="SPMS: Promotion to Committee Member";
				mail.message="Dear "+CMName.getText()+",\n	You have been appointed as Committee member of SPMS.\n"
						+ "Your ID is "+id+"\nPassword: "+pass+"\n\nYours friendly,\nManager,\nSPMS.";
				try {
					mail.send();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				CMEmail.setText("");
				CMName.setText("");
			}
		});
		btnNewButton.setBounds(367, 155, 89, 23);
		addCM.add(btnNewButton);
		
		poolValues = new JPanel();
		workTabPane.addTab("Timings and Fees", null, poolValues, null);
		poolValues.setLayout(null);
		
		lblStartTimeOf = new JLabel("Start time of first Slot:");
		lblStartTimeOf.setBounds(57, 50, 194, 14);
		poolValues.add(lblStartTimeOf);
		
		startTimeSlot = new JTextField();
		startTimeSlot.setBounds(249, 50, 209, 20);
		poolValues.add(startTimeSlot);
		startTimeSlot.setColumns(10);
		
		lblAnyIntegerFrom = new JLabel("any integer from 0 to 23");
		lblAnyIntegerFrom.setBounds(478, 50, 194, 14);
		poolValues.add(lblAnyIntegerFrom);
		
		lblEndTimeOf = new JLabel("End time of Last Slot:");
		lblEndTimeOf.setBounds(57, 119, 194, 14);
		poolValues.add(lblEndTimeOf);
		
		endTimeSlot = new JTextField();
		endTimeSlot.setBounds(249, 119, 209, 20);
		poolValues.add(endTimeSlot);
		endTimeSlot.setColumns(10);
		
		lblAnyIntegerFrom_1 = new JLabel("any integer from 0 to 23");
		lblAnyIntegerFrom_1.setBounds(478, 119, 194, 14);
		poolValues.add(lblAnyIntegerFrom_1);
		
		memberfeelbl = new JLabel("MemberShip Fee:");
		memberfeelbl.setBounds(57, 191, 194, 14);
		poolValues.add(memberfeelbl);
		
		memberFee = new JTextField();
		memberFee.setBounds(249, 191, 209, 20);
		poolValues.add(memberFee);
		memberFee.setColumns(10);
		
		lblInRupees_1 = new JLabel("in rupees");
		lblInRupees_1.setBounds(478, 191, 194, 14);
		poolValues.add(lblInRupees_1);
		
		btnChange_1 = new JButton("Change");
		btnChange_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				int start=0;
				try {
					start=Integer.parseInt(startTimeSlot.getText());
					if(start<0||start>23)
					{
						WarningBox warningBox=new WarningBox("start time slot is out of range!");
						warningBox.setVisible(true);
						startTimeSlot.setText("");
						return;
					}
				} catch (Exception e2) {
					WarningBox warningBox=new WarningBox("Invalid input for start slot!");
					warningBox.setVisible(true);
					startTimeSlot.setText("");
					return;
				}
				int end=0;
				try {
					end=Integer.parseInt(endTimeSlot.getText());
					if(end<0||end>23)
					{
						WarningBox warningBox=new WarningBox("end time slot is out of range!");
						warningBox.setVisible(true);
						endTimeSlot.setText("");
						return;
					}
				} catch (Exception e2) {
					WarningBox warningBox=new WarningBox("Invalid input for end slot!");
					warningBox.setVisible(true);
					endTimeSlot.setText("");
					return;
				}
				int memberfee=0;
				try {
					memberfee=Integer.parseInt(memberFee.getText());
				} catch (Exception e2) {
					WarningBox warningBox=new WarningBox("Invalid input for Member Fee!");
					warningBox.setVisible(true);
					memberFee.setText("");
					return;
				}
				int maxBooking=0;
				try {
					maxBooking=Integer.parseInt(maxBookings.getText());
				} catch (Exception e2) {
					WarningBox warningBox=new WarningBox("Invalid input for Maximum Bookings!");
					warningBox.setVisible(true);
					maxBookings.setText("");
					return;
				}
				db.Update("UPDATE `spmsvalues` SET `start` = "+start+", `end` = "
				+end+", `memberFee` = "+memberfee+", `maxBookings` = "+maxBooking+" WHERE `id` = 1");
				ResultSet resultSet=db.Query("SELECT memberList FROM slots WHERE `hour` < '"+startTimeSlot.getText().replace("'", "")
					+"' OR `hour` > '"+endTimeSlot.getText().replace("'", "")+"'");
				String textNotice="This is to notify you that the slot timings have been changed and so please change your slots.\n"
						+ "Sorry for the inconvenience.\n--\nManager,\nSPMS.";
				db.Update("INSERT INTO notifs VALUES (NULL,'"+textNotice+"',NULL,'0')");
				ResultSet rr=db.Query("SELECT id FROM notifs WHERE `text` = '"+textNotice+"'");
				int notifID=0;
				try {
					if(rr.next())
					{
						notifID=Integer.parseInt(rr.getString("id"));
					}
					String[] members=null;
					String notifics=null;
					while(resultSet.next())
					{
						String member=resultSet.getString("memberList");
						if(member!=null) members=member.split(",");
						else continue;
						for(int i=0;i<members.length;i++)
						{
							ResultSet rrSet=db.Query("SELECT notifics FROM member WHERE id = "+members[i]);
							if (rrSet.next()) {
								notifics=rrSet.getString("notifics");
								if(notifics!=null) notifics+=",";
								else notifics="";
								notifics+=notifID;
								db.Update("UPDATE member SET `notifics` = '"+notifics+"' WHERE `id` = '"+members[i]+"'");
							}
						}
					}
					startTimeSlot.setText("");
					endTimeSlot.setText("");
					memberFee.setText("");
					maxBookings.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				db.Update("UPDATE slots SET `memberList` = NULL WHERE `hour` < "+startTimeSlot.getText().replace("'", "")
				+" OR `hour` > "+endTimeSlot.getText().replace("'", ""));
				db.disconnect();
			}
		});
		btnChange_1.setBounds(249, 313, 89, 23);
		poolValues.add(btnChange_1);
		
		JLabel lblMaximumNoBookings = new JLabel("Maximum No. Bookings Per Slot:");
		lblMaximumNoBookings.setBounds(57, 265, 194, 14);
		poolValues.add(lblMaximumNoBookings);
		
		maxBookings = new JTextField();
		maxBookings.setBounds(249, 265, 209, 20);
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
		
		cF1 = new JTextField("Name");
		cF1.setBounds(218, 82, 158, 20);
		changeForm.add(cF1);
		cF1.setColumns(10);
		
		cF2 = new JTextField("Date of Birth");
		cF2.setBounds(218, 137, 158, 20);
		changeForm.add(cF2);
		cF2.setColumns(10);
		
		cF3 = new JTextField("Email ID");
		cF3.setBounds(218, 194, 158, 20);
		changeForm.add(cF3);
		cF3.setColumns(10);
		
		cF4 = new JTextField("Phone No");
		cF4.setBounds(218, 248, 158, 20);
		changeForm.add(cF4);
		cF4.setColumns(10);
		
		cF5 = new JTextField("Address");
		cF5.setBounds(218, 306, 158, 20);
		changeForm.add(cF5);
		cF5.setColumns(10);
		
		cF6 = new JTextField("Photo");
		cF6.setBounds(491, 82, 229, 20);
		changeForm.add(cF6);
		cF6.setColumns(10);
		
		cF7 = new JTextField("Birth Certificate");
		cF7.setBounds(491, 137, 229, 20);
		changeForm.add(cF7);
		cF7.setColumns(10);
		
		cF8 = new JTextField("Medical Certificate");
		cF8.setBounds(491, 194, 229, 20);
		changeForm.add(cF8);
		cF8.setColumns(10);
		
		cF9 = new JTextField("Fee Receipt");
		cF9.setBounds(491, 248, 229, 20);
		changeForm.add(cF9);
		cF9.setColumns(10);
		
		btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database database=new Database();
				database.Update("UPDATE form SET `cF1`='"+cF1.getText().replace("'", "")
						+"', `cF2` = '"+cF2.getText().replace("'", "")
						+"', `cF3` = '"+cF3.getText().replace("'", "")
						+"', `cF4` = '"+cF4.getText().replace("'", "")
						+"', `cF5` = '"+cF5.getText().replace("'", "")
						+"', `cF6` = '"+cF6.getText().replace("'", "")
						+"', `cF7` = '"+cF7.getText().replace("'", "")
						+"', `cF8` = '"+cF8.getText().replace("'", "")
						+"', `cF9` = '"+cF9.getText().replace("'", "")
						+"' WHERE `id` = 1");
				database.disconnect();
			}
		});
		btnChange.setBounds(435, 305, 89, 23);
		changeForm.add(btnChange);
		
		addBooking = new JPanel();
		workTabPane.addTab("Add Booking", null, addBooking, null);
		addBooking.setLayout(null);
		
		JLabel lblBookedBy = new JLabel("Booked By:");
		lblBookedBy.setBounds(47, 46, 164, 14);
		addBooking.add(lblBookedBy);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(47, 104, 164, 14);
		addBooking.add(lblDate);
		
		JLabel lblStartingSlot = new JLabel("Starting Slot:");
		lblStartingSlot.setBounds(47, 165, 164, 14);
		addBooking.add(lblStartingSlot);
		
		JLabel lblNoOfSlots = new JLabel("No. of Slots:");
		lblNoOfSlots.setBounds(47, 236, 164, 14);
		addBooking.add(lblNoOfSlots);
		
		bookedByID = new JTextField();
		bookedByID.setBounds(190, 43, 249, 20);
		addBooking.add(bookedByID);
		bookedByID.setColumns(10);
		
		bookingDate = new JTextField();
		bookingDate.setBounds(190, 101, 249, 20);
		addBooking.add(bookingDate);
		bookingDate.setColumns(10);
		
		startingSlotBooking = new JTextField();
		startingSlotBooking.setBounds(190, 162, 249, 20);
		addBooking.add(startingSlotBooking);
		startingSlotBooking.setColumns(10);
		
		noOfSlotsBooking = new JTextField();
		noOfSlotsBooking.setBounds(190, 233, 249, 20);
		addBooking.add(noOfSlotsBooking);
		noOfSlotsBooking.setColumns(10);
		
		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database database=new Database();
				String[] data=bookingDate.getText().split(" ");
				LocalDate date;
				try{
					date=LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
				}
				catch(Exception e2)
				{
					WarningBox warningBox=new WarningBox("Invalid Date!");
					warningBox.setVisible(true);
					bookingDate.setText("");
					return;
				}
				String ID=bookedByID.getText()+"_"+data[0]+"_"+data[1]+"_"+data[2];
				ResultSet test=database.Query("SELECT * FROM `member` WHERE `id` = "+bookedByID.getText());
				try {
					if (!test.next()) {
						WarningBox warningBox=new WarningBox("Invalid ID for the member!");
						warningBox.setVisible(true);
						bookedByID.setText("");
						return;
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				int slot;
				String[] members;
				String memberList;
				String notifi;
				String notifics="";
				int startSlot;
				try{
					startSlot=Integer.parseInt(startingSlotBooking.getText());
					if (startSlot<0 || startSlot>23) {
						WarningBox warningBox=new WarningBox("Out of Index input for Start Slot!");
						warningBox.setVisible(true);
						startingSlotBooking.setText("");
						return;
					}
				}
				catch(Exception e2)
				{
					WarningBox warningBox=new WarningBox("Invalid input for start slot!");
					warningBox.setVisible(true);
					startingSlotBooking.setText("");
					return;
				}
				int noOfSlots;
				try{
					noOfSlots=Integer.parseInt(noOfSlotsBooking.getText());
					if(noOfSlots>24-startSlot)
					{
						WarningBox warningBox=new WarningBox("<html>no of slots go out of day range!<br>"
								+ "Enter a value so booking completes in same day.</html>");
						warningBox.setVisible(true);
						noOfSlotsBooking.setText("");
						return;
					}
				}
				catch(Exception e2)
				{
					WarningBox warningBox=new WarningBox("Invalid input for no. of slots!");
					warningBox.setVisible(true);
					noOfSlotsBooking.setText("");
					return;
				}
				for(int i=0;i<noOfSlots;i++)
				{
					slot=startSlot+i;
					database.Update("UPDATE `slots` SET `bookingID` = '"+ID+"' WHERE `date` = '"+java.sql.Date.valueOf(date)+"' AND "
							+ "`hour` = "+slot);
					ResultSet rSet=database.Query("SELECT * FROM `slots` WHERE `date` = '"+java.sql.Date.valueOf(date)+"' AND "
							+ "`hour` = "+slot);
					try {
						if (rSet.next()) {
							memberList=rSet.getString("memberList");
							if (memberList!=null) {
								members=memberList.split(",");
							}
							else {
								members=null;
							}
							notifi="The slot on "+date.toString()+" for the slot "+slot+" is booked by one of the members."
									+" So, the slots have been cancelled please change your slots.\nManager.";
							database.Update("INSERT INTO `notifs` VALUES (NULL,'"+notifi+"',NULL,0)");
							ResultSet rrSet=database.Query("SELECT `id` FROM `notifs` WHERE `text` = "+notifi);
							int id=0;
							if (rrSet.next()) {
								id=Integer.parseInt(rrSet.getString("id"));
							}
							if(members==null)continue;
							for(int j=0;j<members.length;j++)
							{
								ResultSet rrResultSet=database.Query("SELECT `notifics` FROM `member` WHERE `id` = "+members[j]);
								if (rrResultSet.next()) {
									notifics=rrResultSet.getString("notifics");
								}
								if(notifics!=null&&notifics!=""&&!(notifics.equals("NULL")))
									notifics+=","+id;
								else notifics=id+"";
								database.Update("UPDATE `member` SET `notifics` = '"+notifics+"' WHERE `id` = "+members[j]);
							}
							database.Update("UPDATE `slots` SET `memberList` = 'NULL' WHERE `date` = '"+java.sql.Date.valueOf(date)+"' , "
									+ "`hour` = "+slot);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				bookedByID.setText("");
				startingSlotBooking.setText("");
				noOfSlotsBooking.setText("");
				bookingDate.setText("");
			}
		});
		btnBook.setBounds(353, 315, 89, 23);
		addBooking.add(btnBook);
		
		JLabel lblIdOfThe = new JLabel("ID of the member requested for booking");
		lblIdOfThe.setBounds(468, 46, 279, 14);
		addBooking.add(lblIdOfThe);
		
		JLabel lblDateLikeEverywhere = new JLabel("Date like everywhere in dd mm yyyy format");
		lblDateLikeEverywhere.setBounds(468, 104, 306, 14);
		addBooking.add(lblDateLikeEverywhere);
		
		JLabel lblAnyIntegerFrom_2 = new JLabel("any integer from 0 to 23");
		lblAnyIntegerFrom_2.setBounds(468, 165, 233, 14);
		addBooking.add(lblAnyIntegerFrom_2);
		
		JLabel lblNewLabel = new JLabel("no of slots to be booked (int)");
		lblNewLabel.setBounds(468, 236, 233, 14);
		addBooking.add(lblNewLabel);
		
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
						WarningBox warningBox=new WarningBox("Enter some name to start discussion!");
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
				if(disMessPane.getText()==null) {
					WarningBox warningBox=new WarningBox("Enter some message to send!");
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
				rSet=database.Query("SELECT `name` FROM `manager` WHERE `id` = "+manager.id);
				String Name="(Manager) ";
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
					WarningBox warningBox=new WarningBox("Enter Something to post Notice!");
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
				ResultSet rSet=database.Query("SELECT `name` FROM `manager` WHERE `id` = "+manager.id);
				String Name="(Manager) ";
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
		
		Calendar calendar=new Calendar();
		tabbedPane.addTab("CALENDAR", null, calendar.frame.getContentPane(), null);
		socialTab.setLayout(null);
		
		 ButtonGroup group = new ButtonGroup();
		 group.add(rdbtnPublic);
		 group.add(rdbtnPrivate);
	}
}

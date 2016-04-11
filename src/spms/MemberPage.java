package spms;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import user.Member;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class MemberPage {

    public static int loggedIn=0;
    public static Member member;
	
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
    private JPanel activityTab;
    private JTabbedPane tabbedPane_1;
    private JPanel bookSlotsTab;
    private JPanel partyTab;
    private JPanel weekButtonTab;
    private JScrollPane scrollPane_7;
    private JPanel weekButtonPane;
    private JPanel weekTab;
    private JScrollPane scrollPane_6;
    private JPanel weekPane;
    private JPanel bookedSlotsTab;
    private JPanel bookedSlotsPane;
    private JButton btnBook;
    private JButton btnBookBack;
    private JTextPane bookedslots;
    private JButton btnWeekBack;
    private JScrollPane scrollPane_9;
    private JTextPane bookPoolPane;
    private JButton btnBookPool;
    private JScrollPane scrollPane_10;
    private JTextPane cancelMemPane;
    private JButton btnCancelMembership;
    private JScrollPane scrollPane_8;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			if(loggedIn==0)return;
			MemberPage window = new MemberPage();
			window.insertSlots();
			window.insertPosts();
			window.notifs();
			window.frmSpms.setVisible(true);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
    }

    public boolean insertSlots()
    {
	Database database=new Database();
	ResultSet rSet=database.Query("SELECT * FROM `slots` WHERE `hour` = 0");
	try {
	    String fromDate="";
	    String toDate="";
	    while(rSet.next()) {
		fromDate=rSet.getString("date");
		for(int i=0;i<6;i++)
		    rSet.next();
		toDate=rSet.getString("date");
		JButton button=new JButton(fromDate+" -- "+toDate);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    JButton button=(JButton)e.getSource();
			    String fromDate=(String)button.getClientProperty("fromDate");
			    String toDate=(String)button.getClientProperty("toDate");
			    Database database=new Database();
			    ResultSet rrSet=database.Query("SELECT * FROM `slots` WHERE date >= '"+java.sql.Date.valueOf(fromDate)+"' AND "
							   +" `date` <= '"+java.sql.Date.valueOf(toDate)+"'");
			    int i=0;
			    try {
				String membercheck="";
				bookedslots.setText("Your Slot Bookings:\n");
				while(rrSet.next()) {
				    membercheck=rrSet.getString("memberList");
				    if(membercheck!=null&&membercheck.contains(member.id+""))
					{
					    bookedslots.setText(bookedslots.getText()+"Slot: "+rrSet.getString("date")+" Hour: "
								+rrSet.getString("hour")+"\n");
					    i++;
					}
				}
				if(i>=5)
				    btnBook.setEnabled(false);
				else btnBook.setEnabled(true);
			    } catch (SQLException e1) {
				e1.printStackTrace();
			    }
			    btnBook.putClientProperty("num", i);
			    btnBook.putClientProperty("fromDate", fromDate);
			    btnBookBack.putClientProperty("fromDate", fromDate);
			    btnBook.putClientProperty("toDate", toDate);
			    btnBookBack.putClientProperty("toDate", toDate);
			    bookedSlotsTab.setVisible(true);
			    weekButtonTab.setVisible(false);
			    database.disconnect();
			}
		    });
		button.putClientProperty("fromDate", fromDate);
		button.putClientProperty("toDate", toDate);
		weekButtonPane.add(button);
		weekButtonTab.setVisible(true);
		weekTab.setVisible(false);
		bookedSlotsTab.setVisible(false);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return true;
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
	ResultSet rSet=db.Query("SELECT notifics FROM member WHERE id = "+member.id);
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
    public MemberPage() {
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
		
	activityTab = new JPanel();
	tabbedPane.addTab("Activity", null, activityTab, null);
	activityTab.setLayout(null);
		
	tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
	tabbedPane_1.setBounds(0, 0, 789, 443);
	activityTab.add(tabbedPane_1);
		
	bookSlotsTab = new JPanel();
	tabbedPane_1.addTab("Book Slots", null, bookSlotsTab, null);
	bookSlotsTab.setLayout(new CardLayout(0, 0));
		
	weekButtonTab = new JPanel();
	bookSlotsTab.add(weekButtonTab, "name_33257751915485");
	weekButtonTab.setLayout(null);
		
	scrollPane_7 = new JScrollPane();
	scrollPane_7.setBounds(0, 0, 784, 415);
	weekButtonTab.add(scrollPane_7);
		
	weekButtonPane = new JPanel();
	scrollPane_7.setViewportView(weekButtonPane);
	weekButtonPane.setLayout(new GridLayout(0, 2, 9, 9));
		
	weekTab = new JPanel();
	bookSlotsTab.add(weekTab, "name_33499446219938");
	weekTab.setLayout(null);
		
	scrollPane_6 = new JScrollPane();
	scrollPane_6.setBounds(10, 11, 764, 352);
	weekTab.add(scrollPane_6);
		
	weekPane = new JPanel();
	scrollPane_6.setViewportView(weekPane);
	weekPane.setLayout(new GridLayout(0, 3, 5, 5));
		
	btnWeekBack = new JButton("Back");
	btnWeekBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    weekPane.removeAll();
		    weekPane.updateUI();
		    weekTab.setVisible(false);
		    Database database=new Database();
		    ResultSet rrSet=database.Query("SELECT * FROM `slots` WHERE date >= '"+java.sql.Date.valueOf(
														 (String)btnBook.getClientProperty("fromDate"))+"' AND "+" `date` <= '"+java.sql.Date.valueOf(
																									      (String)btnBook.getClientProperty("toDate"))+"'");
		    int i=0;
		    try {
			String membercheck="";
			bookedslots.setText("Your Slot Bookings:\n");
			while(rrSet.next()) {
			    membercheck=rrSet.getString("memberList");
			    if(membercheck!=null&&membercheck.contains(member.id+""))
				{
				    bookedslots.setText(bookedslots.getText()+"Slot: "+rrSet.getString("date")+" Hour: "+rrSet.getString("hour")+"\n");
				    i++;
				}
			}
			if(i>=5)
			    btnBook.setEnabled(false);
			else btnBook.setEnabled(true);
		    } catch (SQLException e1) {
			e1.printStackTrace();
		    }
		    bookedSlotsTab.setVisible(true);
		}
	    });
	btnWeekBack.setBounds(10, 381, 89, 23);
	weekTab.add(btnWeekBack);
		
	bookedSlotsTab = new JPanel();
	bookSlotsTab.add(bookedSlotsTab, "name_62996835998855");
	bookedSlotsTab.setLayout(null);
		
	scrollPane_8 = new JScrollPane();
	scrollPane_8.setBounds(10, 11, 764, 356);
	bookedSlotsTab.add(scrollPane_8);
		
	bookedSlotsPane = new JPanel();
	scrollPane_8.setViewportView(bookedSlotsPane);
	bookedSlotsPane.setLayout(null);
		
	bookedslots = new JTextPane();
	bookedslots.setEditable(false);
	bookedslots.setBounds(10, 11, 742, 286);
	bookedSlotsPane.add(bookedslots);
		
	btnBook = new JButton("Book");
	btnBook.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    JButton button=(JButton)e.getSource();
		    String fromDate=(String)button.getClientProperty("fromDate");
		    String toDate=(String)button.getClientProperty("toDate");
		    Database database=new Database();
		    ResultSet rSet=database.Query("SELECT * FROM `slots` WHERE `date` >= '"+java.sql.Date.valueOf(fromDate)+"'"
						  +" AND `date` <= '"+java.sql.Date.valueOf(toDate)+"'");
		    int num=0;
		    try {
			while(rSet.next())
			    {
				JButton button2=new JButton(rSet.getString("date")+" "+rSet.getString("hour")+":00 + 1");
				int maxnum;
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1)
					{
					    JButton button=(JButton)e1.getSource();
					    String date=(String)button.getClientProperty("date");
					    String fromDate=(String)button.getClientProperty("fromDate");
					    String toDate=(String)button.getClientProperty("toDate");
					    String hour=(String)button.getClientProperty("hour");
					    ResultSet rSet=database.Query("SELECT * FROM `slots` WHERE `date` = '"+date+"'"
									  +" AND `hour` = '"+hour+"'");
					    String memberList="";
					    String number="";
					    int num=1;
					    try {
						if (rSet.next()) {
						    memberList=rSet.getString("memberList");
						    number=rSet.getString("number");
						    if (number!=null&&!number.equals("")&&!number.equals("NULL")) {
							num=Integer.parseInt(number);
							num++;
						    }
						    if (memberList!=null&&!memberList.equals("")&&!memberList.equals("NULL")) {
							memberList+=","+member.id;
						    }
						    else memberList=member.id+"";
						    database.Update("UPDATE `slots` SET `memberList` = '"+memberList+"' , `number` = "+num+" WHERE "
								    + "`date` = '"+date+"' AND "+"`hour` = "+hour);
						    button.setEnabled(false);
						    String membercheck="";
						    int i=0;
						    ResultSet rrSet=database.Query("SELECT * FROM `slots` WHERE `date` >= '"
										   +java.sql.Date.valueOf(fromDate)+"' AND `date` <= '"+java.sql.Date.valueOf(toDate)+"'");
						    while(rrSet.next()) {
							membercheck=rrSet.getString("memberList");
							if(membercheck==null) membercheck="";
							bookedslots.setText("Your Slot Bookings:\n");
							if(membercheck.contains(member.id+""))
							    {
								System.out.println(i);
								bookedslots.setText(bookedslots.getText()+"Slot: "+rrSet.getString("date")+" Hour: "
										    +rrSet.getString("hour")+"\n");
								i++;
							    }
						    }
						    if(i>=5)
							{
							    weekPane.removeAll();
							    weekPane.updateUI();
							    weekTab.setVisible(false);
							    btnBook.setEnabled(false);
							    bookedSlotsTab.setVisible(true);
							}
						}
					    } catch (SQLException e) {
						e.printStackTrace();
					    }
					}
				    });
				button2.putClientProperty("date",rSet.getString("date"));
				button2.putClientProperty("fromDate",fromDate);
				button2.putClientProperty("toDate",toDate);
				button2.putClientProperty("hour",rSet.getString("hour"));
				ResultSet rrrSet=database.Query("SELECT * FROM `slots` WHERE `date` = '"+rSet.getString("date")+"' AND `hour` = "
								+ rSet.getString("hour"));
				ResultSet rrSet=database.Query("SELECT * FROM `spmsvalues` WHERE `id` = 1");
				try {
				    if (rrrSet.next()) {
					String x=rrrSet.getString("number");
					if (x!=null&&!x.equals("NULL")&&!x.equals(""))
					    {
						num=Integer.parseInt(x);
					    }
					else num=0;
					if (rrSet.next()) {
					    maxnum=Integer.parseInt(rrSet.getString("maxBookings"));
					    if(num>=maxnum)
						{
						    button2.setEnabled(false);
						}
					    if(rrrSet.getString("bookingID")!=null
					       ||(rrrSet.getString("bookingID")!=null&&!rrrSet.getString("bookingID").equals(""))
					       ||(rrrSet.getString("bookingID")!=null&&!rrrSet.getString("bookingID").equals("NULL")))
						{
						    button2.setEnabled(false);
						}
					    if(rrrSet.getString("eventID")!=null
					       ||(rrrSet.getString("eventID")!=null&&!rrrSet.getString("eventID").equals(""))
					       ||(rrrSet.getString("eventID")!=null&&!rrrSet.getString("eventID").equals("NULL")))
						{
						    button2.setEnabled(false);
						}
					    if(rrrSet.getString("memberList")!=null&&rrrSet.getString("memberList").contains(member.id+""))
						{
						    button2.setEnabled(false);
						}
					    if (Integer.parseInt(rrSet.getString("start"))>Integer.parseInt(rrrSet.getString("hour"))) {
						button2.setEnabled(false);
					    }
					    if (Integer.parseInt(rrSet.getString("end"))<Integer.parseInt(rrrSet.getString("hour"))) {
						button2.setEnabled(false);
					    }
					}
				    }
				}
				catch(Exception e3)
				    {
					e3.printStackTrace();
				    }
				weekPane.add(button2);
			    }
		    } catch (SQLException e1) {
			e1.printStackTrace();
		    }
		    weekTab.setVisible(true);
		    bookedSlotsTab.setVisible(false);
		}
	    });
	btnBook.setBounds(339, 308, 89, 23);
	bookedSlotsPane.add(btnBook);
		
	btnBookBack = new JButton("Back");
	btnBookBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    weekButtonTab.setVisible(true);
		    bookedSlotsTab.setVisible(false);
		}
	    });
	btnBookBack.setBounds(10, 381, 89, 23);
	bookedSlotsTab.add(btnBookBack);
		
	partyTab = new JPanel();
	tabbedPane_1.addTab("Book Pool", null, partyTab, null);
	partyTab.setLayout(null);
		
	scrollPane_9 = new JScrollPane();
	scrollPane_9.setBounds(10, 11, 764, 352);
	partyTab.add(scrollPane_9);
		
	bookPoolPane = new JTextPane();
	scrollPane_9.setViewportView(bookPoolPane);
		
	btnBookPool = new JButton("Send Request");
	btnBookPool.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (bookPoolPane.getText().equals("")||bookPoolPane.getText()==null) {
			WarningBox warningBox=new WarningBox("Enter the details of booking please!");
			warningBox.setVisible(true);
			return;
		    }
		    Database database=new Database();
		    database.Update("INSERT INTO notifs VALUES(NULL,'"+member.id+" (Member):\n"+bookPoolPane.getText().replace("'", "")+"',NULL,0)");
		    ResultSet rSet=database.Query("SELECT `id` FROM `notifs` WHERE `text` = '"+member.id+" (Student):\n"
						  +bookPoolPane.getText().replace("'", "")+"'");
		    int id;
		    String notifics="";
		    String members="";
		    String[] memList=null;
		    try {
			if (rSet.next()) {
			    id=Integer.parseInt(rSet.getString("id"));
			    rSet=database.Query("SELECT * FROM `manager`");
			    while(rSet.next()) {
				members+=rSet.getString("id")+",";
			    }
			    if(members!="")
				members=members.substring(0,members.length()-1);
			    memList=members.split(",");
			    for(int j=0;j<memList.length;j++)
				{
				    ResultSet rrrSet=database.Query("SELECT `notifics` FROM `manager` WHERE `id` = "+memList[j]);
				    if (rrrSet.next()) {
					notifics=rrrSet.getString("notifics");
				    }
				    if(notifics!=null&&notifics!=""&&!(notifics.equals("NULL")))
					notifics+=","+id;
				    else notifics=id+"";
				    database.Update("UPDATE `manager` SET `notifics` = '"+notifics+"' WHERE `id` = "+memList[j]);
				}
			}
		    } catch (SQLException e1) {
			e1.printStackTrace();
		    }
		    database.disconnect();
		    bookPoolPane.setText("");
		}
	    });
	btnBookPool.setBounds(589, 381, 185, 23);
	partyTab.add(btnBookPool);
		
	JPanel cancelMembeship = new JPanel();
	tabbedPane_1.addTab("Cancel Membership", null, cancelMembeship, null);
	cancelMembeship.setLayout(null);
		
	scrollPane_10 = new JScrollPane();
	scrollPane_10.setBounds(10, 35, 764, 325);
	cancelMembeship.add(scrollPane_10);
		
	cancelMemPane = new JTextPane();
	scrollPane_10.setViewportView(cancelMemPane);
		
	btnCancelMembership = new JButton("Cancel Membership");
	btnCancelMembership.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (cancelMemPane.getText().equals("")) {
			WarningBox warningBox=new WarningBox("Enter a reason Please!");
			warningBox.setVisible(true);
			return;
		    }
		    Database database=new Database();
		    database.Update("INSERT INTO notifs VALUES(NULL,'"+member.id+" (Member):\n"+cancelMemPane.getText().replace("'", "")+"',NULL,0)");
		    ResultSet rSet=database.Query("SELECT `id` FROM `notifs` WHERE `text` = '"+member.id+" (Student):\n"
						  +cancelMemPane.getText().replace("'", "")+"'");
		    int id;
		    String notifics="";
		    String members="";
		    String[] memList=null;
		    try {
			if (rSet.next()) {
			    id=Integer.parseInt(rSet.getString("id"));
			    rSet=database.Query("SELECT * FROM `manager`");
			    while(rSet.next()) {
				members+=rSet.getString("id")+",";
			    }
			    if(members!="")
				members=members.substring(0,members.length()-1);
			    memList=members.split(",");
			    for(int j=0;j<memList.length;j++)
				{
				    ResultSet rrrSet=database.Query("SELECT `notifics` FROM `manager` WHERE `id` = "+memList[j]);
				    if (rrrSet.next()) {
					notifics=rrrSet.getString("notifics");
				    }
				    if(notifics!=null&&notifics!=""&&!(notifics.equals("NULL")))
					notifics+=","+id;
				    else notifics=id+"";
				    database.Update("UPDATE `manager` SET `notifics` = '"+notifics+"' WHERE `id` = "+memList[j]);
				}
			    database.Update("DROP FROM `login` WHERE `id` = "+member.id);
			    database.Update("DROP FROM `member` WHERE `id` = "+member.id);
			}
		    } catch (SQLException e1) {
			e1.printStackTrace();
		    }
		    database.disconnect();
		    cancelMemPane.setText("");
		}
	    });
	btnCancelMembership.setBounds(571, 381, 203, 23);
	cancelMembeship.add(btnCancelMembership);
		
	JLabel lblReason = new JLabel("Reason:");
	lblReason.setBounds(10, 11, 137, 14);
	cancelMembeship.add(lblReason);
		
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
		    ResultSet rSet=database.Query("SELECT notifics FROM member WHERE id = "+member.id);
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
				database.Update("UPDATE `member` SET notifics = '"+tempNotif+"' WHERE id = '"+member.id+"'");
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
		    ResultSet rSet=database.Query("SELECT notifics FROM member WHERE id = "+member.id);
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
				database.Update("UPDATE `member` SET notifics = '"+tempNotif+"' WHERE id = '"+member.id+"'");
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
			    WarningBox warningBox=new WarningBox("Enter a message to Post to discussion!");
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
		    rSet=database.Query("SELECT `name` FROM `member` WHERE `id` = "+member.id);
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
		    if(complainPane.getText().equals(""))
			{
			    WarningBox warningBox=new WarningBox("Enter something to complain about!");
			    warningBox.setVisible(true);
			}
		    Database database=new Database();
		    database.Update("INSERT INTO notifs VALUES(NULL,'"+member.id+" (Member):\n"+complainPane.getText().replace("'", "")+"',NULL,0)");
		    ResultSet rSet=database.Query("SELECT `id` FROM `notifs` WHERE `text` = '"+member.id+" (Member):\n"
						  +complainPane.getText().replace("'", "")+"'");
		    int id;
		    String notifics="";
		    String members="";
		    String[] memList=null;
		    try {
			if (rSet.next()) {
			    id=Integer.parseInt(rSet.getString("id"));
			    rSet=database.Query("SELECT * FROM `manager`");
			    while (rSet.next()) {
				members+=rSet.getString("id")+",";
			    }
			    if(members!="")
				members=members.substring(0,members.length()-1);
			    memList=members.split(",");
			    for(int j=0;j<memList.length;j++)
				{
				    ResultSet rrrSet=database.Query("SELECT `notifics` FROM `manager` WHERE `id` = "+memList[j]);
				    if (rrrSet.next()) {
					notifics=rrrSet.getString("notifics");
				    }
				    if(notifics!=null&&notifics!=""&&!(notifics.equals("NULL")))
					notifics+=","+id;
				    else notifics=id+"";
				    database.Update("UPDATE `manager` SET `notifics` = '"+notifics+"' WHERE `id` = "+memList[j]);
				}
			}
		    } catch (SQLException e1) {
			e1.printStackTrace();
		    }
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
			{
			    WarningBox warningBox=new WarningBox("Enter something to post!");
			    warningBox.setVisible(true);
			    return;
			}
		    Database database=new Database();
		    ResultSet rSet=database.Query("SELECT `name` FROM `member` WHERE `id` = "+member.id);
		    String Name="";
		    try {
			if(rSet.next())
			    {
				Name+=rSet.getString("name");
			    }
		    } catch (SQLException e1) {
			e1.printStackTrace();
		    }
		    database.Update("INSERT INTO `posts` VALUES (NULL,0,'"+Name+"','"+postPane.getText().replace("'", "")+"',NULL)");
		    postPane.setText("");
		    database.disconnect();
		}
	    });
	btnPost.setBounds(615, 364, 89, 23);
	Post.add(btnPost);
		
	Calendar calendar=new Calendar();
	tabbedPane.addTab("CALENDAR", null, calendar.frame.getContentPane(), null);
	socialTab.setLayout(null);
    }
}

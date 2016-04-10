package spms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import application.MemberApplicant;
import application.ParticipantApplic;
import application.Viewer;
import user.CommitteeMember;
import user.Manager;
import user.Member;
import user.Participant;
import user.Student;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;

public class WelcomePage {
	public JFrame frmSpms;
	private JTextField loginId;
	private JPasswordField loginPass;
	private JTextField nameMAF;
	private JTextField dobMAF;
	private JTextField mailMAF;
	private JTextField phoneMAF;
	private JTextField addressMAF;
	private JLabel lblDateOfBirth;
	private JButton btnSubmit;
	private JLabel feelbl;
	private JLabel medicallbl;
	private JLabel birthlbl;
	private JLabel photolbl;
	private JButton btnBrowse_3;
	private JButton btnBrowse_2;
	private JButton btnBrowse_1;
	private JButton btnBrowse;
	private JLabel lblFeeReceipt;
	private JLabel lblMedicalCerti;
	private JLabel lblBirthCertificate;
	private JLabel lblPhoto;
	private JLabel lblAddress;
	private JLabel lblPhoneNo;
	private JLabel lblemail;
	private JLabel lblName;
	private JLabel lblMembershipApplicationForm;
	private JPanel MemberAppl;
	private JButton btnBack;
	private JLabel lblNewLabel;
	private JButton btnParticipateInEvent;
	private JButton btnApplyForA;
	private JButton btnApplyForMembership;
	private JButton btnLogin;
	private JLabel lblPassword;
	private JLabel lblId;
	private JLabel lblNoticeBoard;
	private JLabel lblwelcomeText;
	public JTextPane noticeTextPane;
	private JScrollPane noticeScrollPane;
	private JPanel welcomePage;	
	private MemberApplicant mApplicant;
	private ParticipantApplic participant;
	private Viewer viewer;
	private JPanel networkCheck;
	private JLabel lblNewLabel_3;
	private JPanel participantAppl;
	private JTextField namePAF;
	private JTextField dobPAF;
	private JTextField mailPAF;
	private JTextField phonePAF;
	private JTextField addressPAF;
	private JPanel eventsOptions;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblParticipate;
	private JLabel lblViewEvent;
	private JPanel memberParticipate;
	private JLabel lblUser;
	private JLabel lblPassword_1;
	private JTextField mPIDF;
	private JPasswordField mPPasF;
	private JTextField nameVA;
	private JTextField mailVA;
	private JTextField phoneVA;
	private JTextField addressVA;
	private JLabel viewFeelbl;
	private JPanel viewEvent;
	private JTextField coursePAF;
	private JLabel photoPAF;
	private JLabel medicalPAF;
	private JLabel feePAF;
	private JPanel CourseAppl;
	private JTextField nameCAF;
	private JTextField dobCAF;
	private JTextField courseCAF;
	private JTextField mailCAF;
	private JTextField addressCAF;
	private JTextField phoneCAF;
	private JLabel photoCAF;
	private JLabel medicalCAF;
	private JLabel feeCAF;
	private JTextField mEIDF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Spms.window = new WelcomePage();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Spms.window.frmSpms.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try{
			Database db=new Database();
			db.disconnect();
			Spms.window.lblNewLabel_3.setText("<html>Connected to Database."
					+ "<br>Connecting to FTP server...</html>");
		}
		catch(Exception e)
		{
			Spms.window.lblNewLabel_3.setText("<html>Cannot connect to Database..<br>Check if the server is down.</html>");
			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			Spms.window.frmSpms.dispose();
			return;
		}
		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		try{
			FTPTransfer ftpTransfer=new FTPTransfer();
			ftpTransfer.disconnect();
			Spms.window.lblNewLabel_3.setText("<html>Connected to FTP server."
					+ "<br>Checking network connectivity...</html>");
		}
		catch(Exception e)
		{
			Spms.window.lblNewLabel_3.setText("<html>Cannot connect to FTP server.."
					+ "<br>Check if the server is down.</html>");

			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			Spms.window.frmSpms.dispose();
			return;
		}
		
		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		Mail mail=new Mail();
//		mail.to.add(Spms.managerMailFrom);
//		mail.subject="Network Test";
//		mail.message="Nothing to Worry, its working :D .";
		try {
//			mail.send();
			Spms.window.lblNewLabel_3.setText("<html>Connected to the Network."
					+ "<br>Checking done..</html>");
		} catch (Exception e) {
			Spms.window.lblNewLabel_3.setText("<html>Cannot connect to Network.."
					+ "<br>Check if the network is down.</html>");

			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			Spms.window.frmSpms.dispose();
		}
		
		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		Spms.window.networkCheck.setVisible(false);
		Spms.window.notices();
		Spms.window.welcomePage.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public WelcomePage() {
		initialize();
	}
	
	public void notices() {
		Database db=new Database();
		ResultSet resultSet=db.Query("SELECT * FROM notices");
		try {
			String notice="";
			while(resultSet.next())
			{
				notice=resultSet.getString("text");
				noticeTextPane.setText(noticeTextPane.getText()
						+"\n----------------------------------------------------------------------------------------\n"+notice
						+"\n----------------------------------------------------------------------------------------\n");
				
			}
			noticeTextPane.setCaretPosition(0);
		} catch (SQLException e) {
			e.printStackTrace();
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
		frmSpms.getContentPane().setLayout(new CardLayout(0, 0));
		
		welcomePage = new JPanel();
		frmSpms.getContentPane().add(welcomePage, "name_54045679379261");
		welcomePage.setLayout(null);
		welcomePage.setVisible(false);
		
		noticeScrollPane = new JScrollPane();
		noticeScrollPane.setBounds(32, 101, 380, 359);
		welcomePage.add(noticeScrollPane);
		
		noticeTextPane = new JTextPane();
		noticeTextPane.setEditable(false);
		noticeScrollPane.setViewportView(noticeTextPane);
		
		lblwelcomeText = new JLabel("Welcome to Swimming Pool Management Application");
		lblwelcomeText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblwelcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		lblwelcomeText.setBounds(32, 37, 728, 26);
		welcomePage.add(lblwelcomeText);
		
		lblNoticeBoard = new JLabel("Notice Board:");
		lblNoticeBoard.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNoticeBoard.setBounds(32, 74, 224, 26);
		welcomePage.add(lblNoticeBoard);
		
		loginId = new JTextField();
		loginId.setBounds(562, 101, 166, 20);
		welcomePage.add(loginId);
		loginId.setColumns(10);
		
		loginPass = new JPasswordField();
		loginPass.setBounds(562, 166, 166, 20);
		welcomePage.add(loginPass);
		loginPass.setColumns(10);
		
		lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(449, 104, 103, 14);
		welcomePage.add(lblId);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(449, 166, 103, 14);
		welcomePage.add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(!(loginId.getText().equals(""))&&!(loginPass.getText().equals("")))
				{
					Database db=new Database();
					ResultSet rSet=db.Query("SELECT password,type FROM login WHERE id = "+loginId.getText());
					String pass="";
					int type=10;
					try {
						if(rSet.next())
						{
							pass=rSet.getString("password");
							type=rSet.getInt("type");
							db.disconnect();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(pass.equals(""))
					{
						loginId.setText("");
						loginPass.setText("");
						WarningBox warningBox=new WarningBox("Invalid Username or Password!");
						warningBox.setVisible(true);
						return;
					}
					if(pass.equals(loginPass.getText()))
					{
						switch (type) {
						case 0:
						{
							ManagerPage.manager=new Manager();
							ManagerPage.manager.id=Integer.parseInt(loginId.getText());
							frmSpms.setVisible(false);
							loginId.setText("");
							loginPass.setText("");
							ManagerPage.loggedIn=1;
							ManagerPage.main(null);
						}
							break;
						case 1:
						{
							MemberPage.member=new Member();
							MemberPage.member.id=Integer.parseInt(loginId.getText());
							frmSpms.setVisible(false);
							loginId.setText("");
							loginPass.setText("");
							MemberPage.loggedIn=1;
							MemberPage.main(null);
						}
							break;
						case 2:
						{
							CommitteeMemPage.cMember=new CommitteeMember();
							CommitteeMemPage.cMember.id=Integer.parseInt(loginId.getText());
							Database database=new Database();
							ResultSet rSet2=database.Query("SELECT `type` FROM `cmembers` WHERE `id` = "+CommitteeMemPage.cMember.id);
							int cMType=0;
							try {
								if (rSet2.next()) {
									cMType=Integer.parseInt(rSet2.getString("type"));
									switch (cMType) {
									case 0:
										break;
									case 1:
									{
										rSet2=database.Query("SELECT `ID` FROM `courses` WHERE `coordinatorID` = "
												+CommitteeMemPage.cMember.id);
										if (rSet2.next()) {
											CommitteeMemPage.courseID=rSet2.getString("ID");
										}
										else
										{
											database.Update("UPDATE `cmembers` SET `type` = 0 WHERE `id` = "+CommitteeMemPage.cMember.id);
											cMType=0;
										}
									}
									break;
									case 2:
									{
										ResultSet rSet3=database.Query("SELECT `ID` FROM `events` WHERE `managerID` = "+loginId.getText());
										if (rSet3.next()) {
											CommitteeMemPage.eventID=rSet3.getString("ID");
										}
										else
										{
											database.Update("UPDATE `cmembers` SET `type` = 0 WHERE `id` = "+loginId.getText());
											cMType=0;
										}
									}
									break;
									}
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							frmSpms.setVisible(false);
							loginId.setText("");
							loginPass.setText("");
							CommitteeMemPage.Type=cMType;
							CommitteeMemPage.loggedIn=1;
							CommitteeMemPage.main(null);
						}
						break;
						case 3:
						{
							StudentPage.student=new Student();
							StudentPage.student.course=new Course();
							StudentPage.student.id=Integer.parseInt(loginId.getText());
							frmSpms.setVisible(false);
							loginId.setText("");
							loginPass.setText("");
							StudentPage.loggedIn=1;
							StudentPage.main(null);
						}
						break;
						}
					}
					else {
						loginId.setText("");
						loginPass.setText("");
						WarningBox warningBox=new WarningBox("Invalid Username or Password!");
						warningBox.setVisible(true);
						return;
					}
				}
				else {
					WarningBox warningBox=new WarningBox("Invalid Username or Password!");
					warningBox.setVisible(true);
					return;
				}
			}
		});
		btnLogin.setBounds(639, 224, 89, 23);
		welcomePage.add(btnLogin);
		
		btnApplyForMembership = new JButton("Apply For Membership");
		btnApplyForMembership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database database=new Database();
				ResultSet rSet=database.Query("SELECT * FROM form WHERE `id` = 1");
				try {
					if(rSet.next())
					{
						lblName.setText(rSet.getString("cF1"));
						lblDateOfBirth.setText(rSet.getString("cF2"));
						lblemail.setText(rSet.getString("cF3"));
						lblPhoneNo.setText(rSet.getString("cF4"));
						lblAddress.setText(rSet.getString("cF5"));
						lblPhoto.setText(rSet.getString("cF6"));
						lblBirthCertificate.setText(rSet.getString("cF7"));
						lblMedicalCerti.setText(rSet.getString("cF8"));
						lblFeeReceipt.setText(rSet.getString("cF9"));
					}
				} catch (Exception e2) {
				}
				database.disconnect();
				welcomePage.setVisible(false);
				mApplicant=new MemberApplicant();
				MemberAppl.setVisible(true);
			}
		});
		btnApplyForMembership.setBounds(514, 338, 246, 23);
		welcomePage.add(btnApplyForMembership);
		
		btnApplyForA = new JButton("Apply for a Course");
		btnApplyForA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcomePage.setVisible(false);
				CourseAppl.setVisible(true);
				participant=new ParticipantApplic();
			}
		});
		btnApplyForA.setBounds(514, 386, 246, 23);
		welcomePage.add(btnApplyForA);
		
		btnParticipateInEvent = new JButton("Participate or View Event");
		btnParticipateInEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcomePage.setVisible(false);
				eventsOptions.setVisible(true);
			}
		});
		btnParticipateInEvent.setBounds(514, 437, 246, 23);
		welcomePage.add(btnParticipateInEvent);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setEnabled(false);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(".\\files\\spms.jpg"));
		lblNewLabel.setBounds(0, 0, 794, 471);
		welcomePage.add(lblNewLabel);
		
		MemberAppl = new JPanel();
		frmSpms.getContentPane().add(MemberAppl, "name_22081044029390");
		MemberAppl.setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberAppl.setVisible(false);
				nameMAF.setText("");
				dobMAF.setText("");
				mailMAF.setText("");
				phoneMAF.setText("");
				addressMAF.setText("");
				photolbl.setText("");
				medicallbl.setText("");
				birthlbl.setText("");
				feelbl.setText("");
				welcomePage.setVisible(true);
			}
		});
		btnBack.setBounds(10, 437, 89, 23);
		MemberAppl.add(btnBack);
		
		lblMembershipApplicationForm = new JLabel("Membership Application Form");
		lblMembershipApplicationForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblMembershipApplicationForm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMembershipApplicationForm.setBounds(10, 11, 774, 28);
		MemberAppl.add(lblMembershipApplicationForm);
		
		lblName = new JLabel("Name");
		lblName.setBounds(84, 85, 105, 14);
		MemberAppl.add(lblName);
		
		lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(84, 147, 105, 14);
		MemberAppl.add(lblDateOfBirth);
		
		lblemail = new JLabel("Email ID");
		lblemail.setBounds(84, 204, 105, 14);
		MemberAppl.add(lblemail);
		
		lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setBounds(84, 263, 105, 14);
		MemberAppl.add(lblPhoneNo);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(84, 324, 105, 14);
		MemberAppl.add(lblAddress);
		
		lblPhoto = new JLabel("photo");
		lblPhoto.setBounds(438, 85, 132, 14);
		MemberAppl.add(lblPhoto);
		
		lblBirthCertificate = new JLabel("Birth Certificate");
		lblBirthCertificate.setBounds(438, 147, 132, 14);
		MemberAppl.add(lblBirthCertificate);
		
		lblMedicalCerti = new JLabel("Medical Certificate");
		lblMedicalCerti.setBounds(438, 204, 132, 14);
		MemberAppl.add(lblMedicalCerti);
		
		lblFeeReceipt = new JLabel("Fee Receipt");
		lblFeeReceipt.setBounds(438, 263, 132, 14);
		MemberAppl.add(lblFeeReceipt);
		
		btnBrowse = new JButton("Browse...");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            photolbl.setText(fileChooser.getSelectedFile().getName());
			            mApplicant.photo=fileChooser.getSelectedFile().getPath();
			    }
			}
		});
		btnBrowse.setBounds(553, 81, 89, 23);
		MemberAppl.add(btnBrowse);
		
		btnBrowse_1 = new JButton("Browse...");
		btnBrowse_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            birthlbl.setText(fileChooser.getSelectedFile().getName());
			            mApplicant.birthCert=fileChooser.getSelectedFile().getPath();
			    }
			}
		});
		btnBrowse_1.setBounds(553, 143, 89, 23);
		MemberAppl.add(btnBrowse_1);
		
		btnBrowse_2 = new JButton("Browse...");
		btnBrowse_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            medicallbl.setText(fileChooser.getSelectedFile().getName());
			            mApplicant.medicalCert=fileChooser.getSelectedFile().getPath();
			    }
			}
		});
		btnBrowse_2.setBounds(553, 200, 89, 23);
		MemberAppl.add(btnBrowse_2);
		
		btnBrowse_3 = new JButton("Browse...");
		btnBrowse_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            feelbl.setText(fileChooser.getSelectedFile().getName());
			            mApplicant.feeReceipt=fileChooser.getSelectedFile().getPath();
			    }
			}
		});
		btnBrowse_3.setBounds(553, 259, 89, 23);
		MemberAppl.add(btnBrowse_3);
		
		photolbl = new JLabel("");
		photolbl.setBounds(652, 85, 132, 14);
		MemberAppl.add(photolbl);
		
		birthlbl = new JLabel("");
		birthlbl.setBounds(652, 147, 132, 14);
		MemberAppl.add(birthlbl);
		
		medicallbl = new JLabel("");
		medicallbl.setBounds(652, 204, 132, 14);
		MemberAppl.add(medicallbl);
		
		feelbl = new JLabel("");
		feelbl.setBounds(652, 263, 132, 14);
		MemberAppl.add(feelbl);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mApplicant.name=nameMAF.getText();
				mApplicant.address=addressMAF.getText();
				mApplicant.emailID=mailMAF.getText();
				if(!Spms.checkMail(mApplicant.emailID))
				{
					mailMAF.setText("");
					return;
				}
				mApplicant.phoneNo=phoneMAF.getText();
				if(!Spms.checkPhone(mApplicant.phoneNo))
				{
					phoneMAF.setText("");
					return;
				}
				
				if (mApplicant.photo==null||mApplicant.birthCert==null||mApplicant.medicalCert==null||mApplicant.feeReceipt==null) {
					WarningBox warningBox=new WarningBox("Upload all the files!");
					warningBox.setVisible(true);
					return;
				}
				try{
					FTPTransfer fTransfer=new FTPTransfer();
					fTransfer.uploadFile=mApplicant.photo;
					fTransfer.uploadTo="/spms/mapplications/"+mApplicant.name+"_"+photolbl.getText();
					fTransfer.upload();
					fTransfer.uploadFile=mApplicant.birthCert;
					fTransfer.uploadTo="/spms/mapplications/"+mApplicant.name+"_"+birthlbl.getText();
					fTransfer.upload();
					fTransfer.uploadFile=mApplicant.medicalCert;
					fTransfer.uploadTo="/spms/mapplications/"+mApplicant.name+"_"+medicallbl.getText();
					fTransfer.upload();
					fTransfer.uploadFile=mApplicant.feeReceipt;
					fTransfer.uploadTo="/spms/mapplications/"+mApplicant.name+"_"+feelbl.getText();
					fTransfer.upload();
					fTransfer.disconnect();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				String[] date=dobMAF.getText().split(" ");
				try {
					mApplicant.dob=LocalDate.of(Integer.parseInt(date[2]),
							Integer.parseInt(date[1]),Integer.parseInt(date[0]));	
				} catch (Exception e2) {
					WarningBox warningBox=new WarningBox("Wrong Date Format!");
					warningBox.setVisible(true);
					dobMAF.setText("");
					return;
				}
				Database database=new Database();
				database.Update("Insert into mapplics value(NULL,'"
						+ mApplicant.name+"','"+mApplicant.emailID+"','"+mApplicant.phoneNo
						+"','"+mApplicant.address+"','"+java.sql.Date.valueOf(mApplicant.dob)
						+"','/spms/mapplications/"+mApplicant.name+"_"+photolbl.getText()
						+"','/spms/mapplications/"+mApplicant.name+"_"+birthlbl.getText()
						+"','/spms/mapplications/"+mApplicant.name+"_"+medicallbl.getText()
						+"','/spms/mapplications/"+mApplicant.name+"_"+feelbl.getText()+"')");
				ResultSet rrSet=database.Query("SELECT id FROM mapplics WHERE `name` = '"+mApplicant.name
						+"' AND `phoneNo` = '"+mApplicant.phoneNo+"'");
				int applicID=0;
				try {
					if(rrSet.next())
					{
						applicID=Integer.parseInt(rrSet.getString("id"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.Update("INSERT INTO `notifs` VALUE(NULL,NULL,'"+applicID+"',1)");
				rrSet=database.Query("SELECT * FROM manager");
				ResultSet rrrSet;
				String notifId=null;
				String notifications;
				int id=0;
				try {
					while(rrSet.next())
					{
						id=Integer.parseInt(rrSet.getString("id"));
						notifications=rrSet.getString("notifics");
						rrrSet=database.Query("SELECT id FROM notifs WHERE `userID` = '"+applicID+"' AND `type` = '1'");
						if(rrrSet.next())
						{
							notifId=rrrSet.getString("id");
						}
						if(notifId!=null)
							notifications+=","+notifId;
						database.Update("UPDATE `manager` SET `notifics` = '"+notifications+"' WHERE `id` = "+id);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.disconnect();
				
				nameMAF.setText("");
				dobMAF.setText("");
				mailMAF.setText("");
				phoneMAF.setText("");
				addressMAF.setText("");
				photolbl.setText("");
				medicallbl.setText("");
				birthlbl.setText("");
				feelbl.setText("");
				MemberAppl.setVisible(false);
				welcomePage.setVisible(true);
			}
		});
		btnSubmit.setBounds(438, 324, 89, 23);
		MemberAppl.add(btnSubmit);
		
		nameMAF = new JTextField();
		nameMAF.setBounds(199, 82, 217, 20);
		MemberAppl.add(nameMAF);
		nameMAF.setColumns(10);
		
		dobMAF = new JTextField();
		dobMAF.setBounds(199, 144, 217, 20);
		MemberAppl.add(dobMAF);
		dobMAF.setColumns(10);
		
		mailMAF = new JTextField();
		mailMAF.setBounds(199, 201, 217, 20);
		MemberAppl.add(mailMAF);
		mailMAF.setColumns(10);
		
		phoneMAF = new JTextField();
		phoneMAF.setBounds(199, 260, 217, 20);
		MemberAppl.add(phoneMAF);
		phoneMAF.setColumns(10);
		
		addressMAF = new JTextField();
		addressMAF.setBounds(199, 321, 217, 20);
		MemberAppl.add(addressMAF);
		addressMAF.setColumns(10);
		
		networkCheck = new JPanel();
		frmSpms.getContentPane().add(networkCheck, "name_2185917681702");
		networkCheck.setLayout(null);
		networkCheck.setVisible(true);
		
		lblNewLabel_3 = new JLabel("Connecting..");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 116, 774, 221);
		networkCheck.add(lblNewLabel_3);
		
		JLabel lblCheckingNetworkConnectivity = new JLabel("Checking Network connectivity");
		lblCheckingNetworkConnectivity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCheckingNetworkConnectivity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckingNetworkConnectivity.setBounds(10, 11, 774, 28);
		networkCheck.add(lblCheckingNetworkConnectivity);
		
		participantAppl = new JPanel();
		frmSpms.getContentPane().add(participantAppl, "name_8445208699195");
		participantAppl.setLayout(null);
		
		JLabel lblParticipantApplicationForm = new JLabel("Event Participant Application Form");
		lblParticipantApplicationForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblParticipantApplicationForm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblParticipantApplicationForm.setBounds(10, 11, 774, 28);
		participantAppl.add(lblParticipantApplicationForm);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(84, 85, 105, 14);
		participantAppl.add(label_1);
		
		namePAF = new JTextField();
		namePAF.setColumns(10);
		namePAF.setBounds(199, 82, 217, 20);
		participantAppl.add(namePAF);
		
		JLabel label_2 = new JLabel("Date of Birth");
		label_2.setBounds(84, 147, 105, 14);
		participantAppl.add(label_2);
		
		dobPAF = new JTextField();
		dobPAF.setColumns(10);
		dobPAF.setBounds(199, 144, 217, 20);
		participantAppl.add(dobPAF);
		
		mailPAF = new JTextField();
		mailPAF.setColumns(10);
		mailPAF.setBounds(199, 201, 217, 20);
		participantAppl.add(mailPAF);
		
		JLabel label_3 = new JLabel("Email ID");
		label_3.setBounds(84, 204, 105, 14);
		participantAppl.add(label_3);
		
		JLabel label_4 = new JLabel("Phone No");
		label_4.setBounds(84, 263, 105, 14);
		participantAppl.add(label_4);
		
		phonePAF = new JTextField();
		phonePAF.setColumns(10);
		phonePAF.setBounds(199, 260, 217, 20);
		participantAppl.add(phonePAF);
		
		JLabel label_5 = new JLabel("Address");
		label_5.setBounds(84, 324, 105, 14);
		participantAppl.add(label_5);
		
		addressPAF = new JTextField();
		addressPAF.setColumns(10);
		addressPAF.setBounds(199, 321, 217, 20);
		participantAppl.add(addressPAF);
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				participant.name=namePAF.getText();
				participant.emailID=mailPAF.getText();
				if(!Spms.checkMail(participant.emailID))
				{
					mailPAF.setText("");
					return;
				}
				participant.phoneNo=phonePAF.getText();
				if (!Spms.checkPhone(participant.phoneNo)) {
					phonePAF.setText("");
					return;
				}
				participant.address=addressPAF.getText();
				participant.event=new Event();
				participant.event.ID=coursePAF.getText();
				
				Database db=new Database();
				ResultSet test=db.Query("SELECT * FROM `eventd` WHERE `id` = '"+courseCAF+"'");
				try {
					if (test.next()) {
					}
					else {
						WarningBox warningBox=new WarningBox("Wrong Event ID!");
						warningBox.setVisible(true);
						coursePAF.setText("");
						return;
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
								
				if(participant.photo==null||participant.medicalCert==null||participant.feeReceipt==null)
				{
					WarningBox warningBox=new WarningBox("Upload all the files!");
					warningBox.setVisible(true);
					return;
				}
				try{
					FTPTransfer fTransfer=new FTPTransfer();
					fTransfer.uploadFile=participant.photo;
					fTransfer.uploadTo="/spms/papplications/"+participant.name+"_"+photoPAF.getText();
					fTransfer.upload();
					fTransfer.uploadFile=participant.medicalCert;
					fTransfer.uploadTo="/spms/papplications/"+participant.name+"_"+medicalPAF.getText();
					fTransfer.upload();
					fTransfer.uploadFile=participant.feeReceipt;
					fTransfer.uploadTo="/spms/papplications/"+participant.name+"_"+feePAF.getText();
					fTransfer.upload();
					fTransfer.disconnect();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				String[] date=dobPAF.getText().split(" ");
				try {
					participant.dob=LocalDate.of(Integer.parseInt(date[2]),
							Integer.parseInt(date[1]),Integer.parseInt(date[0]));	
				} catch (Exception e2) {
					WarningBox warningBox=new WarningBox("Wrong Date Format or Date!");
					warningBox.setVisible(true);
					return;
				}
				
				Database database=new Database();
				database.Update("Insert into papplics values(NULL,'"
						+ participant.name+"','"+participant.emailID+"','"+participant.address+"','"+participant.phoneNo
						+"','"+java.sql.Date.valueOf(participant.dob)
						+"','/spms/papplications/"+participant.name+"_"+medicalPAF.getText()
						+"','/spms/papplications/"+participant.name+"_"+photoPAF.getText()
						+"','/spms/papplications/"+participant.name+"_"+feePAF.getText()
						+"','"+participant.event.ID+"')");
				ResultSet rrSet=database.Query("SELECT id FROM papplics WHERE `name` = '"+participant.name
						+"' AND `phoneNo` = '"+participant.phoneNo+"'");
				int applicID=0;
				try {
					if(rrSet.next())
					{
						applicID=Integer.parseInt(rrSet.getString("id"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.Update("INSERT INTO `notifs` VALUE(NULL,NULL,'"+applicID+"',3)");
				rrSet=database.Query("SELECT * FROM manager");
				ResultSet rrrSet;
				String notifId=null;
				String notifications;
				int id=0;
				try {
					while(rrSet.next())
					{
						id=Integer.parseInt(rrSet.getString("id"));
						notifications=rrSet.getString("notifics");
						rrrSet=database.Query("SELECT id FROM notifs WHERE `userID` = '"+applicID+"' AND `type` = '3'");
						if(rrrSet.next())
						{
							notifId=rrrSet.getString("id");
						}
						if(notifId!=null)
							notifications+=","+notifId;
						database.Update("UPDATE `manager` SET `notifics` = '"+notifications+"' WHERE `id` = "+id);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.disconnect();
				
				participantAppl.setVisible(false);
				namePAF.setText("");
				dobPAF.setText("");
				mailPAF.setText("");
				phonePAF.setText("");
				addressPAF.setText("");
				photoPAF.setText("");
				medicalPAF.setText("");
				feePAF.setText("");
				coursePAF.setText("");
				welcomePage.setVisible(true);
			}
		});
		button.setBounds(438, 324, 89, 23);
		participantAppl.add(button);
		
		JButton button_5 = new JButton("Back");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				participantAppl.setVisible(false);
				namePAF.setText("");
				dobPAF.setText("");
				mailPAF.setText("");
				phonePAF.setText("");
				addressPAF.setText("");
				photoPAF.setText("");
				medicalPAF.setText("");
				feePAF.setText("");
				coursePAF.setText("");
				eventsOptions.setVisible(true);
			}
		});
		button_5.setBounds(10, 437, 89, 23);
		participantAppl.add(button_5);
		
		JLabel label = new JLabel("photo");
		label.setBounds(426, 141, 105, 14);
		participantAppl.add(label);
		
		JButton button_1 = new JButton("Browse...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            photoPAF.setText(fileChooser.getSelectedFile().getName());
			            participant.photo=fileChooser.getSelectedFile().getPath();
			    }
			}
		});
		button_1.setBounds(541, 137, 89, 23);
		participantAppl.add(button_1);
		
		photoPAF = new JLabel("");
		photoPAF.setBounds(640, 141, 132, 14);
		participantAppl.add(photoPAF);
		
		medicalPAF = new JLabel("");
		medicalPAF.setBounds(640, 199, 132, 14);
		participantAppl.add(medicalPAF);
		
		JButton button_2 = new JButton("Browse...");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            medicalPAF.setText(fileChooser.getSelectedFile().getName());
			            participant.medicalCert=fileChooser.getSelectedFile().getPath();
			    }
			}
		});
		button_2.setBounds(541, 195, 89, 23);
		participantAppl.add(button_2);
		
		JLabel label_8 = new JLabel("Medical Certificate");
		label_8.setBounds(426, 199, 119, 14);
		participantAppl.add(label_8);
		
		JLabel label_9 = new JLabel("Fee Receipt");
		label_9.setBounds(426, 258, 105, 14);
		participantAppl.add(label_9);
		
		JButton button_3 = new JButton("Browse...");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            feePAF.setText(fileChooser.getSelectedFile().getName());
			            participant.feeReceipt=fileChooser.getSelectedFile().getPath();
			    }
			}
		});
		button_3.setBounds(541, 254, 89, 23);
		participantAppl.add(button_3);
		
		feePAF = new JLabel("");
		feePAF.setBounds(640, 258, 132, 14);
		participantAppl.add(feePAF);
		
		JLabel lblCourseId = new JLabel("Event ID:");
		lblCourseId.setBounds(426, 85, 101, 14);
		participantAppl.add(lblCourseId);
		
		coursePAF = new JTextField();
		coursePAF.setBounds(541, 82, 132, 20);
		participantAppl.add(coursePAF);
		coursePAF.setColumns(10);
		
		eventsOptions = new JPanel();
		frmSpms.getContentPane().add(eventsOptions, "name_9950578648088");
		eventsOptions.setLayout(null);
		
		btnNewButton = new JButton("Already Member");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsOptions.setVisible(false);
				memberParticipate.setVisible(true);
			}
		});
		btnNewButton.setBounds(283, 110, 206, 23);
		eventsOptions.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Not Member");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsOptions.setVisible(false);
				participant=new ParticipantApplic();
				participantAppl.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(283, 183, 206, 23);
		eventsOptions.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Submit Transaction Receipt");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsOptions.setVisible(false);
				viewer=new Viewer();
				viewEvent.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(283, 287, 206, 23);
		eventsOptions.add(btnNewButton_2);
		
		lblParticipate = new JLabel("Participate");
		lblParticipate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblParticipate.setBounds(283, 64, 116, 23);
		eventsOptions.add(lblParticipate);
		
		lblViewEvent = new JLabel("View Event");
		lblViewEvent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblViewEvent.setBounds(283, 242, 116, 23);
		eventsOptions.add(lblViewEvent);
		
		JButton btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsOptions.setVisible(false);
				welcomePage.setVisible(true);
			}
		});
		btnBack_2.setBounds(10, 437, 89, 23);
		eventsOptions.add(btnBack_2);
		
		memberParticipate = new JPanel();
		frmSpms.getContentPane().add(memberParticipate, "name_10488286047049");
		memberParticipate.setLayout(null);
		
		lblUser = new JLabel("ID");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(197, 124, 97, 28);
		memberParticipate.add(lblUser);
		
		lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword_1.setBounds(197, 176, 97, 23);
		memberParticipate.add(lblPassword_1);
		
		mPIDF = new JTextField();
		mPIDF.setBounds(304, 130, 269, 20);
		memberParticipate.add(mPIDF);
		mPIDF.setColumns(10);
		
		mPPasF = new JPasswordField();
		mPPasF.setBounds(304, 179, 269, 20);
		memberParticipate.add(mPPasF);
		
		JButton btnSubmit_1 = new JButton("Apply");
		btnSubmit_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Database database=new Database();
				if (mPIDF.equals("")||mPPasF.equals("")) {
					mPIDF.setText("");
					mPPasF.setText("");
					mEIDF.setText("");
					WarningBox warningBox=new WarningBox("Invalid Username or Password!");
					warningBox.setVisible(true);
					return;
				}
				ResultSet rSet=database.Query("SELECT * FROM `member` WHERE `id` = "+mPIDF.getText());
				try {
					if (rSet.next()) {
						if (!(mPPasF.getText().equals(rSet.getString("password")))) {
							mPIDF.setText("");
							mPPasF.setText("");
							mEIDF.setText("");
							WarningBox warningBox=new WarningBox("Invalid Username or Password!");
							warningBox.setVisible(true);
							return;
						}
						Participant participant=new Participant();
						participant.Name=rSet.getString("name");
						participant.emailID=rSet.getString("emailID");
						participant.eventID=mEIDF.getText();
						ResultSet rrSet=database.Query("SELECT * FROM `events` WHERE `ID` = '"+participant.eventID+"'");
						if (!rrSet.next()) {
							WarningBox warningBox=new WarningBox("Invalid Event ID!");
							warningBox.setVisible(true);
							mEIDF.setText("");
							return;
						}
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
						mail.subject="SPMS Participation Approval";
						mail.message="Dear "+participant.Name+",\n    Your request for participation in SPMS event has been accepted. "
								+ "The event you registered for is "+participant.eventID
								+"\nYour ID for event is "+participant.id+"\nPlease do bring your certificates for the event"
										+ "\n\nRegards,\nManager,\nSPMS.";
						try {
							mail.send();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				database.disconnect();
				mPIDF.setText("");
				mPPasF.setText("");
				mEIDF.setText("");
			}
		});
		btnSubmit_1.setBounds(304, 286, 89, 23);
		memberParticipate.add(btnSubmit_1);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mPIDF.setText("");
				mPPasF.setText("");
				mEIDF.setText("");
				memberParticipate.setVisible(false);
				eventsOptions.setVisible(true);
			}
		});
		btnBack_1.setBounds(10, 437, 89, 23);
		memberParticipate.add(btnBack_1);
		
		JLabel lblEventId = new JLabel("Event ID:");
		lblEventId.setBounds(197, 241, 97, 14);
		memberParticipate.add(lblEventId);
		
		mEIDF = new JTextField();
		mEIDF.setBounds(304, 238, 269, 20);
		memberParticipate.add(mEIDF);
		mEIDF.setColumns(10);
		
		viewEvent = new JPanel();
		frmSpms.getContentPane().add(viewEvent, "name_11529464427094");
		viewEvent.setLayout(null);
		
		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setBounds(169, 111, 88, 14);
		viewEvent.add(lblName_1);
		
		JLabel lblNewLabel_4 = new JLabel("Email:");
		lblNewLabel_4.setBounds(169, 165, 88, 14);
		viewEvent.add(lblNewLabel_4);
		
		JLabel lblPhoneNo_1 = new JLabel("Phone No:");
		lblPhoneNo_1.setBounds(169, 218, 88, 14);
		viewEvent.add(lblPhoneNo_1);
		
		JLabel lblAddress_1 = new JLabel("Address:");
		lblAddress_1.setBounds(169, 264, 88, 14);
		viewEvent.add(lblAddress_1);
		
		JLabel lblPaymentReceipt = new JLabel("Payment Receipt:");
		lblPaymentReceipt.setBounds(169, 318, 98, 14);
		viewEvent.add(lblPaymentReceipt);
		
		nameVA = new JTextField();
		nameVA.setBounds(304, 108, 169, 20);
		viewEvent.add(nameVA);
		nameVA.setColumns(10);
		
		mailVA = new JTextField();
		mailVA.setBounds(304, 162, 169, 20);
		viewEvent.add(mailVA);
		mailVA.setColumns(10);
		
		phoneVA = new JTextField();
		phoneVA.setBounds(304, 215, 169, 20);
		viewEvent.add(phoneVA);
		phoneVA.setColumns(10);
		
		addressVA = new JTextField();
		addressVA.setBounds(304, 261, 169, 20);
		viewEvent.add(addressVA);
		addressVA.setColumns(10);
		
		JButton btnBrowse_5 = new JButton("Browse...");
		btnBrowse_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            viewFeelbl.setText(fileChooser.getSelectedFile().getName());
			            viewer.ticketReceipt=fileChooser.getSelectedFile().getPath();
			    }
			}
		});
		btnBrowse_5.setBounds(304, 314, 89, 23);
		viewEvent.add(btnBrowse_5);
		
		viewFeelbl = new JLabel("");
		viewFeelbl.setBounds(427, 318, 278, 14);
		viewEvent.add(viewFeelbl);
		
		JButton btnSubmit_2 = new JButton("Submit");
		btnSubmit_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewer.name=nameVA.getText();
				viewer.emailID=mailVA.getText();
				if(!Spms.checkMail(viewer.emailID))
				{
					mailVA.setText("");
					return;
				}
				viewer.phoneNo=phoneVA.getText();
				if(!Spms.checkPhone(viewer.phoneNo))
				{
					phoneVA.setText("");
					return;
				}
				viewer.address=addressVA.getText();
				
				if(viewer.ticketReceipt==null){
					WarningBox warningBox=new WarningBox("Upload all the files!");
					warningBox.setVisible(true);
					return;
				}
				try{
					FTPTransfer fTransfer=new FTPTransfer();
					fTransfer.uploadFile=viewer.ticketReceipt;
					fTransfer.uploadTo="/spms/vapplications/"+viewer.name+"_"+viewFeelbl.getText();
					fTransfer.upload();
					fTransfer.disconnect();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				Database database=new Database();
				database.Update("Insert into vapplics value(NULL,'"
						+ viewer.name+"','"+viewer.emailID+"','"+viewer.address+"','"+viewer.phoneNo
						+"','/spms/vapplications/"+viewer.name+"_"+viewFeelbl.getText()
						+"')");
				ResultSet rrSet=database.Query("SELECT id FROM vapplics WHERE `name` = '"+viewer.name
						+"' AND `phoneNo` = '"+viewer.phoneNo+"'");
				int applicID=0;
				try {
					if(rrSet.next())
					{
						applicID=Integer.parseInt(rrSet.getString("id"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.Update("INSERT INTO `notifs` VALUE(NULL,NULL,'"+applicID+"',4)");
				rrSet=database.Query("SELECT * FROM manager");
				ResultSet rrrSet;
				String notifId=null;
				String notifications;
				int id=0;
				try {
					while(rrSet.next())
					{
						id=Integer.parseInt(rrSet.getString("id"));
						notifications=rrSet.getString("notifics");
						rrrSet=database.Query("SELECT id FROM notifs WHERE `userID` = '"+applicID+"' AND `type` = '4'");
						if(rrrSet.next())
						{
							notifId=rrrSet.getString("id");
						}
						if(notifId!=null)
							notifications+=","+notifId;
						database.Update("UPDATE `manager` SET `notifics` = '"+notifications+"' WHERE `id` = "+id);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.disconnect();
				
				nameVA.setText("");
				mailVA.setText("");
				phoneVA.setText("");
				addressVA.setText("");
				viewFeelbl.setText("");
				viewEvent.setVisible(false);
				welcomePage.setVisible(true);
			}
		});
		btnSubmit_2.setBounds(304, 383, 89, 23);
		viewEvent.add(btnSubmit_2);
		
		JButton btnBack_3 = new JButton("Back");
		btnBack_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewEvent.setVisible(false);
				nameVA.setText("");
				mailVA.setText("");
				phoneVA.setText("");
				addressVA.setText("");
				viewFeelbl.setText("");
				eventsOptions.setVisible(true);
			}
		});
		btnBack_3.setBounds(10, 437, 89, 23);
		viewEvent.add(btnBack_3);
		
		CourseAppl = new JPanel();
		frmSpms.getContentPane().add(CourseAppl, "name_19309179621149");
		CourseAppl.setLayout(null);
		
		JLabel label_6 = new JLabel("Name");
		label_6.setBounds(84, 85, 105, 14);
		CourseAppl.add(label_6);
		
		nameCAF = new JTextField();
		nameCAF.setColumns(10);
		nameCAF.setBounds(199, 82, 217, 20);
		CourseAppl.add(nameCAF);
		
		JLabel lblCourseApplicationForm = new JLabel("Course Application Form");
		lblCourseApplicationForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseApplicationForm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCourseApplicationForm.setBounds(10, 11, 774, 28);
		CourseAppl.add(lblCourseApplicationForm);
		
		JLabel label_10 = new JLabel("Date of Birth");
		label_10.setBounds(84, 147, 105, 14);
		CourseAppl.add(label_10);
		
		dobCAF = new JTextField();
		dobCAF.setColumns(10);
		dobCAF.setBounds(199, 144, 217, 20);
		CourseAppl.add(dobCAF);
		
		JLabel label_11 = new JLabel("Course ID:");
		label_11.setBounds(426, 85, 101, 14);
		CourseAppl.add(label_11);
		
		courseCAF = new JTextField();
		courseCAF.setColumns(10);
		courseCAF.setBounds(541, 82, 132, 20);
		CourseAppl.add(courseCAF);
		
		JButton button_4 = new JButton("Browse...");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            photoCAF.setText(fileChooser.getSelectedFile().getName());
			            participant.photo=fileChooser.getSelectedFile().getPath();
			    }
			}
		});
		button_4.setBounds(541, 137, 89, 23);
		CourseAppl.add(button_4);
		
		JLabel label_12 = new JLabel("photo");
		label_12.setBounds(426, 141, 105, 14);
		CourseAppl.add(label_12);
		
		mailCAF = new JTextField();
		mailCAF.setColumns(10);
		mailCAF.setBounds(199, 201, 217, 20);
		CourseAppl.add(mailCAF);
		
		JLabel label_13 = new JLabel("Email ID");
		label_13.setBounds(84, 204, 105, 14);
		CourseAppl.add(label_13);
		
		JLabel label_14 = new JLabel("Medical Certificate");
		label_14.setBounds(426, 199, 119, 14);
		CourseAppl.add(label_14);
		
		medicalCAF = new JLabel("");
		medicalCAF.setBounds(640, 199, 132, 14);
		CourseAppl.add(medicalCAF);
		
		JButton button_6 = new JButton("Browse...");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            medicalCAF.setText(fileChooser.getSelectedFile().getName());
			            participant.medicalCert=fileChooser.getSelectedFile().getPath();
			    }
			}
		});
		button_6.setBounds(541, 195, 89, 23);
		CourseAppl.add(button_6);
		
		photoCAF = new JLabel("");
		photoCAF.setBounds(640, 141, 132, 14);
		CourseAppl.add(photoCAF);
		
		JButton button_7 = new JButton("Browse...");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            feeCAF.setText(fileChooser.getSelectedFile().getName());
			            participant.feeReceipt=fileChooser.getSelectedFile().getPath();
			    }
			}
		});
		button_7.setBounds(541, 254, 89, 23);
		CourseAppl.add(button_7);
		
		feeCAF = new JLabel("");
		feeCAF.setBounds(640, 258, 132, 14);
		CourseAppl.add(feeCAF);
		
		JLabel label_18 = new JLabel("Fee Receipt");
		label_18.setBounds(426, 258, 105, 14);
		CourseAppl.add(label_18);
		
		addressCAF = new JTextField();
		addressCAF.setColumns(10);
		addressCAF.setBounds(199, 321, 217, 20);
		CourseAppl.add(addressCAF);
		
		phoneCAF = new JTextField();
		phoneCAF.setColumns(10);
		phoneCAF.setBounds(199, 260, 217, 20);
		CourseAppl.add(phoneCAF);
		
		JButton button_8 = new JButton("Submit");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				participant.name=nameCAF.getText();
				participant.emailID=mailCAF.getText();
				if(!Spms.checkMail(participant.emailID))
				{
					mailCAF.setText("");
					return;
				}
				participant.phoneNo=phoneCAF.getText();
				if(!Spms.checkPhone(participant.phoneNo))
				{
					phoneCAF.setText("");
					return;
				}
				participant.address=addressCAF.getText();
				participant.course=new Course();
				participant.course.ID=courseCAF.getText();
				Database db=new Database();
				ResultSet rrSet=db.Query("SELECT * FROM `courses` WHERE `ID` = '"+participant.course.ID+"'");
				try {
					if (!rrSet.next()) {
						WarningBox warningBox=new WarningBox("Invalid Course ID!");
						warningBox.setVisible(true);
						mEIDF.setText("");
						return;
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				if (participant.photo==null||participant.medicalCert==null||participant.feeReceipt==null) {
					WarningBox warningBox=new WarningBox("Please fill all fields!");
					warningBox.setVisible(true);
					mEIDF.setText("");
					return;
				}
				try{
					FTPTransfer fTransfer=new FTPTransfer();
					fTransfer.uploadFile=participant.photo;
					fTransfer.uploadTo="/spms/capplications/"+participant.name+"_"+photoCAF.getText();
					fTransfer.upload();
					fTransfer.uploadFile=participant.medicalCert;
					fTransfer.uploadTo="/spms/capplications/"+participant.name+"_"+medicalCAF.getText();
					fTransfer.upload();
					fTransfer.uploadFile=participant.feeReceipt;
					fTransfer.uploadTo="/spms/capplications/"+participant.name+"_"+feeCAF.getText();
					fTransfer.upload();
					fTransfer.disconnect();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				String[] date=dobCAF.getText().split(" ");
				try {
					participant.dob=LocalDate.of(Integer.parseInt(date[2]),
							Integer.parseInt(date[1]),Integer.parseInt(date[0]));	
				} catch (Exception e2) {
					dobCAF.setText("");
					WarningBox warningBox=new WarningBox("Incorrect Date Field!");
					warningBox.setVisible(true);
				}
				
				Database database=new Database();
				database.Update("Insert into capplics values(NULL,'"
						+ participant.name+"','"+participant.emailID+"','"+participant.address+"','"+participant.phoneNo
						+"','"+java.sql.Date.valueOf(participant.dob)
						+"','/spms/capplications/"+participant.name+"_"+medicalCAF.getText()
						+"','/spms/capplications/"+participant.name+"_"+photoCAF.getText()
						+"','/spms/capplications/"+participant.name+"_"+feeCAF.getText()
						+"','"+participant.course.ID+"')");
				rrSet=database.Query("SELECT id FROM capplics WHERE `name` = '"+participant.name
						+"' AND `phoneNo` = '"+participant.phoneNo+"'");
				int applicID=0;
				try {
					if(rrSet.next())
					{
						applicID=Integer.parseInt(rrSet.getString("id"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.Update("INSERT INTO `notifs` VALUE(NULL,NULL,'"+applicID+"',2)");
				rrSet=database.Query("SELECT * FROM manager");
				ResultSet rrrSet;
				String notifId=null;
				String notifications;
				int id=0;
				try {
					while(rrSet.next())
					{
						id=Integer.parseInt(rrSet.getString("id"));
						notifications=rrSet.getString("notifics");
						rrrSet=database.Query("SELECT id FROM notifs WHERE `userID` = '"+applicID+"' AND `type` = '2'");
						if(rrrSet.next())
						{
							notifId=rrrSet.getString("id");
						}
						if(notifId!=null)
							notifications+=","+notifId;
						database.Update("UPDATE `manager` SET `notifics` = '"+notifications+"' WHERE `id` = "+id);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.disconnect();
				
				CourseAppl.setVisible(false);
				nameCAF.setText("");
				dobCAF.setText("");
				mailCAF.setText("");
				phoneCAF.setText("");
				addressCAF.setText("");
				photoCAF.setText("");
				medicalCAF.setText("");
				feeCAF.setText("");
				courseCAF.setText("");
				welcomePage.setVisible(true);
			}
		});
		button_8.setBounds(438, 324, 89, 23);
		CourseAppl.add(button_8);
		
		JLabel label_19 = new JLabel("Phone No");
		label_19.setBounds(84, 263, 105, 14);
		CourseAppl.add(label_19);
		
		JLabel label_20 = new JLabel("Address");
		label_20.setBounds(84, 324, 105, 14);
		CourseAppl.add(label_20);
		
		JButton button_9 = new JButton("Back");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseAppl.setVisible(false);
				nameCAF.setText("");
				dobCAF.setText("");
				mailCAF.setText("");
				phoneCAF.setText("");
				addressCAF.setText("");
				photoCAF.setText("");
				medicalCAF.setText("");
				feeCAF.setText("");
				courseCAF.setText("");
				welcomePage.setVisible(true);
			}
		});
		button_9.setBounds(10, 437, 89, 23);
		CourseAppl.add(button_9);
	}
}

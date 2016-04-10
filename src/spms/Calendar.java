package spms;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class Calendar {

	public JFrame frame;
	private JPanel dayPane;
	private JPanel daysPanel;
	private JPanel daysPane;
	private JPanel dayPanel;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calendar window = new Calendar();
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
	public Calendar() {
		initialize();
		setDates();
	}

	public void setDates()
	{
		Database database=new Database();
		ResultSet rSet=database.Query("SELECT * FROM `slots` WHERE `hour` = 0");
		try {
			int i=0;
			while(rSet.next()) {
				JButton button=new JButton("Week: "+(i/7)+" "+rSet.getString("date"));
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton button=(JButton)e.getSource();
						String date=(String)button.getClientProperty("date");
						Database database=new Database();
						ResultSet rrrSet = database.Query("SELECT * FROM `slots` WHERE `date` = '"
								+java.sql.Date.valueOf(date)+"'");
						try {
							String number="";
							while(rrrSet.next())
							{
								number=rrrSet.getString("number");
								if(number==null||number.equals("NULL")) number="0";
								JLabel label=new JLabel("Slot: "+String.format("%02d",Integer.parseInt(rrrSet.getString("hour")))+":00-"
										+String.format("%02d",(int)(Integer.parseInt(rrrSet.getString("hour"))+1))+":00."
												+ "  No. of bookings for the slot = "
										+number+".  Event(if any): "+rrrSet.getString("eventID")
										+".  Booking(if any): "+rrrSet.getString("bookingID")+" ");
								dayPanel.add(label);
							}
						} 
						catch (SQLException e1) {
							e1.printStackTrace();
						}
						dayPane.setVisible(true);
						daysPane.setVisible(false);
						database.disconnect();
					}
				});
				button.putClientProperty("date", rSet.getString("date"));
				daysPanel.add(button);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		daysPane.setVisible(true);
		dayPane.setVisible(false);
		database.disconnect();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setTitle("SPMS Calender");
		
		dayPane = new JPanel();
		frame.getContentPane().add(dayPane, "name_23170623871414");
		dayPane.setLayout(null);
		
		dayPanel = new JPanel();
		dayPanel.setBounds(10, 11, 764, 439);
		dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(dayPanel);
		scrollPane.setBounds(10, 11, 764, 360);
		dayPane.add(scrollPane);
		
		button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayPanel.removeAll();
				dayPanel.updateUI();
				dayPane.setVisible(false);
				daysPanel.removeAll();
				daysPanel.updateUI();
				setDates();
				daysPane.setVisible(true);
			}
		});
		button_1.setBounds(10, 382, 89, 23);
		dayPane.add(button_1);
		
		daysPane = new JPanel();
		frame.getContentPane().add(daysPane, "name_4689653066295");
		daysPane.setLayout(null);
		
		daysPanel = new JPanel();
		daysPanel.setBounds(10, 11, 764, 439);
		
		JScrollPane scrollPane_1 = new JScrollPane(daysPanel);
		daysPanel.setLayout(new GridLayout(0, 4, 5, 5));
		scrollPane_1.setBounds(10, 11, 764, 394);
		daysPane.add(scrollPane_1);
	}
}

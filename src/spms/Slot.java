package spms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import user.User;

public class Slot {
	public LocalDateTime date;
	public String eventID;
	public String bookingID;
	public ArrayList<User> users;
	public int maxNum;
	
	public boolean changeMax()
	{
		return true;
	}
	
	public boolean addMem()
	{
		return true;
	}
}

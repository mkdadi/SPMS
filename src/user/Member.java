package user;

import spms.Discussion;

/**
 * 
 * @author Madhu Kumar Dadi
 *
 */
public class Member extends User{
	public String emailID;
	
	public Member() {
	}
	
	public boolean cancelMembership()
	{
		return true;
	}
	
	public boolean payFee()
	{
		return true;
	}
	
	public boolean bookSlots()
	{
		return true;
	}
	
	public boolean makePost()
	{
		return true;
	}
	
	public Discussion startDiscussion()
	{
		return new Discussion();
	}
	
	public boolean makeComplaint()
	{
		return true;
	}
	
	public boolean bookPool()
	{
		return true;
	}
}

/**
 * 
 */
package user;

import spms.Discussion;

/**
 * @author Madhu Kumar Dadi
 *
 */
public class CommitteeMember extends User{
	
	public boolean makePost() {
		return true;
	}
	
	public boolean makeNotice()
	{
		return true;
	}
	
	public Discussion startDiscussion()
	{
		return new Discussion();
	}
	
	public boolean viewComplaints()
	{
		return true;
	}
}

package user;

/**
 * This is the base class for Members, Students, Participants and Committee Members
 * 
 * @author Madhu Kumar Dadi
 *
 */
public class User {
	public String id;
	private String password;
	public String Name;
	
	public User() {
	}
	
	String getPass()
	{
		String temp=this.password;
		return temp;
	}
}

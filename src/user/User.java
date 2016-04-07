package user;

/**
 * This is the base class for Members, Students, Participants and Committee Members
 * 
 * @author Madhu Kumar Dadi
 *
 */
public class User {
	public int id;
	protected String password;
	public String Name;
	
	public User() {
	}
	
	public String getPass()
	{
		String temp=this.password;
		return temp;
	}
}

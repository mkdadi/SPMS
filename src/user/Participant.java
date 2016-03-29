package user;

/**
 * 
 * @author Madhu Kumar Dadi
 *
 */
public class Participant extends User{
	public String emailID;
	public String eventID;
	
	/**
	 * 
	 */
	public Participant() {
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean makeComplaint()
	{
		return true;
	}
}

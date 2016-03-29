package user;

import spms.Course;

/**
 * 
 * @author Madhu Kumar Dadi
 *
 */
public class Student extends User{
	public String emailID;
	public Course course;
	
	public Student() {
	}
	
	/**
	 * 
	 * @return return true if there is no error
	 */
	public boolean makeComplaint()
	{
		return true;
	}
}

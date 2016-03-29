/**
 * 
 */
package spms;

import java.util.Date;
import java.time.Duration;
import java.util.ArrayList;

import user.Student;

/**
 * @author Madhu Kumar Dadi
 *
 */
public class Course {
	public String courseID;
	public ArrayList<Student> students;
	public Date start;
	public Duration duration;
	
	public boolean addMember()
	{
		return true;
	}
}

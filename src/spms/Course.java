/**
 * 
 */
package spms;

import java.time.LocalDate;
import java.util.ArrayList;

import user.Student;

/**
 * @author Madhu Kumar Dadi
 *
 */
public class Course {
	public String Name;
	public String ID;
	public int coordinatorID;
	public ArrayList<Student> students;
	public LocalDate start;
	public int duration;
	public int fee;
	
}

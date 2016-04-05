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
	
	public boolean addMember()
	{
		return true;
	}
	
	public void add()
	{
		Database db=new Database();
		db.Update("INSERT INTO courses VALUES('"+this.ID+"','"+this.Name+"','"+this.coordinatorID+"','"
				+java.sql.Date.valueOf(this.start)+"','"+this.duration+"','"+this.fee+"',NULL)");
		db.disconnect();
	}
}

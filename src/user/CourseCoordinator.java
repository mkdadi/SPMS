/**
 * 
 */
package user;

import spms.Course;

/**
 * author Madhu Kumar Dadi
 *
 */
public class CourseCoordinator extends CommitteeMember{
	public Course course;
	
	public boolean rescheduleClass()
	{
		return true;
	}
	
	public boolean cancelClass()
	{
		return true;
	}
	
	public boolean extraClass()
	{
		return true;
	}
	
	public boolean suspendCourse()
	{
		return true;
	}
}

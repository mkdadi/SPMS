package user;

import java.util.Random;

import spms.Course;
import spms.Database;
import spms.Mail;

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
	public void setPassword() {
		String pass="";
		int i;
		Random random=new Random();
		for(int j=0;j<9;j++)
		{
			i= random.nextInt(27)+'A';
			pass+=(char)i;
		}
		Database database=new Database();
		database.Update("UPDATE students SET `password` = '"+pass+"' WHERE `id` = '"+this.id+"'");
		Mail mail=new Mail();
		mail.to.add(this.emailID);
		mail.subject="SPMS Credentials";
		mail.message="Your credentials for the Swimming Pool Management Software are as below:\nID: "+this.id
				+"\nPassword: "+pass+"\n\nHave a happy Swimming Course.\n--\nManager,\nSPMS.";
		try {
			mail.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

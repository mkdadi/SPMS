package user;


import java.util.Random;

import spms.Database;
import spms.Discussion;
import spms.Mail;

/**
 * 
 * @author Madhu Kumar Dadi
 *
 */
public class Member extends User{
	public String emailID;
	
	public Member() {
	}
	
	public boolean setPassword()
	{
		String pass="";
		int i;
		Random random=new Random();
		for(int j=0;j<9;j++)
		{
			i= random.nextInt(27)+'A';
			pass+=(char)i;
		}
		Database database=new Database();
		database.Update("UPDATE member SET `password` = '"+pass+"' WHERE `id` = '"+this.id+"'");
		Mail mail=new Mail();
		mail.to.add(this.emailID);
		mail.subject="SPMS Credentials";
		mail.message="Your credentials for the Swimming Pool Management Software are as below:\nID: "+this.id
				+"\nPassword: "+pass+"\n\nHave a happy Swim.\n--\nManager,\nSPMS.";
		try {
			mail.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean payFee()
	{
		return true;
	}
	
	public boolean bookSlots()
	{
		return true;
	}
	
	public boolean makePost()
	{
		return true;
	}
	
	public Discussion startDiscussion()
	{
		return new Discussion();
	}
	
	public boolean makeComplaint()
	{
		return true;
	}
	
	public boolean bookPool()
	{
		return true;
	}
}

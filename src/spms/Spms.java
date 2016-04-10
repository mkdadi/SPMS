package spms;

public class Spms {
	
	public static WelcomePage window;
	
	//Database Data
	public static String dbHost=<host address>;
	public static int dbPort=3306;
	public static String dbUser=<db User>;
	public static String dbPassword=<db User Password>;
	public static String dbName=<db Name>;
	
	//Mail Data
	public static String managerMailID=<Mail ID>;
	public static String managerMailHost=<mail SMTP HOST>;
	public static String managerMailFrom=<Mail ID u want others to see as>;
	public static String managerMailPass=<Mail ID Password>;
	
	//FTP Data
	public static String FTPHost=<FTP HOST>;
	public static int FTPPort=21;
	public static String FTPUser=<FTP USER>;
	public static String FTPPass=<ftp Password>;
	
	public static boolean checkMail(String mail)
	{
		String[] submail;
		if(mail!=null&&mail.contains("@"))
		{
			submail=mail.split("@");
			if (submail.length==2) {
				if(submail[1].contains("."))
				{
					return true;
				}
				else {
					WarningBox warningBox=new WarningBox("Not a Valid Mail!");
					warningBox.setVisible(true);
					return false;
				}
			}
			else {
				WarningBox warningBox=new WarningBox("Not a Valid Mail!");
				warningBox.setVisible(true);
				return false;
			}
		}
		else {
			WarningBox warningBox=new WarningBox("Not a Valid Mail!");
			warningBox.setVisible(true);
			return false;
		}
	}
	
	public static boolean checkPhone(String phone)
	{
		if(phone.length()>9&&phone.length()<15)
		{
			for(int i=0;i<phone.length();i++)
			{
				if(!(phone.charAt(i)<='9'&&phone.charAt(i)>='0')&&!(phone.charAt(i)=='+'&&i==0)){
					WarningBox warningBox=new WarningBox("Not a Valid Phone Number!");
					warningBox.setVisible(true);
					return false;
				}
			}
			return true;
		}
		else {
			WarningBox warningBox=new WarningBox("Not a Valid Phone Number!");
			warningBox.setVisible(true);
			return false;
		}
	}
}

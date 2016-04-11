package spms;

public class Spms {
	
	public static WelcomePage window;
	
	//Database Data
	public static String dbHost=<db host>;
	public static int dbPort=3306;
	public static String dbUser=<db user>;
	public static String dbPassword=<db user password>;
	public static String dbName=<db name>;
	
	//Mail Data
	public static String managerMailID=<email ID>;
	public static String managerMailHost=<mail SMTP HOST>;
	public static String managerMailFrom=<mail to show>;
	public static String managerMailPass=<mail pass>;
	
	//FTP Data
	public static String FTPHost=<FTP HOST>;
	public static int FTPPort=21;
	public static String FTPUser=<USER>;
	public static String FTPPass=<PASSWORD>;
	
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

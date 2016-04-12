package spms;

public class Spms {
	
	public static WelcomePage window;
	
	//Database Data
	public static String dbHost=<db host>;
	public static int dbPort=3306;
	public static String dbUser=<db user>;
	public static String dbPassword=<db password>;
	public static String dbName="spms";
	
	//Mail Data
	public static String managerMailID=<mail ID>;
	public static String managerMailHost=<mail Host>;
	public static String managerMailFrom=<mail shown as From>;
	public static String managerMailPass=<mail Password>;
	
	//FTP Data
	public static String FTPHost=<ftp Host>;
	public static int FTPPort=21;
	public static String FTPUser=<ftp user>;
	public static String FTPPass=<ftp pass>;
	
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

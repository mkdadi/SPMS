package spms;

public class MainWindow {

	public static void main(String[] args) throws Exception {
		
		//MAIL TEST
		/*
		Mail mail=new Mail();
		mail.to.add(new String("kumardadi100@gmail.com"));
		mail.to.add(new String("kumardadi.9@gmail.com"));
		mail.subject="Testing Original!";
		mail.message="Nothing to Worry, its working :D .";
		mail.send();
		*/
		
		//DB TEST
		/*
		Database db=new Database();
		db.Update("DROP TABLE test");
		db.Query("SELECT password from login where id = 1");
		db.disconnect();
		*/
		
		//FTP TEST
		/*
		FTPTransfer ftpTransfer=new FTPTransfer();
		ftpTransfer.uploadFile="C:/Users/Madhukumar/Downloads/connector-j.pdf";
		ftpTransfer.uploadTo="/spms/test/sample.pdf";
		ftpTransfer.upload();
		ftpTransfer.downloadFrom="/spms/test/sample.pdf";
		ftpTransfer.downloadTo="http.pdf";
		ftpTransfer.download();
		ftpTransfer.disconnect();
		*/
	}
}

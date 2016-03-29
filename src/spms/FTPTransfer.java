/**
 * 
 */
package spms;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * @author Madhu Kumar Dadi
 *
 */
public class FTPTransfer {
	
	public String uploadFile;
	public String uploadTo;
	public String downloadTo;
	public String downloadFrom;
	private FTPClient ftpClient;
	
	public FTPTransfer() {
		ftpClient = new FTPClient();
        try {
			ftpClient.connect(Spms.FTPHost, Spms.FTPPort);
			ftpClient.login(Spms.FTPUser, Spms.FTPPass);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        }
        catch(IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
	}
	
	public boolean upload()
	{
        try {
            InputStream inputStream = new FileInputStream(uploadFile);
            boolean done = this.ftpClient.storeFile(uploadTo, inputStream);
            inputStream.close();
            if (done) {
                return true;
            }
            else{
            	return false;
            }
        }
        catch(IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
	}
	
	public boolean download() throws Exception
	{
        File downloadFile1 = new File(downloadTo);
        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
        boolean success = this.ftpClient.retrieveFile(downloadFrom, outputStream1);
        outputStream1.close();
        if (success) 
        {
        	return true;
        }
        else
        {
        	return false;
		}
	}
	
	public boolean disconnect()
	{
		if(this.ftpClient.isConnected())
    	{
    		try {
				this.ftpClient.logout();
	    		this.ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
    	}
		return true;
	}
}
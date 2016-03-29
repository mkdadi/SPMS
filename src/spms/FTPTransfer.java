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
	
	public FTPTransfer() {
	}
	
	public boolean upload()
	{
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(Spms.FTPHost, Spms.FTPPort);
            ftpClient.login(Spms.FTPUser, Spms.FTPPass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            InputStream inputStream = new FileInputStream(uploadFile);
            boolean done = ftpClient.storeFile(uploadTo, inputStream);
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
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
	}
	
	public boolean download() throws Exception
	{
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect(Spms.FTPHost, Spms.FTPPort);
        ftpClient.login(Spms.FTPUser, Spms.FTPPass);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        File downloadFile1 = new File(downloadTo);
        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
        boolean success = ftpClient.retrieveFile(downloadFrom, outputStream1);
        outputStream1.close();
        if (success) 
        {
        	if(ftpClient.isConnected())
        	{
        		ftpClient.logout();
        		ftpClient.disconnect();
        	}
        	return true;
        }
        else
        {
        	if(ftpClient.isConnected())
        	{
        		ftpClient.logout();
        		ftpClient.disconnect();
        	}
        	return false;
		}
	}
}
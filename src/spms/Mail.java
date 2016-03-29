package spms;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.smtp.SMTPTransport;

/**
 * 
 * @author Madhu Kumar Dadi
 *
 */
public class Mail {
	public ArrayList<String> to;
	public String subject;
	public String message;
	
	public Mail() {
		this.to=new ArrayList<String>();
	}
	
	public boolean send() throws Exception
	{
		Properties props = System.getProperties();
        props.put("mail.smtps.host",Spms.managerMailHost);
        props.put("mail.smtps.auth","true");
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(Spms.managerMailFrom));
        InternetAddress[] address = new InternetAddress[to.size()];
        for (int i = 0; i < to.size(); i++) {
            address[i] = new InternetAddress(to.get(i));
        }
        msg.setRecipients(Message.RecipientType.TO,address);
        msg.setSubject(subject);
        msg.setText(message);
        msg.setHeader("X-Mailer", "MKDADI's rules");
        msg.setSentDate(new Date());
        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
        t.connect(Spms.managerMailHost, Spms.managerMailID, Spms.managerMailPass);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
		return true;
	}
}

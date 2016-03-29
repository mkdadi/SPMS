/**
 * 
 */
package spms;

import java.util.Date;
import java.time.Duration;
import java.util.ArrayList;
import application.Participant;

/**
 * @author Madhukumar
 *
 */
public class Event {
	public String eventID;
	public ArrayList<Participant> participants;
	public Date start;
	public Duration duration;
	
	public boolean addParticipant()
	{
		return true;
	}
}

/**
 * 
 */
package spms;

import java.util.Date;
import java.time.Duration;
import java.util.ArrayList;
import application.ParticipantApplic;

/**
 * @author Madhukumar
 *
 */
public class Event {
	public String eventID;
	public ArrayList<ParticipantApplic> participants;
	public Date start;
	public Duration duration;
	
	public boolean addParticipant()
	{
		return true;
	}
}

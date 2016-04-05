/**
 * 
 */
package spms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import application.ParticipantApplic;

/**
 * @author Madhukumar
 *
 */
public class Event {
	public String name;
	public String ID;
	public ArrayList<ParticipantApplic> participants;
	public LocalDateTime start;
	public int duration;
	public int fee;
	
	public boolean addParticipant()
	{
		return true;
	}
}

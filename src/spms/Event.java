/**
 * 
 */
package spms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import application.ParticipantApplic;
import user.EventManager;

/**
 * @author Madhukumar
 *
 */
public class Event {
	public EventManager manager;
	public String name;
	public String ID;
	public ArrayList<ParticipantApplic> participants;
	public LocalDateTime start;
	public int duration;
	public int fee;
	
}

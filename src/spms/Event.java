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
	
	public boolean addParticipant()
	{
		return true;
	}

	public void add() {
		Database db=new Database();
		db.Update("INSERT INTO events VALUES('"+this.ID+"','"+this.name+"','"+this.manager.id+"','"
				+java.sql.Timestamp.valueOf(this.start)+"','"+this.duration+"','"+this.fee+"',NULL)");
		db.disconnect();
	}
}

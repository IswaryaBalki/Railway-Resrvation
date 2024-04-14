package Railway;
import java.time.LocalDate;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Booking 
{
    private Date booked_Date;
    private String booked_By;
	private Schedule schedule;
	private String from;
	private String to;
	private String quota;
	private Ticket pnr;
	private Train booked_Train;
	private LinkedList<Passenger>passengers;
	
	
    public Booking(Schedule schedule, String from, String to, Ticket pnr,Train booked_Train) {
    	this.pnr=pnr;
    	this.schedule=schedule;
    	this.from=from;
    	this.to=to;
    	this.booked_Train=booked_Train;
	}
	public Booking() {
	}
	public Booking(String from, String to, Date booked_date, String quota, LinkedList<Passenger> passengers,Train booked_train) 
	{
		this.from=from;
		this.to=to;
		this.booked_Date=booked_date;
		this.quota=quota;
		this.passengers=passengers;
		this.booked_Train=booked_train;
	}
	public Train getTrain() {
		return booked_Train;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public String getQuota()
	{
		return quota;
	}
	public Date getDate() {
		return booked_Date;
	}
	public String getBookedBy() {
		return booked_By;
	}
	public Ticket getTicket() {
		return pnr;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public LinkedList<Passenger> getPassengers()
	{
		return passengers;
	}
}
 
package Railway;
import java.sql.Date;
import java.time.*;
public class Schedule
{
    private static Schedule instance;
	private LocalTime arrivalTime;
    private Date departure_date;
    private Date Arrival_date;
    private String departure_day;
    private String arrival_day;
    //private LocalDate date;
    //private ArrayList<Schedule> datesList;
    Schedule(){}
    Schedule(Date  departure_date,Date arrival_date,String departure_day,String arrival_day)
    {
    	this.departure_date=departure_date;
    	this.Arrival_date=arrival_date;
    	this.departure_day=departure_day;
    	this.arrival_day=arrival_day;	
    	
    }
    public Schedule(LocalTime arrivalTime)
    {
        this.arrivalTime=arrivalTime;
    }
    public Schedule(Date date, String day) {
		departure_date=date;
		departure_day=day;
	}
	public LocalTime getArrivalTime()
    {
        return arrivalTime;
    }
    public Date getDepartureDate()
    {
    	return departure_date;
    }
    public Date getArrivalDate()
    {
    	return departure_date;
    }
    public String getDepartureDay()
    {
    	return departure_day;
    }
    public String getArrivalDay()
    {
    	return departure_day;
    }
    public static Schedule getInstance() {
		// TODO Auto-generated method stub
		if(instance==null)
			instance=new Schedule();
		return instance;
	} 


      
}

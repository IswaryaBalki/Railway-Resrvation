package Railway;

public class Coach {

	 private String number;
	    private String type;
	    private int totalSeats;
	    private int availableSeats;
	    private Seat seats;
	    public Coach(String type,String number, Seat seats)
	    {
	        this.number=number;
	        this.type=type;
	        this.seats=seats;
	    }
	    public Coach(String type,int availableSeats)
	    {
	    	this.type=type;
	    	 this.availableSeats=availableSeats;
	    }
	    public String getNumber()
	    {
	        return number;
	    }
	    public String getCoachType()
	    {
	        return type;
	    }
	    public int getAvailables()
	    {
	        return availableSeats;
	    }
	   
	    public Seat getSeats()
	    {
	        return seats;
	    }
	   
	    public int getTotalSeats()
	    {
	        return totalSeats;
	    }
}

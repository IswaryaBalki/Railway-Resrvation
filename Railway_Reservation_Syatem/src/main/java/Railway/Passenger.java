package Railway;
import java.time.LocalDate;
import java.time.Period;  
import java.util.Date;
public class Passenger
{
    private int id;
    private  int age;
    private String name;
    private String gender;
    private static  Passenger passenger=null;
      
    public Passenger(){}
    Passenger(String name,int age,String gender)
    {
       
        this.name=name;
        this.age=age;
        this.gender=gender;
    }
    Passenger(int id)
    {
    	this.id=id;
    }
    public String getPassengerName()
    {
        return name;
    }
    public String getPassengerGender()
    {
        return gender;
    }
    public int getAge()
    {
    	return age;
    }
    public int getPassengerId()
    {
    	return id;
    }
     public static Passenger getInstance()
     {
        if(passenger==null)
        passenger=new Passenger();
        return passenger;
     }
	public int calculateAge(LocalDate dob) {
	
		return Period.between(dob, LocalDate.now()).getYears();
	}
     
}
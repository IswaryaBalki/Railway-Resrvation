package Railway;

import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class User
{
	private static int id;
    private String name="";
    private String email="";
    private String password="";
    private long phoneNo=0;
   // private static HashMap<Integer,LinkedList<Ticket> > bookings=new HashMap<Integer,LinkedList<Ticket>>();

    User(int id){
    	this.id=id;
    }
    User(String email)
    {
        this.email=email;
       
    }
    User(int id,String name,String email,String password,long phoneNo)
    {
    	this.id=id;
        this.email=email;
        this.name=name;
        this.password=password;
        this.phoneNo=phoneNo;
        // this.bookings=new HashMap<>();
    }
    public String getPassword()
    {
        return password;
    }
    public String getName()
    {
        return name;
    }
    public static int getId() {
		return id;
	}
    /*public HashMap<Integer,LinkedList<Ticket>> getBookings()
    {
        return bookings;
    }*/
    public String getEmail()
    {
        return email;
    }
   
    public static boolean validName(String name)
    {
        // for(int i=0;i<name.length();i++)
        // {
        //     if(!(name.charAt(i)<=65 && name.charAt(i)>=91)||!(name.charAt(i)<=99 && name.charAt(i)>=122))
        //     return false;
        //   return true;
        // }    
        Pattern p=Pattern.compile("[a-zA-z\\s]+");
        Matcher m=p.matcher(name);
        return  m.matches();

    }
    public static boolean validEmailId(String email)
    {
        boolean isvalid=Pattern.matches("[a-zA-z\\s]+", email);
        return  isvalid;

    }
    public static boolean validPhoneNo(String phoneNo)
    {
        Pattern p=Pattern.compile("[6-9][0-9]{9}");
        Matcher m=p.matcher(phoneNo);
        return m.matches(); 
    }
    public static boolean validPassword(String password)
    {
        boolean isvalid=Pattern.matches("[a-zA-z0-6]+{8}", password);
        return  isvalid;

    }
    public static void myBookings()
    {
    	LinkedList<Booking> bookings = null;
    	try 
    	{
			 bookings=DataManager.getInstance().getMyBookings();
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	if(bookings!=null)
    	{
    		for(Booking bookingList:bookings)
        	{
    			System.out.println("************************************************************************");
    			System.out.print("\t"+bookingList.getTrain().getTrainName());
    			System.out.print("   "+bookingList.getTrain().getTrainNum());
    			System.out.println("   \t"+bookingList.getTicket().getPnr());
    			System.out.println("\t"+bookingList.getFrom()+"------>"+bookingList.getTo());
    			System.out.print(bookingList.getSchedule().getDepartureDay()+" "+bookingList.getSchedule().getDepartureDate());
    			System.out.println();
    			System.out.println("*************************************************************************");
        	}
    		
    	}
    	

        

    }
	
} 
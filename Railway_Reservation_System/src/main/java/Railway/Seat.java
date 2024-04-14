package Railway;
import java.util.HashMap;
import java.util.ArrayList;
public class Seat 
{
    private int number;
    private String berthType;
    private String status;
    private String type;
    private static Seat seat;
    private Seat(){}
    public String getSeatPosition()
    {
        return berthType;
    }
    public String getStatus()
    {
        return status;
    } 
    public void setStatus(String status)
    {
        this.status=status;
    } 
    public int getNumber()
    {
        return number;
    }   
    public static Seat getInstance()
    {
        if(seat==null)
            seat=new Seat();
     return seat;
    }
    public Seat(int number,String type,String berthType)
    {
        this.number=number;
        this.type=type;
        this.berthType=berthType;
    }
	public Seat(int number, String berthtype) {
	this.number=number;
      this.type=type;
	}
	
    
    
}

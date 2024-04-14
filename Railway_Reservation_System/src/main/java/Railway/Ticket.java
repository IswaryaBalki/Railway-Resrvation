package Railway;
import java.sql.SQLException;
import java.time.*;
import java.util.*;     
public class Ticket{

        private  long pnr;
        private String status;
		private Booking booking;
        private static Ticket instance;
        
        Ticket(){}
        Ticket(long pnr)
        {
        	this.pnr=pnr;
        }       
        public Ticket(long pnr, String status, Booking booking)
        {
        	this.pnr=pnr;
        	this.status=status;
        	this.booking=booking;
        }
		public long getPnr()
        {   
            return pnr;
        }
        public String getStatus()
        {
            return status;
        }
        public Booking getBooking()
        {
            return booking;
        }
        public static Ticket getInstance()
        {
        	if(instance==null)
        		instance=new Ticket();
        	return instance;
        }
        
}
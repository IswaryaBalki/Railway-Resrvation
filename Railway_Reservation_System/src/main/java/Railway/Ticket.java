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
        public static Ticket getInstance()
        {
        	if(instance==null)
        		instance=new Ticket();
        	return instance;
        }
		public void viewTicket(long pnr) {
			try 
			{
				System.out.println("--------------------------------------------------------------------------------------");
				Ticket ticket=DataManager.getInstance().getMyTicket(pnr);
				System.out.println("From \t\t\t\t  To");
				System.out.println(ticket.booking.getFrom()+"---------------->"+ticket.booking.getTo());
				System.out.print(ticket.booking.getTrain().getSchedule().getDepartureDate()+" "+ticket.booking.getTrain().getSchedule().getDepartureDay());
				System.out.println("\t\t\t"+ticket.booking.getTrain().getSchedule().getArrivalDate() +" "+ticket.booking.getTrain().getSchedule().getArrivalDay());
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("PNR\t\t\tTrainNo/Name\t\t\tClass");
				System.out.print(ticket.pnr);
				System.out.print("\t\t"+ticket.booking.getTrain().getTrainNum()+"/"+ticket.booking.getTrain().getTrainName());
				System.out.println("\t\t"+ticket.booking.getTrain().getCoach().getCoachType());
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("Quota\t\t\tBooking Date");
				System.out.print(ticket.booking.getQuota());
				System.out.println("\t\t\t"+ ticket.booking.getDate());
				System.out.println("--------------------------------------------------------------------------------------");

				System.out.println();
				
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			
		}
		public void cancelTicket() {
			System.out.println("Enter The Pnr for Cancel Ticket");
			Scanner sc=new Scanner(System.in);
			Long pnrNum=sc.nextLong();
			long count=0;
			try {
				count = DataManager.getInstance().getTicketCount(pnrNum);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			System.out.println(count);
			if(count==1)
			{
				try {
					DataManager.getInstance().cancelTicket(pnrNum);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Ticket Canceled Successfully");
			}
			else if(count>1)
			{
				int passengerId;
				System.out.println("Enter the Passenger Id");
				passengerId=sc.nextInt();	
				try
				{
					DataManager.getInstance().cancelTicket(pnrNum,passengerId);
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				System.out.println("Ticket Canceled Successfully");
			}
			else
			{
				System.out.println("INVALID PNR");
			}
			
		}
		
}
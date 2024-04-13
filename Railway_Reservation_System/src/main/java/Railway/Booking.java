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
}
//	public void bookTicket(User user) throws SQLException
//    {
//    	ArrayList<Train> availables=new ArrayList<Train>();
//    	ArrayList<String> fromList=new ArrayList<>();
//    	
//        Random random=new Random();
//        long pnr=random.nextInt(900000000) + 1000000000;
//        System.out.println(pnr);
//        Scanner scan=new Scanner(System.in);
//        System.out.println("Enter the  Date");
//        String journeyDate=scan.nextLine();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
//        LocalDate date=LocalDate.parse(journeyDate,formatter);
//        fromList=DataManager.getInstance().getFrom(date);
//        int count=0;
//        for(String from:fromList)
//        {
//        	System.out.println(++count +" "+ from);
//        }
//        count=0;
//        System.out.println("Select the From Station");
//        int fromOption=scan.nextInt();
//        String from=fromList.get(fromOption-1);
//        ArrayList<String> toList=DataManager.getInstance().getTo(date,from);
//        for(String to:toList)
//        {
//        	System.out.println(++count+" "+ to);
//        }
//        System.out.println("Select the To Station");
//        int toOption=scan.nextInt();
//        String to=toList.get(toOption-1);
//        scan.nextLine();
//        System.out.println(from+" "+to);
//        System.out.println("Enter your Quota");
//        Quota quota=Quota.valueOf(scan.nextLine().toUpperCase());
//        try 
//        {
//			availables=DataManager.getInstance().searchAvailables(date,from,to);
//		}
//        catch (SQLException e1) 
//        {
//			e1.printStackTrace();
//		}
//        if(!availables.isEmpty())
//        {
//            for(Train trains:availables)
//            {
//                System.out.println("***********************************************************");
//                System.out.println("Train Number : "+trains.getTrainNum());
//                System.out.println("Train Name : "+trains.getTrainName());
//                System.out.print("\t From : "+trains.getStation().getFrom());
//                System.out.println("\t\t\t To : "+trains.getStation().getTo());
//                System.out.print("\t Departure Date : "+trains.getSchedule(). getDepartureDate()+"  "+trains.getSchedule(). getDepartureDay());
//                System.out.println("\t Arrival Date : "+trains.getSchedule(). getArrivalDate()+" "+trains.getSchedule(). getArrivalDay());
//                //takal reffer previous
//                for(Coach coaches:trains.getCoaches())
//                {
//                	System.out.print("\t"+coaches.getCoachType()+"  AVL-"+coaches.getAvailables());
//                }
//                System.out.println();
//            
//                System.out.println("***********************************************************");
//                System.out.println();
//            }
//            System.out.println("Enter the Train Number");
//            int train_num=scan.nextInt();
//            scan.nextLine();
//            System.out.println("Enter the Class");
//            String type=scan.nextLine();
//            ClassType classType=ClassType.valueOf(type.toUpperCase());
//            Train selectedTrain=null;
//            for(Train train:availables)
//            {
//            	if(train_num==train.getTrainNum())
//            	{
//            		for(Coach coach:train.getCoaches())
//            		{
//            			if(type==coach.getCoachType())
//            			{
//            				selectedTrain=train;
//            			}
//            		}		
//            	}
//            }
//            Ticket ticketNum=new Ticket(pnr); 
//            System.out.println("Enter the No.of Tickets want to book");
//            int no_Of_Tickets=scan.nextInt();
//            scan.nextLine();
//            DataManager.getInstance().addTickets(pnr);
//
//           
//                        for(int passenger=0;passenger<no_Of_Tickets;passenger++)
//                        {
//                            System.out.println("Enter the Passenger Name \n Format YYYY-MM-DD" );
//                            String name=scan.nextLine();
//                            System.out.println();
//                            System.out.println("Enter the D.O.B");
//                            String birthDate=scan.nextLine();
//                            LocalDate dob=LocalDate.parse(birthDate);
//                            int age=Passenger.getInstance().calculateAge(dob);
//                            System.out.println("age "+age);
//                            System.out.println("Enter the Gender");
//                            String gender=scan.nextLine();
//                            Passenger passengerInfo = new Passenger(name,age, gender);
//                            DataManager.getInstance().addPassenger(passengerInfo,pnr);
//                            Seat seat=DataManager.getInstance().allocateSeat(train_num,type,date);
//                            System.out.println(seat.getNumber());
//                            DataManager.getInstance().addBookings(pnr,seat,from,to,quota.toString().toUpperCase());
//
//                        } 
//                        
////                                
////                            ticketsTemp.add(ticket);
////                        }
////                                
////                    }
////
////                }
////            }
////            DataManager.getInstance().addTickets(pnr,ticketsTemp);
////            System.out.println("************************ BOOKED SUCESSFULLY ************************"); 
//        }
//        else
//        {
//            System.out.println("************************NO AVAILABLES***********************");
//        }

//    }

//}
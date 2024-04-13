package Railway;

import java.util.*;
// import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.regex.*;
import java.io.*;
import java.sql.SQLException;
public class RailwayReservation
{
    private static Train trainObj=new Train();
    private static RailwayReservation railway=new RailwayReservation();
    public void userRegistration()
    {
    	DataManager db=new DataManager();
        boolean flag=true;
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the Name");
        String name=scan.nextLine();
        System.out.println("Enter the EmailId");
        String email=scan.nextLine();
        System.out.println("Enter the Password");
        String password=scan.nextLine();
        System.out.println("Enter the Phone number");
        long phoneNo=scan.nextLong();   
        boolean user=false;
        try {
			user =db.addUser(name,email,password,phoneNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        if(!user)
        {
        	System.out.println("..........User Added Successfully...........");
        }
        else
        {
        	System.out.println("..........User Already Exist...........");
        	railway.userRegistration();
        }
    }
    
   
    
    public void signIn()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Email Id");
        String mail=sc.nextLine();
        System.out.println("Enter the Password");
        String password=sc.nextLine();
        User user=new User(mail);
        boolean validUser=false;
        
       
		try {
			validUser = DataManager.getInstance().authenticateUser(mail,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        if(validUser)
        {
        	railway.userOptions(user);  
        }
        else
        {
            System.out.println("...........Invalid Email or Password.........");
            railway.signIn();
        }  
    }
  

    private void adminOptions() {
    	Scanner scan=new Scanner(System.in);
    	 while(true)
         {
                 System.out.println("Select your Option");
                 System.out.println(" 1.ADD TRAIN \n 2.REMOVE TRAIN \n 3.EXIT");
                 int option=scan.nextInt();
                 switch(option)
                 {
                     case 1:
                           
                     case 2:
                          

                     case 3:
                        return; 
                 }
         }
		
		
	}



	public  void userOptions(User currentUser)
    {
        Scanner scan=new Scanner(System.in);
        Booking b=new Booking();
        Ticket ticket=new Ticket();
        while(true)
        {
                System.out.println("Select your Option");
                System.out.println(" 1.MY BOOKINGS \n 2.BOOK TICKET \n 3.CANCEL TICKET \n 4.VIEW TICKET \n 5.PNR Status \n 6.EXIT");
                int option=scan.nextInt();
                switch(option)
                {
                    case 1:
                            User.myBookings();
                            break;
                    case 2:
//					try
//					{
////						b.bookTicket(currentUser);
//					}
//					catch (SQLException e1) 
//					{
//						e1.printStackTrace();
//					}
//					break;
					
                    case 3:
                    	Ticket.getInstance().cancelTicket();
                        break;
                    case 4:
                        System.out.println("Enter PNR");
                        long pnr=scan.nextLong();
                        Ticket.getInstance().viewTicket(pnr);
                        
                    	break;
                    case 5:     
                          return;
                }
        }
            
        
    }   

    public static void main(String args[])
    { 
        Scanner scan=new Scanner(System.in);
        
        while(true)
        {
            System.out.println("Enter the Choose:\n 1.Signup \n 2.Signin \n 3.Exit");
            int choice=scan.nextInt();
            switch(choice)
            {
                case 1:
                    railway.userRegistration();
                    // System.out.println(railway.userDetails);
                    break;
                case 2:
                   railway.signIn();
                    break;
                case 3:
                    return;        
                default:
                     System.out.println("..............Enter the Valid Choice...........");
            }
        }

    }
}


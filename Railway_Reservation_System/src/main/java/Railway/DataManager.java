package Railway;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class DataManager {
	private static DataManager instance;
	public static DataManager getInstance()
	{
		if(instance==null)
			instance=new DataManager();
		return instance;
	}
	public boolean addUser(String name,String email,String password,long phoneNo) throws SQLException 
    {
        //User users=new User(email,name,password,phoneNo);
		
        DBConnection connection=DBConnection.getInstance();
        Connection con=connection.getConnection();
        PreparedStatement statement;
        boolean userExist=true;
        ResultSet rs = null;
        String query="select email from userProfile where email=?";
        statement = con.prepareStatement(query);
		statement.setString(1, email);
		rs = statement.executeQuery();
		if (rs.next()) {
			userExist = false;
		}
		rs.close();
		if(userExist)
		{
			
	            String insertQuery = "INSERT INTO userProfile(name, email, password, phone_num) VALUES (?, ?, ?, ?)";
	            statement = con.prepareStatement(insertQuery);
	            statement.setString(1, name);
	            statement.setString(2, email);
	            statement.setString(3, password);
	            statement.setLong(4, phoneNo);
	            statement.executeUpdate();
	        
	             if (statement != null) 
	             {
	                statement.close();
	            }	
		}
		
		
		return userExist;

    }
	public boolean authenticateUser(String mail,String password) throws SQLException
	{
		DBConnection db=DBConnection.getInstance();
		Connection con=db.getConnection();
		PreparedStatement statement;  
		String query="select id,email,password from userProfile where email=? and password=?";
		statement=con.prepareStatement(query);
		statement.setString(1,mail);
		statement.setString(2,password);
		
		ResultSet rs=statement.executeQuery();
		
		while(rs.next())
		{
			User user=new User(rs.getInt("id"));
			return true;
		}
		return false;
		
	}

	public ArrayList<Train> searchAvailables(LocalDate date,String from,String to) throws SQLException
    {
		System.out.println(from+ " "+to+" "+date);
       ArrayList<Train> matchingTrains = new ArrayList<>();
       Connection connection=DBConnection.getInstance().getConnection();
       PreparedStatement statement;
       PreparedStatement statement1;    
       String query = "SELECT DISTINCT train.name, station.name as stn_name,station.station_id, dates.* FROM station"
	       		+ " LEFT JOIN train ON train.id = station.train_id"
	       		+ " LEFT JOIN dates ON station.train_id = dates.train_id"
	       		+ " LEFT JOIN seat ON seat.date_id = dates.date_id"
	       		+ " WHERE dates.departure_date = ? "
	       		+ " AND seat.status = 'Available'"
	       		+ " AND station.name IN (?,?) order by station_id";

        System.out.println(query);
        statement=connection.prepareStatement(query);
        statement.setDate(1, Date.valueOf(date));
        statement.setString(2, from);
        statement.setString(3, to);
        ResultSet rs=statement.executeQuery();
        boolean departure_point=false;
        boolean arrival_point=false;
        Station stations = null;
        while(rs.next())
        {
        	System.out.println(rs.getDate(5));
            LinkedList<Coach> coach=new LinkedList<>();
        	if(from.equals(rs.getString("stn_name")))
        	{
        		departure_point=true;
        	}
        	else if(departure_point && to.equals(rs.getString("stn_name")))
        	{
        		arrival_point=true;
        	}
        	
        	if(departure_point && arrival_point)
        	{
        		stations=new Station(from,to);
        	}
        	Schedule schedule=new Schedule(rs.getDate("departure_date"),rs.getDate("arrival_date"),rs.getString("departure_day"),rs.getString("arrival_day"));
        	if(stations!=null)
        	{
        		
        		String queryCoach="select type,total_seats as avilalbe from coach where train_id=?";
        		System.out.println("HI");
        		statement1=connection.prepareStatement(queryCoach);
        		statement1.setInt(1, rs.getInt("train_id"));
        		ResultSet rs1=statement1.executeQuery();
        		String available="select count(coach.type) as available from coach"
        				+ " left join seat on seat.coach_id=coach.coach_id"
        				+ " where coach.train_id=? and seat.status='Available'"
        				+ " group by coach.type having coach.type=?";
        		PreparedStatement stmnt=connection.prepareStatement(available);
        		stmnt.setInt(1,rs.getInt("train_id"));        	  
        	    int avlCount=0;
        		while(rs1.next())
        		{
        			stmnt.setString(2,rs1.getString("type"));
            		ResultSet result=stmnt.executeQuery();      		
        			result.next();
        			avlCount = result.getInt("available")+DataManager.getInstance().findAvl(rs.getInt("train_id"), from, date,rs1.getString("type"));
        			Coach avlCoach=new Coach(rs1.getString("type"),avlCount);
        		   coach.add(avlCoach);
        		
        		}
        		Train train=new Train(rs.getInt("train_id"),rs.getString("name"),stations,schedule,coach);
        		matchingTrains.add(train);
        	} 
        	String midStation="select count(booking.train_id),booking.train_id,train.name,coach.type,"
        			+ "dates.arrival_date,dates.arrival_day,dates.departure_date,dates.departure_day from booking "
        			+ "join seat on seat.seat_id=booking.seat_id "
        			+ "join coach on coach.coach_id=seat.coach_id "
        			+ "join dates on dates.date_id=seat.date_id "
        			+ "join train on train.id=booking.train_id "
        			+ "where booking.to_location=? and dates.departure_date=? "
        			+ "group by(booking.train_id,train.name,coach.type,dates.arrival_date,dates.arrival_day,dates.departure_date,dates.departure_day)";
        	String validToStation="select station.name from station "
        			+ "where train_id=? and "
        			+ "station_id>(select station_id from station where station.name='"+from+"') "
        			+ "and name in('"+to+ "')";
        	Date journeyDate=Date.valueOf(date);
        	System.out.println(midStation);
        	PreparedStatement stmnt=connection.prepareStatement(midStation);
        	stmnt.setString(1, from);
        	stmnt.setDate(2,journeyDate);        	
        	PreparedStatement st=connection.prepareStatement(validToStation);
        	ResultSet result=stmnt.executeQuery();
        	stations=new Station(from,to);
        	while(result.next())
        	{
        		st.setInt(1, result.getInt("train_id"));
        		ResultSet validToStationResult=stmnt.executeQuery();
        		if(validToStationResult.next())
        		{
                	schedule=new Schedule(result.getDate("departure_date"),result.getDate("arrival_date"),result.getString("departure_day"),result.getString("arrival_day"));
                	Coach avlCoach=new Coach(result.getString("type"),result.getInt("count"));
        			Train train=new Train(result.getInt("train_id"),result.getString("name"),stations,schedule,coach);
            		matchingTrains.add(train);
        			
        		}
        		
        	}


        	
        	
        }
        connection.close();
        System.out.println(matchingTrains+"szxdtfyguhijo");
            return matchingTrains; 
    }
	public int findAvl(int train_num,String from,LocalDate date,String coach)
	{
		int count=0;
		Date journeyDate=Date.valueOf(date);
		try
		{
			Connection con=DBConnection.getInstance().getConnection();
			String query="select count(to_location) from booking "
					+ "left join seat on seat.seat_id=booking.seat_id "
					+ "left join dates on dates.date_id=seat.date_id "
					+ "left join coach on coach.coach_id=seat.coach_id "
					+ "where booking.train_id=? and dates.departure_date=?  and coach.type=? "
					+ "group by to_location having to_location=?";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setInt(1,train_num);
			statement.setDate(2, journeyDate);
			statement.setString(3,from);
			statement.setString(4,coach); 
			ResultSet rs=statement.executeQuery();
			rs.next();
			count=rs.getInt("count");
		}
		catch (SQLException e) 
		{
 			e.printStackTrace();
		}
		return  count;
	}
	public int getPassengerId()
	{
		int lastPassengerID = 0;
		Connection con;
		try 
		{
			con = DBConnection.getInstance().getConnection();
			PreparedStatement statement1=con.prepareStatement("SELECT id FROM Passengers ORDER BY id DESC LIMIT 1");
			ResultSet resultSet = statement1.executeQuery();
			
			if (resultSet.next())
			{
	            lastPassengerID = resultSet.getInt("id");
	        }
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return lastPassengerID;
	}
	
	public void addPassenger(ArrayList<Passenger> passengers,long pnr) throws SQLException {
		Connection con=DBConnection.getInstance().getConnection();
		String passengerDetails="insert into passengers(name,age,gender,id,pnr) values(?,?,?,?,?)";
		PreparedStatement statement=con.prepareStatement(passengerDetails);
		for(Passenger passenger:passengers)
		{
		int lastPassengerID =DataManager.getInstance().getPassengerId();
		lastPassengerID++;
		statement.setString(1, passenger.getPassengerName());
		statement.setInt(2,passenger.getAge());
		statement.setString(3, passenger.getPassengerGender());
		statement.setLong(4,lastPassengerID);
		statement.setLong(5,pnr);
	    statement.executeUpdate();
		}
	}
	
	
	    
	public Seat allocateSeat(int train_id,String type,LocalDate date) throws SQLException
	{
	   ResultSet rs = null;
	   Connection con;
       PreparedStatement statement = null;
	   Seat seat=null;
		try 
		{
			con = DBConnection.getInstance().getConnection();
			String seatQuery="select seat.train_id,seat.type as seat_type,seat.seat_num,seat.seat_id,seat.berthType,dates.departure_date,coach.type from seat"
					+ " join dates on seat.date_id=dates.date_id "
					+ "join coach on seat.coach_id=coach.coach_id"
					+ " where seat.train_id=? and dates.departure_date=? and coach.type=? and seat.status='Available'"
					+ "order by seat.seat_id";
		    statement=con.prepareStatement(seatQuery);
		    statement.setInt(1, train_id);
	        statement.setDate(2, Date.valueOf(date));
	        statement.setString(3, type);

		    rs=statement.executeQuery();
		    while(rs.next())
			{
			 seat = new Seat(rs.getInt("seat_id"),rs.getString("seat_type"),rs.getString("berthType"));
			 String changeStatus="update seat set status='Reserved' where seat_id=?";
			 PreparedStatement s1=con.prepareStatement(changeStatus);
 			 s1.setInt(1,rs.getInt("seat_id"));
 			 s1.executeUpdate();
 			 
			 break;
			}
		}
		catch (SQLException e) 
		{	
			e.printStackTrace();
		}
		finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	    }
		
		
	return seat;
	}
	public ArrayList<String> getFrom() throws SQLException
	{
		Connection connection=DBConnection.getInstance().getConnection();
			String getfrom="select distinct station.name as stationName,station.station_id from station"
					+ "	 where station_id<> ("
					+ "	select MAX(Station_id)"
					+ "	from station as stns"
					+ " where stns.train_id=station.train_id"
					+ "	)  order by station_id";
		PreparedStatement statement=connection.prepareStatement(getfrom);
		ResultSet rs=statement.executeQuery();
		ArrayList<String> from=new ArrayList<>();
		while(rs.next())
		{
			from.add(rs.getString("stationName"));
		}
		Collections.sort(from);
		return from;
		
	}
	public ArrayList<String> getTo() throws SQLException
	{
		Connection connection=DBConnection.getInstance().getConnection();
		String getTo="select distinct station.name as stationName,station.station_id from station"
				+ "	where station_id<> ("
				+ "	select Min(station_id)"
				+ "	from station as stns"
				+ "	where stns.train_id=station.train_id"
				+ "	)  order by station_id";
		PreparedStatement statement=connection.prepareStatement(getTo);
		ResultSet rs=statement.executeQuery();
		ArrayList<String> to=new ArrayList<>();
		rs.next();
		while(rs.next())
		{
			to.add(rs.getString("stationName"));
		}
		Collections.sort(to);
		return to;
	}
	public void addTickets(long pnr)
	{
		 Connection con=null;
	     PreparedStatement statement = null;
	     String addTickets="";
	     try
	     {
	    	 con=DBConnection.getInstance().getConnection();
	    	 addTickets="insert into ticket(pnr,ticketstatus) values(?,?)";
	    	 statement=con.prepareStatement(addTickets);
	    	 statement.setLong(1, pnr);
	    	 statement.setString(2,"BOOKED");
	    	 statement.executeUpdate();
	     }
	     
	     catch(SQLException e)
	     {
	    	 e.printStackTrace();
	     }   
	}
	public void addBookings(long pnr, Seat seat, String from, String to,String quotaType) {
		 Connection con=null;
	     PreparedStatement statement1 = null;
	     PreparedStatement statement2=null;
	     PreparedStatement statement3=null;
	     ResultSet rs;
	     ResultSet getQuota = null;
	     String bookings="insert into booking(booked_by,booked_date,seat_id,from_location,to_location,train_id,quota_id,passenger_id) values(?,?,?,?,?,?,?,?)";
	     String trainId="select train_id from seat where seat_id=?";
	     String currentUser=getCurrentUser();
	     LocalDate currentDate = LocalDate.now();	    
	     Date sqlDate = Date.valueOf(currentDate);	 
	     String quotaId="select quota_id as id from quota where quota_type='"+quotaType+"'";
	     
	     try 
	     {
	    	con=DBConnection.getInstance().getConnection();
	    	statement3=con.prepareStatement(quotaId);
			getQuota=statement3.executeQuery();
			getQuota.next();
		} 
	    catch (SQLException e1) {
			e1.printStackTrace();
		}
	    
	     
	     try
	     {
	    	 
	    	 statement1=con.prepareStatement(bookings);
	    	 statement2=con.prepareStatement(trainId);
	    	 statement2.setInt(1,seat.getNumber());
	    	 rs=statement2.executeQuery();
	    	 rs.next();
	    	 statement1.setString(1,currentUser);
	    	 statement1.setDate(2,sqlDate);
	    	 statement1.setInt(3,seat.getNumber());
	    	 statement1.setString(4, from);
	    	 statement1.setString(5, to);
	    	 statement1.setInt(6,rs.getInt("train_id"));
	    	 statement1.setInt(7,getQuota.getInt("id"));
	    	 statement1.setInt(8,DataManager.getInstance().getPassengerId());
	    	 statement1.executeUpdate();
	     }
	     
	     catch(SQLException e)
	     {
	    	 e.printStackTrace();
	     }   
	     
	}
	private String getCurrentUser()
	{
		Connection con;
		String user="";
		try {
			con = DBConnection.getInstance().getConnection();
			String getuser="select email from userProfile where id=?";
			PreparedStatement statement=con.prepareStatement(getuser);
			statement.setInt(1,User.getId());
			ResultSet rs=statement.executeQuery();
			rs.next();
			user=rs.getString("email");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user ;
	}
	public LinkedList<Booking> getMyBookings() throws SQLException {
		
	Connection connection=DBConnection.getInstance().getConnection();
	String read= "select distinct ticket.pnr,"
			+ "dates.departure_date as date,dates.departure_day as day,"
			+ "booking.booked_date,booking.from_location,booking.to_location,"
			+ "booking.train_id,train.name as train_Name from booking "
			+ "left join seat on seat.seat_id=booking.seat_id "
			+ "left join dates on dates.date_id=seat.date_id "
			+ "left join passengers on passengers.id=booking.passenger_id "
			+ "left join ticket on ticket.pnr=passengers.pnr "
			+ "left join train on train.id=booking.train_id "
			+ "where booking.booked_by=?"; 
	PreparedStatement statement=connection.prepareStatement(read);
	statement.setString(1,DataManager.getInstance().getCurrentUser());
	ResultSet rs=statement.executeQuery();
	rs.next();
	LinkedList<Booking> bookings=new LinkedList<>();
	while(rs.next())
	{
		Date date=rs.getDate("date");
		String day=rs.getString("day");
		Schedule schedule=new Schedule(date,day);
		String from=rs.getString("From_location");
		String to=rs.getString("to_location");
		long pnr=rs.getLong("pnr");
		Ticket ticket_pnr=new Ticket(pnr);
		Train bookedTrain=new Train(rs.getInt("train_id"),rs.getString("train_name"));
		Booking bookingInfo=new Booking(schedule,from,to,ticket_pnr,bookedTrain);
		bookings.add(bookingInfo);		
	}
    return bookings;	
	}
	public long getTicketCount(long pnr) throws SQLException {
		Connection con=DBConnection.getInstance().getConnection();
		String getCount="select count(*) from passengers join booking on booking.passenger_id=passengers.id where booking.status='BOOKED' group by pnr Having pnr="+pnr;
		PreparedStatement statement=con.prepareStatement(getCount);
		ResultSet rs=statement.executeQuery();
		long count=0;
		while(rs.next())
		{
			count=rs.getLong("count");
		}
		
		return count;
	}
	public void cancelTicket(Long pnrNum, int passengerId) throws SQLException {
		Connection connection=DBConnection.getInstance().getConnection();
		String cancel="update booking set status='CANCELED' where passenger_id=?";
		PreparedStatement statement=connection.prepareStatement(cancel);
		//statement.setLong(1,pnrNum);
		statement.setInt(1,passengerId);
		statement.executeUpdate();
		String seatStatus="update  seat set status='Available'"
				+" where seat_id in("
				+" select seat_id from booking"
				+" left join passengers on passengers.id=booking.passenger_id"
				+" left join ticket on ticket.pnr=passengers.pnr"
				+" where ticket.pnr=? and passengers.id=?)";
		PreparedStatement statement1=connection.prepareStatement(seatStatus);
		statement1.setLong(1,pnrNum);
		statement1.setInt(2,passengerId);		
		statement1.executeUpdate();
		
		
		
	}
	public void cancelTicket(Long pnrNum) throws SQLException {
		
		Connection connection=DBConnection.getInstance().getConnection();
		String cancel="update ticket set ticketstatus='CANCELED' where pnr=?";
		PreparedStatement statement=connection.prepareStatement(cancel);
		statement.setLong(1,pnrNum);
		statement.executeUpdate();
		String cancelTicket="UPDATE booking  SET status = 'CANCELED' from passengers "
				+ "WHERE passengers.id = booking.passenger_id and passengers.pnr = ? and booking.status='BOOKEd'";
		PreparedStatement ticketStatement=connection.prepareStatement(cancelTicket);
		ticketStatement.setLong(1,pnrNum);
		ticketStatement.executeUpdate();
		String seatStatus="update seat set status='Available'"
				+ "where seat_id in("
				+ "	select seat_id from booking"
				+ "	left join passengers on passengers.id=booking.passenger_id "
				+ "	left join ticket on ticket.pnr=passengers.pnr"
				+ " where ticket.pnr=?)";
		PreparedStatement statement1=connection.prepareStatement(seatStatus);
		statement1.setLong(1,pnrNum);
		statement1.executeUpdate();
	}
	//add getFrom,getTo 
	public Ticket getMyTicket(long pnr) throws SQLException {
		Connection con=DBConnection.getInstance().getConnection();
		String ticketQuery="select ticket.pnr,ticket.ticketstatus,train.id,train.name,booking.from_location as from,booking.to_location as to,booking.booked_date,"
				+ "	dates.departure_date as d_date,dates.departure_day as d_day ,"
				+ "	dates.arrival_date as a_date ,dates.arrival_day as a_day,"
				+ "	coach.type as coach_type,coach.number as coach_num,quota.quota_type,seat.seat_num,seat.berthtype,"
				+ "	passengers.name as passenger_name,passengers.age,passengers.gender "
				+ "	from ticket"
				+ "	join passengers on passengers.pnr=ticket.pnr"
				+ "	join booking on booking.passenger_id=passengers.id"
				+ "	join train on train.id=booking.train_id"
				+ "	join seat on seat.seat_id=booking.seat_id"
				+ "	join dates on dates.date_id=seat.date_id"
				+ "	join coach on coach.coach_id=seat.coach_id"
				+ "	join quota on quota.quota_id=booking.quota_id"
				+ "	where ticket.pnr=?";
		PreparedStatement statement=con.prepareStatement(ticketQuery);
		statement.setLong(1, pnr);
		ResultSet rs=statement.executeQuery();
		boolean flag=true;
		
		Schedule date=null;
		Seat seat=null;
		Coach coach=null;
		Train train=null;
		Booking booking=null;
		Ticket ticket=null;
		LinkedList<Passenger>passengers=new LinkedList<>();
		while(rs.next())
		{
			Passenger passenger=new Passenger(rs.getString("passenger_name"),rs.getInt("age"),rs.getString("gender"));
			passengers.add(passenger);
			if(flag)
			{
				date=new Schedule(rs.getDate("d_date"),rs.getDate("a_date"),rs.getString("d_day"),rs.getString("a_day"));
				seat=new Seat(rs.getInt("seat_num"),rs.getString("berthtype"));
				coach=new Coach(rs.getString("coach_type"),rs.getString("coach_num"),seat);
				train=new Train(rs.getInt("id"),rs.getString("name"),date,coach);
				booking=new Booking(rs.getString("from"),rs.getString("to"),rs.getDate("booked_date"),rs.getString("quota_type"),passengers,train);
			    ticket=new Ticket(rs.getLong("pnr"),rs.getString("ticketstatus"),booking);
				flag=false;
			}
		}
		
		
		
	
		return ticket;
	}
	
	
		
		
		
	
}

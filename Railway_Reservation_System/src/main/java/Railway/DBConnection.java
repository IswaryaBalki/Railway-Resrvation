package Railway;
import java.sql.*;
public class DBConnection {
	
	
	 private static final String url = "jdbc:postgresql://localhost:5432/Railway";
	 private static final String uname="postgres";
	 private static final String pword="root";
	 private static DBConnection instance=null;
	 static
	 {
		 try 
		 {
			Class.forName("org.postgresql.Driver");
		 }
		 catch (ClassNotFoundException e) 
		 {
			e.printStackTrace();
		}
	 }
	
	 public Connection getConnection() throws SQLException
	 {				 
		 return DriverManager.getConnection(url,uname,pword);
	 }
	 public static DBConnection getInstance()
	 {
		 if(instance==null)
		 instance=new DBConnection();
		 return instance;
		 
	 }
}

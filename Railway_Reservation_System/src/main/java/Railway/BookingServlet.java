package Railway;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class BookingServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		 HttpSession session=request.getSession();
		 request.setAttribute("From", session.getAttribute("from"));
		 request.setAttribute("To", session.getAttribute("to"));
		 String name = (String) request.getParameter("selectedTrainName");
     	 int num = Integer.parseInt(request.getParameter("selectedTrainNum")); // Cast to Integer
		 LocalDate dDate = LocalDate.parse(request.getParameter("dDate"));
		 String dDay = (String) request.getParameter("dDay");
		 LocalDate aDate = LocalDate.parse(request.getParameter("aDate"));
		 String aDay = (String) request.getParameter("aDay");
		 String coachType = request.getParameter("coachType[" + Integer.parseInt(request.getParameter("index")) + "]");
		 String availables = request.getParameter("availableSeats[" + Integer.parseInt(request.getParameter("index"))+ "]");

		 int available=Integer.parseInt(availables);
		 session.setAttribute("AVL", available);
		 session.setAttribute("TrainName",name);
		 session.setAttribute("TrainNum",num);
		 session.setAttribute("ArrivDate", aDate);
		 session.setAttribute("ArrivDay",aDay);
		 session.setAttribute("DepDate",dDate);
		 session.setAttribute("DepDay", dDay);
		
		 System.out.println(available);
		 System.out.println(coachType);
		 
		 request.setAttribute("AVL", available);
		 request.setAttribute("TrainName",name);
		 request.setAttribute("TrainNum",num);
		 request.setAttribute("ArrivDate", aDate);
		 request.setAttribute("ArrivDay",aDay);
		 request.setAttribute("DepDate",dDate);
		 request.setAttribute("DepDay", dDay);
		 
		RequestDispatcher dispatcher = request.getRequestDispatcher("Booking.jsp");
        dispatcher.forward(request, response);
	}
	

}

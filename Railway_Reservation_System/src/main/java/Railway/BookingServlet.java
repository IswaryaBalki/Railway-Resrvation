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
		 int available = Integer.parseInt(request.getParameter("AVL"));
		 String name = (String) request.getParameter("selectedTrainName");
     	 int num = Integer.parseInt(request.getParameter("selectedTrainNum")); // Cast to Integer
		 LocalDate dDate = LocalDate.parse(request.getParameter("dDate"));
		 String dDay = (String) request.getParameter("dDay");
		 LocalDate aDate = LocalDate.parse(request.getParameter("aDate"));
		 String aDay = (String) request.getParameter("aDay");
		 
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

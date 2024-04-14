package Railway;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TicketDetailServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
	   HttpSession session=request.getSession();
	    long pnr=Long.parseLong(request.getParameter("pnr"));
	    System.out.println("pnrrrrrrrrrrrrrrrrrr");
	    Ticket ticketDetails = null;
		try {
			ticketDetails = Railway.DataManager.getInstance().getMyTicket(pnr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(ticketDetails);
	    session.setAttribute("Ticket", ticketDetails);
	    request.getRequestDispatcher("Ticket.jsp").forward(request, response);
    
	    
	}

}

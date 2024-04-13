package Railway;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchServlets extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession();

        if (session.getAttribute("userStatus") == null ) {//|| !(boolean) session.getAttribute("userStatus")) {
            res.sendRedirect("LogIn.jsp");
        } else {
            String from = req.getParameter("from");
            String to = req.getParameter("to");
            String date = req.getParameter("date");

//            // Check if from, to, and date are not null or empty
//            if (from != null && !from.isEmpty() && to != null && !to.isEmpty() && date != null && !date.isEmpty()) {
                session.setAttribute("from", from);
                session.setAttribute("to", to);
                session.setAttribute("date", date);
                System.out.println(session.getAttribute("to"));
                RequestDispatcher rd = req.getRequestDispatcher("Available");
                rd.forward(req, res); // Use forward instead of include if you don't want to include previous output
//            } 
//            else 
//            {
//               
//            }
        }
    }
}

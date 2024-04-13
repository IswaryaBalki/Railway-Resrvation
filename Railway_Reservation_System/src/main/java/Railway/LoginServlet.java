package Railway;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        DataManager dataManager = Railway.DataManager.getInstance();
        boolean authenticated = false;
		try {
			authenticated = dataManager.authenticateUser(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
       
        if (authenticated) 
        {
            HttpSession session = request.getSession();
            session.setAttribute("userStatus", true);
            response.sendRedirect("Search.jsp");
            
        } else {
            
            response.sendRedirect("LogIn.jsp"); 
        }
    }
}


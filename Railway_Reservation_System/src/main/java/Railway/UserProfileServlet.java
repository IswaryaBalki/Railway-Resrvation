package Railway;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class UserProfileServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		String username = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        long phone = Long.parseLong(request.getParameter("phoneNum"));
        boolean success = false;
       
		try {
			success =Railway.DataManager.getInstance().addUser(username, email, password, phone);
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        if (success) {
            
            response.sendRedirect("LogIn.jsp");
        } 
        else
        {
               request.setAttribute("errorMessage", "Email is already exists");
               request.getRequestDispatcher("SignUp.jsp").forward(request, response);
        }
		
	}

}

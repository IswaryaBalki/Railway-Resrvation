package Railway;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmTicketServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		String[] passengerNames = request.getParameterValues("passengerName[]");
        String[] passengerAges = request.getParameterValues("passengerAge[]");
        String[] passengerGenders = request.getParameterValues("gender[]");

        for (int i = 0; i < passengerNames.length; i++) {
            String name = passengerNames[i];
            int age = Integer.parseInt(passengerAges[i]);
            String gender = passengerGenders[i];
            System.out.println("Passenger " + (i + 1) + ": Name - " + name + ", Age - " + age + ", Gender - " + gender);
        }
		
	}
	

}

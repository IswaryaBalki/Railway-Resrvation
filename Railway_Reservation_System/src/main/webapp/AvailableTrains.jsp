<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Railway.*,java.time.LocalDate,java.time.format.DateTimeFormatter" %>

<html>
<head>
    <meta charset="UTF-8">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f8f9fa;
        }

        .container {
            width: 80%;
            margin: 50px auto;
            background: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #dee2e6;
            padding: 12px;
            text-align: left;
        }

        th {
            background-image: linear-gradient(to right, rgb(250, 62, 187), rgb(248, 16, 178), rgba(217, 14, 163, 0.755));
            color: #fff;
        }

        th.train-number {
            width: 8%;
        }

        th.available-seats {
            width: 30%; 
        }

        .seat {
            cursor: pointer;
            background-image: linear-gradient(to left, #fb1427, #fc2a85,rgb(252, 51, 54),rgb(255, 34, 82));
            border: none;
            color: white;
            padding: 8px 16px;
            border-radius: 4px;
            transition: background-color 0.3s;
            font-size: 17px;
            margin-bottom: 5px;
            display: block;
            width: 100%;
            text-align: center;
            
        }

        .seat:hover {
background-image: linear-gradient(to right, #f92d3e, #fc512a,rgb(252, 58, 58),rgb(245, 50, 92));             
        }

        .coach-info {
           
        }
    </style>
</head>
<body>
<%
    String from = request.getParameter("from");
    String to = request.getParameter("to");
    String dateString = request.getParameter("date");
    LocalDate date = null;
    if (dateString != null && !dateString.isEmpty()) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = LocalDate.parse(dateString, formatter);
    }
    System.out.println(from + " " + to + " " + date);

    ArrayList<Train> available = Railway.DataManager.getInstance().searchAvailables(date, from, to);
    System.out.println(available.size());
%>

<div class="container">
    <table>
        <thead>
        <tr>
            <th>Train Name</th>
            <th class="train-number">Train Number</th>
            <th>Departure Station</th>
            <th>Departure Time</th>
            <th>Arrival Station</th>
            <th>Arrival Time</th>
            <th class="available-seats">Available Seats</th> 
        </tr>
        </thead>
        <tbody>
        <% for(Train detail : available) { %>
        <tr>
            <td><%= detail.getTrainName() %></td>
            <td ><%= detail.getTrainNum() %></td>
            <td><%= detail.getStation().getFrom() %> </td>
            <td><%= detail.getSchedule().getDepartureDate() %> <%= detail.getSchedule().getDepartureDay() %></td>
            <td><%= detail.getStation().getTo() %></td>
            <td><%= detail.getSchedule().getArrivalDate() %> <%= detail.getSchedule().getArrivalDay() %></td>
            <td>
                <% LinkedList<Coach> coaches = detail.getCoaches();%>
               
                <form action="classType" method="post">
    
					    <input type="hidden" name="selectedTrainName" value="<%=detail.getTrainName() %>">
					    <input type="hidden" name="selectedTrainNum" value="<%=detail.getTrainNum() %>">
					    <input type="hidden" name="dDate" value="<%=detail.getSchedule().getDepartureDate() %>">
					    <input type="hidden" name="dDay" value="<%=detail.getSchedule().getDepartureDay() %>">
					    <input type="hidden" name="aDate" value="<%=detail.getSchedule().getArrivalDate() %>">
					    <input type="hidden" name="aDay" value="<%=detail.getSchedule().getArrivalDay() %>">
					    
		    			<%  
				    		int i=0;
					    	for (Coach coachDetail : coaches) 
					        {
					    	    i++; %>
						        <input type="hidden" name="coachType[<%= i %>]" value="<%= coachDetail.getCoachType() %>">
						        <input type="hidden" name="availableSeats[<%= i %>]" value="<%= coachDetail.getAvailables() %>">
						        <button type="submit" class="seat"name="index" value="<%=i%>">
						            <%= coachDetail.getCoachType() %> AVL- <%= coachDetail.getAvailables() %>
						        </button>
   						 <% } %>
				</form>

            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>

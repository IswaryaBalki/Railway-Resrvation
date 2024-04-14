<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList, Railway.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ticket Details</title>
    <style>body {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
    margin: 0;
    padding: 0;
}

.container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
}

h1, h2 {
    color: #333;
}

.ticket {
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    padding: 20px;
}

hr {
    border: none;
    border-top: 1px solid #ddd;
    margin: 10px 0;
}

ul {
    list-style-type: none;
    padding: 0;
}

ul li {
    margin-bottom: 5px;
}
    </style>
</head>
<body>
    <div class="container">
        <h1>Ticket Details</h1>
        <div class="ticket">
        <% 
        Ticket ticket=(Ticket)session.getAttribute("Ticket");%>
            <h2>Ticket Information</h2>
            <p><strong>PNR:</strong> <%= ticket.getPnr() %></p>
            <p><strong>Status:</strong> <%= ticket.getStatus() %></p>
            <hr>
            <h2>Booking Details</h2>
            <p><strong>From:</strong> <%= ticket.getBooking().getFrom() %></p>
            <p><strong>To:</strong> <%= ticket.getBooking().getTo() %></p>
            <p><strong>Booked Date:</strong> <%= ticket.getBooking().getDate() %></p>
            <p><strong>Quota Type:</strong> <%= ticket.getBooking().getQuota() %></p>
            <hr>
            <h2>Train Details</h2>
            <p><strong>Train ID:</strong> <%= ticket.getBooking().getTrain().getTrainNum() %></p>
            <p><strong>Train Name:</strong> <%= ticket.getBooking().getTrain().getTrainName() %></p>
            <p><strong>Departure Date:</strong> <%= ticket.getBooking().getTrain().getSchedule().getDepartureDate() %></p>
            <p><strong>Departure Day:</strong> <%= ticket.getBooking().getTrain().getSchedule().getDepartureDay() %></p>
            <hr>
            <h2>Passengers</h2>
            <ul>
                <% for (Passenger passenger : ticket.getBooking().getPassengers()) { %>
                <li><%= passenger.getPassengerName() %> - <%= passenger.getAge() %> years - <%= passenger.getPassengerGender() %></li>
                <% } %>
            </ul>
        </div>
    </div>
</body>
</html>

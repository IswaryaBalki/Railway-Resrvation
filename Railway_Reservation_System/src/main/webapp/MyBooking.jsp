<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList, Railway.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Bookings</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #333;
            text-align: center;
            padding-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        button {
            background-color: #008CBA;
            color: white;
            border: none;
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }

        button:hover {
            background-color: #005f78;
        }
    </style>
</head>
<body>
<h1>My Bookings</h1>
<table>
    <tr>
        <th>PNR</th>
        <th>Date</th>
        <th>Day</th>
        <th>Booked Date</th>
        <th>From Location</th>
        <th>To Location</th>
        <th>Train Name</th>
        <th>Action</th>
    </tr>
    <%
        LinkedList<Booking> bookings = DataManager.getInstance().getMyBookings();
        for (Booking booking : bookings) {
    %>
    <tr>
        <td><%= booking.getTicket().getPnr() %></td>
        <td><%= booking.getSchedule().getDepartureDate() %></td>
        <td><%= booking.getSchedule().getDepartureDay() %></td>
        <td><%= booking.getDate() %></td>
        <td><%= booking.getFrom() %></td>
        <td><%= booking.getTo() %></td>
        <td><%= booking.getTrain().getTrainName() %></td>
        <td>
            <form action="Ticket" method="post">
                <input type="hidden" name="pnr" value="<%= booking.getTicket().getPnr() %>">
                <button type="submit">View Ticket</button>
            </form>
            <form action="cancelBooking.jsp" method="post">
                <input type="hidden" name="pnr" value="<%= booking.getTicket().getPnr() %>">
                <button type="submit">Cancel</button>
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>

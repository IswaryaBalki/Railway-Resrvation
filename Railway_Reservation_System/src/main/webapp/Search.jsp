<%@page import="Railway.DataManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        .searchDiv {
            width: 80%;
            height: auto;
            border: 1px solid rgb(172, 160, 160);
            margin: 180px auto; 
            padding: 20px;
            box-sizing: border-box; 
            background-color: white;
            font-family: Arial, sans-serif; 
            font-size: 20px;
            color: maroon;
            
        }

        .align {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;            
        }

        .input {
            width:20%;
            border-radius: 5px;
            border: 1px solid #ccc;
            padding: 10px; 
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 30%;
            height: 40px; 
            color: white;
            fond-size:20px;
            border: 1px solid blueviolet;
            margin-left: 300px;
            border-radius:10px;
            cursor: pointer;
            background-color: darkmagenta;
            transition: background-color 0.3s, color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: fuchsia;
            color: white;
        }
        h3{
            margin-left:20px;
            margin-right:20px;
        }
        
        body {
            background-image: url('searchBg.jpg');
            background-size: cover; 
            background-position: center;
        }
    </style>
</head>
<body>
<form action="view" method="post">
    <div class="searchDiv">
        <div class="align">
            <h3>From</h3>
            <select class="input"  name="from" required>
              <option value="">
            <% 
            ArrayList<String> list=Railway.DataManager.getInstance().getFrom();
            for(String from:list)
            {%>
            <option value="<%= from%>"><%= from%></option>
            <%}%>
            </select>
            <h3>To</h3>           
            <select class="input"  name="to" required >
            <option value="">
            <% ArrayList<String> listTo=Railway.DataManager.getInstance().getTo();
            for(String to:listTo)
            {%>
            <option value="<%= to %>" ><%= to%></option>
            <%}%>
            </select>
            <h3>Date</h3>
            <input type="date" id="dateInput" class="input" name="date" required>
            <h3>Quota</h3>
            <select class="input" name="quota" required>
            <option value="">
            <option value="General">General</option>
            <option value="LadiesQuota">LadiesQuota</option>
            <option value="Takal">Takal</option>
            </select>
        </div>
        <input type="submit" value="Search">
    </div>
    </form>
    
    <script>
   
  

    

    </script>

    
</body>
</html>
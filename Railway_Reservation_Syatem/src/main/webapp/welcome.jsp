<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
         body {
    margin:0;
   
  }
  img{
    width: 1400px;
    height: 600px;
  }
        .homeBar {
            width: 100%;
            height:30%;
            border:none;
            background-color:  #1e1545;
            display: flex; 
            justify-content: flex-end; 
            
        }

        .homeLink {
            border:none;
            margin-top: 8px;
            margin-right: 10px; 
            height:60%;
            width: auto; 
            padding: 0 10px; 
            background-color:  #1e1545;
            align-items: center; 
            color:white;
            font-weight: bold;
            font-size: 20px;
            font-family:Informal Roman;
        }
        .overlay {
        position: absolute;
        top:40%;
        left:4%;
        width:45%;
        height:50%;
        /* background-color: rgba(255, 255, 255, 0.5); Adjust opacity as needed */
        text-align: center;
        padding-top: 20px;
        box-sizing: border-box;
        color: aliceblue;
    }
    #d
    {
        border: 1px solid grey;
        width: 30%;
        height: 20%;
        padding-top:20px;
        font-size: 25px;
        color: aqua;
        background-color:#1e1545;
      }
      .homeLink:hover
      {
   		 background-color: white;
   		 color:black; 
  		 
	  }
    </style>
</head>
<body>

<div class="homeBar">
    <form action="welcome.jsp">
    	<input type="submit" class="homeLink"value="Home">
    </form>
    <form action="a">
    	<input type="submit" class="homeLink" value="My Bookings">
    </form>
    <form action="SignUp.jsp">
		<input type="submit" class="homeLink" value="Sign Up">  
	</form>
    <form action="LogIn.jsp">
    	<input type="submit" class="homeLink" value="Log In">
    </form>
    
    
</div>
<img src="trainBG.jpg" >
<form action="Search.jsp">
<div class="overlay">
    <h1>Get Ready to Explore - Secure Your Seat on the Rails Today!</h1>
    <input type="submit" value="Search Trains" id="d"> 

</div>
</form>
</body>
</html>

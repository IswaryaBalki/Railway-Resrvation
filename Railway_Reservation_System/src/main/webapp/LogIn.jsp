<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    body {
        margin: 0;
        padding: 0;
        background-color: #fdecf8; 
    }

    .logIn {
        width: 30%;
        max-width: 400px; 
        height: auto; 
        border: 2px solid rgb(82, 79, 79);
        border-radius: 10px; 
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
        margin: auto;
        margin-top: 100px;
        background-color:white;
        padding: 20px;
        color:maroon;
    }

    input[type="text"], input[type="password"] {
        margin-top: 10px;
        width: 100%;
        height: 40px;
        padding: 10px; 
        border-radius: 20px;
        border: 1px solid #ccc; 
        box-sizing: border-box; 
    }

    input[type="submit"] {
        margin-top: 20px; 
        width: 100%;
        height: 40px;
        border: none;
        border-radius: 20px;
        background-color:rgb(154, 21, 21);
        color: #fff; 
        font-size: 20px;
        cursor: pointer; 
        transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
       background-color: rgb(82, 79, 79);
    }
</style>
</head>
<body>
	<form action="Login" method="post">
		<div class="logIn">
	        <p>Email Id</p>
	        <input type="text" name="email" >
	        <p>Password</p>
	        <input type="password" name="password">
	         <p>Don't have an account? <a href="SignUp.jsp">Sign Up</a></p> 
	        <input type="submit" value="Login">
  		</div>
	
	</form>
</body>
</html>

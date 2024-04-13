<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <style>
        body {
            background-color: #f0f0f0; 
            font-family: Arial, sans-serif;
        }
        .container {
            width: 30%;
            border: 1px solid black;
            margin: 80px auto;
            background-color: silver;
            padding: 20px; 
            border-radius: 10px; 
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); 
        }
        h2 {
            text-align: center;
            font-family: cursive;
        }
        h4 {
            font-size: 16px;
            font-family: cursive;
            margin-bottom: 5px; 
        }
        .input {
            width: 100%;
            height: 40px; 
            margin-bottom: 10px; 
            border: 1.5px solid aqua;
            border-radius: 5px; 
            padding: 5px 10px; 
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            height: 40px; 
            border-radius: 10px;
            margin-top: 20px;
            background-image: linear-gradient(to right, #f92d3e, #fc512a,rgb(252, 58, 58),rgb(245, 50, 92)); 
            color: white; 
            font-size: 16px;
            font-weight: bold; 
            cursor: pointer;
            border: none; 
            transition: background-color 0.3s; 
        }
        input[type="submit"]:hover {
            background-image: linear-gradient(to right,rgb(224, 43, 224),rgb(247, 29, 174),rgb(215, 40, 163),rgba(217, 14, 163, 0.755));
        }
    </style>
</head>
<body>
    <div class="container">
    <form action="SignUp" method="post"></form>
        <h2>Sign Up</h2>
        <form action="#">
            <h4>Email Id</h4>
            <input type="email" placeholder="Enter your email" class="input" name="email" required>
            <h4>Password</h4>
            <input type="password" placeholder="Enter your password" class="input" name="password" required>
            <h4>User Name</h4>
            <input type="text" placeholder="Enter your username" class="input" name="name" required>
            <h4>Mobile</h4>
            <input type="text" placeholder="Enter your phone num" class="input" name="phoneNum" required>
            <br>
            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>

            

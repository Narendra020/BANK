<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Failure</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f8f8;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .container {
        background-color: #ffffff;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
        text-align: center;
        width: 300px;
    }
    .button {
        display: inline-block;
        margin-top: 20px;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        background: linear-gradient(to right, #00c6ff, #0072ff);
        color: white;
        text-decoration: none;
        font-size: 16px;
        cursor: pointer;
    }
    .button:hover {
        background: linear-gradient(to right, #0072ff, #00c6ff);
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Login Failed</h2>
        <p>Please try again</p>
        <a href="login.jsp" class="button">Try Again</a><br>
        <a href="login.jsp" class="button">Go Back to Home</a>
    </div>
</body>
</html>

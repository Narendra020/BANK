<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Account Details</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-image: url('https://img.freepik.com/premium-vector/blue-digital-technology-background-with-white-building-logo-bank_666034-682.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
        .container {
            text-align: center;
            border: 1px solid #ccc;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: rgba(255, 255, 255, 0.9);
            width: 80%;
            max-width: 400px;
        }
        h1 {
            color: #4facfe;
            margin-bottom: 20px;
        }
        input, button {
            margin: 10px 0;
            padding: 10px;
            width: 100%;
            max-width: 300px;
            box-sizing: border-box;
        }
        button {
            background-color: #4facfe;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #00f2fe;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Account Details</h1>
        <form action="EditAccountDetailsServlet" method="post">
            <label for="accountNumber">Account Number:</label><br>
            <input type="text" id="accountNumber" name="accountNumber" required><br>
            
            <button type="submit">Get Details</button>
        </form>
    </div>
</body>
</html>

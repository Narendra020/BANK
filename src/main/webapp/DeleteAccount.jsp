<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Account</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
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
            box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        .container h1 {
            margin-bottom: 30px;
            font-size: 24px;
            color: white; /* Set h1 color to white */
        }
        label {
            color: red; /* Set account number label color to red */
        }
        input, button {
            margin: 10px 0;
            padding: 15px;
            width: 100%;
            max-width: 300px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        button {
            background: linear-gradient(to right, #00c6ff, #0072ff);
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        button:hover {
            background: linear-gradient(to right, #0072ff, #00c6ff);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Delete Account</h1>
        <form action="DeleteAccountServlet" method="post">
            <label for="accountNumber" style="color: red;">Account Number:</label><br>
            <input type="text" id="accountNumber" name="accountNumber" required><br>

            <button type="submit">Delete Account</button>
        </form>
    </div>
</body>
</html>

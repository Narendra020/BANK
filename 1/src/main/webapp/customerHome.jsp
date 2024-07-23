<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Home</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-image: url('https://img.freepik.com/premium-vector/blue-digital-technology-background-with-white-building-logo-bank_666034-682.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }
        .container h2 {
            margin-bottom: 30px;
            font-size: 24px;
            color: #333333;
        }
        .container a {
            display: block;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            color: white;
            padding: 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-bottom: 20px;
            text-decoration: none;
            transition: background 0.3s ease;
        }
        .container a:hover {
            background: linear-gradient(to right, #0072ff, #00c6ff);
        }
        .container a + a {
            margin-top: 10px; /* Add spacing between buttons */
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Customer Home</h2>
    <a href="customerAccountDetails.jsp">Account Details</a>
    <a href="Deposit.jsp">Deposit</a>
    <a href="Withdraw.jsp">Withdraw</a>
    <a href="ViewBalance.jsp">View Balance</a>
    <a href="Transactions.jsp">Transactions</a>
    <a href="ChangePassword.jsp">Change Password</a>
    <a href="CloseAccount.jsp">Close Account</a>
    <a href="Logout.jsp">Log Out</a>
</div>
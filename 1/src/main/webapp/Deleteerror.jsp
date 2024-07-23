<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            background-image: url('https://img.freepik.com/premium-vector/blue-digital-technology-background-with-white-building-logo-bank_666034-682.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 400px;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
        }
        .try-again {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            background: linear-gradient(to right, #007bff, #0056b3);
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s ease;
        }
        .try-again:hover {
            background: linear-gradient(to right, #0056b3, #007bff);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Invalid account number or password!</h1>
        <a href="DeleteAccount.jsp" class="try-again">Try again</a>
    </div>
</body>
</html>

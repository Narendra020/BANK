<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Get account Details</title>
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
            background-position: center;
        }
        .container {
            text-align: center;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: rgba(255, 255, 255, 0.9);
            width: 400px;
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        input, button {
            margin: 10px 0;
            padding: 10px;
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
        <h1>Get Account Details</h1>
        <form action="AccountDetailServlet" method="post">
            <label for="accountNumber">Account Number:</label><br>
            <input type="text" id="accountNumber" name="accountNumber" required><br>
            
            <button type="submit">Get Details</button>
        </form>
    </div>
</body>
</html>

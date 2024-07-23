<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Close Account</title>
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
        .message-container {
            margin-top: 20px;
            padding: 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            text-align: center;
        }
        .button-container {
            margin-top: 20px;
        }
        button {
            padding: 10px;
            width: 150px;
            box-sizing: border-box;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            color: white;
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
        <h1>Close Account</h1>
        
        <div class="message-container">
            <p>Error closing account. Please check if the balance is zero.</p>
        </div>

        <div class="button-container">
            <form action="customerHome.jsp">
                <button type="submit">Go Back Home</button>
            </form>
        </div>
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>
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
        .form-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
            width: 350px;
            text-align: center;
        }
        .form-container h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333333;
        }
        .form-container label {
            display: block;
            margin-bottom: 8px;
            font-size: 14px;
            color: #666666;
            text-align: left;
        }
        .form-container input, .form-container select, .form-container button {
            width: 100%;
            padding: 10px;
            border: 1px solid #cccccc;
            border-radius: 5px;
            margin-bottom: 15px;
            font-size: 16px;
            box-sizing: border-box;
        }
        .form-container button {
            background: linear-gradient(to right, #00c6ff, #0072ff);
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s ease;
        }
        .form-container button:hover {
            background: linear-gradient(to right, #0072ff, #00c6ff);
        }
    </style>
    <script>
        function generateAccountNumber() {
            return 'ACC' + Math.floor(100000 + Math.random() * 900000);
        }

        function generatePassword() {
            var chars = "0123456789abcdefghijklmnopqrstuvwxyz!@#$%^&*()ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            var passwordLength = 8;
            var password = "";
            for (var i = 0; i <= passwordLength; i++) {
                var randomNumber = Math.floor(Math.random() * chars.length);
                password += chars.substring(randomNumber, randomNumber + 1);
            }
            return password;
        }

        function setGeneratedValues() {
            document.getElementById("accountNumber").value = generateAccountNumber();
            document.getElementById("password").value = generatePassword();
        }

        window.onload = setGeneratedValues;
    </script>
</head>
<body>
    <div class="form-container">
        <h1>Create Account</h1>
        <form action="CreateAccountServlet" method="post">
            <label for="userName">User Name:</label>
            <input type="text" id="userName" name="userName" required>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>

            <label for="phoneNumber">Phone Number:</label>
            <input type="tel" id="phoneNumber" name="phoneNumber" required>

            <label for="gender">Gender:</label>
            <select id="gender" name="gender" required>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </select>

            <label for="accountType">Account Type:</label>
            <select id="accountType" name="accountType" required>
                <option value="Savings">Savings</option>
                <option value="Checking">Checking</option>
                <option value="Business">Business</option>
            </select>

            <label for="joinedDate">Joined Date:</label>
            <input type="date" id="joinedDate" name="joinedDate" required>

            <label for="balance">Balance:</label>
            <input type="number" id="balance" name="balance" required>

            <label for="idProof">ID Proof:</label>
            <input type="file" id="idProof" name="idProof" required>

            <input type="hidden" id="accountNumber" name="accountNumber">
            <input type="hidden" id="password" name="password">

            <button type="submit">Create Account</button>
        </form>
    </div>
</body>
</html>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        html {
            height: 100%; /* Ensure HTML takes full height of viewport */
            background-image: url(https://img.freepik.com/premium-vector/blue-digital-technology-background-with-white-building-logo-bank_666034-682.jpg ); /* Replace with your image path */
            background-size: cover; /* Cover the entire background */
            background-position: center; /* Center the background */
        }
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full viewport height */
            margin: 0;
            background-color: rgba(0, 0, 0, 0.4); /* Optional: Adds a semi-transparent overlay */
        }
        .container {
            background: rgba(255, 255, 255, 0.8);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
        }
        input[type="text"], input[type="password"], select {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"], .forgot-password {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background: #6a11cb;
            color: white;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none; /* Remove underline */
            display: block; /* Make it a block element for full width */
            margin-top: 10px; /* Space between login button and forgot password */
        }
        input[type="submit"]:hover, .forgot-password:hover {
            background: #2575fc;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
    <script>
        function validateForm() {
            var role = document.getElementById("role").value;
            var accountNumber = document.getElementById("accountNumber").value;
            var password = document.getElementById("password").value;

            // Basic client-side validation
            if (role === "" || accountNumber === "" || password === "") {
                document.getElementById("errorMessage").style.display = "block";
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form id="loginForm" action="LoginServlet" method="post" onsubmit="return validateForm()">
            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="">Select Role</option>
                <option value="admin">Admin</option>
                <option value="customer">Customer</option>
            </select>
            <label for="accountNumber">Account Number:</label>
            <input type="text" id="accountNumber" name="accountNumber" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <input type="submit" value="Login">
            <p id="errorMessage" class="error-message" style="display: none;">Please fill out all fields.</p>
        </form>
        
        <a href="forgotPassword.jsp" class="forgot-password">Forgot Password?</a>
    </div>
</body>
</html>

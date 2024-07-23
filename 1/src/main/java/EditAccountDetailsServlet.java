

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditAccountDetailsServlet")
public class EditAccountDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditAccountDetailsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String accountNumber = request.getParameter("accountNumber");

        String jdbcURL = "jdbc:mysql://localhost:3306/user_management";
        String jdbcUsername = "root";
        String jdbcPassword = "75830102";

        String sqlSelect = "SELECT * FROM bulkdata WHERE accountNumber = ?";

        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet result = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            selectStatement = connection.prepareStatement(sqlSelect);
            selectStatement.setString(1, accountNumber);
            result = selectStatement.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Edit Account Details</title>");
            out.println("<style>");
            out.println("body {");
            out.println("    background-image: url('https://img.freepik.com/premium-vector/blue-digital-technology-background-with-white-building-logo-bank_666034-682.jpg');");
            out.println("    background-size: cover;");
            out.println("    background-repeat: no-repeat;");
            out.println("    background-attachment: fixed;");
            out.println("    font-family: Arial, sans-serif;");
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    height: 100vh;");
            out.println("}");
            out.println(".container {");
            out.println("    width: 90%; /* Adjust the width as per your requirement */");
            out.println("    max-width: 500px; /* Max width to maintain readability */");
            out.println("    margin: 20px;");
            out.println("    padding: 20px;");
            out.println("    background-color: rgba(255, 255, 255, 0.9);");
            out.println("    border-radius: 10px;");
            out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
            out.println("}");
            out.println("h2 {");
            out.println("    color: #333;");
            out.println("    text-align: center;");
            out.println("}");
            out.println("form {");
            out.println("    margin-top: 20px;");
            out.println("}");
            out.println("label {");
            out.println("    display: block;");
            out.println("    margin-bottom: 3px; /* Space between labels and inputs */");
            out.println("}");
            out.println("input[type='text'], input[type='number'], input[type='email'], select {");
            out.println("    width: calc(100% - 22px); /* Adjust width to account for padding and border */");
            out.println("    padding: 10px;");
            out.println("    font-size: 16px;");
            out.println("    border-radius: 5px;");
            out.println("    border: 1px solid #ccc;");
            out.println("    margin-top: 5px;");
            out.println("}");
            out.println(".button-container {");
            out.println("    text-align: center;");
            out.println("    margin-top: 20px;");
            out.println("}");
            out.println(".button {");
            out.println("    padding: 10px 20px;");
            out.println("    font-size: 16px;");
            out.println("    background: linear-gradient(to right, #007bff, #0056b3);");
            out.println("    color: #fff;");
            out.println("    border: none;");
            out.println("    border-radius: 5px;");
            out.println("    cursor: pointer;");
            out.println("    transition: background 0.3s ease;");
            out.println("}");
            out.println(".button:hover {");
            out.println("    background: linear-gradient(to right, #0056b3, #007bff);");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2>Edit Account Details</h2>");

            if (result.next()) {
                out.println("<form action='UpdateAccountDetailsServlet' method='post'>");
                out.println("<input type='hidden' name='accountNumber' value='" + accountNumber + "'>");
                out.println("<label>Full Name:</label><input type='text' name='fullName' value='" + result.getString("userName") + "' required><br>");
                out.println("<label>Date of Birth:</label><input type='date' name='dateOfBirth' value='" + result.getString("dob") + "' required><br>");
                out.println("<label>Email ID:</label><input type='email' name='emailId' value='" + result.getString("email") + "' required><br>");
                out.println("<label>Address:</label><input type='text' name='address' value='" + result.getString("address") + "' required><br>");
                out.println("<label>Mobile No:</label><input type='text' name='mobileNo' value='" + result.getString("phoneNumber") + "' required><br>");
                out.println("<label>Gender:</label><input type='text' name='gender' value='" + result.getString("gender") + "' required><br>");
                out.println("<label>Account Type:</label>");
                out.println("<select name='accountType' required>");
                out.println("<option value='Saving' " + (result.getString("accountType").equals("Saving") ? "selected" : "") + ">Saving</option>");
                out.println("</select><br>");
                out.println("<label>Joined Date:</label><input type='date' name='joinedDate' value='" + result.getString("joinedDate") + "' required><br>");
                out.println("<label>Balance:</label><input type='number' name='initialBalance' value='" + result.getDouble("balance") + "' min='1000' required><br>");
                out.println("<label>ID Proof:</label><input type='text' name='idProof' value='" + result.getString("idProof") + "' required><br>");
                out.println("<div class='button-container'>");
                out.println("<input type='submit' value='Update Account' class='button'>");
                out.println("</div>");
                out.println("</form>");
            } else {
                out.println("<p>No account found with the provided account number!</p>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.close();
        }
    }
}

import java.io.IOException;import java.io.PrintWriter;import java.sql.Connection;import java.sql.DriverManager;import java.sql.PreparedStatement;import java.sql.SQLException;import javax.servlet.ServletException;import javax.servlet.annotation.WebServlet;import javax.servlet.http.HttpServlet;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;@WebServlet("/CloseAccountServlet")public class CloseAccountServlet extends HttpServlet {    private static final long serialVersionUID = 1L;    protected void doPost(HttpServletRequest request, HttpServletResponse response)            throws ServletException, IOException {        response.setContentType("text/html;charset=UTF-8");        PrintWriter out = response.getWriter();        // Retrieve account number from session        HttpSession session = request.getSession();        String accountNumber = (String) session.getAttribute("accountNumber");        if (accountNumber == null || accountNumber.isEmpty()) {            // Handle case where account number is not found in session            displayErrorMessage(out, "Account number not found in session. Please log in again.");            return;        }        // Database connection parameters        String jdbcURL = "jdbc:mysql://localhost:3306/bankingapplication";        String jdbcUsername = "root";        String jdbcPassword = "123456";        // SQL query to delete account        String deleteSQL = "DELETE FROM customerdata WHERE accountNumber = ? AND balance = 0.0";        Connection connection = null;        PreparedStatement deleteStatement = null;        try {            // Load JDBC driver and establish connection            Class.forName("com.mysql.cj.jdbc.Driver");            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);            // Create delete PreparedStatement            deleteStatement = connection.prepareStatement(deleteSQL);            deleteStatement.setString(1, accountNumber);            // Execute the delete query            int rowsDeleted = deleteStatement.executeUpdate();            if (rowsDeleted > 0) {                // Account successfully closed                displaySuccessMessage(out, "Account closed successfully!");                // Optionally, invalidate the session to logout the user completely                session.invalidate();            } else {                // Error closing account (balance not zero)                displayErrorMessage(out, "Error closing account. Please check if the balance is zero.");            }        } catch (SQLException | ClassNotFoundException e) {            e.printStackTrace(); // Log exception for debugging            displayErrorMessage(out, "Error: " + e.getMessage());        } finally {            // Close resources            try {                if (deleteStatement != null) {                    deleteStatement.close();                }                if (connection != null) {                    connection.close();                }            } catch (SQLException e) {                e.printStackTrace(); // Log any closing exceptions            }            out.close();        }    }    // Method to display success message and "Go Back Home" button    private void displaySuccessMessage(PrintWriter out, String message) {        out.println("<!DOCTYPE html>");        out.println("<html>");        out.println("<head>");        out.println("<meta charset='UTF-8'>");        out.println("<title>Close Account</title>");        out.println("<style>");        out.println("body {");        out.println("    display: flex;");        out.println("    justify-content: center;");        out.println("    align-items: center;");        out.println("    height: 100vh;");        out.println("    margin: 0;");        out.println("    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");        out.println("    background-image: url('https://img.freepik.com/premium-vector/blue-digital-technology-background-with-white-building-logo-bank_666034-682.jpg');");        out.println("    background-size: cover;");        out.println("    background-repeat: no-repeat;");        out.println("    background-position: center;");        out.println("}");        out.println(".container {");        out.println("    text-align: center;");        out.println("    border: 1px solid #ccc;");        out.println("    padding: 20px;");        out.println("    border-radius: 10px;");        out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");        out.println("    background-color: rgba(255, 255, 255, 0.9);");        out.println("    width: 400px;");        out.println("}");        out.println("h1 {");        out.println("    color: #4CAF50;");        out.println("    margin-bottom: 20px;");        out.println("}");        out.println(".message-container {");        out.println("    margin-top: 20px;");        out.println("    padding: 15px;");        out.println("    border: 1px solid #ccc;");        out.println("    border-radius: 8px;");        out.println("    text-align: center;");        out.println("}");        out.println(".button-container {");        out.println("    margin-top: 20px;");        out.println("}");        out.println("button {");        out.println("    padding: 10px;");        out.println("    width: 150px;");        out.println("    box-sizing: border-box;");        out.println("    border: none;");        out.println("    border-radius: 5px;");        out.println("    font-size: 16px;");        out.println("    background: linear-gradient(to right, #00c6ff, #0072ff);");        out.println("    color: white;");        out.println("    cursor: pointer;");        out.println("    transition: background 0.3s ease;");        out.println("}");        out.println("button:hover {");        out.println("    background: linear-gradient(to right, #0072ff, #00c6ff);");        out.println("}");        out.println("</style>");        out.println("</head>");        out.println("<body>");        out.println("<div class='container'>");        out.println("<h1>Close Account</h1>");        out.println("<div class='message-container'>");        out.println("<p>" + message + "</p>");        out.println("</div>");        out.println("<div class='button-container'>");        out.println("<form action='customerHome.jsp'>");        out.println("<button type='submit'>Go Back Home</button>");        out.println("</form>");        out.println("</div>");        out.println("</div>");        out.println("</body>");        out.println("</html>");    }    // Method to display error message and "Go Back Home" button    private void displayErrorMessage(PrintWriter out, String message) {        out.println("<!DOCTYPE html>");        out.println("<html>");        out.println("<head>");        out.println("<meta charset='UTF-8'>");        out.println("<title>Close Account</title>");        out.println("<style>");        out.println("body {");        out.println("    display: flex;");        out.println("    justify-content: center;");        out.println("    align-items: center;");        out.println("    height: 100vh;");        out.println("    margin: 0;");        out.println("    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");        out.println("    background-image: url('https://img.freepik.com/premium-vector/blue-digital-technology-background-with-white-building-logo-bank_666034-682.jpg');");        out.println("    background-size: cover;");        out.println("    background-repeat: no-repeat;");        out.println("    background-position: center;");        out.println("}");        out.println(".container {");        out.println("    text-align: center;");        out.println("    border: 1px solid #ccc;");        out.println("    padding: 20px;");        out.println("    border-radius: 10px;");        out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");        out.println("    background-color: rgba(255, 255, 255, 0.9);");        out.println("    width: 400px;");        out.println("}");        out.println("h1 {");        out.println("    color: #4CAF50;");        out.println("    margin-bottom: 20px;");        out.println("}");        out.println(".message-container {");        out.println("    margin-top: 20px;");        out.println("    padding: 15px;");        out.println("    border: 1px solid #ccc;");        out.println("    border-radius: 8px;");        out.println("    text-align: center;");        out.println("}");        out.println(".button-container {");        out.println("    margin-top: 20px;");        out.println("}");        out.println("button {");        out.println("    padding: 10px;");        out.println("    width: 150px;");        out.println("    box-sizing: border-box;");        out.println("    border: none;");        out.println("    border-radius: 5px;");        out.println("    font-size: 16px;");        out.println("    background: linear-gradient(to right, #00c6ff, #0072ff);");        out.println("    color: white;");        out.println("    cursor: pointer;");        out.println("    transition: background 0.3s ease;");        out.println("}");        out.println("button:hover {");        out.println("    background: linear-gradient(to right, #0072ff, #00c6ff);");        out.println("}");        out.println("</style>");        out.println("</head>");        out.println("<body>");        out.println("<div class='container'>");        out.println("<h1>Close Account</h1>");        out.println("<div class='message-container'>");        out.println("<p>" + message + "</p>");        out.println("</div>");        out.println("<div class='button-container'>");        out.println("<form action='customerHome.jsp'>");        out.println("<button type='submit'>Go Back Home</button>");        out.println("</form>");        out.println("</div>");        out.println("</div>");        out.println("</body>");        out.println("</html>");    }}
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

@WebServlet("/AccountDetailServlet")
public class AccountDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AccountDetailServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve account number to view
        String accountNumber = request.getParameter("accountNumber");

        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/user_management";
        String jdbcUsername = "root";
        String jdbcPassword = "75830102";

        // SQL query
        String sql = "SELECT * FROM bulkdata WHERE accountNumber = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            // Load JDBC driver and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Create PreparedStatement
            statement = connection.prepareStatement(sql);
            statement.setString(1, accountNumber);

            // Execute the query
            result = statement.executeQuery();

            // Start HTML output
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>View Account Details</title>");
            out.println("<style>");
            out.println("body {");
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    height: 100vh;");
            out.println("    margin: 0;");
            out.println("    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
            out.println("    background-image: url('https://img.freepik.com/premium-vector/blue-digital-technology-background-with-white-building-logo-bank_666034-682.jpg');");
            out.println("    background-size: cover;");
            out.println("    background-repeat: no-repeat;");
            out.println("    background-attachment: fixed;");
            out.println("}");
            out.println(".container {");
            out.println("    text-align: left;"); // Align content to the left
            out.println("    border: 1px solid #ccc;");
            out.println("    padding: 20px;");
            out.println("    border-radius: 15px;");
            out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
            out.println("    background-color: rgba(255, 255, 255, 0.9);");
            out.println("    width: 90%;"); // Reduced width to 90%
            out.println("    max-width: 600px;"); // Set maximum width
            out.println("    margin-top: 50px;");
            out.println("}");
            out.println("h2 {");
            out.println("    color: #333;");
            out.println("    text-align: center;");
            out.println("}");
            out.println(".details-box {");
            out.println("    margin-top: 20px;");
            out.println("    padding: 15px;");
            out.println("    border: 1px solid #ccc;");
            out.println("    border-radius: 8px;");
            out.println("}");
            out.println(".button-container {");
            out.println("    text-align: center;");
            out.println("    margin-top: 30px;");
            out.println("}");
            out.println(".button {");
            out.println("    padding: 15px 30px;");
            out.println("    font-size: 18px;");
            out.println("    background: linear-gradient(to right, #00c6ff, #0072ff);");
            out.println("    color: white;");
            out.println("    border: none;");
            out.println("    border-radius: 5px;");
            out.println("    cursor: pointer;");
            out.println("    transition: background 0.3s ease;");
            out.println("}");
            out.println(".button:hover {");
            out.println("    background: linear-gradient(to right, #0072ff, #00c6ff);");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2>Account Details</h2>");

            if (result.next()) {
                out.println("<div class='details-box'>");
                out.println("<p><strong>Account Number:</strong> " + result.getString("accountNumber") + "</p>");
                out.println("</div>");
                
                out.println("<div class='details-box'>");
                out.println("<p><strong>Full Name:</strong> " + result.getString("userName") + "</p>");
                out.println("</div>");

                out.println("<div class='details-box'>");
                out.println("<p><strong>Date of Birth:</strong> " + result.getString("dob") + "</p>");
                out.println("</div>");

                out.println("<div class='details-box'>");
                out.println("<p><strong>Email ID:</strong> " + result.getString("email") + "</p>");
                out.println("</div>");

                out.println("<div class='details-box'>");
                out.println("<p><strong>Address:</strong> " + result.getString("address") + "</p>");
                out.println("</div>");

                out.println("<div class='details-box'>");
                out.println("<p><strong>Mobile No:</strong> " + result.getString("phoneNumber") + "</p>");
                out.println("</div>");

                out.println("<div class='details-box'>");
                out.println("<p><strong>Gender:</strong> " + result.getString("gender") + "</p>");
                out.println("</div>");

                out.println("<div class='details-box'>");
                out.println("<p><strong>Account Type:</strong> " + result.getString("accountType") + "</p>");
                out.println("</div>");

                out.println("<div class='details-box'>");
                out.println("<p><strong>Joined Date:</strong> " + result.getString("joinedDate") + "</p>");
                out.println("</div>");

                out.println("<div class='details-box'>");
                out.println("<p><strong>Balance:</strong> " + result.getDouble("balance") + "</p>");
                out.println("</div>");

                out.println("<div class='details-box'>");
                out.println("<p><strong>ID Proof:</strong> " + result.getString("idProof") + "</p>");
                out.println("</div>");

                // Go back to home button
                out.println("<div class='button-container'>");
                out.println("<form action='adminHome.jsp'>");
                out.println("<input type='submit' value='Go Back to Home' class='button'>");
                out.println("</form>");
                out.println("</div>");
            } else {
                out.println("<p>No account found with the provided account number!</p>");

                // Go back to home button
                out.println("<div class='button-container'>");
                out.println("<form action='adminHome.jsp'>");
                out.println("<input type='submit' value='Go Back to Home' class='button'>");
                out.println("</form>");
                out.println("</div>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Print to server logs
            out.println("<h3>Error: " + e.getMessage() + "</h3>"); // Display on the web page
        } finally {
            // Close resources
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Log any closing exceptions
            }
            out.close();
        }
    }
}

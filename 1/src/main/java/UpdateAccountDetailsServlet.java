import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateAccountDetailsServlet")
public class UpdateAccountDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve form parameters
        String accountNumber = request.getParameter("accountNumber");
        String fullName = request.getParameter("fullName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String emailId = request.getParameter("emailId");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String gender = request.getParameter("gender");
        String accountType = request.getParameter("accountType");
        String joinedDate = request.getParameter("joinedDate");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String idProof = request.getParameter("idProof");

        // JDBC URL, username, and password
        String jdbcUrl = "jdbc:mysql://localhost:3306/user_management";
        String jdbcUsername = "root";
        String jdbcPassword = "75830102";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            // Load MySQL JDBC Driver and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);

            // SQL query to update account details
            String sql = "UPDATE bulkdata SET userName=?, dob=?, email=?, address=?, phoneNumber=?, gender=?, accountType=?, joinedDate=?, balance=?, idProof=? WHERE accountNumber=?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, fullName);
            statement.setString(2, dateOfBirth);
            statement.setString(3, emailId);
            statement.setString(4, address);
            statement.setString(5, mobileNo);
            statement.setString(6, gender);
            statement.setString(7, accountType);
            statement.setString(8, joinedDate);
            statement.setDouble(9, initialBalance);
            statement.setString(10, idProof);
            statement.setString(11, accountNumber);

            // Execute the update operation
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Display success message and button in a styled container
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<title>Update Account Details</title>");
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
                out.println("    text-align: center;");
                out.println("    border: 1px solid #ccc;");
                out.println("    padding: 40px;");
                out.println("    border-radius: 15px;");
                out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
                out.println("    background-color: rgba(255, 255, 255, 0.9);");
                out.println("    width: 80%;");
                out.println("    max-width: 600px;");
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
                out.println("<h2>Account Details Updated Successfully!</h2>");

                // Display updated account details if needed
                // Note: You can customize this part to show updated details as per your application logic

                // Go back to home button
                out.println("<div class='button-container'>");
                out.println("<form action='adminHome.jsp'>");
                out.println("<input type='submit' value='Go Back to Home' class='button'>");
                out.println("</form>");
                out.println("</div>");

                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<p>Failed to update account details.</p>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        } finally {
            // Close resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.close();
        }
    }
}
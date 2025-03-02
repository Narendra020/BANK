import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateAccountServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Retrieve form data
        String userName = request.getParameter("userName");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        String accountType = request.getParameter("accountType");
        String joinedDate = request.getParameter("joinedDate");
        String balance = request.getParameter("balance");
        String idProof = request.getParameter("idProof");
        String accountNumber = request.getParameter("accountNumber");
        String password = request.getParameter("password");

        // Input validation (simplified for brevity)
        if (userName == null || dob == null || email == null || address == null ||
            phoneNumber == null || gender == null || accountType == null ||
            joinedDate == null || balance == null || idProof == null ||
            accountNumber == null || password == null) {
            out.println("<h3>All fields are required!</h3>");
            return;
        }

        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/user_management";
        String jdbcUsername = "root";
        String jdbcPassword = "75830102";

        // SQL query
        String sql = "INSERT INTO bulkdata (userName, dob, email, address, phoneNumber, gender, accountType, joinedDate, balance, idProof, accountNumber, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            
            // Create PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, dob);
            statement.setString(3, email);
            statement.setString(4, address);
            statement.setString(5, phoneNumber);
            statement.setString(6, gender);
            statement.setString(7, accountType);
            statement.setString(8, joinedDate);
            statement.setString(9, balance);
            statement.setString(10, idProof);
            statement.setString(11, accountNumber);
            statement.setString(12, password);

            // Execute the query
            int rows = statement.executeUpdate();
            
            // Check if insertion was successful
            if (rows > 0) {
                out.println("<html><head><title>Account Created</title>");
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
                out.println("    background: rgba(255, 255, 255, 0.9);");
                out.println("    padding: 40px;");
                out.println("    border-radius: 15px;");
                out.println("    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);");
                out.println("    width: 500px;");
                out.println("    text-align: center;");
                out.println("}");
                out.println(".container h3 {");
                out.println("    font-size: 24px;");
                out.println("}");
                out.println(".container form p {");
                out.println("    font-size: 18px;");
                out.println("}");
                out.println(".container input {");
                out.println("    font-size: 16px;");
                out.println("    padding: 5px;");
                out.println("    margin-top: 10px;");
                out.println("    width: calc(100% - 12px);");
                out.println("    border-radius: 5px;");
                out.println("    border: 1px solid #ccc;");
                out.println("}");
                out.println("button {");
                out.println("    background: linear-gradient(to right, #00c6ff, #0072ff);");
                out.println("    color: white;");
                out.println("    padding: 15px 20px;");
                out.println("    border: none;");
                out.println("    border-radius: 5px;");
                out.println("    cursor: pointer;");
                out.println("    font-size: 18px;");
                out.println("    transition: background 0.3s ease;");
                out.println("    width: 100%;");
                out.println("    margin-top: 20px;");
                out.println("}");
                out.println("button:hover {");
                out.println("    background: linear-gradient(to right, #0072ff, #00c6ff);");
                out.println("}");
                out.println("</style>");
                out.println("</head><body>");
                out.println("<div class='container'>");
                out.println("<h3>Account created successfully!</h3>");
                out.println("<form>");
                out.println("<p>Account Number: <input type='text' value='" + accountNumber + "' readonly></p>");
                out.println("<p>Password: <input type='text' value='" + password + "' readonly></p>");
                out.println("</form>");
                out.println("<button onclick=\"window.location.href='adminHome.jsp'\">Go Back to Home</button>");
                out.println("</div>");
                out.println("</body></html>");
            } else {
                out.println("<h3>Error creating account!</h3>");
            }
            
            // Close connection
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            out.close();
        }
    }
}

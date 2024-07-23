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
import javax.servlet.http.HttpSession;

@WebServlet("/ViewBalanceServlet")
public class ViewBalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user_management";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "75830102";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve account number from the session
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountNumber");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Check if accountNumber is null or empty
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            handleInvalidInput(response, "Account number is missing from session.");
            return;
        }

        // Database operations
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // Check if account exists and get current balance
            String query = "SELECT balance FROM bulkdata WHERE accountNumber = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, accountNumber.trim());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double currentBalance = resultSet.getDouble("balance");

                // Display balance
                out.println("<html><head><style>");
                out.println("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f0f0f0; }");
                out.println(".container { background-color: #ffffff; padding: 40px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); text-align: center; width: 400px; }");
                out.println(".container h2 { margin-top: 0; }");
                out.println(".container p { margin-bottom: 20px; }");
                out.println(".container .button { background: linear-gradient(to right, #1e90ff, #00bfff); color: white; padding: 12px 24px; border: none; border-radius: 4px; cursor: pointer; transition: background 0.3s ease; text-decoration: none; }");
                out.println(".container .button:hover { background: linear-gradient(to right, #00bfff, #1e90ff); }");
                out.println("</style></head><body>");
                out.println("<div class='container'>");
                out.println("<h2>Account Balance</h2>");
                out.println("<p>Account Number: " + accountNumber.trim() + "</p>");
                out.println("<p>Balance: " + currentBalance + "</p>");
                out.println("<a href='customerHome.jsp' class='button'>Go back to home</a>");
                out.println("</div>");
                out.println("</body></html>");
            } else {
                // Account not found
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Account not found.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while retrieving balance.");
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to handle invalid input
    private void handleInvalidInput(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><style>");
        out.println("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f0f0f0; }");
        out.println(".container { background-color: #ffffff; padding: 40px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); text-align: center; width: 400px; }");
        out.println(".container h2 { margin-top: 0; }");
        out.println(".container .button { background: linear-gradient(to right, #1e90ff, #00bfff); color: white; padding: 12px 24px; border: none; border-radius: 4px; cursor: pointer; transition: background 0.3s ease; text-decoration: none; }");
        out.println(".container .button:hover { background: linear-gradient(to right, #00bfff, #1e90ff); }");
        out.println("</style></head><body>");
        out.println("<div class='container'>");
        out.println("<h2>Error: " + message + "</h2>");
        out.println("<a href='customerHome.jsp' class='button'>Go back to home</a>");
        out.println("</div>");
        out.println("</body></html>");
    }
}

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

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user_management";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "75830102";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the request
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Validate input
        if (currentPassword == null || newPassword == null || confirmPassword == null ||
                currentPassword.trim().isEmpty() || newPassword.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
            handleInvalidInput(response, "All fields are required.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            handleInvalidInput(response, "New password and confirm password do not match.");
            return;
        }

        // Get account number from session
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountNumber");

        // Database operations
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // Check current password for the given account number
            String query = "SELECT * FROM bulkdata WHERE accountNumber = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, accountNumber);
            preparedStatement.setString(2, currentPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Update password
                query = "UPDATE bulkdata SET password = ? WHERE accountNumber = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, accountNumber);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    // Display success message centered on the page
                    out.println("<html><head><style>body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }</style></head>");
                    out.println("<body>");
                    out.println("<div style='text-align: center; padding: 20px; border: 1px solid #ccc; width: 300px;'>");
                    out.println("<h2 style='color: green;'>Password changed successfully!</h2>");
                    out.println("<a href='customerHome.jsp' style='display: inline-block; padding: 10px 20px; background-color: #007BFF; color: #fff; text-decoration: none; border-radius: 5px;'>Go back to home</a>");
                    out.println("</div>");
                    out.println("</body></html>");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update password.");
                }
            } else {
                // Invalid current password or account number
                handleInvalidInput(response, "Invalid current password.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while processing the password change.");
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
        out.println("<html><head><style>body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }</style></head>");
        out.println("<body>");
        out.println("<div style='text-align: center; padding: 20px; border: 1px solid #ccc; width: 300px;'>");
        out.println("<h2>Error: " + message + "</h2>");
        out.println("<a href='ChangePassword.jsp' style='display: inline-block; padding: 10px 20px; background-color: #007BFF; color: #fff; text-decoration: none; border-radius: 5px;'>Go back</a>");
        out.println("</div>");
        out.println("</body></html>");
    }
}

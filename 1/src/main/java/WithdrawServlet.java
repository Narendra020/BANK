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

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user_management";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "75830102";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the request
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountNumber");
        String withdrawAmountStr = request.getParameter("withdrawAmount");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Check if accountNumber or withdrawAmountStr is null or empty
        if (accountNumber == null || withdrawAmountStr == null || withdrawAmountStr.trim().isEmpty()) {
            handleInvalidInput(response, "Withdraw amount is missing or empty.");
            return;
        }

        // Validate and parse the withdraw amount
        double withdrawAmount = 0.0;
        try {
            withdrawAmount = Double.parseDouble(withdrawAmountStr.trim());
        } catch (NumberFormatException e) {
            handleInvalidInput(response, "Invalid withdraw amount format.");
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

            // Begin transaction (optional, but recommended)
            connection.setAutoCommit(false);

            // Check if account exists and get current balance
            String query = "SELECT balance FROM bulkdata WHERE accountNumber = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, accountNumber.trim());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double currentBalance = resultSet.getDouble("balance");

                // Check if sufficient balance for withdrawal
                if (currentBalance >= withdrawAmount) {
                    double newBalance = currentBalance - withdrawAmount;

                    // Update the balance
                    query = "UPDATE bulkdata SET balance = ? WHERE accountNumber = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setDouble(1, newBalance);
                    preparedStatement.setString(2, accountNumber.trim());

                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        // Insert into transactions table
                        query = "INSERT INTO transactions (accountNumber, transactionType, amount, description) VALUES (?, ?, ?, ?)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, accountNumber.trim());
                        preparedStatement.setString(2, "Withdrawal");
                        preparedStatement.setDouble(3, withdrawAmount);
                        preparedStatement.setString(4, "Withdrawal from account");

                        int rowsInserted = preparedStatement.executeUpdate();

                        if (rowsInserted > 0) {
                            // Commit transaction
                            connection.commit();

                            // Display success message
                            out.println("<html><head><style>");
                            out.println("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f0f0f0; }");
                            out.println(".container { background-color: #ffffff; padding: 40px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); text-align: center; width: 400px; }");
                            out.println(".container h2 { margin-top: 0; }");
                            out.println(".container p { margin-bottom: 20px; }");
                            out.println(".container .button { background: linear-gradient(to right, #1e90ff, #00bfff); color: white; padding: 12px 24px; border: none; border-radius: 4px; cursor: pointer; transition: background 0.3s ease; text-decoration: none; }");
                            out.println(".container .button:hover { background: linear-gradient(to right, #00bfff, #1e90ff); }");
                            out.println("</style></head><body>");
                            out.println("<div class='container'>");
                            out.println("<h2>Withdrawal successful!</h2>");
                            out.println("<p>New balance: " + newBalance + "</p>");
                            out.println("<a href='customerHome.jsp' class='button'>Go back to home</a>");
                            out.println("</div>");
                            out.println("</body></html>");
                        } else {
                            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to insert transaction.");
                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update balance.");
                    }
                } else {
                    handleInvalidInput(response, "Insufficient balance for withdrawal.");
                }
            } else {
                // Account not found
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Account not found.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            ((Throwable) e).printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while processing the withdrawal.");
        } finally {
            // Close resources and rollback transaction on error
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true); // Reset to auto-commit mode
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

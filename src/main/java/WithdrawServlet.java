import java.io.IOException;import java.io.PrintWriter;import java.sql.Connection;import java.sql.DriverManager;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.sql.SQLException;import java.sql.Timestamp;import javax.servlet.ServletException;import javax.servlet.annotation.WebServlet;import javax.servlet.http.HttpServlet;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;@WebServlet("/WithdrawServlet")public class WithdrawServlet extends HttpServlet {    private static final long serialVersionUID = 1L;    // Database connection parameters    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bankingapplication";    private static final String JDBC_USERNAME = "root";    private static final String JDBC_PASSWORD = "123456";    @Override    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        doPost(req, resp);    }    protected void doPost(HttpServletRequest request, HttpServletResponse response)            throws ServletException, IOException {        // Retrieve parameters from the request        HttpSession session = request.getSession(false);        if (session == null || session.getAttribute("accountNumber") == null) {            response.sendRedirect("login.jsp"); // Redirect to login page if session is invalid            return;        }        String accountNumber = (String) session.getAttribute("accountNumber");        String withdrawAmountStr = request.getParameter("withdrawAmount");        response.setContentType("text/html");        PrintWriter out = response.getWriter();        // Check if accountNumber or withdrawAmountStr is null or empty        if (accountNumber == null || withdrawAmountStr == null || withdrawAmountStr.trim().isEmpty()) {            handleInvalidInput(response, "Withdraw amount is missing or empty.");            return;        }        // Validate and parse the withdraw amount        double withdrawAmount = 0.0;        try {            withdrawAmount = Double.parseDouble(withdrawAmountStr.trim());        } catch (NumberFormatException e) {            handleInvalidInput(response, "Invalid withdraw amount format.");            return;        }        // Database operations        Connection connection = null;        PreparedStatement preparedStatementSelect = null;        PreparedStatement preparedStatementUpdate = null;        PreparedStatement preparedStatementInsert = null;        try {            // Establish connection to the database            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);            // Begin transaction            connection.setAutoCommit(false);            // Check if account exists and get current balance            String selectQuery = "SELECT balance FROM customerdata WHERE accountNumber = ?";            preparedStatementSelect = connection.prepareStatement(selectQuery);            preparedStatementSelect.setString(1, accountNumber.trim());            ResultSet resultSet = preparedStatementSelect.executeQuery();            if (resultSet.next()) {                double currentBalance = resultSet.getDouble("balance");                // Check if sufficient balance for withdrawal                if (currentBalance >= withdrawAmount) {                    double newBalance = currentBalance - withdrawAmount;                    // Update the balance                    String updateQuery = "UPDATE customerdata SET balance = ? WHERE accountNumber = ?";                    preparedStatementUpdate = connection.prepareStatement(updateQuery);                    preparedStatementUpdate.setDouble(1, newBalance);                    preparedStatementUpdate.setString(2, accountNumber.trim());                    int rowsUpdated = preparedStatementUpdate.executeUpdate();                    if (rowsUpdated > 0) {                        // Get current timestamp for withdrawal date                        Timestamp withdrawalDate = new Timestamp(System.currentTimeMillis());                        // Insert into transactions table                        String insertQuery = "INSERT INTO transactions (accountNumber, transactionType, amount, description, transactionDate) VALUES (?, ?, ?, ?, ?)";                        preparedStatementInsert = connection.prepareStatement(insertQuery);                        preparedStatementInsert.setString(1, accountNumber.trim());                        preparedStatementInsert.setString(2, "Withdrawal");                        preparedStatementInsert.setDouble(3, withdrawAmount);                        preparedStatementInsert.setString(4, "Withdrawal from account");                        preparedStatementInsert.setTimestamp(5, withdrawalDate);                        int rowsInserted = preparedStatementInsert.executeUpdate();                        if (rowsInserted > 0) {                            // Commit transaction                            connection.commit();                            // Display success message with withdrawal date                            out.println("<html><head><style>");                            out.println("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f0f0f0; }");                            out.println(".container { background-color: #ffffff; padding: 40px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); text-align: center; width: 400px; }");                            out.println(".container h2 { margin-top: 0; }");                            out.println(".container p { margin-bottom: 20px; }");                            out.println(".container .button { background: linear-gradient(to right, #1e90ff, #00bfff); color: white; padding: 12px 24px; border: none; border-radius: 4px; cursor: pointer; transition: background 0.3s ease; text-decoration: none; }");                            out.println(".container .button:hover { background: linear-gradient(to right, #00bfff, #1e90ff); }");                            out.println("</style></head><body>");                            out.println("<div class='container'>");                            out.println("<h2>Withdrawal successful!</h2>");                            out.println("<p>New balance: " + newBalance + "</p>");                            out.println("<p>Withdrawal Date: " + withdrawalDate + "</p>");                            out.println("<a href='customerHome.jsp' class='button'>Go back to home</a>");                            out.println("</div>");                            out.println("</body></html>");                        } else {                            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to insert transaction.");                        }                    } else {                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update balance.");                    }                } else {                    handleInvalidInput(response, "Insufficient balance for withdrawal.");                }            } else {                // Account not found                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Account not found.");            }        } catch (SQLException e) {            e.printStackTrace();            try {                if (connection != null) {                    connection.rollback();                }                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while processing the withdrawal.");            } catch (SQLException ex) {                ex.printStackTrace();            }        } finally {            // Close resources and rollback transaction on error            try {                if (preparedStatementSelect != null) {                    preparedStatementSelect.close();                }                if (preparedStatementUpdate != null) {                    preparedStatementUpdate.close();                }                if (preparedStatementInsert != null) {                    preparedStatementInsert.close();                }                if (connection != null) {                    connection.setAutoCommit(true); // Reset to auto-commit mode                    connection.close();                }            } catch (SQLException e) {                e.printStackTrace();            }        }    }    // Method to handle invalid input    private void handleInvalidInput(HttpServletResponse response, String message) throws IOException {        response.setContentType("text/html");        PrintWriter out = response.getWriter();        out.println("<html><head><style>");        out.println("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f0f0f0; }");        out.println(".container { background-color: #ffffff; padding: 40px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); text-align: center; width: 400px; }");        out.println(".container h2 { margin-top: 0; }");        out.println(".container .button { background: linear-gradient(to right, #1e90ff, #00bfff); color: white; padding: 12px 24px; border: none; border-radius: 4px; cursor: pointer; transition: background 0.3s ease; text-decoration: none; }");        out.println(".container .button:hover { background: linear-gradient(to right, #00bfff, #1e90ff); }");        out.println("</style></head><body>");        out.println("<div class='container'>");        out.println("<h2>Error: " + message + "</h2>");        out.println("<a href='customerHome.jsp' class='button'>Go back to home</a>");        out.println("</div>");        out.println("</body></html>");    }}
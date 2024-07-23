// Ensure you import DriverManager from java.sql package
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

@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountNumber");

        if (accountNumber == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String jdbcURL = "jdbc:mysql://localhost:3306/user_management";
        String jdbcUsername = "root";
        String jdbcPassword = "75830102";

        String sql = "SELECT * FROM transactions WHERE accountNumber = ? ORDER BY transactionDate DESC LIMIT 10";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            statement = connection.prepareStatement(sql);
            statement.setString(1, accountNumber);

            result = statement.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Transactions</title>");
            out.println("<style>");
            out.println("body {");
            out.println("    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
            out.println("    background-color: #f0f0f0;");
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    height: 100vh;");
            out.println("    margin: 0;");
            out.println("}");
            out.println(".container {");
            out.println("    background: rgba(255, 255, 255, 0.9);");
            out.println("    padding: 40px;");
            out.println("    border-radius: 15px;");
            out.println("    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);");
            out.println("    width: 600px;");
            out.println("    text-align: center;");
            out.println("}");
            out.println(".container h2 {");
            out.println("    margin-bottom: 30px;");
            out.println("    font-size: 24px;");
            out.println("    color: #333333;");
            out.println("}");
            out.println(".transaction-box {");
            out.println("    margin-top: 20px;");
            out.println("    padding: 15px;");
            out.println("    border: 1px solid #ccc;");
            out.println("    border-radius: 8px;");
            out.println("    text-align: left;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2>Last 10 Transactions</h2>");

            while (result.next()) {
                out.println("<div class='transaction-box'>");
                out.println("<p><strong>Transaction Date:</strong> " + result.getString("transactionDate") + "</p>");
                out.println("<p><strong>Amount:</strong> " + result.getDouble("amount") + "</p>");
                out.println("<p><strong>Type:</strong> " + result.getString("transactionType") + "</p>");
                out.println("</div>");
            }

            out.println("<div>");
            out.println("<a href='customerHome.jsp' style='background: linear-gradient(to right, #00c6ff, #0072ff); color: white; padding: 15px; border-radius: 5px; text-decoration: none;'>Go Back to Home</a>");
            out.println("</div>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

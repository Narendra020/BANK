import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usernameOrAccountNumber = request.getParameter("accountNumber"); // This will be username for admin and account number for customer
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Create a session object
        HttpSession session = request.getSession();

        try (Connection connection = DBConnection.getConnection()) {
            if (role.equals("admin")) {
                // Validate admin login
                AdminDAO adminDAO = new AdminDAO(connection);
                if (adminDAO.validateAdmin(usernameOrAccountNumber, password)) {
                    session.setAttribute("role", "admin");
                    session.setAttribute("username", usernameOrAccountNumber);
                    response.sendRedirect("adminHome.jsp");
                } else {
                    response.sendRedirect("login.jsp?error=Invalid admin credentials");
                }
            } else if (role.equals("customer")) {
                // Validate customer login
                CustomerDAO customerDAO = new CustomerDAO(connection);
                if (customerDAO.validateCustomer(usernameOrAccountNumber, password)) {
                    session.setAttribute("role", "customer");
                    session.setAttribute("accountNumber", usernameOrAccountNumber);
                    response.sendRedirect("customerHome.jsp");
                } else {
                    response.sendRedirect("login.jsp?error=Invalid customer credentials");
                }
            } else {
                response.sendRedirect("login.jsp?error=Invalid role selected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Database connection error");
        }
    }
}

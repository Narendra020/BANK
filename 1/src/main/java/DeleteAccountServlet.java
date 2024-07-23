import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeleteDAO deleteDAO;

    public void init() {
        deleteDAO = new DeleteDAO(); // Initialize DeleteDAO
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");

        boolean accountDeleted = deleteDAO.deleteAccount(accountNumber);

        if (accountDeleted) {
            response.sendRedirect("DeleteSuccess.jsp"); // Redirect to success page
        } else {
            response.sendRedirect("Deleteerror.jsp"); // Redirect to error page
        }
    }
}

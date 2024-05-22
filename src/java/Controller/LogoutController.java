package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogoutController", urlPatterns = {"/LogoutController"})
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Llama al método común que maneja la lógica de logout
        processLogout(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Llama al método común que maneja la lógica de logout
        processLogout(request, response);
    }

    private void processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalida la sesión actual
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // Redirige a la página de login
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet to handle user logout";
    }
}

package Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import Config.HibernateDBUser;
import Model.User;

@WebServlet("/auth")
public class Auth extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final HibernateDBUser hibernateDBUser = new HibernateDBUser();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equalsIgnoreCase(action)) {
            handleLogin(request, response);
        } else if ("register".equalsIgnoreCase(action)) {
            handleRegister(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción inválida");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (camposVacios(email, password)) {
            setError(request, "error", "Todos los campos son obligatorios.");
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        User user = hibernateDBUser.findUserByEmail(email);
        if (user == null) {
            setError(request, "error", "El usuario no existe.");
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        if (!BCrypt.checkpw(password, user.getPassword())) {
            setError(request, "error", "Credenciales incorrectas.");
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/reservations/make");
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (camposVacios(name, lastname, email, password)) {
            setError(request, "register_error", "Todos los campos son obligatorios.");
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(name, lastname, email, passwordHash);

        try {
            hibernateDBUser.saveUser(newUser);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception e) {
            setError(request, "register_error", "Error al registrar: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/register");
        }
    }

    // Métodos auxiliares
    private boolean camposVacios(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void setError(HttpServletRequest request, String tipo, String mensaje) {
        HttpSession session = request.getSession();
        session.setAttribute(tipo, mensaje);
    }
}

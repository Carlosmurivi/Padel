package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/auth")
public class Auth extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equalsIgnoreCase(action)) {
            handleLogin(request, response);
        } else if ("register".equalsIgnoreCase(action)) {
            handleRegister(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.getSession().setAttribute("error", "Todos los campos son obligatorios.");
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        try (Connection db = Database.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement stmt = db.prepareStatement(query)) {
                stmt.setString(1, email);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String hashedPassword = rs.getString("password");
                        if (BCrypt.checkpw(password, hashedPassword)) {
                            HttpSession session = request.getSession();
                            session.setAttribute("user", new User(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("last_name"),
                                    rs.getString("email")
                            ));
                            response.sendRedirect(request.getContextPath() + "/reservations/make");
                        } else {
                            request.getSession().setAttribute("error", "Credenciales incorrectas.");
                            response.sendRedirect(request.getContextPath() + "/");
                        }
                    } else {
                        request.getSession().setAttribute("error", "El usuario no existe.");
                        response.sendRedirect(request.getContextPath() + "/");
                    }
                }
            }
        } catch (SQLException e) {
            throw new IOException("Database error", e);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name == null || name.isEmpty() || lastname == null || lastname.isEmpty()
                || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.getSession().setAttribute("register_error", "Todos los campos son obligatorios.");
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection db = Database.getConnection()) {
            String query = "INSERT INTO users (name, last_name, email, password) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = db.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, name);
                stmt.setString(2, lastname);
                stmt.setString(3, email);
                stmt.setString(4, passwordHash);

                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    response.sendRedirect(request.getContextPath() + "/");
                } else {
                    request.getSession().setAttribute("register_error", "Error al registrar el usuario.");
                    response.sendRedirect(request.getContextPath() + "/register");
                }
            }
        } catch (SQLException e) {
            request.getSession().setAttribute("register_error", "Error al registrar el usuario: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/register");
        }
    }
}

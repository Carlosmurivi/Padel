package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.*;

public class db {
	
	//private static final String CONNECTION_STRING = "postgresql://neondb_owner:npg_ORn5JSxC2Bur@ep-sweet-mountain-a8qbzzx0.eastus2.azure.neon.tech/neondb?sslmode=require&options=endpoint%3Dep-sweet-mountain-a8qbzzx0";
	private static final String CONNECTION_STRING = "jdbc:postgresql://ep-sweet-mountain-a8qbzzx0.eastus2.azure.neon.tech/neondb?sslmode=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_ORn5JSxC2Bur";
	
	public static Connection getConnection() {
        try {
            return DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
	}
    // Método para realizar el login
    public static void login(String email, String password) {
    	if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
    		System.out.println("Por favor, completa todos los campos.");
            return;
        }

    	String query = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Usuario: " + rs.getString("email") + " - Nombre: " + rs.getString("name"));
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
		login("forky@mail.com", "12345");
	}
}

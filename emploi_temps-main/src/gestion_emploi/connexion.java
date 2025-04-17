package gestion_emploi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class connexion implements DatabaseConnection {
    private static connexion instance;
    private Connection connection;

    private connexion() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Emploi_temps?useSSL=false",
                "root",
                "3kechA123*"
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }

    public static synchronized connexion getInstance() {
        if (instance == null) {
            instance = new connexion();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}

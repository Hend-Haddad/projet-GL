package gestion_emploi;

import java.sql.*;

public class EnseignantDAO {

    private final DatabaseConnection dbConnection;

    public EnseignantDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void ajouter(String matricule, String nom, String contact) throws SQLException {
        PreparedStatement st = dbConnection.getConnection()
            .prepareStatement("INSERT INTO enseignant VALUES (?, ?, ?)");
        st.setString(1, matricule);
        st.setString(2, nom);
        st.setString(3, contact);
        st.executeUpdate();
    }

    public void modifier(String nom, String contact, String matricule) throws SQLException {
        PreparedStatement st = dbConnection.getConnection()
            .prepareStatement("UPDATE enseignant SET nom=?, contact=? WHERE matricule=?");
        st.setString(1, nom);
        st.setString(2, contact);
        st.setString(3, matricule);
        st.executeUpdate();
    }

    public void supprimer(String matricule) throws SQLException {
        PreparedStatement st = dbConnection.getConnection()
            .prepareStatement("DELETE FROM enseignant WHERE matricule=?");
        st.setString(1, matricule);
        st.executeUpdate();
    }

    public ResultSet getAll() throws SQLException {
        PreparedStatement st = dbConnection.getConnection()
            .prepareStatement("SELECT * FROM enseignant");
        return st.executeQuery();
    }

    public ResultSet getByMatricule(String matricule) throws SQLException {
        PreparedStatement st = dbConnection.getConnection()
            .prepareStatement("SELECT * FROM enseignant WHERE matricule=?");
        st.setString(1, matricule);
        return st.executeQuery();
    }
}

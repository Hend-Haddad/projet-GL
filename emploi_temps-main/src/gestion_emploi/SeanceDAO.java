package gestion_emploi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO {
    private final DatabaseConnection db;

    public SeanceDAO(DatabaseConnection db) {
        this.db = db;
    }

    public boolean existe(Seance seance) throws SQLException {
        String query = "SELECT * FROM seance WHERE classe=? AND matiere=? AND jour=? AND heure=? AND matricule=?";
        PreparedStatement st = db.getConnection().prepareStatement(query);
        st.setString(1, seance.getClasse());
        st.setString(2, seance.getMatiere());
        st.setString(3, seance.getJour());
        st.setString(4, seance.getHeure());
        st.setString(5, seance.getMatriculeEnseignant());
        ResultSet rs = st.executeQuery();
        return rs.next();
    }

    public void insert(Seance seance) throws SQLException {
        String query = "INSERT INTO seance (classe, matiere, jour, heure, matricule) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = db.getConnection().prepareStatement(query);
        st.setString(1, seance.getClasse());
        st.setString(2, seance.getMatiere());
        st.setString(3, seance.getJour());
        st.setString(4, seance.getHeure());
        st.setString(5, seance.getMatriculeEnseignant());
        st.executeUpdate();
    }

    public void delete(String idSeance) throws SQLException {
        String query = "DELETE FROM seance WHERE id_seance=?";
        PreparedStatement st = db.getConnection().prepareStatement(query);
        st.setString(1, idSeance);
        st.executeUpdate();
    }

    public ResultSet findAll() throws SQLException {
        String query = "SELECT * FROM seance";
        return db.getConnection().prepareStatement(query).executeQuery();
    }
    public ResultSet findByClasseAndMatiere(String classe, String matiere) throws SQLException {
        Connection conn =  db.getConnection();
        PreparedStatement pst;
        
        if (matiere == null || matiere.trim().isEmpty()) {
            String sql = "SELECT * FROM seance WHERE classe = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, classe);
        } else {
            String sql = "SELECT * FROM seance WHERE classe = ? AND matiere = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, classe);
            pst.setString(2, matiere);
        }

        return pst.executeQuery();
    }
    public List<String> getMatriculesEnseignants() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT matricule FROM enseignant";
        PreparedStatement pst = db.getConnection().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("matricule"));
        }
        return list;
    }

    public List<String> getIdSeances() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT id_seance FROM seance";
        PreparedStatement pst = db.getConnection().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("id_seance"));
        }
        return list;
    }

    public List<String> getLibelleClasses() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT libelle_classe FROM classe";
        PreparedStatement pst = db.getConnection().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("libelle_classe"));
        }
        return list;
    }

    public List<String> getMatieres() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT(matiere) as matiere FROM seance";
        PreparedStatement pst = db.getConnection().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("matiere"));
        }
        return list;
    }

}

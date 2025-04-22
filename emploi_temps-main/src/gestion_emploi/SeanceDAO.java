package gestion_emploi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO {
    private final DatabaseConnection db;

    public SeanceDAO(DatabaseConnection db) {
        this.db = db;
    }

    public boolean chevauchement(Seance seance) throws SQLException {
        String query = "SELECT * FROM seance WHERE jour = ?";
        PreparedStatement st = db.getConnection().prepareStatement(query);
        st.setString(1, seance.getJour());
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String heureExistante = rs.getString("heure");
            String classeExistante = rs.getString("classe");
            String enseignantExistant = rs.getString("matricule");

            if (chevauche(seance.getHeure(), heureExistante)) {
                // même classe déjà occupée
                if (seance.getClasse().equals(classeExistante)) return true;
                // même enseignant déjà occupé
                if (seance.getMatriculeEnseignant().equals(enseignantExistant)) return true;
            }
        }
        return false;
    }

    private boolean chevauche(String heure1, String heure2) {
        try {
            // On découpe les heures par " & " ou " --> "
            String[] heures1 = heure1.split(" & |-->");  // Accepte " & " ou " --> "
            String[] heures2 = heure2.split(" & |-->");

            if (heures1.length != 2 || heures2.length != 2) {
                throw new IllegalArgumentException("Format horaire invalide pour : " + heure1 + " et " + heure2);
            }

            // Calcul des minutes depuis minuit pour chaque plage horaire
            int debut1 = parseHeure(heures1[0].trim());
            int fin1 = parseHeure(heures1[1].trim());
            int debut2 = parseHeure(heures2[0].trim());
            int fin2 = parseHeure(heures2[1].trim());

            return (debut1 < fin2) && (debut2 < fin1);
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification du chevauchement des horaires");
            e.printStackTrace();
            return false;
        }
    }


    private int parseHeure(String heureStr) {
        try {
            // On s'assure que l'input est du format "HH:MM" sans caractères supplémentaires
            String[] parts = heureStr.trim().split(":");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Format heure invalide : " + heureStr);
            }

            int heures = Integer.parseInt(parts[0].trim());
            int minutes = Integer.parseInt(parts[1].trim());

            // Validation de l'heure
            if (heures < 0 || heures >= 24 || minutes < 0 || minutes >= 60) {
                throw new IllegalArgumentException("Heure invalide : " + heureStr);
            }

            return heures * 60 + minutes;
        } catch (Exception e) {
            System.err.println("Erreur dans parseHeure : " + heureStr);
            e.printStackTrace();
            return -1;
        }
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

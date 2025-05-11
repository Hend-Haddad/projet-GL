package gestion_emploi;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class Enseignant {

    private final EnseignantDAO dao;

    public Enseignant(DatabaseConnection dbConnection) {
        this.dao = new EnseignantDAO(dbConnection);
    }
    
    public int getNbSeances(String matricule) {
        try {
            SeanceDAO seanceDAO = new SeanceDAO(dao.getDbConnection());
            return seanceDAO.getNbSeancesByMatricule(matricule);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public void ajouter_enseignant(String matricule, String nom, String contact) {
        try {
            dao.ajouter(matricule, nom, contact);
            JOptionPane.showMessageDialog(null, "Enseignant ajouté avec succès !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout.");
        }
    }

    public void modifier_enseignant(String nom, String contact, String matricule) {
        try {
            dao.modifier(nom, contact, matricule);
            JOptionPane.showMessageDialog(null, "Données modifiées avec succès !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la modification.");
        }
    }

    public void supprimer_enseignant(String matricule) {
        try {
            dao.supprimer(matricule);
            JOptionPane.showMessageDialog(null, "Enseignant supprimé avec succès !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression.");
        }
    }

    public void remplirFormulaire(JTextField nomField, JTextField contactField, String matricule) {
        try {
            ResultSet rs = dao.getByMatricule(matricule);
            if (rs.next()) {
                nomField.setText(rs.getString("nom"));
                contactField.setText(rs.getString("contact"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement des données.");
        }
    }

    public String get_by_matricule(String matricule) {
        try {
            ResultSet rs = dao.getByMatricule(matricule);
            return rs.next() ? "true" : "false";
        } catch (Exception e) {
            return "false";
        }
    }

    public void update_table(JTable table) {
        try {
            ResultSet rs = dao.getAll();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour du tableau.");
        }
    }

    public void update_list(JComboBox<String> combo) {
        try {
            ResultSet rs = dao.getAll();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("Votre Choix :");
            while (rs.next()) {
                model.addElement(rs.getString("matricule"));
            }
            combo.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de la liste.");
        }
    }
}

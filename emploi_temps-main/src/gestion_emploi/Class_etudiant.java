package gestion_emploi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import gestion_emploi.Strategies.JourSpecifiqueStrategy;
import net.proteanit.sql.DbUtils;

public class Class_etudiant {
    public PreparedStatement st;
    private final DatabaseConnection dbConnection;
    private RechercheStrategy strategy;

    public Class_etudiant(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Class_etudiant(RechercheStrategy strategy, DatabaseConnection dbConnection) {
        this.strategy = strategy;
        this.dbConnection = dbConnection;
    }

    public void chercher_et(JTable tabl_seance_et, String choix_classe, String choix_matiere, String choix_jour) {
        try {
            String query = strategy.rechercher(choix_classe, choix_matiere, choix_jour);
            PreparedStatement st = dbConnection.getConnection().prepareStatement(query);
            
            // Configuration des paramètres
            st.setString(1, choix_classe);
            int paramIndex = 2;
            if (choix_matiere != null && !choix_matiere.equals("null")) {
                st.setString(paramIndex++, choix_matiere);
            }
            if (strategy instanceof JourSpecifiqueStrategy) {
                st.setString(paramIndex, choix_jour);
            }

            ResultSet rs = st.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("jour");
            model.addColumn("matiere");
            model.addColumn("heure");
            model.addColumn("nom enseignant");
            model.addColumn("contact enseignant");

            while (rs.next()) {
                Object[] rowData = {
                    rs.getString("jour"),
                    rs.getString("matiere"),
                    rs.getString("heure"),
                    rs.getString("nom"),
                    rs.getString("contact")
                };
                model.addRow(rowData);
            }
            tabl_seance_et.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loading(JComboBox<String> list_classe2, JComboBox<String> list_matiere) {
        try {
            String sql1 = "SELECT * FROM classe";
            PreparedStatement pst1 = dbConnection.getConnection().prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery();

            list_classe2.removeAllItems();
            list_classe2.addItem("Votre Choix:");
            while (rs1.next()) {
                list_classe2.addItem(rs1.getString("libelle_classe"));
            }

            String sql2 = "SELECT DISTINCT(matiere) as matiere FROM seance";
            PreparedStatement pst2 = dbConnection.getConnection().prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery();

            list_matiere.removeAllItems();
            list_matiere.addItem("Votre Choix:");
            while (rs2.next()) {
                list_matiere.addItem(rs2.getString("matiere"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ajout de la gestion d'erreur
        }
    }
}

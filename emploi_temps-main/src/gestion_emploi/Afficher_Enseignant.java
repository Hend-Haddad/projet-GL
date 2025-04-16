package gestion_emploi;

import javax.swing.JPanel;
import javax.swing.JTable;

public class Afficher_Enseignant extends JPanel {

    //  Instance de la façade
    GestionFacade facade = new GestionFacade();

    public Afficher_Enseignant() {
        initComponents();
        // Exemple : charger la liste au démarrage (si un JTable est utilisé)
        // chargerListeEnseignants(maTable); 
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }

    // Exemple d'utilisation future
    public void chargerListeEnseignants(JTable table) {
        facade.updateTableEnseignants(table); 
    }
}

package gestion_emploi;

import javax.swing.JComboBox;
import javax.swing.JTable;

public class GestionFacade {

    private final Classe classeService;
    private final Enseignant enseignantService;
    private final Seance seanceService;
    private final Class_etudiant etudiantService;


    public GestionFacade() {
    	DatabaseConnection dbConnection = connexion.getInstance();
        this.classeService = new Classe(dbConnection);
        this.enseignantService = new Enseignant(dbConnection);
        this.seanceService = new Seance(dbConnection);
        this.etudiantService = new Class_etudiant(dbConnection);

    }

    // ============ CLASSE =============
    public Classe getClasseService() {
        return this.classeService;
    }
    public void ajouterClasse(String libelle, String description) {
        classeService.ajouter_classe(libelle, description);
    }

    public void supprimerClasse(String choix) {
        classeService.supprimer_classe(choix);
    }

    public void modifierClasse(String libelle, String description, String choix) {
        classeService.modifier_classe(libelle, description, choix);
    }

    public void chargerListeClasse(JComboBox<String> list) {
        classeService.update_list(list);
    }

    public void updateTableClasse(JTable table) {
        classeService.update_table(table);
    }

    // ============ ENSEIGNANT ==========
    public void ajouterEnseignant(String matricule, String nom, String contact) {
        enseignantService.ajouter_enseignant(matricule, nom, contact);
    }

    public void supprimerEnseignant(String matricule) {
        enseignantService.supprimer_enseignant(matricule);
    }

    public void modifierEnseignant(String nom, String contact, String matricule) {
        enseignantService.modifier_enseignant(nom, contact, matricule);
    }

    public void chargerListeEnseignant(JComboBox<String> list) {
        enseignantService.update_list(list);
    }

    public void updateTableEnseignant(JTable table) {
        enseignantService.update_table(table);
    }

    // ============ SEANCE ==========
    public void ajouterSeance(String classe, String matiere, String jour, String heure, String enseignant) {
        seanceService.ajouter_seance(classe, matiere, jour, heure, enseignant);
    }

    public void supprimerSeance(String idSeance) {
        seanceService.delete_seance(idSeance);
    }

    public void chercherSeances(JTable table, String classe, String matiere) {
        seanceService.chercher(table, classe, matiere);
    }

    public void updateTableSeance(JTable table) {
        seanceService.update_table(table);
    }

    public void chargerListeMatieres(JComboBox<String> list) {
        seanceService.update_list_matiere(list);
    }

    // ============ ETUDIANT ==========
    public void chercherSeancesEtudiant(JTable table, String classe, String matiere, String jour) {
        etudiantService.chercher_et(table, classe, matiere, jour);
    }

    public void chargerListesEtudiant(JComboBox<String> listeClasse, JComboBox<String> listeMatiere) {
        etudiantService.loading(listeClasse, listeMatiere);
    }
    public void updateTableEnseignants(JTable table) {
        Enseignant enseignant = new Enseignant(connexion.getInstance());
        enseignant.update_table(table);
    }
    public void getClasseListAndTable(JTable table, JComboBox<String> comboBox) {
        classeService.get(table, comboBox);
    }
    public boolean verifierMatriculeExiste(String matricule) {
        return new Enseignant(connexion.getInstance()).get_by_matricule(matricule).equals("true");
    }

    public void remplirFormulaireEnseignant(String matricule, javax.swing.JTextField nom, javax.swing.JTextField contact) {
        new Enseignant(connexion.getInstance()).change_list_enseignant(nom, contact, matricule);
    }
    public void loadingSeance(JComboBox<String> enseignants, JComboBox<String> seances, JComboBox<String> classesAjout, JComboBox<String> classesRecherche, JComboBox<String> matieres, JTable table) {
        Seance seance = new Seance(connexion.getInstance());
        seance.loading(enseignants, seances, classesAjout, classesRecherche, matieres, table);
    }

    
    public void rechercherSeance(JTable table, String classe, String matiere) {
        new Seance(connexion.getInstance()).chercher(table, classe, matiere);
    }
    public void updateListeSeance(JComboBox<String> comboBox) {
    	seanceService.update_list_seance(comboBox);
    }

    public void updateListeMatiere(JComboBox<String> comboBox) {
    	 seanceService.update_list_matiere(comboBox);
    }
    public boolean verifierLibelleClasseExiste(String libelle) {
        return new Classe(connexion.getInstance()).get_by_libelle(libelle).equals("true");
    }
    public void remplirFormulaireClasse(String libelleClasse, javax.swing.JTextField champLibelle, javax.swing.JTextArea champDescription) {
        new Classe(connexion.getInstance()).change_list_classe(champLibelle, champDescription, libelleClasse);
    }



   
}

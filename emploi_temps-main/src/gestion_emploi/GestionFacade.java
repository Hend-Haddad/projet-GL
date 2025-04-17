package gestion_emploi;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JTable;

import gestion_emploi.Strategies.TousLesJoursStrategy;

import java.util.List;

public class GestionFacade {

    private final Classe classeService;
    private final Enseignant enseignantService;
    private final SeanceService seanceService;
    private final Class_etudiant etudiantService;


    public GestionFacade() {
    	DatabaseConnection dbConnection = connexion.getInstance();
        this.classeService = new Classe(dbConnection);
        this.enseignantService = new Enseignant(dbConnection);
        this.seanceService = new SeanceService(new SeanceDAO(dbConnection));
       
        this.etudiantService = new Class_etudiant(new TousLesJoursStrategy(), dbConnection);
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
    public boolean ajouterSeance(Seance seance) {
        return seanceService.ajouterSeance(seance);
    }

    public boolean supprimerSeance(String id) {
        return seanceService.supprimerSeance(id);
    }

    public ResultSet getToutesLesSeances() {
        return seanceService.chargerToutesLesSeances();
    }

    public ResultSet chercher(String classe, String matiere) {
        return seanceService.chercher(classe, matiere);
    }
    public List<String> getMatriculesEnseignants() {
        return seanceService.getMatriculesEnseignants();
    }

    public List<String> getIdSeances() {
        return seanceService.getIdSeances();
    }

    public List<String> getLibelleClasses() {
        return seanceService.getLibelleClasses();
    }

    public List<String> getMatieres() {
        return seanceService.getMatieres();
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

  
    public boolean verifierLibelleClasseExiste(String libelle) {
        return new Classe(connexion.getInstance()).get_by_libelle(libelle).equals("true");
    }
    public void remplirFormulaireClasse(String libelleClasse, javax.swing.JTextField champLibelle, javax.swing.JTextArea champDescription) {
        new Classe(connexion.getInstance()).change_list_classe(champLibelle, champDescription, libelleClasse);
    }
    
    public Seance createSeance(String classe, String matiere, String jour, 
            String heureDebut, String heureFin, String matricule) 
            throws IllegalArgumentException {
	// Validation des entrées
	if ("Votre Choix:".equals(classe)) {
	throw new IllegalArgumentException("Sélectionnez une classe valide");
	}
	if ("Votre Choix:".equals(matricule)) {
	throw new IllegalArgumentException("Sélectionnez un enseignant valide");
	}
	if (matiere == null || matiere.trim().isEmpty()) {
	throw new IllegalArgumentException("La matière est obligatoire");
	}
	// Validation supplémentaire des heures
	if ("Votre Choix:".equals(heureDebut) || "Votre Choix:".equals(heureFin)) {
	throw new IllegalArgumentException("Sélectionnez des heures valides");
	}
	
	return new Seance(classe, matiere, jour, heureDebut + " & " + heureFin, matricule);
	}
	
}

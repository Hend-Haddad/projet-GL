package gestion_emploi;

public class Seance {
    private int id;
    private String classe;
    private String matiere;
    private String jour;
    private String heure;
    private String matriculeEnseignant;

    // Constructeur vide (obligatoire pour certaines bibliothèques, ou lors de la création sans init)
    public Seance() {}

    // Constructeur sans id (utile lors de la création avant insertion en base)
    public Seance(String classe, String matiere, String jour, String heure, String matriculeEnseignant) {
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.matriculeEnseignant = matriculeEnseignant;
    }

    // Constructeur complet (avec id, utile pour le chargement depuis la base)
    public Seance(int id, String classe, String matiere, String jour, String heure, String matriculeEnseignant) {
        this.id = id;
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.matriculeEnseignant = matriculeEnseignant;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getClasse() {
        return classe;
    }

    public String getMatiere() {
        return matiere;
    }

    public String getJour() {
        return jour;
    }

    public String getHeure() {
        return heure;
    }

    public String getMatriculeEnseignant() {
        return matriculeEnseignant;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setMatriculeEnseignant(String matriculeEnseignant) {
        this.matriculeEnseignant = matriculeEnseignant;
    }

    // Pour affichage debug
    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", classe='" + classe + '\'' +
                ", matiere='" + matiere + '\'' +
                ", jour='" + jour + '\'' +
                ", heure='" + heure + '\'' +
                ", matriculeEnseignant='" + matriculeEnseignant + '\'' +
                '}';
    }
}

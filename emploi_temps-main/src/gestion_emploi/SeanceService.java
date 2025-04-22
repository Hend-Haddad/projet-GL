package gestion_emploi;

import java.util.List;


import java.sql.ResultSet;

public class SeanceService {
    private final SeanceDAO seanceDAO;

    public SeanceService(SeanceDAO seanceDAO) {
        this.seanceDAO = seanceDAO;
    }

    public boolean ajouterSeance(Seance seance) {
        try {
            if (seanceDAO.chevauchement(seance)) {
                System.out.println("Conflit détecté : chevauchement d'horaire.");
                return false;
            }
            seanceDAO.insert(seance);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerSeance(String id) {
        try {
            seanceDAO.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ResultSet chargerToutesLesSeances() {
        try {
            return seanceDAO.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public ResultSet chercher(String classe, String matiere) {
        try {
            return seanceDAO.findByClasseAndMatiere(classe, matiere);
        } catch (Exception e) {
            return null;
        }
    }
    public List<String> getMatriculesEnseignants() {
        try {
            return seanceDAO.getMatriculesEnseignants();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<String> getIdSeances() {
        try {
            return seanceDAO.getIdSeances();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getLibelleClasses() {
        try {
            return seanceDAO.getLibelleClasses();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getMatieres() {
        try {
            return seanceDAO.getMatieres();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
